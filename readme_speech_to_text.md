# Bobble Speech-to-text SDK 

Bobble Speech-to-text SDK provides speech recognition services in most used Indic languages. Check supported languages [here](#supported_languages). 

## <a name="implementation_steps"></a>Implementation Steps

- Add and initialise BobbleSDK Core in your project. Refer [here](Readme.md#setup) for steps.

- Add following dependency in your application module’s build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:speech-to-text'
```

Sync your Gradle project to ensure that the dependency is downloaded by the build system.

## Required Permissions : 
The SDK requires following permission to work. Make sure to acquire them before using any of the APIs.

```xml
<uses-permission android:name="android.permission.RECORD_AUDIO" />
```

## Bobble Speech-to-text APIs

### 1. BobbleSpeechToText
Create an object of BobbleSpeechToText to start and stop listening. Listening stops when ```onResult(String)``` or ```onError(int)``` callbacks are received.

- Start listening
```java
BobbleSpeechToText speechToText = new BobbleSpeechToText(context, "en_IN"); // Refer list below for supported locales.
speechToText.startListening(new SpeechToTextListener() {
    @Override
    public void onReadyForSpeech() {
        // User can now start speaking.
    }

    @Override
    public void onPartialResults(List<String> results) {
        // Called on every word completion. 
        // Helpful when speech is longer and UI needs to be updated accordingly
        // Multiple results are provided, sorted in descending order of probability.
    }

    @Override
    public void onResult(String result) {
        // Called when final results are available
    }

    @Override
    public void onError(int errorCode) {
        // errorCode = ERROR_MISSING_PERMISSION
        // errorCode = ERROR_NO_INTERNET
    }
});

```
- Stop listening
```java
speechToText.stopListening();
```
## <a name="supported_languages"></a>Supported Languages
| Sl No. | Language name     | Language Locale   |
| -- | ----------------- | ----------        |
| 1. | English (India)   | en_IN             |
| 2. | Hindi             | hi_IN             |
| 3. | Bengali           | bn_IN             |
| 4. | Marathi           | mr_IN             |
| 5. | Tamil             | ta_IN             |
| 6. | Malayalam         | ml_IN             |
| 7. | Kannada           | kn_IN             | 
| 8. | Gujarati          | gu_IN             |
| 9.| Urdu              | ur_IN             |