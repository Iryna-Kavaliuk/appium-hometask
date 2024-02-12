package com.epam.ta.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.time.Duration;

public class CalculatorTest extends AbstractTest{

  @Override
  void configureAppCapabilities() throws Exception {
    APP = "C:\\Learning\\AQA2023\\simple-calculator\\Simple Calculator_3.1.6_apkcombo.com.apk";
    capabilities.setCapability("app", APP);
    driver = new AndroidDriver(new URI(APPIUM).toURL(), capabilities);
  }

  @Test
  public void multiplicationTest() {
    closeConsentWidget();
    driver.findElement(AppiumBy.id("com.bng.calculator:id/btn_1")).click();
    driver.findElement(AppiumBy.id("com.bng.calculator:id/btn_2")).click();
    driver.findElement(AppiumBy.id("com.bng.calculator:id/btn_3")).click();
    driver.findElement(AppiumBy.id("com.bng.calculator:id/_0p_3")).click();
    driver.findElement(AppiumBy.id("com.bng.calculator:id/btn_5")).click();
    driver.findElement(AppiumBy.id("com.bng.calculator:id/multi")).click();
    driver.findElement(AppiumBy.id("com.bng.calculator:id/btn_2")).click();
    driver.findElement(AppiumBy.id("com.bng.calculator:id/equal")).click();
    WebElement resultValue = driver.findElement(AppiumBy.id("com.bng.calculator:id/formula"));
    assert(resultValue.getText().equals("247"));
  }

  private void closeConsentWidget() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    WebElement consentButton = wait.until(ExpectedConditions.presenceOfElementLocated(
        AppiumBy.xpath(".//android.widget.Button[@text='Consent']")));
    consentButton.click();
  }

}
