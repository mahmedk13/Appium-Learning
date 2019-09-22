package appiumlearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SwipeTest {

	public static AndroidDriver<MobileElement> driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.deskclock");
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.deskclock.DeskClock");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		MobileElement panel = driver.findElement(By.id("com.google.android.deskclock:id/desk_clock_pager"));

		int anchor = panel.getSize().getHeight();

		Double screenstart = panel.getSize().getWidth()*0.2;
		Double screenend = panel.getSize().getWidth()*0.95;

		int start = screenstart.intValue();
		int end = screenend.intValue();

		new TouchAction(driver).press(PointOption.point(end, anchor)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
		.moveTo(PointOption.point(start, anchor)).release().perform();

		Thread.sleep(3000);
		driver.quit();
	}
}