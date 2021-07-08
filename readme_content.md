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

- Add following dependency in your application module’s build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:content'
```

Sync your Gradle project to ensure that the dependency is downloaded by the build system.

## Required Permissions : 
The SDK requires following permission if you opt for [Full fledged head creation activity](#full_fledged) (optional) of Bobble Head creation.

```xml
<uses-permission android:name="android.permission.CAMERA" />
```
##  Bobble Content APIs:

Bobble Content can be integrated by 2 methods depending on requirements.

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
Add listeners to capture user's interaction with the content.

```java
public class MainActivity extends Activity {

    BobbleContentView contentView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentView = findViewById(R.id.contentView);
        
        contentView.setListener(new ContentInteractionListener(){
            
            @Override
            public void onSelectContent(String uri){
                //Called when user taps on any of the content.
                //The uri points to the local path of the content that user tapped on.
            }
        })
    }
}
```

### 2. API based
#### i. Bobble Head :
- Creation - Bobble Heads can be created in 2 ways - *Full fledged head creation activity (includes UI)* and *Raw APIs which convert given selfie into avatar*

<a name="full_fledged"></a>Full fledged head creation -
```java 
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Intent intent = new Intent(this, BobbleHeadCreationActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }
       
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE) {
        if (resultCode == BobbleHeadCreationAcitivity.RESULT_SUCCESS){
            BobbleHead head = BobbleHead.fromIntentResult(data);
            long headId = head.getId(); //Unique id (local) of head.
            int width = head.getWidth(); // Width of Bobble head.
            int height = head.getHeight(); // Height of Bobble head.
            String headUri = head.getHeadURI(); // Returns URI of PNG image pointing to the Bobble Head.
            String rawUri = head.getRawImageURI(); // Get raw PNG image used for head creation
            Rect rect = head.getRect(); //Get the square enclosing the head 
        }
    }
}
```
Raw APIs -
```java 
BobbleHeadManager.CreationBuilder builder = new BobbleHeadManager.CreationBuilder(uri);
builder.setGender("male"); //Optional - "male" or "female"
builder.setAge(25); //Optional
builder.setLocale("en_IN")// Optional - to fetch localised content 
builder.setListener(new BobbleHeadProgressListener(){
    @Override
    public void onProgress(int progress){
        //Used to update UI, range of progress = 0-100
    }
    
    @Override
    public void onSuccess(BobbleHead bobbleHead){
        //Use BobbleHead object as per requirement
    }

    @Override
    public void onError(int errorCode){
        //errorCode = ERROR_NO_FACE_DETECTED 
        //errorCode = ERROR_NO_INTERNET
    }
});
BobbleHeadManager.create(this, builder.build());
```
-   Manage created heads

```java
//Fetch created heads
BobbleHeadManager.fetchCreatedHeads(context, new BobbleHeadListener(){
    public void onHeadsFetched(List<BobbleHead> heads){
        //Empty list if no head exists.
    }
})

//Delete a head
BobbleHead head = getHeadToBeDeleted();
BobbleHeadManager.delete(head.getId());
```
#### ii. Sticker packs and Regional GIF packs :

A content pack is a collection of Sticker or regional GIFs. Each pack is accompanied with a icon, name, date released and either a list of stickers, animated stickers or regional GIFs. 

```java
BobblePackManager.RequestBuilder builder = new BobblePackManager.RequestBuilder(STICKERS) // Available enum values -> STICKERS | ANIMATED_STICKERS | REGIONAL_GIFS
builder.blockAgeRestrictedContent(true) // Optional, true to block age restricted content.
builder.setListener(new BobblePackListener(){

    @Override
    public void onSuccess(List<BobblePack> bobblePacks){
        for (BobblePack pack : bobblePacks){
            long id = pack.getId() // Id of pack.
            String icon = pack.getIconURI() // Get URI pointing to icon image.
            String name = pack.getName() // Get name of package.
            List<BobbleContent> stickers = pack.getStickerList() // Get list of stickers in the pack.
        }
    }

    @Override
    public void onError(int errorCode){
        //errorCode = ERROR_NO_PACKS_FOUND 
        //errorCode = ERROR_NO_INTERNET
    }

});
BobblePackManager.fetchPacks(context, builder.build());
```

#### iii. BobbleContent :
```BobbleContent``` is a object that represents a particular content (sticker, animated sticker, regional GIFs). The content can be rendered on a ImageView, fetched as a Drawable or a local file URI.
```java
List<BobbleContent> contents = pack.getStickerList();
for (BobbleContent content : contents){
    long id = content.getId() //Id of content.
    ContentType type = content.getType() //Available enum values -> STICKERS | ANIMATED_STICKERS | REGIONAL_GIFS
    boolean isHeadSupported = content.isHeadSupported(); //Check whether the content has support for adding head.
    boolean previewURI = content.getPreviewURI(); //Get URI of a preview of the content.

    //Create a config for rendering the content 
    BobbleContent.RenderConfig config = new BobbleContent.RenderConfig();
    config.setHead(getBobbleHead()); //Set BobbleHead on the content. The config is ignored if the content doesn't support heads.
    config.setOTF("Happy Birthday!"); //Add a custom text layer on the content for customisation. The string can be max 40 characters long, else will be trimmed.
    config.setQuaility(HIGH); //Available enum values - HIGH | MEDIUM | LOW. Applied quality works propotional to the device's dpi configuration.
    config.setBackgroundColor(Color.parse("#FFFFFFFF")); //Apply a background color to the content. Applicable only if the background of content is transparent, else content background will override this.
    
    //Bind the content to a ImageView to render it. Can be used in RecyclerView as well.
    content.bind(config, viewHolder.imageView);

    //Fetch content as a drawable
    content.createDrawable(config, new DrawableListener(){
        public void onReady(Drawable drawable){
        }

        public void onFailed(){
        }
    });
    
    //Fetch content as a drawable
    content.createFileURI(config, new FileURIListener(){
        public void onReady(String uri){
        }

        public void onFailed(){
        }
    });
}
 ```

#### iv. Search for a content:
Search for a content based on a specific query.
```java 
BobbleContentManager.SearchBuilder builder = new BobbleContentManager.SearchBuilder();
builder.setQuery("Happy Birthday!");
builder.allowTypes(STICKERS, ANIMATED_STICKERS); //Available enum values -> STICKERS | ANIMATED_STICKERS | REGIONAL_GIFS, Searches for only STICKERS by default.
builder.blockAgeRestrictedContent(true) // Optional, true to block age restricted content.
SearchConfig config = builder.build();

//Page size and page number for pagination.
int pageSize = 10;
int pageNumber = 0;
BobbleContentManager.searchContent(config, pageSize, pageNumber, new ContentSearchListener(){
    @Override
    public void onResponse(List<BobbleContent> contents){

    }

    @Override
    public void onError(int errorCode){
        //errorCode = ERROR_INVALID_PARAMS 
        //errorCode = ERROR_NO_INTERNET
        //errorCode = ERROR_END_OF_PAGE
    }

})
 ```
