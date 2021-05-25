

### All Types

| Name | Summary |
|---|---|
| [com.lovoo.android.pickcore.BuildConfig](../com.lovoo.android.pickcore/-build-config/index.md) |  |
| [com.lovoo.android.pickcore.BuildConfig](../com.lovoo.android.pickcore/-build-config/index.md) |  |
| [com.lovoo.android.pickcore.contract.CameraDestination](../com.lovoo.android.pickcore.contract/-camera-destination/index.md) | Model class that represents a destination for captured images. |
| [com.lovoo.android.pickcore.contract.CameraEngine](../com.lovoo.android.pickcore.contract/-camera-engine/index.md) | Engine interface responsible for setting up the PickPic camera. |
| [com.lovoo.android.pickcore.loader.CameraLoader](../com.lovoo.android.pickcore.loader/-camera-loader/index.md) | Object class that helps to start and finish a capture [Intent](#) on Android. |
| [com.lovoo.android.pickcore.contract.CaptureCallback](../com.lovoo.android.pickcore.contract/-capture-callback/index.md) | The interface that the calling UI should implement to receive the captured file. |
| [com.lovoo.android.pickcore.loader.Collector](../com.lovoo.android.pickcore.loader/-collector/index.md) | An [LoaderManager.LoaderCallbacks](#) implementation that load a cursor and set the [MutableLiveData](#) cursor. Others can observe this. |
| [com.lovoo.android.pickcore.Constants](../com.lovoo.android.pickcore/-constants/index.md) | Collection of non changing values. |
| [com.lovoo.android.pickcore.engine.DefaultImageEngine](../com.lovoo.android.pickcore.engine/-default-image-engine/index.md) | Default implementation of [ImageEngine](../com.lovoo.android.pickcore.contract/-image-engine/index.md) that loads images without any strategy or cache. Be aware that this should never be used in production, because of OutOfMemory-Exceptions. |
| [com.lovoo.android.pickcore.engine.DisabledCameraEngine](../com.lovoo.android.pickcore.engine/-disabled-camera-engine/index.md) | Default implementation of [CameraEngine](../com.lovoo.android.pickcore.contract/-camera-engine/index.md) that disables PickPic camera support. |
| [java.io.File](../com.lovoo.android.pickcore.destination/java.io.-file/index.md) (extensions in package com.lovoo.android.pickcore.destination) |  |
| [com.lovoo.android.pickcore.model.Gallery](../com.lovoo.android.pickcore.model/-gallery/index.md) | Model class that represents an PickPic Gallery Folder. |
| [com.lovoo.android.pickcore.model.GalleryLib](../com.lovoo.android.pickcore.model/-gallery-lib/index.md) | Model class that represents an Android GalleryLib Folder loaded from the Database. |
| [com.lovoo.android.pickcore.loader.GalleryLoader](../com.lovoo.android.pickcore.loader/-gallery-loader/index.md) | A [CursorLoader](#) implementation that fetch album information from external [MediaStore.Files](#) database. The current [Cursor](#) should be converted to [GalleryLib](../com.lovoo.android.pickcore.model/-gallery-lib/index.md) model. |
| [com.lovoo.android.pickcore.contract.GalleryPresenter](../com.lovoo.android.pickcore.contract/-gallery-presenter/index.md) | Contract to implement for Presenter. |
| [com.lovoo.android.pickcore.presenter.GalleryPresenterImpl](../com.lovoo.android.pickcore.presenter/-gallery-presenter-impl/index.md) | Implementation of [GalleryPresenter](../com.lovoo.android.pickcore.contract/-gallery-presenter/index.md) for Fragments to load the device galleries. Don't forget to forward these events: onAttach() onCreate() onDestroy() |
| [com.lovoo.android.pickcore.contract.GalleryView](../com.lovoo.android.pickcore.contract/-gallery-view/index.md) | Contract to implement for UI. |
| [com.lovoo.android.pickcore.contract.ImageEngine](../com.lovoo.android.pickcore.contract/-image-engine/index.md) | Engine interface responsible for setting up the PickPic image loading. |
| [com.lovoo.android.pickcore.permission.Permission](../com.lovoo.android.pickcore.permission/-permission/index.md) | A small solution for Android Permission handle. |
| [com.lovoo.android.pickcore.PickPicProvider](../com.lovoo.android.pickcore/-pick-pic-provider/index.md) | Object that allows at runtime to change core feature from PickPic. If you use [com.lovoo.android.pickapp.view.PickPicActivity](#) you don`t need to provide anything on your own. |
| [com.lovoo.android.pickcore.model.Picture](../com.lovoo.android.pickcore.model/-picture/index.md) | Model class that represents an PickPic Picture. |
| [com.lovoo.android.pickcore.model.PictureLib](../com.lovoo.android.pickcore.model/-picture-lib/index.md) | Model class that represents an Android PictureLib loaded from the Database. |
| [com.lovoo.android.pickcore.loader.PictureLoader](../com.lovoo.android.pickcore.loader/-picture-loader/index.md) | A [CursorLoader](#) implementation that fetch picture information from external [MediaStore.Files](#) database for the given [GalleryLib](../com.lovoo.android.pickcore.model/-gallery-lib/index.md). The current [Cursor](#) should be converted to [PictureLib](../com.lovoo.android.pickcore.model/-picture-lib/index.md) model. |
| [com.lovoo.android.pickcore.contract.PicturePresenter](../com.lovoo.android.pickcore.contract/-picture-presenter/index.md) | Contract to implement for Presenter. |
| [com.lovoo.android.pickcore.presenter.PicturePresenterImpl](../com.lovoo.android.pickcore.presenter/-picture-presenter-impl/index.md) | Implementation of [PicturePresenter](../com.lovoo.android.pickcore.contract/-picture-presenter/index.md) for Fragments to load the pictures from a [Gallery](../com.lovoo.android.pickcore.model/-gallery/index.md). Don't forget to forward onDestroy(). |
| [com.lovoo.android.pickcore.contract.PictureView](../com.lovoo.android.pickcore.contract/-picture-view/index.md) | Contract to implement for UI. |
| [com.lovoo.android.pickcore.destination.PrivateDirectory](../com.lovoo.android.pickcore.destination/-private-directory/index.md) | Implementation of [CameraDestination](../com.lovoo.android.pickcore.contract/-camera-destination/index.md) for pictures that should be stored in private app directory. Keep in mind that other apps does not have access to this directory / file unless you provide a own content provider. |
| [com.lovoo.android.pickcore.destination.PublicDirectory](../com.lovoo.android.pickcore.destination/-public-directory/index.md) | Implementation of [CameraDestination](../com.lovoo.android.pickcore.contract/-camera-destination/index.md) for pictures that should be stored in public directory. |
| [com.lovoo.android.pickcore.adapter.RecyclerViewCursorAdapter](../com.lovoo.android.pickcore.adapter/-recycler-view-cursor-adapter/index.md) | An [RecyclerView.Adapter](#) implementation that handle [Cursor](#) as data source. |
| [com.lovoo.android.pickcore.contract.SelectionHolder](../com.lovoo.android.pickcore.contract/-selection-holder/index.md) | Contract for the selection implementation. |
| [com.lovoo.android.pickcore.contract.ToggleCallback](../com.lovoo.android.pickcore.contract/-toggle-callback/index.md) |  |
