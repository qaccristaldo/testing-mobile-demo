package pages.lgcreateaccount;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;

import pages.base.BackBasePage;
import utils.Constants;
import utils.MyLogger;

import java.time.Duration;

import static extended.MobileActions.*;
import static utils.Utils.forceWait;


@Getter
public class ChooseRolePage  extends BackBasePage {

    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"SELECCIONA TU ROL\")")
    private WebElement selectRoleLbl;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"ELEGIR CUENTA DE TUTOR\")")
    private WebElement selectLGRoleButton;

    public ChooseRolePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }


    public Boolean isOpen(){
        boolean flag = false;
        try{
            flag = isElementVisible(driver, selectRoleLbl);
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
    }

    public GettingStartedLGPage clickOnSelectLGRoleButton(){
        scrollToElement(driver, Constants.SELECT_GUARDIAN_ACCOUNT);
        clickElement(driver, selectLGRoleButton);
        return new GettingStartedLGPage(driver);
    }

    public Boolean estaAbierto(){

        return selectRoleLbl.isDisplayed();
    }


}
