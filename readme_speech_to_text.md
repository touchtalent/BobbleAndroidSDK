# Bobble SpeechToText SDK 



## Setup
This is an Android library containing an API to BobbleAI's speech recognition services.

Add and initialise BobbleSDK Core in your project. Refer [here](readme_core.md) for steps

### Gradle
```groovy
implementation 'com.touchtalent.bobblesdk:speechToText'
```

## Required Permissions

 You need to grant `RECORD_AUDIO` permission to use the SpeechToText library

 Add this in your `AndroidManifest.xml`

```xml
 <uses-permission android:name="android.permission.RECORD_AUDIO" />
```

## Initialization
You need to call Init from your Activity `onCreate()` method 
```java
public class MainActivity extends AppCompatActivity {

    BobbleSpeechToText _speechToText;

    Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        _speechToText = new BobbleSpeechToText(this, getPackageName());

        _speechToText.setLocal(Locale.ENGLISH); // default 
    }

    @Override
    protected void onDestroy() {
        //Stops the speech to text, if already started.
        _speechToText.destroy();
    }
}
```


## Usage
### Speech recognition
Inside an activity:
```java


    _speechToText.startListening(new SpeechRecognitionListener() {
        @Override
        public void onReadyForSpeech() {
            //The user has started to speak.
        }

        @Override
        public void onRmsChanged(float value) {
            //The sound level in the audio stream has changed.
        }

        @Override
        public void onPartialResults(List<String> results) {
            //Called when partial results are available.
        }

        @Override
        public void onResult(String result) {
            //Called when results are ready
        }


        @Override
        public void onError(int errorCode) {
            //A network or recognition error occurred.
        }
    });

```

`onPartialResults` is called every time when SpeechToText detects a new word you have spoken, before the end of listening whereas `onResults` is called when SpeechToText finishes listening.

`onPartialResults` is helpful when audio is longer and you need to update UI accordingly

## Configuration
You can configure various parameters by using the setter methods on the speech instance, which you can get like this anywhere in your code:

```java
_speechToText
```


## Get current locale 

Use `_speechToText.getSpeechToTextLanguage()`. 


## Set Speech To Text Language 
Use `_speechToText.setLocale(locale)`.

> When you set the locale, the voice is automatically changed to the default voice of that language. If you want to set a particular voice, remember to re-set it every time you change the locale, too.


## Start And Stop Speech Listener

Use `_speechToText.startListening(speechRecognitionListener)` to start listening voice and use `_speechToText.destroy()` to stop and destroy current listener.
