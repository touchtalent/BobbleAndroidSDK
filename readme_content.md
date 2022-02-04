 #  Bobble Content SDK

Bobble Content SDK provides an all in one solution for your content requirements:
1. Creating Bobble Heads (Cartoonised Avatar heads from user's selfie)
2. Stickers (with Bobble Head and OTF* support)
3. Animated Stickers (with Bobble Head and OTF support)
4. Regional GIFs (with OTF support).

The SDK hosts a massive repository with more than 100K+ content.

> *OTF - OTF refers to <b>On The Fly</b>, which is a capability of adding on-demand text layer within a content to give it a personalised touch.
## <a name="implementation_steps"></a>Implementation Steps

- Add and initialise BobbleSDK Core in your project. Refer [here](Readme.md#setup) for steps.

- Add following dependencies in your application module’s build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:content'
implementation 'com.touchtalent.bobblesdk:avatar'
```

>Both the SDKs can be used independently as well as together. Content SDK would auto-detect Avatar SDK (if added) to enable personalised sticker creation using Avatar.

- (Optional) Compatibility with Kotlin Coroutines and RxJava2 is also supported to avoid callback based APIs. Include following libraries to add support for them
```groovy
implementation 'com.touchtalent.bobblesdk:content-ktx'
implementation 'com.touchtalent.bobblesdk:avatar-ktx'

implementation 'com.touchtalent.bobblesdk:content-rx'
implementation 'com.touchtalent.bobblesdk:avatar-rx'
```
Sync your Gradle project to ensure that the dependency is downloaded by the build system.

## Required Permissions : 
The SDK requires following permission if you opt for [Full fledged head creation activity](#full_fledged) (optional) of Bobble Head creation.

```xml
<uses-permission android:name="android.permission.CAMERA" />
```
##  Bobble Content APIs:

Bobble Content can be integrated via 2 methods depending on requirements.

### 1. BobbleContentView

```BobbleContentView``` imports a complete view showing different formats of contents. The UI is customizable via themes and interaction with content can be captured via listeners. The view handles multiple functionalities :

- Display content and capture user interactions with them.
- Search option to query based content search.
- Host content stores for browsing content packs.
- Bobble Head creation and management. 

#### i. Define a custom theme to customize the look of the UI

```xml
<style name="CustomTheme" parent="BobbleContentViewTheme" >
    <item name="ascentColorPrimary">@color/ascentColor</item>
    <item name="ascentColorLight">@color/ascentColor</item>
    <item name="topBarColor">@color/topBarColor</item>        
    <item name="bottomBarColor">@color/bottomBarColor</item>
    <item name="dividerColor">@color/dividerColor</item>        
    <item name="cardColor">@color/cardColor</item>        
    <item name="stickerIcon">@drawable/ic_sticker_icon</item>        
    <item name="animatedStickerIcon">@drawable/ic_animated_sticker_icon</item>        
    <item name="regionalGIFIcon">@drawable/ic_regional_gif_icon</item>        
    <item name="searchIcon">@drawable/ic_search_icon</item>        
</style>
```
#### ii. Add custom view inside a XML layout of your Activity/Fragment
```xml
<com.touchtalent.bobblesdk.content.BobbleContenView
    style = "@style/CustomTheme"
    android:id="@+id/contentView"
    android:layout_width="match_parent"
    android:layout_height="500dp"/>
```
#### iii. APIs
- ```load(config)``` needs to be called via code passing essential params for the view to init. 
- Add listeners to capture user's interaction with the content. 
- The content adapts to locale of the ```Context``` used.
- ``supportedMimeTypes`` is used to determine which type of mime type is supported by caller. Refer below for supported MIME types

Content format | Supported MIME type
-- | --
STICKERS | `image/png`, `image/webp`, `image/webp.wasticker`, `image/jpeg`, `image/jpg`
ANIMATED_STICKERS | `image/webp`, `image/webp.wasticker`, `image/gif`  
REGIONAL_GIFS | `image/gif`  

```kotlin
class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contentView = findViewById<BobbleContentView>(R.id.contentView)
        contentView.load(BobbleContentView.Config().also {
            it.supportedMimeTypes = listOf("image/png", "image/webp", "image/gif") // MIME type for generated content 
            it.otf = "Hello!" // Set OTF for the content
        })

        contentView.setContentListener{ uri ->
                //Called when user taps on any of the content.
                //The uri points to the local path of the content that user tapped on.
        }
    }
}
```

### 2. API based
#### i. Bobble Head :
- Creation - Bobble Heads can be created in 2 ways - *Full fledged head creation activity (includes UI)* and *Raw APIs which convert given selfie into avatar*

<a name="full_fledged"></a>Full fledged head creation -
```kotlin 
class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        BobbleHeadSDK.getHeadCreator()
            .newBuilder(this)
            .setHeadType(2101) // Bobble type denotes the sytle of the avatar to be created. 2101 => S2B (Flat) , 2301 => S2D (Cartoonistic), 2401 => S3 (3D head)
            .setInitialSource(InitialSource.CAMERA) // Initial mode for head creation - Camera or Gallery
            .setListener({ head:BobbleHead ->
                val headId = head.id //Unique id (local) of head.
                val width = head.width // Width of Bobble head.
                val height = head.height // Height of Bobble head.
                val headUri = head.headUri // Returns URI of PNG image pointing to the Bobble Head.
                val rawUri = head.rawImageURI // Get raw PNG image used for head creation
            }, {err ->
                // FaceNotFoundException
                // MultipleFaceFoundException
                // InvalidUriException
                // UserAbortedException
                // NoInternetException
            })
            .startActivity()
    }

}
```
Raw APIs -
```kotlin
BobbleHeadSDK.getHeadCreator()
    .newBuilder(this)
    .setImageUri(imageUri)
    .setGender("male") //Optional - "male" or "female"
    .setAge(25) //Optional
    .setListener({ head ->
        val headId = head.id //Unique id of the head.
        val width = head.width // Width of Bobble head.
        val height = head.height // Height of Bobble head.
        val headUri = head.headUri // Returns URI of PNG image pointing to the Bobble Head.
        val rawUri = head.rawImageURI // Get raw PNG image used for head creation
    }, {err ->
        // FaceNotFoundException
        // MultipleFaceFoundException
        // UserAbortedException
        // NoInternetException
    })
    .createBobbleHead()
