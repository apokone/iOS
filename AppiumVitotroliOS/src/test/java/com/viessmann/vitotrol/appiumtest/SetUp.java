/**
 * 
 */
package test.java.com.viessmann.vitotrol.appiumtest;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * @author marcincabanek
 *
 */
public class SetUp {
	public WebDriver wd;  /*Short name for WebDriver*/
    WebDriverWait wait = new WebDriverWait(wd, 10);

	
	public WebElement finishBtn;	/* Button Finish for helps */

	
	public void main() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("device", "iphone");
		capabilities.setCapability("language", "en");
		//capabilities.setCapability("app", "/Users/admin/jenkins/workspace/iOS-Appium-tests/build" + filename);
		wd = new SwipeableWebDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		wd.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);



	}
	

	public void XyTap(double x, double y) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("x", x); // in pixels from left
        tapObject.put("y", y); // in pixels from top
        js.executeScript("mobile: tap", tapObject);
    }
	 
 public void MobileSwipeDown() throws Exception {
	 
	 double x = 0.5;
	 double y1 = 0.8;
	 double y2 = 0.2;
			 
    JavascriptExecutor js = (JavascriptExecutor) wd;
    HashMap<String, Double> swipeObject = new HashMap<String, Double>();
    swipeObject.put("startX", x); //  start position
    swipeObject.put("startY", y1); // start position
    swipeObject.put("endX", x); //  end position
    swipeObject.put("endY", y2); // end position
    js.executeScript("mobile: swipe", swipeObject);
}
	 
 public void MobileSwipeLeft() throws Exception {
		 
	 double y = 0.5;
	 double x1 = 0.8;
	 double x2 = 0.2;
	 
			 
    JavascriptExecutor js = (JavascriptExecutor) wd;
    HashMap<String, Double> swipeObject = new HashMap<String, Double>();
    swipeObject.put("startX", x1); // in pixels start position
    swipeObject.put("startY", y); // in pixels start position
    swipeObject.put("endX", x2); // in pixels end position
    swipeObject.put("endY", y); // in pixels end position
    js.executeScript("mobile: swipe", swipeObject);
    }
 
 public void MobileSwipeRight() throws Exception {
	 
	 double y = 0.5;
	 double x1 = 0.2;
	 double x2 = 0.8;
	 
			 
    JavascriptExecutor js = (JavascriptExecutor) wd;
    HashMap<String, Double> swipeObject = new HashMap<String, Double>();
    swipeObject.put("startX", x1); // in pixels start position
    swipeObject.put("startY", y); // in pixels start position
    swipeObject.put("endX", x2); // in pixels end position
    swipeObject.put("endY", y); // in pixels end position
    js.executeScript("mobile: swipe", swipeObject);
}
	 
 	public void iosFinishedBtn () throws Exception {
		 
		 finishBtn = wd.findElement(By.tagName("UIAButton"));
		 finishBtn.click();
		 
	 }
	 
	public void iosBackBtn () throws Exception {
		 
		 wd.findElement(By.name("Back")).click();
		 
	 }
	
	public void MobileScroll(String direction){
		JavascriptExecutor js = (JavascriptExecutor) wd;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", direction);
		js.executeScript("mobile: scroll", scrollObject);
	}

	public void takeScreenshot (String testName) throws Exception{
		 
		 System.out.println("Taking screenshot");
		 new File("screenshot/").mkdirs();
		 String filename = "screenshot/" + testName + ".png";
		 WebDriver augmentedDriver = new Augmenter().augment(wd);
		 File srcFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(srcFile, new File(filename), true);
		 System.out.println("Took screenshot" + filename);

		 
	 }
	 public static class SwipeableWebDriver extends RemoteWebDriver implements HasTouchScreen {
	     private RemoteTouchScreen touch;
	     
	     public SwipeableWebDriver(){
	    	 
	     }
	     
	     public SwipeableWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
	         super(remoteAddress, desiredCapabilities);
	         touch = new RemoteTouchScreen(getExecuteMethod());
	     }

	     public TouchScreen getTouch() {
	         return touch;
	     }
	 }
}
