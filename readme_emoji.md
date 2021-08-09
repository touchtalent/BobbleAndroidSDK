
# Bobble Contextual Emoji Suggestion SDK

Bobble Contextual Emoji Suggestion SDK is a tool that can be integrated into any Android app to contextually detect emotions contained within a text message and suggest emojis based on it. Currently, [68 languages](#supported_languages) are supported.

## <a name="implementation_steps"></a>Setting Up

- Add and initialize BobbleSDK Core in your project. Refer [here](Readme.md#setup) for steps.

- Add the following dependency in your application module’s build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:contextual-emojis'
```
Sync your Gradle project to ensure that the dependency is downloaded by the build system.


## <a name="apis"></a>Bobble Contextual Emoji APIs

### BobbleEmojiSuggestions

1. Initiate a object of ```BobbleEmojiSuggestions```.

```java
BobbleEmojiSuggestions emojiSuggestions = new BobbleEmojiSuggestions();
emojiSuggestions.setLanguage("hi") // Optional, for targetting a particular language. When this is not used, language is automatically detected from text.
```

2. Get emoji suggestions by calling getEmojis()

```java
List<String> emojis = emojiSuggestions.getEmojis("gussa mat dila", new EmojiSuggestionsCallback(){
    public void onResult(List<String> emojis){
        //emojis = [😤,👿,😡,🙏,😠]
        ...
    }

    public void onError(int errorCode){
        //errorCode = ERROR_RESOURCES_UNAVAILABLE
        //errorCode = ERROR_NO_RESULT
        ...
    }
}); 
```
3. Close BobbleEmojiSuggestions object - The ```BobbleEmojiSuggestions``` object must be closed to safely release resources when not required.

```java
public class MainActivity extends Activity {

    BobbleEmojiSuggestions suggestions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        suggestions = new BobbleEmojiSuggestions();
        ...
    }

    @Override
    public void onDestroy() {
        super.onCreate(savedInstanceState);
        suggestions.close();
        ...
    }

}
```
## <a name="supported_languages"></a>Supported Languages
|Sl no| Language name        |
|-----| --------------       |
|1| English (India)        |
|2| Hindi / हिन्दी            |
|2| Bangla / বাংলা               |
|3| Marathi / मराठी              |
|4| Telugu / తెలుగు                |
|5| Tamil / தமிழ்             |
|6| Gujarati / ગુજરાતી       |
|7| اردو / Urdu    |
|8| Kannada / ಕನ್ನಡ              |
|9| Odia / ଓଡ଼ିଆ                 |
|10| Malayalam / മലയാളം             |
|11| Punjabi / ਪੰਜਾਬੀ            |
|12| Assamese / অসমীয়া       |
|13| Acehnese               |
|14| Balinese              |
|15| Banjarese        |
|16| العَرَبِيَّة / Arabic     |
|17| Armenian / հայերէն              |
|18| Azerbaijani / Azərbaycan    |
|19| Bahasa Indonesia                 |
|20| Javanese    |
|21| Madurese      |
|22|Batak Karo             
|23|Minangkabau                    |       
|24|Sundanese                  |
|25|Catalan / Català           |
|26|Croatian / Hrvatski        |
|27|Danish / Dánský        |
|28|Banggai                |
|29|Dutch / Nederlands         |
|30|Estonian / Eesti           |
|31|Finnish / Suomi            |
|32|Bangka Malay           |
|33|Georgian / ქართული         |
|34|German / Deutsch           |
|35|Greek / Ελληνικά           |
|36|Batak Toba         |
|37|Hungarian / Magyar|
|38|Buginese|
|39|Italian / Italiano|
|40|Kazakh / Қазақ|
|41|Gayo|
|42|Gorontalo|
|43|Hawu|
|44|Latvian / Latviešu|
|45|Jambi Malay|
|46|Lithuanian / Lietuvių|
|47|Komering|
|48|Macedonian / Mакедонски|
|49|Lampung api|
|50|Malay / Bahasa Melayu|
|51|Makassar Malay|
|52|Mandar|
|53|Manggarai|
|54|Mongondow|
|55|Muna|
|56|Nepali / नेपाली|
|57|Ngaju Dayak|
|58|Norwegian / Norsk|
|59|Persian / فارسی|
|60|Polish / Polski|
|61|Portuguese (BR) / Português|
|62|Portuguese (PT)/ Português|
|63|Sinhala/සිංහල|
|64|Spanish / Español|
|65|Spanish / Latinoamérica|
|66|Sasak|
|67|Tamil/தமிழ் (Srilanka)|
|68|Tetum|
