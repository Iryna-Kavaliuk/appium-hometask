package com.epam.ta.test;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class AbstractTest {

  protected static final String APPIUM = "http://localhost:4723/wd/hub";
  protected String APP;
  protected static final int WAIT_TIMEOUT_SECONDS = 20;
  DesiredCapabilities capabilities;

  protected AndroidDriver driver;

  @BeforeEach
  public void setUp() throws Exception {
    capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("platformVersion", "14");
    capabilities.setCapability("deviceName", "Android Emulator");
    capabilities.setCapability("automationName", "UiAutomator2");
    configureAppCapabilities();
  }

  abstract void configureAppCapabilities() throws Exception;

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
