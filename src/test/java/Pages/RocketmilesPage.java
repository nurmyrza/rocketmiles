package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class RocketmilesPage {
    WebDriver driver;

    public RocketmilesPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@translate='common.ok']")
    public WebElement okButton;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement closeButton;

    @FindBy(xpath = "//input[@placeholder='Where do you need a hotel?']")
    public WebElement cityHotelInput;

    @FindBy(xpath = "//a[@class='ng-binding ng-scope']//strong[.='Chicago, Il']")
    public WebElement cityChicago;

    @FindBy(xpath = "//input[@placeholder='Check in']")
    public WebElement startDateInput;

    @FindBy(xpath = "(//a[@href='#'])[4]")
    public WebElement choose30;

    @FindBy(xpath = "//a[@class='ui-state-default ui-state-active']")
    public WebElement chooseTestDate;

    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']")
    public WebElement dateChooseTable;

    @FindBy(xpath = "//span[.='2 Guests']")
    public WebElement guestClick;

    @FindBy(xpath = "//a[.='1 Guest']")
    public WebElement oneGuest;

    @FindBy(xpath = "//span[contains(text(),'1 Room')]")
    public WebElement roomClick;

    @FindBy(xpath = "//a[contains(text(),'2 Rooms')]")
    public WebElement twoRooms;

    @FindBy(xpath = "//input[@placeholder='Select reward program']")
    public WebElement rewardInput;

//    @FindBy(xpath = "//button[@type='submit']//span[@class='ng-scope']")
//    public WebElement searchButton;

    @FindBy(xpath = "//span[.='Search properties and earn rewards']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@class='popover-content ng-binding']")
    public WebElement emptyHotelInputMessage;

    @FindBy(xpath = "//div[@class='popover-content']")
    public WebElement emptyRewardInputMessage;




}
