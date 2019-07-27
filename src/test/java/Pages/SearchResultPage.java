package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SearchResultPage {
    WebDriver driver;

    public SearchResultPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@translate='search_page.search_summary.n_hotels']")
    public WebElement validateCity;

    @FindBy(xpath = "//span[@translate='search_page.search_summary.n_hotels']")
    public WebElement validateRewardProgram;

    @FindBy(xpath = "//div[@class='location-search-search-request-dates ng-binding ng-scope']")
    public WebElement rangeDate;
}
