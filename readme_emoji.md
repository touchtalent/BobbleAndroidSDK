
# Bobble Contextual Emoji Suggestion SDK

Bobble Contextual Emoji Suggestion SDK is a tool that can be integrated into any Android app to contextually detect emotions contained within a text message and suggest emojis based on it.

## <a name="implementation_steps"></a>Setting Up

- Add and initialize BobbleSDK Core in your project. Refer [here](readme_core.md) for steps.

- Add the following dependency in your application module’s build.gradle.
```groovy
implementation 'com.touchtalent.bobblesdk:contextual-emojis'
```

## <a name="apis"></a>Bobble Contextual Emoji APIs

### BobbleEmojiSuggestions

1. Initiate a object of ```BobbleEmojiSuggestions```.

```java
BobbleEmojiSuggestions emojiSuggestions = new BobbleEmojiSuggestions();
emojiSuggestions.setLanguage("hi") // Optional, for targetting a particular language.
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