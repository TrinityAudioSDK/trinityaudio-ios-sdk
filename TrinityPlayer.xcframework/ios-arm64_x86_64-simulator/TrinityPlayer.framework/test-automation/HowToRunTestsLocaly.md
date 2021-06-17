1. Run Appium server via terminal: 
appium

2. Open project in IDE and make sure that there is no red text in pom.xml (If red is still present - Right click -> Maven -> Reimport)

3. Uncomment io.qameta.allure plugin in pom.xml 

4. In src\test\java\com\thetimmedia\runner\RunnerLocalTest.java
   - uncomment @RunWith and @CucumberOptions
   - put tag to run particular test in CucumberOptions
   - put http://127.0.0.1:4723/wd/hub as server url
   - put app path in "app" capability
   
5. In src\test\java\com\thetimmedia\runner\RunnerKobitonTest.java
   - comment @RunWith and @CucumberOptions to avoid execution on Kobiton server

6. Open folder with project in terminal and enter
	mvn clean verify

7. After tests execution, Allure report page will open
  Press Ctrl + C to terminate Allure reporing server.