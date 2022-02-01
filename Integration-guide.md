## Trinity Audio iOS SDK Integration Guide


This document describes how to integrate the Trinity Audio Player into
an iOS app as well as how to configure and control it.

-   Updated: Sep  14, 2021
-   Document version: 0.1

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
4.  Select `TrinityAudioSDK` to be added as a library in the `Add
    packages` view

That’s it! You can now Import the library and use the SDK according
to[ these steps](#id.811shq5pgetr)

* * * * *

#### CocoaPods

The TrinityAudio sdk is available via cocoapods at:
[https://cocoapods.org/pods/TrinityAudioSDK](https://cocoapods.org/pods/TrinityAudioSDK) 

For installing via CocoaPods dependency manager add
``` 
target 'MyApp' do

  pod 'TrinityAudioSDK', '~> 0.1'

end
```

To your project podfile. 
After running `pod install` at your project directory with Podfile, the
trinity audio SDK will be available at your project. 


<div style="page-break-after: always;"></div>

## Usage and main objects


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

parameter | description
--------- | ------
parentViewController                 | View controller in which the player will be rendered 
unitId                               | Your player unit identifier - will be provided by TrinityAudio team  
sourceView                           | Content view in which the player will be displayed 
fabViewTopLeftCoordinates            | Top left corner of the FAB (Floating Action Button) unit. Optional value. Pass nil - to disable FAB functionality.           
contentURL                           | URL which contains the content.            
settings                             | Dictionary with optional* player settings 

Common Player Settings:

 name | description
--------- | ------
language                             | Language of the content provided. Use this incase it differs from the language configured 
voiceId                              | Overrides the unit level configuration for voice ID           

For the full list of available params look in our player setting doc
[Here](https://trinity-audio-player.s3.amazonaws.com/TTS.pdf) under
`Script Tag Parameters` section.


#### Player API

To pause the player's audio use the `pause()` method of `TrinityAudioProtocol`. 
For example : 

```swift
    myPlayer.pause()
```
*this method accepts playerID which is only required in multiple player setups.


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


