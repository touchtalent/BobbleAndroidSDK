## Bobble IME SDK
Bobble IME SDK is a fully functional set of keyboard which can be integrated in any keyboard. The keyboard includes 
- Global typing support
- Smooth typing experience
- Content sharing
- Emojis and BigMojis
- Keyboard themes
- PopText 
and many more exciting features.

## Methods to Integrate
To make it tailored for every application's specific needs, you can integrate Bobble IME SDK in your application in two ways. 

### 1. Integrating the SDK via Dynamic module delivery
* Dynamic module distribution allows end-users to download the SDK as an add-on feature, which downloads the complete SDK on runtime instead of install-time.
* It results in minimal APK download size increase ~200KB.
* This method is applicable only if the distribution format of your app is App Bundle. This distribution mode doesn't work well with APK as distribution format.

Please refer [here](readme_keyboard_dynamic.md) to know more about Bobble IME SDK via *Dynamic module*.


### 2. Integrating the SDK traditionally within main module

* The SDK is bundled with the app, and is downloaded at install time.
* SDK size is ~6MB which would add to the download size on Google Play Store.
 
Please refer [here](readme_keyboard_traditional.md) to know more about Bobble IME SDK via *Traditional method*.

 
