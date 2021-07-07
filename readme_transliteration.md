
# Bobble Transliteration SDK

Bobble Transliteration SDK is an input tool that can be integrated into any Android app to enable typing in regional languages. It can be leveraged to bridge the gap between users who prefer mixed scripts and users who prefer pure regional experience. Currently, [74 regional languages](#supported_languages) are supported.

## <a name="setting_up"></a>Setting Up

- Add and initialise BobbleSDK Core in your project. Refer [here](readme_core.md) for steps.

- Add the following dependency in your application module’s build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:transliteration'
```

## <a name="apis"></a>Bobble Transliterator APIs

### BobbleTransliterator

1. Managing languages - A language must be installed before it can be used for transliteration. Install / uninstall language(s) via ```BobbleTransliterator.manage(TransliteratorRequest)```. 
```java
public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TransliteratorRequest.Builder builder = new TransliteratorRequest.Builder(this);
        builder.installLanguages("hi", "te");
        builder.uninstallLangauges("bn");
        builder.addListener(new TransliteratorInstallListener(){
            public void onLanguageDownloaded(String languageIdentifier){
                ...
            }

            public void onLanguageError(String languageIdentifier){
                
            }
        });
        BobbleTransliterator.manage(builder.build());
        ...
    }
}
```

2. Start transliterating
Create an instance of ```BobbleTransliterator``` to start a new transliteration session. Pass the language identifier (refer [here](#supported_languages) for complete list) in the context of which transliteration needs to take place.

>P.S - The language needs to be installed before it can be used. Use following function to check availability of a particular language
>```java
>boolean isInstalled = BobbleTransliterator.isInstalled("hi");
>```

 ```BobbleTransliterator``` facilitates both <b><i>continuous typing</i></b> as well as <b><i>non-continuous typing</i></b>.

#### continuous Typing
```void bind(EditText inputBox)``` - The transliterator binds itself with given ```EditText``` and automatically transliterates it, as it receives input events.
```java
EditText input = view.findViewById(R.id.input);
transliterator.bind(input);
```

#### Mixed Typing (continuous / Non-continuous typing)
```String transliterate(String input)``` - Pass the complete input to get the transliterated output. ```BobbleTransliterator``` evaluates the latest input based on last input and handles both continuous / non-continuous cases accordingly. 
```java
String transliteration;
transliteration = transliterator.transliterate("n");      // transliteration = "न"
transliteration = transliterator.transliterate("na");     // transliteration = "ना"
transliteration = transliterator.transliterate("nam");    // transliteration = "नम"
transliteration = transliterator.transliterate("nama");   // transliteration = "नामा"
transliteration = transliterator.transliterate("namas");  // transliteration = "नमस"
transliteration = transliterator.transliterate("namast"); // transliteration = "नमस्त"
transliteration = transliterator.transliterate("namaste");// transliteration = "नमस्ते" 
```

>P.S. - BobbleTransliterator can be used only in a single mode at a time, either ```binded mode``` or ```transliterate mode```, trying to use both modes on a single object will throw ```UnsupportedOperationException```. Create different objects to use multiple modes at the same time.

3. Close BobbleTransliterator object
The ```BobbleTransliterator``` object must be closed to safely release resources when not required.

```java
public class MainActivity extends Activity {

    BobbleTransliterator transliterator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transliterator = new BobbleTransliterator("hi");
        ...
    }

    @Override
    public void onDestroy() {
        super.onCreate(savedInstanceState);
        transliterator.close();
        ...
    }

}
```
## <a name="supported_languages"></a>Supported Languages
| Language name | Language Identifier |
| ------------------------------------- | ---------- |
| Tamil (India)        | ta         |
| Malayalam            | ml         |
| Telugu               | te         |
| Marathi              | mr         |
| Hindi                | hi         |
| Gujarati             | gu         |
| Bangla (India)       | bn         |
| Punjabi, Gurmukhi    | pa         |
| Kannada              | kn         |
| Odia                 | or         |
| Sanskrit             | sa         |
| Assamese             | as         |
| Nepali (India)       | ne         |
| Urdu (India)         | ur         |
| Ahirani              | ahr        |
| Awadhi               | awa        |
| Bagheli              | bfy        |
| Bagri (India)        | bgq\_dev   |
| Bagri (Pakistan)     | bgq\_arab  |
| Sinhala              | si         |
| Dogri, Devanagari    | doi        |
| Tulu                 | tcy        |
| Bhili, Devanagari    | bhb\_dev   |
| Bhili, Gujarati      | bhb\_gujr  |
| Bhojpuri             | bho\_dev   |
| Bishnupriya          | bpy\_beng  |
| Bodo, Bengali        | brx\_beng  |
| Bodo, Devanagari     | brx\_dev   |
| Brahui               | brh\_urdu  |
| Bundeli              | bns\_dev   |
| Chhattisgarhi        | hne\_dev   |
| Chittagonian         | ctg\_beng  |
| Dhundhari            | dhd\_hindi |
| Dogri, Arabic        | doi\_arab  |
| Garhwali             | gbm\_dev   |
| Garo                 | grt\_beng  |
| Godwari              | gdx\_dev   |
| Gujari (India)       | gju\_dev   |
| Gujari (Pakistan)    | gju\_arab  |
| Halbi, Devanagari    | hlb\_dev   |
| Halbi, Odia          | hlb\_oria  |
| Harauti              | hoj\_dev   |
| Haryanvi             | bgc\_dev   |
| Kannauji,Transliteration              | bjj\_dev   |
| Kashmiri, Arabic     | ks\_arab   |
| Kashmiri, Devanagari | ks\_dev    |
| Kok Borok, Bengali   | trp\_beng  |
| Konkani, Devanagari  | kok\_dev   |
| Konkani, Kannada     | kok\_knda  |
| Korku                | kfq\_dev   |
| Kumaoni              | kfy\_dev   |
| Kurukh, Devanagari   | kru\_dev   |
| Kurukh, Malayalam    | kru\_mylm  |
| Lambadi, Devanagari  | lmn\_dev   |
| Lambadi, Telugu      | lmn\_telu  |
| Lambadi, Kannada     | lmn\_knda  |
| Magahi (India)       | mag\_dev   |
| Maithili             | mai\_dev   |
| Malvi                | mup\_dev   |
| Mandeali             | mjl\_dev   |
| Manipuri, Bengali    | mni\_beng  |
| Marwari (India)      | mwr\_dev   |
| Marwari (Pakistan)   | mwr\_arab  |
| Mewari               | mtr\_dev   |
| Nimadi               | noe\_dev   |
| Northern Hindko      | hno\_arab  |
| Pahari-Potwari       | phr\_arab  |
| Pashto               | ps\_arab   |
| Punjabi, Arabic      | pa\_arab   |
| Rangpuri, Devanagari | rkt\_dev   |
| Santali, Bengali     | sat\_beng  |
| Sindhi, Arabic       | sd \_urdu  |
| Sindhi, Devanagari   | sd\_dev    |

