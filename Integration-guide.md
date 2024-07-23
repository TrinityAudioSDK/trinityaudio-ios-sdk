## Trinity Audio iOS SDK Integration Guide


This document describes how to integrate the Trinity Audio Player into
an iOS app as well as how to configure and control it.

-   Updated: Nov 28, 2023
-   Document version: 2.0

### Integration

* * * * *

In order to integrate a player into an app, first import the SDK. \
You can choose between:

-   Via [Swift Package Manager
     ](https://swift.org/package-manager/)
-   Via Cocoapods -
    [https://cocoapods.org/pods/TrinityAudioSDK](https://cocoapods.org/pods/TrinityAudioSDK) 

#### Swift Package Manager

1.   In Xcode go to: `File -> Swift Packages -> Add Package
    Dependency…`
2.  Enter the TrinityAudio SDK GitHub repository -
    [https://github.com/TrinityAudioSDK/trinityaudio-ios-sdk](https://github.com/TrinityAudioSDK/trinityaudio-ios-sdk)
3.  Select the latest SDK version in the `Choose package options`
    view
4.  Select `TrinityPlayer` to be added as a library in the `Add
    packages` view

That’s it! You can now Import the library and use the SDK according
to[ these steps](#usage-and-main-objects)

* * * * *

#### CocoaPods

The TrinityAudio sdk is available via cocoapods at:
[https://cocoapods.org/pods/TrinityAudioSDK](https://cocoapods.org/pods/TrinityAudioSDK) 

For installing via CocoaPods dependency manager add
``` 
target 'MyApp' do

  pod 'TrinityAudioSDK', '~> 1.0'

end
```

To your project podfile. 
After running `pod install` at your project directory with Podfile, the
trinity audio SDK will be available at your project. 


<div style="page-break-after: always;"></div>

## Usage and main objects

The SDK supports both UIKit & SwiftUI frameworks. See the following demo apps for a reference on SDK integration

- [UIKit demo app](https://github.com/TrinityAudioSDK/trinityaudio-ios-demo) 
- [SwiftUI demo app](https://github.com/TrinityAudioSDK/trinityaudio-ios-swiftui-demo) 

### UIKit

#### SDK initialization

* * * * *

Import the library

`Import TrinityPlayer`

To start using the SDK - first initialize the global \`TrinityAudio\`
Object.\
Create a new instance with:  `let myPlayer = TrinityAudio.newInstance()` to initialize
the library. The result is an object that implements the
 `TrinityAudioProtocol`

See [here](#id.oqdhd1eiodax) on configuring a delegate property. The delegate can be used to listen on events emitted by the player.

* * * * *


#### Rendering the TTS player

* * * * *

To render the player, call the `render` method of the TrinityAudio class.        

```swift
  func render(parentViewController: UIViewController, 
                        unitId: String,\                                  
                        sourceView: UIView, \                             
                        fabViewTopLeftCoordinates:  CGPoint?, \           
                        contentURL: URL, \                                
                        settings: [String : String]?)                     
```

| parameter                 | description                                                                                                        |
| ------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| parentViewController      | View controller in which the player will be rendered                                                               |
| unitId                    | Your player unit identifier - will be provided by TrinityAudio team                                                |
| sourceView                | Content view in which the player will be displayed                                                                 |
| fabViewTopLeftCoordinates | Top left corner of the FAB (Floating Action Button) unit. Optional value. Pass nil - to disable FAB functionality. |
| contentURL                | URL which contains the content.                                                                                    |
| settings                  | Dictionary with optional* player settings                                                                          |

Common Player Settings:

| name     | description                                                                               |
| -------- | ----------------------------------------------------------------------------------------- |
| language | Language of the content provided. Use this incase it differs from the language configured |
| voiceId  | Overrides the player level configuration for voice ID                                     |

For the full list of available params look in our player setting doc
[Here](https://trinity-audio-player.s3.amazonaws.com/TTS.pdf) under
`Script Tag Parameters` section.

* Dark mode support - the SDK will attempt to identify whether the containing app is running in dark-mode, in which case it will change the player theme to a dark-mode theme if applicable

### SwiftUI

#### SDK initialization

* * * * *

Import the library

`Import TrinityPlayer`

To start using the SDK - first initialize the audio controller `TrinityAudioController`
Object.
Create a new instance with the `init` method to initialize the library. The result is an object that implements the `TrinityAudioProtocol`

```swift
public class TrinityAudioController : TrinityAudioProtocol {
    public init(unitId: String, 
                contentURL: String, 
                settings: [String : String]? = nil, 
                delegate: TrinityPlayer.TrinityAudioDelegate? = nil)
}
```

| parameter  | description                                                         |
| ---------- | ------------------------------------------------------------------- |
| unitId     | Your player unit identifier - will be provided by TrinityAudio team |
| contentURL | URL which contains the content.                                     |
| settings   | Dictionary with optional* player settings                           |
| delegate   | TrinityAudioDelegate                                                |

```swift
// example
var trinityAudioController = TrinityAudioController(unitId: [YOUR_UNIT_ID], contentURL:[URL_TO_READ_TEXT_FROM])
```

* * * * *

#### Rendering the TTS player

* * * * *

To render the player, create an `TrinityAudioPlayer` view and insert to layout hierarchy
```swift
public struct TrinityAudioPlayer : View {
    public init(audioController: TrinityPlayer.TrinityAudioController)
}
```

| parameter       | description            |
| --------------- | ---------------------- |
| audioController | TrinityAudioController |


* Dark mode support - the SDK will attempt to identify whether the containing app is running in dark-mode, in which case it will change the player theme to a dark-mode theme if applicable

#### Floating button with SwiftUI

* * * * *
To support FAB (Floating Action Button) when placing the player in a scroll view - please follow these requirements
1. Observe the view using the `TrinityAudioController` created in the initial `init` method by calling `trinityObserveScrollViewContentSize` 

```swift
public func trinityObserveScrollViewContentSize(controller: TrinityPlayer.TrinityAudioController) -> some View
```

| parameter       | description            |
| --------------- | ---------------------- |
| audioController | TrinityAudioController |

2. Create the FAB element within the view you'd like to by calling the `trinityFAB` method. Use  `fabViewTopLeftCoordinates` to position the FAB within the view
```swift
public func trinityFAB(controller: TrinityPlayer.TrinityAudioController, fabViewTopLeftCoordinates: CGPoint?) -> some View
```

| parameter                 | description                                                                                                        |
| ------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| controller                | TrinityAudioController                                                                                             |
| fabViewTopLeftCoordinates | Top left corner of the FAB (Floating Action Button) unit. Optional value. Pass nil - to disable FAB functionality. |

```
// example
rootView.trinityFAB(
    controller: trinityAudioController,
    fabViewTopLeftCoordinates: CGPoint(x: 20, y: 80)
)
```

### GDPR & US privacy support
GDPR & US privacy consent string can be directly passed to the player as part of the `settings` dictionary.  
These values are not mandatory, and in the case of their absence Trinity will look for these values in the IAB standard location as detailed [here](https://github.com/InteractiveAdvertisingBureau/GDPR-Transparency-and-Consent-Framework/blob/master/Mobile%20In-App%20Consent%20APIs%20v1.0%20Final.md#cmp-internal-structure-defined-api-)


The supported params are:

| name                               | description                            |
| ---------------------------------- | -------------------------------------- |
| TrinityParams.USPrivacy.rawValue   | US Privacy consent string, e.g. 1-Y-   |
| TrinityParams.GDPR.rawValue        | GDPR version 1. 1 for accepted, 0 - no |
| TrinityParams.GDPRConsent.rawValue | GDPR version 2 consent string          |

For example: 
```swift
  let settings : [String: String] = [
        TrinityParams.USPrivacy.rawValue : "1-Y-",
        TrinityParams.GDPR.rawValue : "0",
        TrinityParams.GDPRConsent.rawValue : "CPSzMzJPTeGtxADABCENB_CoAP_AAEJAAAAADGwBAAGABPADCAY0BjYAgADAAngBhAMaAAA.YAAABBBBB"
    ]
    player.render(parentViewController, unitId, sourceView, fabViewTopLeftCoordinates, contentURL, settings)
```

Note: If you would like to utilize IDFA for ad tracking (for monetized player only) - add a request for IDFA consent into `Info.plist`.   
for example :

```plist
    <key>NSUserTrackingUsageDescription</key>
	<string>This identifier will be used to deliver personalized ads to you.</string>
```
For requesting user authorization to access app-related data for tracking see the detailed [here](https://developer.apple.com/documentation/apptrackingtransparency)

### Player API

To pause the player's audio use the `pause()` method of `TrinityAudioProtocol`. 
For example : 

```swift
    myPlayer.pause()
```
*This method accepts playerID which is only required in multiple player setups.

*The `playerId` can be obtained from the `playerId` property of `TrinityAudioProtocol`. However, it is only available after the `trinity(service: TrinityAudioProtocol, onPlayerReady playerId: String)` method of `TrinityAudioDelegate` is called.

To pause all players, use the `pauseAll()` method of `TrinityAudioProtocol`.
For example : 
```swift
    myPlayer.pauseAll()
```

To resume playback, use the `play()` method of `TrinityAudioProtocol`.
For example : 

```swift
    myPlayer.play()
```

The TTS player supports autoplay if the `autoPlay` property of `TrinityAudioProtocol` is set to `true`. The autoplay properties must be set before calling the `render()` method for autoplay to work.
When enabled, the player will autoplay when it is ready to play.

The trinity player offers multiple other APIs and to interact with it. 
This can be done by invoking the JS player API methods. 
For example - to get the text being read by the player you can invoke: 
```swift
    myPlayer.evaluate(javaScript:  "TRINITY_PLAYER.api.resultReadingText;")
```

For the full reference please check the [JS API docs](https://trinity-audio-player.s3.amazonaws.com/TTS.pdf) under `API` section. 


* * * * *

### TrinityAudioDelegate

* * * * *

You can configure a delegate for the TrinityAudio instance you created.

The delegate is a property of the TrinityAudio instance, conforming to
the delegate protocol TrinityAudioDelegate.

The delegate can be used to subscribe to events emitted by the player. This can allow you to connect your in-app analytics platform to track player usage.

TrinityAudioDelegate methods.

* * * * *

- To receive a callback when the player is ready to play, implement this optional method

```swift
func trinity(service: TrinityAudio.TrinityAudioProtocol, 
             onPlayerReady playerId: String)
```

- For monitoring errors use:

```swift
func trinity(service: TrinityAudio.TrinityAudioProtocol, 
             receiveError: TrinityAudio.TrinityError)
```

TrinityError - an enum with these values:

```swift
 public enum TrinityError : Error {                                       
     case wrongURL                                                        
     case paramsIsEmpty                                                   
     case wrongParams                                                     
     case containerViewNotLoaded                                          
     case somethingWrong(error: Error?)                                   
 }                                                                        
```

- For detecting changes of the content height in differents states use:

```swift 
func trinity(service: TrinityAudio.TrinityAudioProtocol,   
             detectUpdateForContentHeight height: Float, 
             inState state: TrinityAudio.TrinityState)                                              
```

TrinityState - an enum with two cases :

```swift 
 enum TrinityState {                                                                                                                                
     case main                                                            
     case fab                                                             
 }                                                                        
```

- For tracking player events: 
```swift 
  func trinity(service: TrinityAudio.TrinityAudioProtocol,                
               didReceivePostMessage: [String: Any])                                    
```

This tracks all `postmessages` that are triggered by the player - similar to the web integration.
Event structure is :  
```javascript
{
  type: 'TRINITY_TTS',
  value: {
    action: String,
    message: Object|String|undefined,
    playerId: String
  }
}
```
For a list of events please see [Here](https://trinity-audio-player.s3.amazonaws.com/TTS.pdf) Under `Events` section. 


