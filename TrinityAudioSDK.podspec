Pod::Spec.new do |s|
s.platform = :ios
s.ios.deployment_target = '14.0'
s.name = 'TrinityAudioSDK'
s.version = '3.2.118'
s.summary = 'Trinity Audio SDKs'
s.description  = 'Using the Trinity Audio service you can turn your readers into listeners by turning text into lifelike speech. Make your content accessible via audio with a quick and seamless integration. Choose from dozens of voices across a variety of languages.'
s.requires_arc = true
s.homepage = 'https://github.com/TrinityAudioSDK/trinityaudio-ios-sdk'
s.license = { :type => 'MIT' }
s.authors = { 'Trinity Audio' => 'devservices@trinityaudio.ai' }
s.source = { :git => 'https://github.com/TrinityAudioSDK/trinityaudio-ios-sdk.git', :tag => s.version.to_s}
s.frameworks = 'AdSupport', 'UIKit', 'WebKit'
s.ios.vendored_frameworks = 'TrinityPlayer.xcframework'
end
