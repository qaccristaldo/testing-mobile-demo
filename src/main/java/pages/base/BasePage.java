package pages.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;

import pages.LoginPage;
import utils.Constants;
import utils.MyLogger;

import java.time.Duration;

import static extended.MobileActions.clickElement;

@Getter
public class BasePage {


    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"INICIAR SESIÓN\")")
    protected WebElement loginButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"MÁS INFORMACIÓN\")")
    private WebElement moreInfoElement;

    @FindBy(xpath = "//steps-bar/ion-button[2]")
    private WebElement rightArrowButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(3)")
    private WebElement leftArrowButton;


    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);

    }

    public LoginPage clickOnLoginButton(){
        clickElement(driver, loginButton);
        return new LoginPage(driver);
    }





}


