[pickapp](../index.md) / [com.lovoo.android.pickapp.view](./index.md)

## Package com.lovoo.android.pickapp.view

### Types

| Name | Summary |
|---|---|
| [PickPicActivity](-pick-pic-activity/index.md) | `class PickPicActivity : AppCompatActivity, SelectionHolder, CameraEngine, CaptureCallback`<br>Ready to use PickPic implementation. Configurable with [PickPicConfig](../com.lovoo.android.pickapp.model/-pick-pic-config/index.md). |
| [Preview](-preview/index.md) | `class Preview`<br>UI extension to handle PickPicActivities Preview ViewPager. It observes [Picker.SelectionState](../com.lovoo.android.pickapp.model/-picker/-selection-state/index.md) changes and show, update or hide the Preview. You have to call destroy if the UI is removed. |
| [PreviewFragment](-preview-fragment/index.md) | `class PreviewFragment : Fragment`<br>The [Fragment](#) that is used for the [Preview](-preview/index.md) ViewPager. |
| [ReverseLayoutManager](-reverse-layout-manager/index.md) | `open class ReverseLayoutManager : LinearLayoutManager`<br>Custom LinearLayoutManager that triggers scroll to position 0 on view added when it is enabled. |
| [Selectionbar](-selectionbar/index.md) | `class Selectionbar`<br>UI extension to handle the PickPicActivities Selectionbar and RecyclerView. It observes changes emitted by [Picker](../com.lovoo.android.pickapp.model/-picker/index.md) and add, removes or select the Thumbnails. You have to call destroy if the UI is removed. |
