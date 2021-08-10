  
# Bobble Language Detection SDK

Bobble Language Detection SDK is an input tool that can be integrated into any Android app to detect language from an input text. In addition to listed languages, it can detect language for macaronic text as well like Hinglish, Bengalish and Malayalish etc. Currently, [83 languages](#supported_languages) are supported.

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

            public void onError(String error){
                
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
```LanguageDetectionResponse detectLanguage(String input)``` - Pass the complete input to get the detected langauge. 

```java
BobbleLanguageDetector languageDetector = new BobbleLanguageDetector();
LanguageDetectionResponse response = languageDetector.detectLanguage(text, new DetectorInstallListener(){
	public void onSuccess(LanguageDetectionResponse response) {
	      ...
	   // Display list of detected languages sorted by detection confidence.   
	   for (LanguageDetected language : response.getLanguagesList()) {   
		String code = language.getCode();
		double confidence = language.getConfidence(); //ranges between 0 - 1.
	   }
    	}
	
	public void onError(String error){
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
|14| Sindhi, Devanagari   | sd_IN    |
|15| Kashmiri, Devanagari | ks_IN    |
|16| Dogri, Devanagari    | doi_IN       |
|17| Konkani, Devanagari  | kok_IN   |
|18| Bodo, Devanagari     | brx_IN   |
|19| Sanskrit             | sa_IN         |
|20| Bhojpuri             | bho_IN   |
|21| Marwari (India)      | mwr_IN   |
|22| Nepali (India)       | ne_IN         |
|23| Ahirani              | ahr        |
|24| Awadhi               | awa        |
|25| Bagheli              | bfy        |
|26| Bagri (India)        | bgq\_dev   |
|27| Bagri (Pakistan)     | bgq\_arab  |
|28| Sinhala              | si_LK         |
|29| Tulu                 | tcy        |
|30| Bhili, Devanagari    | bhb\_dev   |
|31| Bhili, Gujarati      | bhb\_gujr  |
|32| Bishnupriya          | bpy\_beng  |
|33| Bodo, Bengali        | brx\_beng  |
|34| Brahui               | brh\_urdu  |
|35| Bundeli              | bns\_dev   |
|36| Chhattisgarhi        | hne\_dev   |
|37| Chittagonian         | ctg\_beng  |
|38| Dhundhari            | dhd\_hindi |
|39| Dogri, Arabic        | doi\_arab  |
|40| Garhwali             | gbm\_dev   |
|41| Garo                 | grt\_beng  |
|42| Godwari              | gdx\_dev   |
|43| Gujari (India)       | gju\_dev   |
|44| Gujari (Pakistan)    | gju\_arab  |
|45| Halbi, Devanagari    | hlb\_dev   |
|46| Halbi, Odia          | hlb\_oria  |
|47| Harauti              | hoj\_dev   |
|48| Haryanvi             | bgc\_dev   |
|49| Kannauji,Transliteration              | bjj\_dev   ||
|50| Kashmiri, Arabic     | ks\_arab   |
|51| Kok Borok, Bengali   | trp\_beng  |
|52| Konkani, Kannada     | kok\_knda  |
|53| Korku                | kfq\_dev   |
|54| Kumaoni              | kfy\_dev   |
|55| Kurukh, Devanagari   | kru\_dev   |
|56| Kurukh, Malayalam    | kru\_mylm  |
|57| Lambadi, Devanagari  | lmn\_dev   |
|58| Lambadi, Telugu      | lmn\_telu  |
|59| Lambadi, Kannada     | lmn\_knda  |
|60| Magahi (India)       | mag\_dev   |
|61| Malvi                | mup\_dev   |
|62| Mandeali             | mjl\_dev   |
|63| Manipuri, Bengali    | mni\_beng  |
|64| Marwari (Pakistan)   | mwr\_arab  |
|65| Mewari               | mtr\_dev   |
|66| Nimadi               | noe\_dev   |
|67| Northern Hindko      | hno\_arab  |
|68| Pahari-Potwari       | phr\_arab  |
|79| Pashto               | ps\_arab   |
|70| Punjabi, Arabic      | pa\_arab   |
|71| Rangpuri, Devanagari | rkt\_dev   |
|72| Santali, Bengali     | sat\_beng  |
|73| Sindhi, Arabic       | sd \_urdu  |
|74| Hinglish       | en\_hi  |
|75| Bengalish       | en \_bn  |
|76| Malayalish       | en \_ml  |
|77| Marathish       | en \_mr  |
|78| Punjabish       | en \_pa  |
|79| Gujaratish       | en \_gu  |
|80| Teleguish       | en \_te  |
|81| Tamilish       | en \_ta |
|82| Kannadish       | en \_kn  |
|83| Sinhalish       | en\_si_lk  |
