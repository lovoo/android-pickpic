[pickcore](../../index.md) / [com.lovoo.android.pickcore](../index.md) / [PickPicProvider](index.md) / [cameraEngine](./camera-engine.md)

# cameraEngine

`var cameraEngine: `[`CameraEngine`](../../com.lovoo.android.pickcore.contract/-camera-engine/index.md) [(source)](https://github.com/lovoo/android-pickpic/blob/master/pickcore/src/main/kotlin/com/lovoo/android/pickcore/PickPicProvider.kt#L61)

[CameraEngine](../../com.lovoo.android.pickcore.contract/-camera-engine/index.md) that defines if the camera will be available for a certain [Gallery](#).
Please consider to keep the user flow consistent. Its a bad experience when the user
navigate to a custom folder, used the camera button there and the taken picture is added
to the DCIM or another folder. It would be better to show the camera button only in
Galleries were you can save the picture, so that it appears afterwards.

