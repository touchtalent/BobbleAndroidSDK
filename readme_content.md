 #  Bobble Content SDK

Bobble Content SDK provides an all in one solution for your content requirements:
1. Creating Bobble Heads (Cartoonised Avatar heads from user's selfie)
2. Stickers (with Bobble Head and OTF* support)
3. Animated Stickers (with Bobble Head and OTF support)
4. Regional GIFs (with OTF support).

The SDK hosts a massive repository with more than 100K+ content.

> *OTF - OTF refers to <b>On The Fly</b>, which is a capability of adding on-demand text layer within a content to give it a personalised touch.
## <a name="implementation_steps"></a>Implementation Steps

- Add and initialise BobbleSDK Core in your project. Refer [here](readme_core.md) for steps.

- Add following dependency in your application module’s build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:content'
```

Sync your Gradle project to ensure that the dependency is downloaded by the build system.

### Required Permissions : 
The SDK requires following permission if you opt for automatic flow (optional) of Bobble Head creation.

```xml
<uses-permission android:name="android.permission.CAMERA" />
```
##  Bobble Content APIs:

### 1. Bobble Head :
- Creation - Bobble Heads can be created in 2 ways - *Full fledged head creation activity (includes UI)* and *Raw APIs which convert given selfie into avatar*

Full fledged head creation -
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
            //Unique id (local) of head.
            long headId = head.getId();
            // Width of Bobble head.
            int width = head.getWidth();
            // Height of Bobble head.
            int height = head.getHeight();
            // Returns URI of PNG image pointing to the Bobble Head.
            String headUri = head.getHeadURI();
            // Get raw PNG image used for head creation
            String rawUri = head.getRawImageURI();
            //Get the square enclosing the head 
            Rect rect = head.getRect();
        }
    }
}
```
Raw APIs -
```java 
public class MainActivity extends AppCompatActivity {
    
    BobbleHeadCreator headCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        String uri = fetchSelfieImageURI();
        headCreator = new BobbleHeadCreator(uri);
        headCreator.setGender("male"); //Optional - "male" or "female"
        headCreator.setAge(25); //Optional
        headCreator.start(this, new BobbleHeadCreationProgressListener(){
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
                //errorCode = ERROR_AUTHORISATION_FAILED
                //errorCode = ERROR_NO_FACE_DETECTED 
                //errorCode = ERROR_NO_INTERNET
                //errorCode = ERROR_UNKNOWN
            }
        });
    }
       
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
```
-   Manage created heads

```java
    BobbleHeadManager manager = new BobbleHeadManager();
    
    //Fetch created heads
    manager.fetchCreatedHeads(context, new BobbleHeadListener(){
        public void onHeadsFetched(List<BobbleHead> heads){
            //Empty list if no head exists.
        }
    })

    //Delete a head
    BobbleHead head = getHeadToBeDeleted();
    manager.delete(head.getId());
```
### 2. Sticker and regional GIFs packs :

A content pack is a collection of Sticker or regional GIFs. Each pack is accompanied with a icon, name, date released and either a list of stickers, animated stickers or regional GIFs. 

```java 
BobblePackManager packManager = new BobblePackManager();
packManager.fetchStickerPacks(this, new StickerPackListener(){

    @Override
    public void onSuccess(List<BobbleStickerPack> bobblePacks){
        for (BobbleStickerPack pack : bobblePacks){
            long id = pack.getId() // Id of pack.
            String icon = pack.getIconURI() // Get URI pointing to icon image.
            String name = pack.getName() // Get name of package.
            pack.getStickerList() // Get 
        }
    }
});
packManager.fetchAnimatedStickerPacks
```
### Get Default Sticker Pack List :
 There are two types of sticker one is static sticker and second one animated sticker. both stickers have same packId.
``` 
List<BobbleContentPacks> StickerPackList = BobbleContentPackManager.getDefaultStickerPack(context).listener(new 
          BobbleContentPackListener() {    
          @Override                     
          public void onSuccess(List<BobbleContentPacks> stickerPackList){                        
                int packId = stickerPackList.getPackId(); 
                String packName = stickerPackList.getPackName(); 
                String PackIconURL = stickerPackList.getPackIcon(); 
                String isSupportBobbleAvatar =stickerPackList.isSupportBobbleAvatar() ; // if sticker pack support bobble avatar then isSupportBobbleAvatar will return true otherwise false.
                String bannerImage = stickerPackList.getBannerImage();

          }                    
         @Override
         public void onFailure(Error e) {         
               Log.e("TAG", e.getMessage);                   
         }  
      }).start();

 ```
### Get Default Regional GIF Pack List :
``` 
BobbleContentPackManager.getDefaultRegionalGIFsPack(context).listener(new 
          BobbleContentPackListener() {    
          @Override                     
          public void onSuccess(List<BobbleContentPacks> regionalGIFsPackList){                        
   

          }                    
         @Override
         public void onFailure(Error e) {         
               Log.e("TAG", e.getMessage);                   
         }  
      }).start();
 ```

### Get Avatar List :
``` 
          BobbleAvatarListManager.getAvatarList(context).listener(new 
          BobbleAvatarListener() {    
          @Override                     
          public void onSuccess(List<BobbleAvatar> bobbleAvatarList){                        
   

          }                    
         @Override
         public void onFailure(Error e) {         
               Log.e("TAG", e.getMessage);                   
         }  
      }).start();
 ```

### To Create Sticker / Regional GIFs :
``` 
 BobbleContentCreation createContent = new BobbleContentCreation(); 
.setOtf(string otf) //text for draw text on content - optional
.setFace(BobbleAvatar bobbleAvatar) // set face obect - optional
.setWatermarkWithPosition(String watermarkimageUri, int position, float height, float width) // position value is static variable: LEFT_TOP,LEFT_BOTTOM,RIGHT_TOP,RIGHT_BOTTOM - optional
.setMaxNumberOfContent(int maxStickerSize) // return less than maxStickerSize stickers - optional
.setPageNumber(int pageNumber) // this is used for pagination . - optional
.setContentInPage(int numberOfStickers) // Number of content in each page - optional
.setContentType(int type) // type value are BobbleContentConstant.STICKER ,BobbleContentConstant.ANIMATED_STICKER ,BobbleContentConstant.REGIONAL_GIF  
.listener(new ContentListener() {    
          @Override                     
          public void onSuccess(List <ContentObeject> contentObejectList){                        
          //Do the operation here 
           for(ContentObeject contentObeject : contentObejectList) {
                // contentObject.getStickerId() // return sticker Id
                // contentObect.getPackId() // return packId
                //contentObject.getContentUri(); // // return internal storage URI of final content 
               //contentObject.getHeight();  // return height of content
               //contentObject.getWidth(); // return width of content

            }
          }                    
         @Override
         public void onFailure(Error e) {         
               Log.e("TAG", e.getMessage);                   
         }  
 }).create();

```

In above code snippet, this will return trending content.

you can get relevant content searched by user. you have to add this:
 
``` 
createContent.setSearchedStickerByText(String inputText)
```

if you want to get content by pack Id: 

``` 
createContent.setPackId(int packId) 
```

if you want to get content by content Id: 

``` 
createContent.setContentId(int contentId) 
```


