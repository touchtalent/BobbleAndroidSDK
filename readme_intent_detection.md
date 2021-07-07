# Bobble Intent Detection SDK

Bobble Intent Detection SDK is an on-device detection approach powered by BobbleAI. Our AI Model processes an input text and derives the user's intent out of it without user's data actually leaving the user's device. Find all supported list of intents [here](#supported_intents).

## Setting up

* Add and initialize BobbleSDK Core in your project. Refer [here](Readme.md) for steps.
* Add the following line to the dependencies element in your application module’s build.gradle.

Sync your Gradle project to ensure that the dependency is downloaded by the build system.
```groovy
implementation 'com.touchtalent.bobblesdk:intent-detection'
```

* Sync your Gradle project to ensure that the dependency is downloaded by the build system.

## Bobble Intent Detection APIs

### BobbleIntentDetector

#### 1. Get Intent

Initialize an object of  `BobbleIntentDetector` and use it to extract intents from the text.

```java
BobbleIntentDetector intentDetector = new BobbleIntentDetector();
String input = "I want to go for shopping and then garage for repairing my car";
intentDetector.extractIntents(input, new IntentDetectionListener(){
        @Override
        public void onDetectionSuccess(List <BobbleIntent> intents){
             //Multiple intent received from BobbleIntentDetector
             for(BobbleIntent intent : intents){
                 String userIntent = intent.getIntent();// Intent for e.g - SHOPPING, TRAVEL, FOOD, etc
                 int probability = intent.getProbability(); // Value in the range 0-100 
             }
        }
                    
        @Override
        public void onDetectionFailure(int errorCode) {
             //errorCode = ERROR_RESOURCES_UNAVAILABLE
             //errorCode = ERROR_NO_RESULT
             //errorCode = NO_INTERNET
        }
});
```

#### 2. Close BobbleIntentDetector object

The `BobbleIntentDetector` object must be closed to safely release resources when not required.

```java
public class MainActivity extends Activity {

    BobbleIntentDetector intentDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentDetector = new BobbleIntentDetector();
        ...
    }

    @Override
    public void onDestroy() {
        super.onCreate(savedInstanceState);
        intentDetector.close();
        ...
    }

}
```
## <a name="supported_intents"></a>Supported Intents
| Sl No. | Intent       | Description   |
| -- | -----------------| ----------    |
| 1. | ```SHOPPING```   | Shows user's interest of shopping         |
| 2. | ```TRAVEL```     | Shows user's interest in travelling         |
| 3. | ```BFSI```       | Banking, Financial Services and Insurance         |
| 4. | ```FOOD```       | Shows user's interest in food         |