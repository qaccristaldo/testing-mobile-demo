package pages.enrollment;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import pages.base.BackBasePage;
import utils.MyLogger;

import java.time.Duration;

import static extended.MobileActions.*;
import static utils.Utils.forceWait;
import static utils.Utils.generateRandomAlphaString;

@Getter
public class TellAboutYourStudentPage extends BackBasePage {


    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Tell us about your student\")")
    private WebElement mainLabel;

    // uiAutomator example
    @AndroidFindBy(xpath = "//*[@hint ='First name Type first name...']")
    private WebElement firstNameInput;

    public TellAboutYourStudentPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public Boolean isOpen(){

        return isElementVisible(driver, mainLabel);
    }

    public TellAboutYourStudentPage setFirstName(){
        typeOnElement(driver, firstNameInput, generateRandomAlphaString(10));
        return this;
    }
}
