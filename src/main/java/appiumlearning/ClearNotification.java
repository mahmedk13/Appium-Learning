package appiumlearning;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class ClearNotification {

	public static AndroidDriver<WebElement> driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.apps.nexuslauncher");
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".NexusLauncherActivity");

		
		/*AppiumDriverLocalService service;
		AppiumServiceBuilder builder;
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(caps);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		*/
		
		


		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		service.start();

		
		System.out.println("Started");

		
		

		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		driver.openNotifications();
		Thread.sleep(3000);
		driver.findElement(By.id("com.android.systemui:id/dismiss_text")).click();



		driver.quit();
		service.stop();



	}

}
