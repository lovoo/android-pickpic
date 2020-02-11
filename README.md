# android-pickpic (Pick a Picture)
[ ![Download](https://api.bintray.com/packages/lovoo/maven/PickPic/images/download.svg) ](https://bintray.com/lovoo/maven/PickPic/_latestVersion)

## Description
Ready to use library that allows people to select pictures from their device and Facebook account. It's designed to unify multiple picture sources in one flow.
PickPic can be configured for different use cases:
* select one profile picture
* select multiple pictures as attachment
* select a minimum of 2 pictures to finish an onboarding

* ...

It is also possible to configure title, subtitle and the call to action (text or icon).
If you want to communicate a rule set for your guidelines you can pass an FAQ url.

PickPic will handle all permissions for storage, camera and Facebook.

What PickPic will not handle are animated pictures and videos.

## Screenshot

 <img src="/images/screenshot.png" width="50%" height="50%" alt="Screenshot">
 
 ## Import 
 PickPic is hosted on Jcenter. Add this to your project's `build.gradle`.
 
 ```groovy
 repositories {
     jcenter()
 }
 ```
 
 Then add this to your app's `build.gradle` and replace `<tag>` with the version you want to use:
 
 ```groovy
 pickpic_version = "<tag>"
 
 dependencies {
     implementation "com.lovoo.android:pickapp:$pickpic_version"
 }
 ```
 
 ```groovy
 pickpic_version = "<tag>"
 
 dependencies {
     implementation ("com.lovoo.android:pickapp:$pickpic_version") {
         exclude group: 'com.lovoo.android', module: 'pickfacebook'
         exclude group: 'com.lovoo.android', module: 'pickcam'
     }
 }
 ```
 
 Depending on the function you need you could also leave out some modules like `com.lovoo.android:pickfacebook` (see chapter `Modules`).

## Usage
Start the `PickPicActivity` for result and pass a `PickPicConfig` that fits your needs.

```kotlin
    private fun startPicker() {
        val config = PickPicConfig(
            style = R.style.AppTheme_NoActionbar,           // your app theme
            minCount = 2,                                   // button will be active when 2 pictures are selected
            maxCount = 10,                                  // limit selection count to 10
            sendIcon = R.drawable.ic_upload,                // provide icon for the button
            header = "Please choose 2 or more pictures",    // provide text for the title
            title = "My Activity Title",
            faqUrl = "https://www.myDomain.com/myPictureGuidelines"
        )

        Intent(this, PickPicActivity::class.java).let {
            PickPicActivity.applyConfig(it, config)
            startActivityForResult(it, 101)
        }
    }
```

Wait and handle the Activity result to fetch the selected pictures

```kotlin
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 101) {
            val result = PickPicActivity.getResult(data)
            
            // handle the selected Uri's for your use case
            
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
```

You are free to call the provided PickPickActivity or do your own implementation and use the Fragments and Adapter that are provided by the modules.

## Facebook implementation
You have to set up your app as described in [Facebook docs](https://developers.facebook.com/docs/android).
Overwrite these strings with your own Facebook app id and protocol scheme:
```xml
    <string name="facebook_app_id">your_app_id</string>
    <string name="fb_login_protocol_scheme">your_protocol_scheme</string>
```
Do not forget to apply your APK for Facebook review to unlock picture permission.

## Customization
Design can be changed easily by overwriting the style attributes and resources. It's also possible to overwrite whole layouts, but keep in mind that some view ids and classes may be mandatory.
So please don't change an AndroidX RecyclerView to a SupportRecyclerView or worse GridLayout :)
When changing the style attributes, please keep in mind that you need to copy the whole theme

Example: [styles.xml](pickapp/src/main/res/values/styles.xml)

Or extend your app theme from PickPic `<style name="ExampleAppTheme" parent="PickPicAppTheme">` 

PickPic provides only English as translation, so that you can decide which languages you offer on top.
To localize PickPic in your languages just provide translations for all PickPic strings.

Example: [strings.xml](app/src/main/res/values-de/strings.xml)

## Modules
PickPick is divided in different modules:

| Module | Description | Dependency Path | External Dependencies | Documentation |
| --- | --- | --- | --- | --- |
| _PickCore_ | The base of PickPick | `com.lovoo.android:pickcore:$pickpic_version` | androidx.exifinterface:exifinterface<br>androidx.appcompat:appcompat<br>androidx.recyclerview:recyclerview<br>androidx.lifecycle:lifecycle-livedata | [link](pickcore/javadoc/pickcore/index.md) |
| _PickUI_ | The basic UI implementation | `com.lovoo.android:pickui:$pickpic_version` | _pickcore_<br>androidx.vectordrawable:vectordrawable<br>androidx.constraintlayout:constraintlayout | [link](pickui/javadoc/pickui/index.md) |
| _PickCam_ | The camera capture implementation | `com.lovoo.android:pickcam:$pickpic_version` | _pickcore_<br>androidx.appcompat:appcompat<br>androidx.constraintlayout:constraintlayout<br>android.arch.work:work-runtime<br>android.arch.work:work-runtime-ktx<br>android.arch.work:work-rxjava2 | [link](pickcam/javadoc/pickcam/index.md) |
| _PickFacebook_ | Facebook module for PickPic | `com.lovoo.android:pickfacebook:$pickpic_version` | _pickui_<br>com.google.code.gson:gson<br>com.facebook.android:facebook-android-sdk | [link](pickfacebook/javadoc/pickfacebook/index.md) |
| _PickApp_ | A ready to use PickPicActivity that can be configured | `com.lovoo.android:pickapp:$pickpic_version` | _pickfacebook_ (alternative _pickui_)<br>_pickcam_ (optional)<br>com.google.android.material:material<br>io.reactivex.rxjava2:rxjava<br>io.reactivex.rxjava2:rxandroid<br>com.github.bumptech.glide:compiler<br>androidx.lifecycle:lifecycle-compiler | [link](pickapp/javadoc/pickapp/index.md) |

For _PickApp_: The Facebook Tab will only appear if _PickFacebook_ is packed within your dependencies.
For _PickApp_: The Camera Capture will only appear if _PickCam_ is packed within your dependencies.

Permissions, such as storage and camera, are handled by PickPic. Camera permission will be only requested when using the camera functionality and when your manifest declares this permission.

# License

```
Copyright 2018 LOVOO GmbH

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.`
```
