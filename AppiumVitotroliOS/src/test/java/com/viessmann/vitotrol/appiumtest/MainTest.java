/** 
 * 
 */ 
package test.java.com.viessmann.vitotrol.appiumtest; 

import java.util.List; 
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert; 
import org.testng.Reporter; 
import org.testng.annotations.AfterSuite; 
import org.testng.annotations.BeforeSuite; 
import org.testng.annotations.Test; 

import test.java.com.viessmann.vitotrol.appiumtest.SetUp; 


public class MainTest extends SetUp{ 
        public WebElement showcaseBtn; /* Schowcase button */ 
        public WebElement helpBtn; /*Help button*/ 
        public WebElement copyright; /* Copyright text on splash screen*/ 
        public WebElement firstInstallation; /* First installation on list*/ 
        public WebElement secoundInstallation; /* 2nd installation*/ 
        public WebElement installationListLabel; 
        public WebElement installationListText; 
        public WebElement menuBtn; 
        public WebElement settingsBtn; /* Settings button */ 
        public WebElement rightArrowBtn; /* Right arrow Home screen button */ 
        public WebElement leftArrowBtn; /* Left arrow Home screen button */ 
        public WebElement homeHelpBtn; /* Help on Home screen */ 
        public WebElement operationModeBtn; /* Home screen operation button */ 
        public int x = 20; /* Loop count for Multiple switch Splash<->Installation List */ 
        public int y = 20; /* Loop count for Multiple switch Home<->Menu */ 
        public WebElement hc1Name; 
        public WebElement hc2Name; 
        public WebElement roomTempPlusBtn; 
        public WebElement roomTempMinusBtn; 
        public WebElement refreshBtn; 
        public WebElement lastUpdateLabel;
		private List<WebElement> buttons; 

        
        
        @BeforeSuite 
        public void setUp () throws Exception { 
                main(); 
        } 


        @Test(priority = 1) 
        public void splashHelpPresentCheck() throws Exception { 
                Assert.assertTrue(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")).isDisplayed()); 
                helpBtn = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")); /*Finding Help button*/ 
                Assert.assertTrue(helpBtn.isDisplayed(), "No help button"); /* Checking if help button is present on splash screen */ 
                Assert.assertTrue(helpBtn.isEnabled(), "Help button not enabled"); 
        } 
        @Test(priority = 1)
        public void logInBUtton() throws Exception {
        	buttons = wd.findElements(By.className("UIAButton"));
        	
        	for (int b = 0 ; b < buttons.size(); b++){
    			
    			Reporter.log(buttons.get(b).getText() + "<p>");
    		}
        }
        
        @Test(priority = 2) 
        public void CopyrightText() throws Exception { 
                Assert.assertTrue(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")).isDisplayed()); 
                copyright = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")); /* Location of Copyright text */ 
                Assert.assertTrue(copyright.isDisplayed(), "Text is not displayed"); 
                takeScreenshot("screen"); 
                Reporter.log(copyright.getText()); 
        } 


        @Test(priority = 3) 
        public void splashButtonHelpActionCheck() throws Exception { /*Checking if Splash screen Help button can be clicked*/ 
                helpBtn.click();        /* Click on help button on splash screen */ 

                Assert.assertTrue(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]")).isDisplayed(),"Help content not displayed"); 
                Thread.sleep(1000); 
                
        } 
        
        @Test(priority = 4) 
        public void splashButtonHelpContentCheck() throws Exception { 
                
                List<WebElement> elements = wd.findElements(By.className("UIAStaticText")); 
                int i = 0; 
                // Searching for Help Text 
                while (i < elements.size()){ 
                        Reporter.log("Value: " + elements.get(i).getText() + "<p>" + "Name: " + elements.get(i).getAttribute("name") + "<p>"); // Logging names of elements 
                        i++; 
                } 
                wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")).click(); // Finish button 
                Assert.assertTrue((elements.size() != 0), "No help content"); 
        } 

        @Test(priority = 5) 
        public void splashShowcaseButtonCheck() throws Exception { 
                showcaseBtn = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]"));                // finding Showcase button 
                
                Assert.assertTrue(showcaseBtn.isDisplayed(), "Button  is not displayed");  /*Checking if  button is displayed*/ 
                Assert.assertTrue(showcaseBtn.isEnabled(), "Button not enabled"); 

        } 

        @Test(priority = 6) 
        public void LogIn() throws Exception {  /*Checks if  button can be clicked*/ 
                
                buttons.get(1).click();          /*Button click*/ 
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("UIASecureTextField")));
                
