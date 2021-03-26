# Internal deploy manual

## Description
PickPic is an external library that should be tested before it final released to the public.

Here some best practises
* test your changes with the example app
* make your changes accessible in LOVOO see `SNAPSHOT deploy`
* release your changes see `RELEASE deploy`

## SNAPSHOT deploy
### With Gradle
* Changes in PickPic `<root>/build.gradle`
    * change version so that it ends with SNAPSHOT
```
    ext.version_name = version == null ? "1.3.0-SNAPSHOT" : version
    ext.version_code = code == null ? 30 : code.toInteger()
```
    * sync project and run `gradle artifactoryPublish`
* Update dependencies in LOVOO `LovooDepVersion.kt`
* Test the LOVOO PickPic integration

### With Bitrise
* open android-pickpic in bitrise
* run new task
    * switch tab to advanced
    * select the branch to deploy example: `develop`
    * add variable `VERSION` with value `1.3.0-SNAPSHOT`
* Update dependencies in LOVOO `LovooDepVersion.kt`
* Test the LOVOO PickPic integration

## RELEASE deploy
### With Gradle
* Changes in PickPic `<root>/build.gradle`
    * change version
```
    ext.version_name = version == null ? "1.3.0" : version
    ext.version_code = code == null ? 30 : code.toInteger()
```
    * sync project and run `gradle artifactoryPublish`

### With Bitrise
* open android-pickpic in bitrise
* run new task
    * switch tab to advanced
    * select the branch to deploy example: `develop`
    * add variable `VERSION` with value `1.3.0`