package com.epam.ta.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class EpamConnectTest extends AbstractTest {

  WebDriverWait wait;

  @Override
  void configureAppCapabilities() throws URISyntaxException, MalformedURLException {
    APP = "C:\\Learning\\AQA2023\\epam-connect\\EPAM Connect_2.26_apkcombo.com.apk";
    capabilities.setCapability("app", APP);
    driver = new AndroidDriver(new URI(APPIUM).toURL(), capabilities);
  }

  @Test
  public void loginScreenNegativeTest() throws Exception {
    wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    closeWelcomeWidget();
    signInWithLinkedin();
    System.out.println(driver.getPageSource());
    acceptLinkedinCookies();
    WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("username")));
    username.clear();
    username.sendKeys("Lorem");
    WebElement password = driver.findElement(AppiumBy.id("password"));
    password.sendKeys("Ipsum");
    driver.findElement(AppiumBy.xpath(".//android.widget.Button[@text='Sign In']")).click();
    WebElement errorMessage = driver.findElement(AppiumBy.xpath(".//android.widget.TextView[@text='Please enter a valid username']"));
    assert(errorMessage.getText().equals("Please enter a valid username"));
  }

  private void closeWelcomeWidget() {
    WebElement skipButton = wait.until(ExpectedConditions.presenceOfElementLocated(
        AppiumBy.xpath(".//android.widget.TextView[@text='Skip']")));
    skipButton.click();
  }

  private void signInWithLinkedin() {
    WebElement sighInButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Sign Up")));
    sighInButton.click();
    WebElement loginWithLinkedinButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Continue with LinkedIn")));
    loginWithLinkedinButton.click();
  }

  private void acceptLinkedinCookies() {
    WebElement acceptCookiesButton = wait.until(ExpectedConditions.presenceOfElementLocated(
        AppiumBy.xpath(".//android.widget.Button[@text='Accept']")));
    acceptCookiesButton.click();
  }
}
