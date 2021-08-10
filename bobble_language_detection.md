  
# Bobble Language Detection SDK

Bobble Language Detection SDK is an input tool that can be integrated into any Android app to detect language from an input text. In addition to known languages, it can detect language for macaronic text as well like Hinglish, Bengalish and Malayalish etc. Currently, [85 languages](#supported_languages) are supported.

## <a name="setting_up"></a>Setting Up

- Add and initialise BobbleSDK Core in your project. Refer [here](Readme.md#setup) for steps.

- Add the following dependency in your application module’s build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:language-detection'
```

## <a name="apis"></a>Bobble Language Detection APIs

### BobbleLanguageDetector

1. Setup - The module must be installed before usage.  ```BobbleLanguageDetector.install()```. 
```java
public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        BobbleLanguageDetector.install(new DetectorInstallListener(){
            public void onComplete(){
                ...
            }

            public void onError(BobbleError error){
                
            }
        });
        ...
    }
}
```

2. Start detection -
Create an instance of ```BobbleLanguageDetector``` to start a new detection session. Pass the text in the context of which detection needs to take place.

>P.S - The module needs to be installed before it can be used. Use following function to check availability of module
>```java
>boolean isInstalled = BobbleLanguageDetector.isInstalled();
>```

Use following to detect the language
```LanguageDetectionResponse detectLanguage(String text)```

```java
BobbleLanguageDetector languageDetector = new BobbleLanguageDetector();
languageDetector.detectLanguage(text, new DetectionListener(){
    public void onSuccess(LanguageDetectionResponse response) {
    	...
	// Display list of detected languages sorted by detection confidence.   
	for (LanguageDetected language : response.getLanguagesList()) {   
	   String code = language.getCode();
	   double confidence = language.getConfidence(); //ranges between 0 - 1.
	}
    }
	
    public void onError(BobbleError error){
    }
});  

```

3. Close BobbleLanguageDetector object -
The ```BobbleLanguageDetector``` object must be closed to safely release resources when not required.

```java
public class MainActivity extends Activity {

    BobbleLanguageDetector languageDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        languageDetector = new BobbleLanguageDetector();
        ...
    }

    @Override
    public void onDestroy() {
        super.onCreate(savedInstanceState);
        languageDetector.close();
        ...
    }

}
```
## <a name="supported_languages"></a>Supported Languages
|Sl no.| Language name | Language locale |
|---| ------------- | ---------- |
|1|Hindi                | hi_IN         |
|2|Bangla (India)       | bn_IN         |
|3|Marathi              | mr_IN         |
|4|Telugu               | te_IN         |
|5|Tamil (India)        | ta_IN         |
|6|Gujarati             | gu_IN         |
|7|Urdu (India)         | ur_IN         |
|8|Kannada              | kn_IN         |
|9|Odia                 | or_IN         |
|10| Malayalam            | ml_IN         |
|11| Punjabi, Gurmukhi    | pa_IN         |
|12| Assamese             | as_IN         |
|13| Maithili             | mai_IN   |
|14| Santhali, Devanagari   | sat_IN    |
|15| Sindhi, Devanagari   | sd_IN    |
|16| Kashmiri, Devanagari | ks_IN    |
|17| Dogri, Devanagari    | doi_IN       |
|18| Konkani, Devanagari  | kok_IN   |
|19| Bodo, Devanagari     | brx_IN   |
|20| Sanskrit             | sa_IN         |
|21| Bhojpuri             | bho_IN   |
|22| Marwari (India)      | mwr_IN   |
|23| Nepali (India)       | ne_IN         |
|24| Rajasthani (India)       | raj_IN         |
|25| Ahirani              | ahr        |
|26| Awadhi               | awa        |
|27| Bagheli              | bfy        |
|28| Bagri (India)        | bgq\_dev   |
|29| Bagri (Pakistan)     | bgq\_arab  |
|30| Sinhala              | si_LK         |
|31| Tulu                 | tcy        |
|32| Bhili, Devanagari    | bhb\_dev   |
|33| Bhili, Gujarati      | bhb\_gujr  |
|34| Bishnupriya          | bpy\_beng  |
|35| Bodo, Bengali        | brx\_beng  |
|36| Brahui               | brh\_urdu  |
|37| Bundeli              | bns\_dev   |
|38| Chhattisgarhi        | hne\_dev   |
|39| Chittagonian         | ctg\_beng  |
|40| Dhundhari            | dhd\_hindi |
|41| Dogri, Arabic        | doi\_arab  |
|42| Garhwali             | gbm\_dev   |
|43| Garo                 | grt\_beng  |
|44| Godwari              | gdx\_dev   |
|45| Gujari (India)       | gju\_dev   |
|46| Gujari (Pakistan)    | gju\_arab  |
|47| Halbi, Devanagari    | hlb\_dev   |
|48| Halbi, Odia          | hlb\_oria  |
|49| Harauti              | hoj\_dev   |
|50| Haryanvi             | bgc\_dev   |
|51| Kannauji,Transliteration              | bjj\_dev   ||
|52| Kashmiri, Arabic     | ks\_arab   |
|53| Kok Borok, Bengali   | trp\_beng  |
|54| Konkani, Kannada     | kok\_knda  |
|55| Korku                | kfq\_dev   |
|56| Kumaoni              | kfy\_dev   |
|57| Kurukh, Devanagari   | kru\_dev   |
|58| Kurukh, Malayalam    | kru\_mylm  |
|59| Lambadi, Devanagari  | lmn\_dev   |
|60| Lambadi, Telugu      | lmn\_telu  |
|61| Lambadi, Kannada     | lmn\_knda  |
|62| Magahi (India)       | mag\_dev   |
|63| Malvi                | mup\_dev   |
|64| Mandeali             | mjl\_dev   |
|65| Manipuri, Bengali    | mni\_beng  |
|66| Marwari (Pakistan)   | mwr\_arab  |
|67| Mewari               | mtr\_dev   |
|68| Nimadi               | noe\_dev   |
|69| Northern Hindko      | hno\_arab  |
|70| Pahari-Potwari       | phr\_arab  |
|71| Pashto               | ps\_arab   |
|72| Punjabi, Arabic      | pa\_arab   |
|73| Rangpuri, Devanagari | rkt\_dev   |
|74| Santali, Bengali     | sat\_beng  |
|75| Sindhi, Arabic       | sd \_urdu  |
|76| Hinglish       | en\_hi  |
|77| Bengalish       | en \_bn  |
|78| Malayalish       | en \_ml  |
|79| Marathish       | en \_mr  |
|80| Punjabish       | en \_pa  |
|81| Gujaratish       | en \_gu  |
|82| Teleguish       | en \_te  |
|83| Tamilish       | en \_ta |
|84| Kannadish       | en \_kn  |
|85| Sinhalish       | en\_si_lk  |