                Assert.assertTrue(wd.findElement(By.className("UIASecureTextField")).isDisplayed(),"No login page");          /*Checking if list of installation is displayed*/ 
        } 
        

        @Test(priority = 7) 
        public void LogInMultipleBack() throws Exception { 
                do { 
                        wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click(); 
                        Thread.sleep(500); 
                        buttons.get(1).click();          //Button click 
                        x--; 
                        Thread.sleep(500); 
                        Assert.assertTrue(wd.findElement(By.className("UIASecureTextField")).isDisplayed(),"List of installation is not displayed");         // Checking if list of installation is displayed 
                } while (x > 0);         
        } 
        @Test(priority = 8) 
        public void ShowcaseInstallationList() throws Exception {  /*Checks if  button can be clicked*/ 
                
                buttons.get(2).click();          /*Button click*/ 
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("UIATableView")));
                
                Assert.assertTrue(wd.findElement(By.className("UIATableView")).isDisplayed(),"No login page");          /*Checking if list of installation is displayed*/ 
        } 
        

        @Test(priority = 9) 
        public void InstallationListMultipleBack() throws Exception { 
                do { 
                        wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click(); 
                        Thread.sleep(500); 
                        buttons.get(2).click();          //Button click 
                        x--; 
                        Thread.sleep(500); 
                        Assert.assertTrue(wd.findElement(By.className("UIATableView")).isDisplayed(),"List of installation is not displayed");         // Checking if list of installation is displayed 
                } while (x > 0);        
                wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click(); 
        } 
        

        @Test(priority = 10) 
        public void LoginToIntallationWrongPass() throws Exception { 

                buttons.get(1);
                Thread.sleep(1000); 
                wd.findElement(By.className("UIATextField")).sendKeys("Hans@Muster.de");
                wd.findElement(By.className("UIASecureTextField")).sendKeys("badpassword");
                wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[1]")).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[3]")));
                Assert.assertEquals(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAButton[1]")).getText(), "Cancel");
                Thread.sleep(2000);
                Assert.assertEquals(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAStaticText[2]")).getText(), "Your email address or password is wrong");
                wd.findElement(By.name("Enter new data")).click();
        } 
        @Test(priority = 11) 
        public void LoginToIntallation() throws Exception { 

                buttons.get(1);
                Thread.sleep(1000); 
                wd.findElement(By.className("UIATextField")).sendKeys("Hans@Muster.de");
                wd.findElement(By.className("UIASecureTextField")).sendKeys("Hans@Muster.de");
                wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[1]")).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[3]")));
                Assert.assertEquals(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAButton[1]")).getText(), "Cancel");
                Thread.sleep(2000);
                Assert.assertEquals(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAStaticText[2]")).getText(), "Your system configuration data is being read.  Please wait…");
        } 
        
        @Test(priority = 12) 
        public void HomeRefreshBtn() throws Exception { 
                refreshBtn = wd.findElement(By.name("online static")); /* Refresh image button location */ 

                Assert.assertTrue(refreshBtn.isDisplayed(), "Button not displayed"); 
                Assert.assertFalse(refreshBtn.isEnabled(), "Button enabled"); /* For first 10 sec. button is inactive */         
        } 
        
        @Test(priority = 12) 
        public void HomeMenuBtn() throws Exception { 
                menuBtn = wd.findElement(By.name("icon menu"));        // Finding Menu btn 
                
                Assert.assertTrue(menuBtn.isDisplayed()); 
                Assert.assertTrue(menuBtn.isEnabled(), "Button not enabled"); 
        } 
        
        @Test(priority = 12) 
        public void HomeHC1Name() throws Exception { 
                hc1Name = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"));        /* Heating circuit name label location */ 
                
                Assert.assertNotNull(hc1Name.getText(), "No Heating Circuit 1 name"); 
        } 

        @Test(priority = 13) 
        public void HomeArrowRightBtn() throws Exception { 
                rightArrowBtn = wd.findElement(By.name("Right arrow")); /* Right arrow image button location */ 
                
                Assert.assertTrue(rightArrowBtn.isDisplayed(), "Button not displayed"); 
                Assert.assertTrue(rightArrowBtn.isEnabled(), "Button not enabled"); 
        } 
        
        @Test(priority = 14) 
        public void HomeOperationModeBtn() throws Exception { 
                operationModeBtn = wd.findElement(By.name("Operation mode")); /* Operation mode image button location */ 
                
                Assert.assertTrue(operationModeBtn.isDisplayed(), "Button not displayed"); 
                Assert.assertTrue(operationModeBtn.isEnabled(), "Button not enabled"); 
        } 
        
        @Test(priority = 15) 
        public void HomeOperationModeBtnAction1stHC() throws Exception { 
                operationModeBtn.click(); 
                
                List<WebElement> elements = wd.findElements(By.className("UIATableCell")); //List of available operation modes 
                int i = 0; 
                String msg = " "; 
        while (i < elements.size()){ 

        if (elements.get(i).getAttribute("value").equals("1")){ 
                msg = msg + "(Curently selected)"; 
                Reporter.log(elements.get(i).getAttribute("name") + msg + "<p>");         // Logs available mode 
        } else { 
                msg = " "; 
                Reporter.log(elements.get(i).getAttribute("name") + msg + "<p>"); 
        } 
        i++; 
        } 
                wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click(); 
        } 
        
        
        @Test(priority = 16) 
        public void HomeRoomTempPlusBtn() throws Exception { 
                roomTempPlusBtn = wd.findElement(By.name("Increase temperature")); /* Room Temperature Plus image button location */ 
                
                Assert.assertTrue(roomTempPlusBtn.isDisplayed(), "Button not displayed"); 
                Assert.assertTrue(roomTempPlusBtn.isEnabled(), "Button not enabled"); 
        } 
        
        @Test(priority = 17) 
        public void HomeRoomTempMinusBtn() throws Exception { 
                roomTempMinusBtn = wd.findElement(By.name("btn minus normal")); /* Room Temperature Minus image button location */ 
                
                Assert.assertTrue(roomTempMinusBtn.isDisplayed(), "Button not displayed"); 
                Assert.assertTrue(roomTempMinusBtn.isEnabled(), "Button not enabled"); 
        } 
        
        
        
        
        @Test(priority = 18) 
        public void HomeHelpBtn() throws Exception { 
                homeHelpBtn = wd.findElement(By.name("icon help")); /* Help image button location */ 
                

                Assert.assertTrue(homeHelpBtn.isDisplayed(), "Button not displayed"); 
                Assert.assertTrue(homeHelpBtn.isEnabled(), "Button not enabled"); 
        } 
        
        @Test(priority = 19) /* Clicks y times Home and than Back */ 
        public void HomeMenuBtnMultipleClick() throws Exception { 
                do { 
                        menuBtn.click(); 
                        menuBtn.click(); 
                        y --; 
                        Assert.assertTrue(menuBtn.isDisplayed()); 
                } while (y > 0); 
                
        } 
        
        @Test(priority = 20) 
        public void HomeHelpCheck() throws Exception { 
                Thread.sleep(2000); 
                homeHelpBtn.click(); 
                Thread.sleep(1000); 
                finishBtn = wd.findElement(By.name("Finished")); 
                
                Assert.assertTrue(finishBtn.isDisplayed(), "No Finished button"); 
                List<WebElement> elements = wd.findElements(By.className("UIAStaticText")); // Locating help texts 
                int i = 0; 
                while (i < elements.size()){ 
                        Reporter.log(elements.get(i).getAttribute("name").toString() + "<p>" ); 
                        i ++; 
                } 
                //MobileScroll("down"); 
                finishBtn.click(); 

        } 
        
        @Test(priority = 21) 
        public void HomeRoomTempPlusBtnClick1stHC() throws Exception { 
                roomTempPlusBtn.click(); 
                
                Thread.sleep(1000); 
                Assert.assertFalse(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAStaticText[1]")).isDisplayed(), "No message about temp change"); // Checking if message is displayed 
                Thread.sleep(5000);         // Waiting 5 sec. for dim disappears 
        } 
        @Test(priority = 22) 
        public void HomeRoomTempMiusBtnClick1stHC() throws Exception { 

                roomTempMinusBtn.click(); 
                Assert.assertFalse(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAStaticText[1]")).isDisplayed(), "No message about temp change"); 
                Thread.sleep(5000); 
        } 
        
        @Test(priority = 23) 
        public void HomeHCArrowSwitch() throws Exception { 
                rightArrowBtn.click();                                                        // Changing HC by clicking arrow button 
                Thread.sleep(1000); 
                hc2Name = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"));        // Locating name of 2nd CH 
                Reporter.log(hc2Name.getText());                                                // Getting name of 2nd HC 
                Assert.assertNotNull(hc2Name.getText(), "No HC name"); 
        } 
        
        @Test(priority = 24)         
        public void HomeOperationModeBtn2ndHC() throws Exception { 
                operationModeBtn = wd.findElement(By.name("Operation mode")); /* Operation mode image button location */ 
                
                Assert.assertTrue(operationModeBtn.isDisplayed(), "Button not displayed"); 
                Assert.assertTrue(operationModeBtn.isEnabled(), "Button not enabled"); 
        } 
        
        @Test(priority = 25) 
        public void HomeArrowLeftBtn() throws Exception { 
                Thread.sleep(1000); 
                leftArrowBtn = wd.findElement(By.name("Left arrow")); /* Left arrow image button location */ 
                
                Assert.assertTrue(leftArrowBtn.isDisplayed(), "Button not displayed"); 
                Assert.assertTrue(leftArrowBtn.isEnabled(), "Button not enabled"); 
        } 
        
        @Test(priority = 26) 
        public void HomeRoomTempPlusBtnClick2ndHC() throws Exception { 
                Reporter.log(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAStaticText[2]")).getText()); 
                roomTempPlusBtn.click(); 
                roomTempPlusBtn.click(); 
                
                Thread.sleep(1000); 
                Assert.assertFalse(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAStaticText[5]")).isDisplayed(), "No message about temp change"); 
                Thread.sleep(5000); 
        } 
        
        @Test(priority = 27) 
        public void HomeRoomTempMiusBtnClick2ndHC() throws Exception { 
                Reporter.log(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAStaticText[2]")).getText()); 
                roomTempMinusBtn.click(); 
                Assert.assertFalse(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAStaticText[5]")).isDisplayed(), "No message about temp change"); 
                Thread.sleep(5000); 
        } 

        @Test(priority = 28) 
        public void HomeOperationModeBtnAction2ndHC() throws Exception { 
                operationModeBtn.click(); 
                
                List<WebElement> elements = wd.findElements(By.className("UIATableCell")); //List of available operation modes 
                int i = 0; 
                String msg = " "; 
        while (i < elements.size()){ 

        if (elements.get(i).getAttribute("value").equals("1")){ 
                msg = msg + "(Curently selected)"; 
                Reporter.log(elements.get(i).getAttribute("name") + msg + "<p>"); 
        } else { 
                msg = " "; 
                Reporter.log(elements.get(i).getAttribute("name") + msg + "<p>"); 
        } 
        i++; 
        } 
                wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click(); //Cancel button 
        } 
        

        @Test(priority = 29) 
        public void HomeHelpcheck2ndHC() throws Exception { 
                Thread.sleep(2000); 
                homeHelpBtn.click(); 
                Thread.sleep(1000); 
                finishBtn = wd.findElement(By.name("Finished")); 
                
                Assert.assertTrue(finishBtn.isDisplayed(), "No Finished button"); 
                List<WebElement> elements = wd.findElements(By.className("UIAStaticText")); // Locating help texts 
                int i = 0; 
                while (i < elements.size()){ 
                        Reporter.log(elements.get(i).getAttribute("name").toString() + "<p>" ); 
                        i ++; 
                } 
                MobileSwipeDown(); 
                finishBtn.click(); 
        } 
        
          
        @AfterSuite 
        public void tearDown() throws Exception { 
                wd.quit(); 
        } 

} 
