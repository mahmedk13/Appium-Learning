package appiumlearning;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ScrollTest {

	public static AppiumDriver<MobileElement> driver = null;
	public static AppiumDriverLocalService service=null;

	public static void scrollDown() {
		Dimension dimension = driver.manage().window().getSize();

		Double scrollHeightStart = dimension.getHeight() * 0.5;
		int scrollStart = scrollHeightStart.intValue();

		Double scrollHeightEnd = dimension.getHeight() * 0.2;
		int scrollEnd = scrollHeightEnd.intValue();

		new TouchAction(driver).press(PointOption.point(0, scrollStart)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
		.moveTo(PointOption.point(0, scrollEnd)).release().perform();
	}


	@BeforeTest
	public static void setUp() throws MalformedURLException, InterruptedException {




		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".AssetBrowserActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		Thread.sleep(15000);



	}




	public List<MobileElement> getList(){
		return driver.findElements(By.xpath("//android.widget.TextView[@text='Awesome Android - UI Libraries']"));
	}

	@Test
	public void test01() throws InterruptedException {

		Thread.sleep(5000);


		try {
			driver.findElement(By.id("com.android.vending:id/search_box_idle_text")).click();
		}catch(Exception e) {
			e.printStackTrace();
		}


		driver.findElement(By.id("com.android.vending:id/search_box_text_input")).sendKeys("Api Demos");
		driver.findElement(By.id("com.android.vending:id/search_box_text_input")).sendKeys(Keys.ENTER);

		while(getList().size()==0) {
			scrollDown();

		}

		if(getList().size()>0) {
			getList().get(0).click();
		}

		driver.quit();


	}




}




