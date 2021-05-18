

### All Types

| Name | Summary |
|---|---|
| [com.lovoo.android.pickapp.BuildConfig](../com.lovoo.android.pickapp/-build-config/index.md) |  |
| [com.lovoo.android.pickapp.BuildConfig](../com.lovoo.android.pickapp/-build-config/index.md) |  |
| [com.lovoo.android.pickapp.factory.GlideEngine](../com.lovoo.android.pickapp.factory/-glide-engine/index.md) | Implementation of [ImageEngine](#) to use [Glide](#) within PickPic. |
| [com.lovoo.android.pickapp.factory.PickDependencies](../com.lovoo.android.pickapp.factory/-pick-dependencies/index.md) | Define the optional Feature configuration from PickPic based on the available dependencies. |
| [com.lovoo.android.pickapp.model.Picker](../com.lovoo.android.pickapp.model/-picker/index.md) | The data source for PickPicActivity. It holds a map of picked Uris with the depending [Gallery](#). [State](../com.lovoo.android.pickapp.model/-picker/-state/index.md) changes are emitted via observable and should be disposed. |
| [com.lovoo.android.pickapp.view.PickPicActivity](../com.lovoo.android.pickapp.view/-pick-pic-activity/index.md) | Ready to use PickPic implementation. Configurable with [PickPicConfig](../com.lovoo.android.pickapp.model/-pick-pic-config/index.md). |
| [com.lovoo.android.pickapp.adapter.PickPicAdapter](../com.lovoo.android.pickapp.adapter/-pick-pic-adapter/index.md) | [FragmentStatePagerAdapter](#) that contains [GalleryFragment](#) and optional [FbGalleryFragment](#). |
| [com.lovoo.android.pickapp.model.PickPicConfig](../com.lovoo.android.pickapp.model/-pick-pic-config/index.md) | The configuration for [com.lovoo.android.pickapp.view.PickPicActivity](../com.lovoo.android.pickapp.view/-pick-pic-activity/index.md) behaviour. |
| [com.lovoo.android.pickapp.model.PickType](../com.lovoo.android.pickapp.model/-pick-type/index.md) | Simple enum class that contains all the types used by [com.lovoo.android.pickapp.adapter.PickPicAdapter](../com.lovoo.android.pickapp.adapter/-pick-pic-adapter/index.md) |
| [com.lovoo.android.pickapp.view.Preview](../com.lovoo.android.pickapp.view/-preview/index.md) | UI extension to handle PickPicActivities Preview ViewPager. It observes [Picker.State](../com.lovoo.android.pickapp.model/-picker/-state/index.md) changes and show, update or hide the Preview. You have to call destroy if the UI is removed. |
| [com.lovoo.android.pickapp.view.PreviewFragment](../com.lovoo.android.pickapp.view/-preview-fragment/index.md) | The [Fragment](#) that is used for the [Preview](../com.lovoo.android.pickapp.view/-preview/index.md) ViewPager. |
| [com.lovoo.android.pickapp.adapter.SelectionAdapter](../com.lovoo.android.pickapp.adapter/-selection-adapter/index.md) | [RecyclerView.Adapter](#) for the selection bar. |
| [com.lovoo.android.pickapp.view.Selectionbar](../com.lovoo.android.pickapp.view/-selectionbar/index.md) | UI extension to handle the PickPicActivities Selectionbar and RecyclerView. It observes changes emitted by [Picker](../com.lovoo.android.pickapp.model/-picker/index.md) and add, removes or select the Thumbnails. You have to call destroy if the UI is removed. |
