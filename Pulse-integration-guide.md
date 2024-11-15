## Pulse Player Developer Guide


This document describes how to integrate Pulse Player into a
an iOS app as well as how to configure and control it.

-   Updated: Nov 14, 2024
-   Document version: 2.0

### Integration

* * * * *

In order to integrate a player into an app, first import the SDK.
Please follow the instructions [here](https://github.com/TrinityAudioSDK/trinityaudio-ios-sdk/blob/main/Integration-guide.md#integration) 

## Usage and Main Objects

The Pulse unit only supports UIKit. See the following demo apps for a reference on SDK integration

- [UIKit demo app](https://github.com/TrinityAudioSDK/trinityaudio-ios-demo) 

### UIKit

#### Initialization

* * * * *

Import the library

`Import TrinityPlayer`

To start using the pulse unit - first initialize the global `TrinityAudioPulse`
object.
Create a new instance with:  `let myPlayer = TrinityAudioPulse.newInstance()` to initialize
the unit. The result is an object that implements the
 `TrinityAudioPulseProtocol`

See [here](#trinityaudiopulsedelegate) on configuring a delegate property. The delegate can be used to listen on events emitted by the pulse player.

* * * * *

#### Creating the Player View

The pulse player will display the content using the instance of `TrinityPlayerView`.
You can create it programmatically like a normal UIView or add it to a XIB or storyboard file.

#### Rendering the Pulse player

* * * * *

To render the player, invoke the `render` method of the TrinityAudioPulseProtocol protocol.        

```swift
  func render(unitId: String,
                rootView: UIView,
                playerView: TrinityPlayerView,
                playlistURL: URL,
                settings: [String : String]?)                    
```

| parameter     | description                                                                                                        |
| ------------- | ------------------------------------------------------------------------------------------------------------------ |
| unitId        | Your player unit identifier - will be provided by TrinityAudio team                                                |
| rootView      | It should be the `playerView`'s superview, such as the ViewController's view.
| playerView    | The `TrinityPlayerView` that will display the content                                                              |
| playlistURL   | URL which contains the playlist content                                                                            |
| settings      | Dictionary with optional* player settings                                                                          |


**Common Player Settings:**

For the full list of available params look in our player setting doc
[Here](https://github.com/TrinityAudioSDK/trinityaudio-web-sdk/blob/main/Pulse.md#tag-parameters) under
`Script Tag Parameters` section.

#### Pulse Player Sliding Effect for Specific Unit IDs

For units that implement the sliding effect for some unit IDs provided by the Trinity Audio Team. 


The pulse unit should open in a specific location with only part of it visible, such as the top navigation.

The SDK does not include built-in transitions for the player, allowing for customization of the sliding animation. Please listen to the event from the callback function `trinityOnBrowseMode` of the `TrinityAudioPulseDelegate` to collect the `expectedHeight` value.

#### Minimum Sizes for the Pulse Player

*Default Sizes:*
- **Width:** Min = 300 points (no maximum)  
- **Height:** Min & Max = 350 points  

*Pulse-Sliding Minimum Sizes:*  
- **Width:** Min = 300 points (no maximum)  
- **Height:** Min = 145 points, Max = 332 points  

#### Autoplay

The player supports autoplay if the `autoPlay` property of `TrinityAudioPulseProtocol` is set to `true`. The autoplay property *must* be set before calling the `render()` method for autoplay to work.
When enabled, the player will play the audio once ready, without waiting for user interaction. 

```swift
myPlayer.autoPlay = true;
```

### GDPR & US privacy support
For details on GDPR US privacy support please go [here](https://github.com/TrinityAudioSDK/trinityaudio-ios-sdk/blob/main/Integration-guide.md#gdpr--us-privacy-support)

### Player API

To pause the player's audio use the `pause()` method of `TrinityAudioPulseProtocol`. 
For example : 

```swift
    myPlayer.pause(playerID: nil)
```
*This method accepts a playerID, but for the Pulse player, you can pass `nil` if you do not keep track of the `playerId` and use a single player.

*The `playerId` can be obtained from the `playerId` property of `TrinityAudioPulseProtocol`. However, it is only available after the `trinity(service: TrinityAudioPulseProtocol, onPlayerReady playerId: String)` method of `TrinityAudioPulseDelegate` is called.

To pause all players, use the `pauseAll()` method of `TrinityAudioPulseProtocol`.
For example : 
```swift
    myPlayer.pauseAll()
```

To resume playback, use the `play()` method of `TrinityAudioPulseProtocol`.
For example : 

```swift
    myPlayer.play()
```

The trinity player offers multiple other APIs and to interact with it. 
This can be done by invoking the JS player API methods. 
For example - to get the text being read by the player you can invoke: 
```swift
    myPlayer.evaluate(javaScript:  "TRINITY_PULSE.api.setVolume(0.5);")
```

For the full reference please check the [JS API docs](https://github.com/TrinityAudioSDK/trinityaudio-web-sdk/blob/main/Pulse.md#api).

If you would like to remove the player - make sure to call the `invalidate` method to release all resources 

```swift
    myPlayer.invalidate()
```

* * * * *

### TrinityAudioPulseDelegate

* * * * *

You can configure a delegate for the TrinityAudioPulse instance you created.

The delegate is a property of the TrinityAudioPulse instance, conforming to
the delegate protocol `TrinityAudioPulseDelegate`.

The delegate can be used to subscribe to events emitted by the player. This can allow you to connect your in-app analytics platform to track player usage.

TrinityAudioPulseDelegate methods.

* * * * *

- To receive a callback when the player is ready to play, implement this optional method

```swift
func trinity(service: TrinityAudioPulseProtocol, 
             onPlayerReady playerId: String)
```

- For monitoring errors use:

```swift
func trinity(service: TrinityAudioPulseProtocol, 
             receiveError: TrinityError)
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

- To detect changes in the player mode (sliding units only) and update the `trinityPlayer` frame with or without animation using the `expectedHeight` value.

```swift 
func trinity(service: TrinityAudioPulseProtocol,   
             onBrowseMode toggled: Bool, expectedHeight: CGFloat)                                              
```

- For tracking player events: 
```swift 
  func trinity(service: TrinityAudioPulseProtocol,                
               didReceivePostMessage: [String: Any])                                    
```

This tracks all `postMessage` events that are triggered by the player - similar to the web integration.
Event structure is :  
```javascript
{
  type: 'TRINITY_PULSE',
  value: {
    action: String,
    message: Object|String|undefined,
    playerId: String
  }
}
```
For a list of events please see [Here](https://github.com/TrinityAudioSDK/trinityaudio-web-sdk/blob/main/Pulse.md#events).


