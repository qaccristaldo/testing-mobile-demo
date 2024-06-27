package pages.lgcreateaccount;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;

import pages.base.BackBasePage;
import utils.MyLogger;

import java.time.Duration;

import static extended.MobileActions.clickElement;
import static extended.MobileActions.isElementVisible;


public class GettingStartedLGPage extends BackBasePage {

    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"¡Excelente! Sigue adelante.\")")
    private WebElement greatKeepGoingLbl;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"TUTOR LEGAL\")")
    private WebElement LGLbl;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"CREA UNA CUENTA\")")
    private WebElement createAccountButton;

    public GettingStartedLGPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    public Boolean isOpen(){

        boolean flag = false;
        try{
            flag = isElementVisible(driver, greatKeepGoingLbl) &&
                    isElementVisible(driver, LGLbl);
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
    }

    public CreateLGAccountPage clickOnCreateAccountButton(){
        clickElement(driver, createAccountButton);
        return new CreateLGAccountPage(driver);
    }
}
