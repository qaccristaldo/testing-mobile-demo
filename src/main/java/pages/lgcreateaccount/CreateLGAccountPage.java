package pages.lgcreateaccount;

import pages.enrollment.EnrollmentPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;

import pages.base.BackBasePage;
import utils.GetProperty;
import utils.MyLogger;

import java.time.Duration;
import java.util.List;

import static extended.MobileActions.*;

import static utils.Utils.*;

@Getter
public class CreateLGAccountPage extends BackBasePage {

    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();
    static String file = "local.properties";


    //@AndroidFindBy(xpath = "//android.view.View[@text=\"First name\"]/following-sibling::android.view.View/android.widget.EditText")
    @AndroidFindBy(xpath = "//*[@hint = 'First name']")
    private WebElement firstNameInput;

    //@AndroidFindBy(xpath = "//android.view.View[@text=\"Last name\"]/following-sibling::android.view.View/android.widget.EditText")
    @AndroidFindBy(xpath = "//*[@hint = 'Last name']")
    private WebElement lastNameInput;

    //@AndroidFindBy(xpath = "//android.view.View[@text=\"Email Address\"]/following-sibling::android.view.View/android.widget.EditText")
    @AndroidFindBy(xpath = "//*[@hint = 'Email Address']")
    private WebElement emailInput;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Password\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement passwordInput;


    //security questions
    @AndroidFindBy(xpath = "//android.view.View[@text=\"Answer security question 1\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement answerSecurityQuestion1;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Answer security question 2\"]/following-sibling::android.view.View/android.widget.EditText")
    private WebElement answerSecurityQuestion2;


    //Selectors dropdown
    @AndroidFindBy(xpath = "//android.view.View[@text=\"Please add a security question 1\"]/following::android.view.View/android.widget.Spinner[@text=\"Select a question for the security question\"]")
    private WebElement security1Dropdown;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Please add a security question 2\"]/following::android.view.View/android.widget.Spinner[@text=\"Select a question for the security question\"]")
    private WebElement security2Dropdown;


    //set options
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"What is the name of your favorite pet?\")")
    private WebElement option1Selector1;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"What is the name of your favorite movie?\")")
    private WebElement option3Selector2;

    @AndroidFindBy(xpath = "//android.widget.Spinner[@text=\"UNITED STATES OF AMERICA\"]")
    private WebElement countrySelector;

    @AndroidFindBy(xpath = "//android.widget.Spinner[@text=\"Select State\"]")
    private WebElement stateSelector;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"AZ\"]")
    private WebElement stateOption;

    @AndroidFindBy(xpath = "//android.widget.ListView[@text=\"Country\"]/child::android.view.View/android.widget.TextView")
    List<WebElement> countriesList;

    @AndroidFindBy(xpath = "//android.widget.ListView[@text=\"State\"]/child::android.view.View/android.widget.TextView")
    List<WebElement> statesList;

    @AndroidFindBy(xpath = "//android.widget.ListView[@text=\"Contact Preference\"]")
    List<WebElement> contactPreferenceList;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Phone number\"]/following::android.view.View/android.widget.EditText")
    private WebElement phoneInput;

    @AndroidFindBy(xpath = "//android.widget.CheckBox[@text=\"Opt in to receive text messages\"]")
    private WebElement optionCheckBox;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Contact Preference\"]/following::android.view.View/android.widget.Spinner")
    private WebElement contactPreferenceDropdown;

    @AndroidFindBy(xpath = "//android.widget.ListView[@text=\"Contact Preference\"]/following::android.view.View/android.widget.TextView")
    private WebElement contactPreferenceSelector;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sign Up\")")
    private WebElement signUpButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Create an account\")")
    private WebElement createAnAccountLbl;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Contact Preference\"]/following::Android.view.View/android.widget.TextView")
    private WebElement contactSelector;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Email\")")
    private WebElement contactEmailSelector;


    public CreateLGAccountPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

    public CreateLGAccountPage setFirstNameInput() {
        String firstName = generateRandomAlphaString(10);
        logger.info(String.format("First Name = %s",firstName));
        typeOnElement(driver, firstNameInput, firstName);
        return this;
    }

    public CreateLGAccountPage setLastNameInput() {
        String lastName = generateRandomAlphaString(10);
        logger.info(String.format("Last Name = %s",lastName));
        typeOnElement(driver, lastNameInput, lastName);
        return this;
    }

    public CreateLGAccountPage setEmailInput() {
        //String email = GetProperty.getProperties(file, "temp_mail");
        String email = generateRandomEmail(8);
        logger.info(String.format("Email = %s",email));
        typeOnElement(driver, emailInput, email);
        return this;
    }


    public CreateLGAccountPage setPasswordInput() {
        scrollToElement(driver, "Password");
        String password = generateRandomPass(10);
        logger.info(String.format("Password = %s", password));
        typeOnElement(driver, passwordInput, password);
        return this;
    }

    public CreateLGAccountPage setSecurityQuestion1() {
        clickElement(driver, security1Dropdown);
        clickElement(driver, option1Selector1);
        typeOnElement(driver, answerSecurityQuestion1, "pet1");
        return this;
    }

    public CreateLGAccountPage setSecurityQuestion2() {
        clickElement(driver, security2Dropdown);
        clickElement(driver, option3Selector2);
        typeOnElement(driver, answerSecurityQuestion2, "movie1");
        return this;
    }

    public CreateLGAccountPage setCountry() {
        scrollToElement(driver, "Country");
        clickElement(driver, countrySelector);
        if (getCountriesList().isEmpty()) {
            logger.error("Country list is empty");
            System.exit(1);
        } else {
            clickElement(driver, getWebElementFromList(getCountriesList(), "UNITED STATES OF AMERICA"));
        }
        return this;
    }

    public CreateLGAccountPage setState() {
        clickElement(driver, stateSelector);
        if (getStatesList().isEmpty()) {
            logger.error("State list is empty");
            System.exit(1);
        } else {
            clickElement(driver, getWebElementFromList(getStatesList(), "AZ"));
        }
        return this;
    }

    public CreateLGAccountPage setPhone() {
        String phone = generateRandomNumericString(10);
        logger.info(String.format("Phone = %s",phone));
        typeOnElement(driver, phoneInput, phone);
        return this;
    }

    //TODO: investigate this
    public CreateLGAccountPage setOptToReceiveMsg() {
        clickElement(driver, optionCheckBox);
        clickElement(driver, contactPreferenceDropdown);
        scrollToElement(driver, "Email");
        clickElement(driver, contactEmailSelector);
        forceWait(3000);

        return this;
    }

    public EnrollmentPage clickOnSignUpButton(){
        scrollToElement(driver, "Sign Up");
        clickElement(driver, signUpButton);
        return new EnrollmentPage(driver);
    }


    public Boolean isOpen(){
        boolean flag = false;
        try{
            flag = isElementVisible(driver, createAnAccountLbl);
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
        return flag ;
    }


}
