package pages.carrousel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

import static extended.MobileActions.isElementVisible;

public class WhoWeArePage extends BasePage {



    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"¿QUIÉNES SOMOS?\"]")
    private WebElement whoWeAreLabel;

    public WhoWeArePage(AndroidDriver driver) {
        super(driver);
    }

    public Boolean isOpen(){
        return isElementVisible(getDriver(), whoWeAreLabel);
    }

}
