package pages.lccreateaccount;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import pages.DashboardPage;
import pages.base.BackBasePage;
import utils.GetProperty;
import utils.MyLogger;
import utils.Utils;

import java.time.Duration;

import static extended.MobileActions.*;
import static utils.GetProperty.getProperties;
import static utils.Utils.*;

@Getter
public class CreateAnAccountMENTORPage extends BackBasePage {

    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();
    static String file = "local.properties";
    static String newUser = generateRandomAlphaString(10);
    static String homePhone = generateRandomNumericString(10);
    static String password = generateRandomPass(10);

   @AndroidFindBy(uiAutomator = "new UiSelector().text(\"MENTOR\")")
   private WebElement mentorLabel;

//   @AndroidFindBy(uiAutomator = "new UiSelector().text(\"CONTINUAR\")")
//   private WebElement continueButton;

   @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
   private WebElement createUsernameInput;

   @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Crear un nombre de usuario\"]/following::android.view.View/android.widget.RadioButton")
   private WebElement createUsernameRadioButton;

   @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
   private WebElement createNewMailInput;

    @AndroidFindBy(xpath = "//*[@hint = 'Número de Teléfono de Casa OBLIGATORIO']")
    private WebElement homePhoneInput;

    @AndroidFindBy(xpath = "//*[@hint = 'Contraseña OBLIGATORIO']")
    private WebElement passwordInput1;

    @AndroidFindBy(xpath = "//*[@hint = 'Vuelva a introducir la Contraseña OBLIGATORIO']")
    private WebElement passwordInput2;

    @AndroidFindBy(xpath = "//*[@text = ' Pregunta de Seguridad OBLIGATORIO ']")
    private WebElement securityQuestion;

    @AndroidFindBy(xpath = "//*[@hint = 'Respuesta de Seguridad OBLIGATORIO']")
    private WebElement securityAnswerInput;

    @AndroidFindBy(xpath = "//*[@class = 'android.widget.RadioButton' and (@text = 'Name of your school?' or . = 'Name of your school?')]")
    private WebElement question1;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"OK\")")
    private WebElement okButton;

    @AndroidFindBy(xpath = "//*[@class = 'android.widget.Button' and (@text = 'CONTINUAR' or . = 'CONTINUAR')]")
    private WebElement continueButton;

    public CreateAnAccountMENTORPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    public CreateAnAccountMENTORPage createUsername(){
        logger.info("new Username = {}", newUser);
        clickElement(driver, createUsernameRadioButton);
        typeOnElement(driver, createUsernameInput, newUser);
        return this;
    }

    public CreateAnAccountMENTORPage setNewMail(){
        //String newMail = newUser.concat("@qa-team.org");
        logger.info("new User Mail = {}", getProperties(file, "temp_mail"));
        typeOnElement(driver, createNewMailInput, "qgkw_hmssc43@juaxe.com");
        return this;
    }

    public CreateAnAccountMENTORPage setHomePhone(){
        logger.info("new User Home Phone = {}", homePhone);
        scrollDown(driver);
        typeOnElement(driver, homePhoneInput, homePhone);
        return this;
    }

    public CreateAnAccountMENTORPage setPassword(){
        logger.info("new User password = {}", password);
        typeOnElement(driver, passwordInput1, password);
        scrollDown(driver);
        typeOnElement(driver, passwordInput2, password);
        return this;
    }

    public CreateAnAccountMENTORPage setSecQuestion(){
        scrollDown(driver);
        clickElement(driver, securityQuestion);
        clickElement(driver, question1);
        clickElement(driver, okButton);
        typeOnElement(driver, securityAnswerInput, "school1");

        return this;
    }

    public DashboardPage clickOnContinueButton(){
        clickElement(driver, continueButton);
        forceWait(20000);
        return new DashboardPage(driver);
    }



}
