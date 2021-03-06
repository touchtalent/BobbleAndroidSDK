
# Bobble Contextual Emoji Suggestion SDK

Bobble Contextual Emoji Suggestion SDK is a tool that can be integrated into any Android app to contextually detect emotions contained within a text message and suggest emojis based on it. Currently, [68 languages](#supported_languages) are supported.

## <a name="implementation_steps"></a>Setting Up

- Add and initialize BobbleSDK Core in your project. Refer [here](Readme.md#setup) for steps.

- Add the following dependency in your application moduleโs build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:contextual-emojis'
```
Sync your Gradle project to ensure that the dependency is downloaded by the build system.


## <a name="apis"></a>Bobble Contextual Emoji APIs

### BobbleEmojiSuggestions

1. Initiate a object of ```BobbleEmojiSuggestions```.

```java
BobbleEmojiSuggestions emojiSuggestions = new BobbleEmojiSuggestions();
emojiSuggestions.setLanguage("hi") // Optional, for targetting a particular language. When this is not used, language would be automatically detected.
```

2. Get emoji suggestions by calling getEmojis()

```java
List<String> emojis = emojiSuggestions.getEmojis("gussa mat dila", new EmojiSuggestionsCallback(){
    public void onResult(List<String> emojis){
        //emojis = [๐ค,๐ฟ,๐ก,๐,๐ ]
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
|2| Hindi / เคนเคฟเคจเฅเคฆเฅ            |
|2| Bangla / เฆฌเฆพเฆเฆฒเฆพ               |
|3| Marathi / เคฎเคฐเคพเค เฅ              |
|4| Telugu / เฐคเฑเฐฒเฑเฐเฑ                |
|5| Tamil / เฎคเฎฎเฎฟเฎดเฏ             |
|6| Gujarati / เชเซเชเชฐเชพเชคเซ       |
|7| ุงุฑุฏู / Urdu    |
|8| Kannada / เฒเฒจเณเฒจเฒก              |
|9| Odia / เฌเฌกเฌผเฌฟเฌ                 |
|10| Malayalam / เดฎเดฒเดฏเดพเดณเด             |
|11| Punjabi / เจชเฉฐเจเจพเจฌเฉ            |
|12| Assamese / เฆเฆธเฆฎเงเฆฏเฆผเฆพ       |
|13| Acehnese               |
|14| Balinese              |
|15| Banjarese        |
|16| ุงูุนูุฑูุจููููุฉ / Arabic     |
|17| Armenian / ีฐีกีตีฅึีงีถ              |
|18| Azerbaijani / Azษrbaycan    |
|19| Bahasa Indonesia                 |
|20| Javanese    |
|21| Madurese      |
|22|Batak Karo             
|23|Minangkabau                    |       
|24|Sundanese                  |
|25|Catalan / Catalร            |
|26|Croatian / Hrvatski        |
|27|Danish / Dรกnskรฝ        |
|28|Banggai                |
|29|Dutch / Nederlands         |
|30|Estonian / Eesti           |
|31|Finnish / Suomi            |
|32|Bangka Malay           |
|33|Georgian / แฅแแ แแฃแแ         |
|34|German / Deutsch           |
|35|Greek / ฮฮปฮปฮทฮฝฮนฮบฮฌ           |
|36|Batak Toba         |
|37|Hungarian / Magyar|
|38|Buginese|
|39|Italian / Italiano|
|40|Kazakh / าะฐะทะฐา|
|41|Gayo|
|42|Gorontalo|
|43|Hawu|
|44|Latvian / Latvieลกu|
|45|Jambi Malay|
|46|Lithuanian / Lietuviลณ|
|47|Komering|
|48|Macedonian / Mะฐะบะตะดะพะฝัะบะธ|
|49|Lampung api|
|50|Malay / Bahasa Melayu|
|51|Makassar Malay|
|52|Mandar|
|53|Manggarai|
|54|Mongondow|
|55|Muna|
|56|Nepali / เคจเฅเคชเคพเคฒเฅ|
|57|Ngaju Dayak|
|58|Norwegian / Norsk|
|59|Persian / ูุงุฑุณ?|
|60|Polish / Polski|
|61|Portuguese (BR) / Portuguรชs|
|62|Portuguese (PT)/ Portuguรชs|
|63|Sinhala/เทเทเถเทเถฝ|
|64|Spanish / Espaรฑol|
|65|Spanish / Latinoamรฉrica|
|66|Sasak|
|67|Tamil/เฎคเฎฎเฎฟเฎดเฏ (Srilanka)|
|68|Tetum|
