# Bobble Intent Detection SDK

Bobble Intent Detection SDK is an on-device detection approach powered by BobbleAI. Our AI Model processes an input text and derive the user's intent out of it without the user's data actually leaving the user's device.

* Fast - It is an AI-Based model which works on the user's device itself for getting the exact intent from the text messages.
* Lightweight - It is light weighted, the AI model used is < 1MB.

## Setting up

* Add and initialize BobbleSDK Core in your project. Refer [here](readme_core.md) for steps.
* Add the following line to the dependencies element in your application module’s build.gradle.

```groovy
implementation 'com.touchtalent.bobblesdk:intent-detection'
```

* Sync your Gradle project to ensure that the dependency is downloaded by the build system.

## Bobble IntentDetection APIs

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
                 String userIntent = intent.getIntent();// Intent for e.g - SHOPPING, LOAN, FOOD, etc
                 int probability = intent.getProbability(); // Value in the range 0-100 
             }
        }
                    
        @Override
        public void onDetectionFailure(int errorCode) {
             //errorCode = ERROR_RESOURCES_UNAVAILABLE
             //errorCode = ERROR_AUTHORISATION_FAILED
             //errorCode = ERROR_NO_RESULT
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