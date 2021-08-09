
# Bobble Contextual Emoji Suggestion SDK

Bobble Contextual Emoji Suggestion SDK is a tool that can be integrated into any Android app to contextually detect emotions contained within a text message and suggest emojis based on it. Currently, [79 languages](#supported_languages) are supported.

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
|3| Hinglish            |
|4| Bangla / বাংলা               |
|5| Banglish               |
|6| Marathi / मराठी              |
|7| Marathinglish               |
|8| Telugu / తెలుగు                |
|9| Tenglish                |
|10| Tamil / தமிழ்             |
|11| Tanglish                |
|12| Gujarati / ગુજરાતી       |
|13| Gujaratinglish       |
|14| اردو / Urdu    |
|15| Kannada / ಕನ್ನಡ              |
|16| Kanglish              |
|17| Odia / ଓଡ଼ିଆ                 |
|18| Odinglish                 |
|19| Malayalam / മലയാളം             |
|20| Manglish             |
|21| Punjabi / ਪੰਜਾਬੀ            |
|22| Punglish             |
|23| Assamese / অসমীয়া       |
|24| Acehnese               |
|25| Balinese              |
|26| Banjarese        |
|27| العَرَبِيَّة / Arabic     |
|28| Armenian / հայերէն              |
|29| Azerbaijani / Azərbaycan    |
|30| Bahasa Indonesia                 |
|31| Javanese    |
|32| Madurese      |
|33|Batak Karo             
|34|Minangkabau                    |       
|35|Sundanese                  |
|36|Catalan / Català           |
|37|Croatian / Hrvatski        |
|38|Danish / Dánský        |
|39|Banggai                |
|40|Dutch / Nederlands         |
|41|Estonian / Eesti           |
|42|Finnish / Suomi            |
|43|Bangka Malay           |
|44|Georgian / ქართული         |
|45|German / Deutsch           |
|46|Greek / Ελληνικά           |
|47|Batak Toba         |
|48|Hungarian / Magyar|
|49|Buginese|
|50|Italian / Italiano|
|51|Kazakh / Қазақ|
|52|Gayo|
|53|Gorontalo|
|54|Hawu|
|55|Latvian / Latviešu|
|56|Jambi Malay|
|57|Lithuanian / Lietuvių|
|58|Komering|
|59|Macedonian / Mакедонски|
|60|Lampung api|
|61|Malay / Bahasa Melayu|
|62|Makassar Malay|
|63|Mandar|
|64|Manggarai|
|65|Mongondow|
|66|Muna|
|67|Nepali / नेपाली|
|68|Ngaju Dayak|
|69|Norwegian / Norsk|
|70|Persian / فارسی|
|71|Polish / Polski|
|72|Portuguese (BR) / Português|
|73|Portuguese (PT)/ Português|
|74|Sinhala/සිංහල|
|75|Spanish / Español|
|76|Spanish / Latinoamérica|
|77|Sasak|
|78|Tamil/தமிழ் (Srilanka)|
|79|Tetum|
