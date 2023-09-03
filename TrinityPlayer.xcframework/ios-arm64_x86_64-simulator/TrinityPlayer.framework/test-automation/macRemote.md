1. Install Xcode
2. Install brew
3. Install nodejs
4. 

```bash
brew install carthage
npm install -g appium
appium driver install xcuitest
npm install -g appium-doctor

appium-doctor

cd ~/Downloads
curl https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.zip > apache-maven-3.9.4-bin.zip
unzip apache-maven-3.9.4-bin.zip
```

Add to `~/.zshrc`:

```
export M2_HOME=~/Downloads/apache-maven-3.9.4
export PATH=$PATH:$M2_HOME/bin
```

### Manually test
```bash
zsh

# build app
cd iOS
xcodebuild -project TrinityPlayer.xcodeproj -target TrinityPlayerDemoApp -configuration Debug -sdk iphonesimulator -destination 'platform=iOS Simulator,name=iPhone 8' build

# run tests
cd test-automation
appium # in separate console tab
mvn clean verify # in separate console tab
```

### Inspect XPaths

1. Install [Appium Inspector](https://github.com/appium/appium-inspector/releases)
2. Attach to existing session or start a new one, using the following capabilities
```json
{
  "platformName": "iOS",
  "appium:deviceName": "iPhone 14",
  "appium:platformVersion": "16.2",
  "appium:app": "/Users/alex/Library/Developer/Xcode/DerivedData/TrinityPlayer-bqltdpwknwqwnvdynjovdejzgvqw/Build/Products/Debug-iphonesimulator/TrinityPlayerDemoApp.app",
  "appium:automationName": "XCUITest"
}
```
