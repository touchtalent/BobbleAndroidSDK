 #  Bobble Content SDK

Bobble Content SDK provides an all in one solution for your content requirements:
1. Creating Bobble Heads (Cartoonised Avatar heads from user's selfie)
2. Stickers
3. Animated Stickers
4. Regional GIFs.

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

```xml
<uses-permission android:name="android.permission.CAMERA" />
```
##  Bobble Content APIs:

Bobble Content can be integrated by following

### BobbleContentView
```BobbleContentView``` imports a complete view showing different formats of contents. The UI is customizable via themes and interaction with content can be captured via listeners. The view handles multiple functionalities :

- Display content and capture user interactions with them.
- Host content stores for browsing content packs.
- Bobble Head creation and management. 

#### i. Add custom view inside a XML layout of your Activity/Fragment
```xml
<com.touchtalent.bobblesdk.content.BobbleContenView
    android:id="@+id/contentView"
    android:layout_width="match_parent"
    android:layout_height="500dp"/>
```
#### ii. APIs
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
        });
        
        //optional - suggests content based on passed text.
        contentView.suggest(text);
    }
}
```

### Bobble Head Creation

- Creation - Bobble Heads can be created via *Full fledged head creation activity (includes UI)*

```java 
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Intent intent = new Intent(this, BobbleHeadCreationActivity.class);
        //optional
        intent.putExtra(BobbleContentConstants.AGE, age);
        intent.putExtra(BobbleContentConstants.GENDER, gender);
        
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
        }
    }
}
```

- Manage created heads

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

