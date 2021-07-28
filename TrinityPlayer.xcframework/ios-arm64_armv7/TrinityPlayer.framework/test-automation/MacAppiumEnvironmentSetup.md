Appium + iOS instalation
https://medium.com/2359media/tutorial-automated-testing-on-ios-with-appium-test-ng-and-java-on-mac-bc115d0ec881

1. Install XCode
Once XCODE installed, Launch Xcode and select Xcode > Preferences > Components to install the simulators that you might want to test against.


2. brew.sh ->
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
 copy url to terminal and execute


3. Carthage is a dependency manager. In our case, it is required by WebDriverAgent.

brew install carthage

4. Node and npm install

check if installed: 
node -v
npm -v

Install commands:
brew install node (This install npm as well)
where node (check path)

5. Install Appium
npm install -g appium
appium -v
where appium

OR install from appium.io

6. Install appium-doctor to check instalation

npm install -g appium-doctor

install all required apps if needed;


7. Install authorize-ios
authorize-ios is a little utility that pre-authorizes Instruments to run UIAutomation scripts against iOS devices. You need this utility to run tests on real devices
In terminal, enter the following:

		npm install -g authorize-ios

8. Install ios-deploy
ios-deploy is a small utility to install and debug iPhone apps from the command line, without using Xcode.
In terminal, enter the following:

		brew install ios-deploy

9. Install ideviceinstaller
ideviceinstaller is a tool to interact with the installation_proxy
of an iOS device allowing to install, upgrade, uninstall, archive, restore
and enumerate installed or archived apps. You also need this utility to run tests on real devices.

brew install ideviceinstaller

**If you are macOS High Sierra or Mojave you may encounter an error involving “invalid active developer path” in which case run the following command in terminal:
xcode-select --install
sudo xcode-select -r
Then try install ideviceinstaller one more time.
Install ios_webkit_debug_proxy
Appium uses this tool to access web views on real iOS devices. In terminal, run the following command
brew install ios-webkit-debug-proxy

JAVA and MAVEN instalation
https://dev.to/jeannienguyen/how-to-install-java-jdk-and-maven-on-mac-os-168f

 Java Instalation:
1. Open a new terminal and run java -version. If JDK is installed - skip java instalation.
If you don't have a JDK installed, you can download it:
https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html#license-lightbox
(Oracle account required)
2. You can check the installed Java path by going to your Mac's Settings > Java > Java (within the Java Control Panel) > Path.
3. In your terminal, if you run java -version again now, it should return details of the installed JDK.
4. Next, you'll need to add the $JAVA_HOME variable in your .bash_profile (if you have a .bash_profile, you can skip the next step). 
   If you run echo $JAVA_HOME and it returns blank, it means you haven't set the variable yet.
5. If you don't have a .bash_profile, go ahead and create one with touch .bash_profile.
6. Open your .bash_profile by running open -e .bash_profile.
7. Add export JAVA_HOME=$(/usr/libexec/java_home) to the file and save it.


Maven Instalation:
1. download maven
https://www2.apache.paket.ua/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip
Once it's downloaded, move it into your Applications folder and unzip it (unzip apache-maven-3.6.3-bin.zip).
Open your .bash_profile again and add these two variables. Version number will vary based on when you're reading this. The latest version as of 08/26/20 is 3.6.3.

export M2_HOME=/Applications/apache-maven-3.6.3
export PATH=$PATH:$M2_HOME/bin
If you're using the same terminal, go ahead and clear it with CMD + K. Run source .bash_profile to refresh it and then run mvn -version.

If successful, mvn -version will return info on what was just installed.

Install Intellij IDEA ("Community" is free)
https://www.jetbrains.com/idea/download/#section=mac

Download project from git and open test in IDE.
