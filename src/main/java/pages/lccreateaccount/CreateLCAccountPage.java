package pages.lccreateaccount;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import pages.base.BackBasePage;
import pages.enrollment.EnrollmentPage;
import pages.lgcreateaccount.CreateLGAccountPage;
import utils.GetProperty;
import utils.MyLogger;

import java.time.Duration;
import java.util.List;

import static extended.MobileActions.*;
import static utils.Utils.*;

@Getter
public class CreateLCAccountPage extends BackBasePage {

    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();
    static String file = "local.properties";

   @AndroidFindBy(uiAutomator = "new UiSelector().text(\"MENTOR\")")
   private WebElement mentorLabel;

   @AndroidFindBy(uiAutomator = "new UiSelector().text(\"CONTINUAR\")")
   private WebElement continueButton;

   @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
   private WebElement regidInput;

    public CreateLCAccountPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }



    public Boolean isOpen(){
        boolean flag = false;
        try{
            flag = isElementVisible(driver, mentorLabel);
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
    }

    public CreateLCAccountPage setRegidInput() {
        typeOnElement(driver, regidInput, GetProperty.getProperties(file, "regid"));
        return this;
    }

    public CreateAnAccountMENTORPage clickOnContinueButton(){
        scrollToElement(driver, "CONTINUAR");
        clickElement(driver, continueButton);
        return new CreateAnAccountMENTORPage(driver);
    }

}