```
-   Manage created heads

```kotlin
//Fetch list of all heads
val headManager = BobbleHeadSDK.getHeadManager()

val config = Config().withHeadType(2101)
    .withGender("female")

// Fetch all BobbleHeads corresponding to given config
headManager.getBobbleHeads(config){ bobbleHeads:List<BobbleHead> ->
    // Get list of BobbleHeads corresponding to config    
}

//Fetch list of currently selected (default head in case multiple heads has been created) corresponding to the gender provided. If no gender is specified, a random gender head would be given by default.
headManager.getCurrentBobbleHeads(config){ bobbleHead:BobbleHead ->
    // Get last created BobbleHead corresponding to config        
}

// Set a particular head as the current (deafult) head. This head will be used for all creating all content.
headManager.setCurrentHead(head.id)

//Delete a head
BobbleHeadSDK.getHeadManager().delete(bobbleHead.id)
```
>P.S Any new head creation/ deleting will automatically update the current selected head to latest available head.
#### ii. Sticker packs and Regional GIF packs :

A content pack is a collection of Sticker or regional GIFs. Each pack is accompanied with a name, icon, banner and a list containing stickers, animated stickers or regional GIFs. 

```kotlin
val contentPackManager = BobbleContentSDK.getContentPackManager()

val config = Config(STICKERS) // Available enum values -> STICKERS | ANIMATED_STICKERS | REGIONAL_GIFS
        .blockAgeRestrictedContent(true) // Optional
        .withLocale("en_IN") // Optional

contentPackManager.fetchContentPacks(config, { bobblePacks:List<BobblePack> ->
    bobblePacks.forEach{ pack ->
        val id = pack.id // Id of pack.
        val icon = pack.iconUri // Get URI pointing to icon for the pack.
        val banner = pack.bannerUri //Get URI for a banner for the pack
        val name = pack.name // Get name of package.
        val contentList:List<BobbleContent> = pack.contentList // Get list of content in the pack.
    }
}, err -> {
    // err = NoPackFoundException 
    // err = NoInternetException
})
```

#### iii. BobbleContent :
```BobbleContent``` is a object that represents a particular content (sticker, animated sticker, regional GIFs). The content can be rendered on a `BobbleContentPreview` (subclass of `ImageView`), fetched as a `Drawable` or a local file `Uri`.

```kotlin
val content:BobbleContent

val id = content.id //Id of content
val type = content.type //Available enum values -> STICKERS | ANIMATED_STICKERS | REGIONAL_GIFS
val isHeadSupported = content.isHeadSupported //Check whether the content has support for adding head
val previewURI = content.getPreviewURI //Get URI of a preview of the content

//Create a config for rendering the content 
val renderConfig = RenderConfig()
    .withHead(getBobbleHead()) //Set BobbleHead on the content. The config is ignored if the content doesn't support heads. By default, a user bobble head is applied, if present. Use null to bypass this. 
    .withOtf("Happy Birthday!") //Add a custom text layer on the content for customisation. The string can be max 40 characters long, else will be trimmed.
    .withQuality(HIGH) //Available enum values - HIGH | MEDIUM | LOW. Applied quality works propotional to the device's dpi configuration.
    .setTransparent(false) //Decide whether the background of the content should be transparent or opaque. Transparent is set by default
    
//Render the content on BobbleContentPreview.
val bobbleContentPreview = findViewById(R.id.bobbleContentPreview)
bobbleContentPreview.setContent(config, content)

//Fetch content as a drawable
content.createDrawable(config){ drawable ->

}
    
//Fetch content as a local file Uri
content.createFileUri(config){ uri ->

}
 ```

#### iv. Search for a content:
Search for a content based on a specific query.
```kotlin
val bobbleContentManager = BobbleContentSDK.getContentManager()
val config = Config().withQuery("Happy Birthday!")
    .allowTypes(STICKERS, ANIMATED_STICKERS) //Available enum values -> STICKERS | ANIMATED_STICKERS | REGIONAL_GIFS, Searches for only STICKERS by default.
    .blockAgeRestrictedContent(true) // Optional
    .withLocale("en_IN") // Optional

//Page size and page number for pagination.
val pageSize = 10
val pageNumber = 0

bobbleContentManager.searchContent(pageSize, pageNumber, { contentList:List<BobbleContent> ->

}, { err ->
    // EndOfPageException
    // NoInternetException
})
 ```
