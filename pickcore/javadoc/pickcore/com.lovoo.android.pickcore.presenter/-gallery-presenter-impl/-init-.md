[pickcore](../../index.md) / [com.lovoo.android.pickcore.presenter](../index.md) / [GalleryPresenterImpl](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`GalleryPresenterImpl(view: `[`GalleryView`](../../com.lovoo.android.pickcore.contract/-gallery-view/index.md)`)`

Implementation of [GalleryPresenter](../../com.lovoo.android.pickcore.contract/-gallery-presenter/index.md) for Fragments to load the device galleries.
Don't forget to forward these events:
onAttach()
onCreate()
onDestroy()

Start loading with load() if you have the permission to access the Filesystem.

### Parameters

`view` - the contract to the UI Layer