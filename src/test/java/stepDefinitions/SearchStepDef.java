package stepDefinitions;

import Pages.RocketmilesPage;
import Pages.SearchResultPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import utilities.Configuration;
import utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

import java.util.List;


public class SearchStepDef {
    WebDriver driver = Driver.getDriver();
    RocketmilesPage rocketmilesPage = new RocketmilesPage();
    SearchResultPage searchResultPage = new SearchResultPage();
    WebDriverWait wait = new WebDriverWait(driver, 7);
    static final Logger LOGGER = LogManager.getLogger(SearchStepDef.class);


    @Given("^User is on Rocketmiles website$")
    public void user_is_on_Rocketmiles_website() {
        driver.get(Configuration.getProperty("rocketUrl"));
    }

    @When("^User search hotels in \"([^\"]*)\" for July 30, 2019 - July 31, 2019 with one guest")
    public void user_search_hotels_in_for(String city) throws InterruptedException {
        closeDateGuestRoom();
        rocketmilesPage.cityHotelInput.sendKeys(city);
        rocketmilesPage.cityChicago.click();
    }

    @And("^User choose \"([^\"]*)\" reward program$")
    public void user_choose_reward_program(String rewardProgram) {
        rocketmilesPage.rewardInput.sendKeys(rewardProgram + Keys.DOWN + Keys.ENTER);
    }

    @Then("^User get available hotels at given date range \"([^\"]*)\" in \"([^\"]*)\" with \"([^\"]*)\" reward$")
    public void user_get_available_hotels_at_given_date_range_in_with_reward(String expRangeDate, String expCity, String expRewardProgram) {
        rocketmilesPage.searchButton.click();
        Assert.assertEquals(expRangeDate, searchResultPage.rangeDate.getText());
        Assert.assertTrue(searchResultPage.validateCity.getText().contains(expCity));
        Assert.assertTrue(searchResultPage.validateRewardProgram.getText().contains(expRewardProgram));
    }

    @Then("^All listed hotels are in Chicago, IL area$")
    public void all_listed_hotels_are_in_Chicago_IL_area() {

    }

    @When("^User search hotels but with hotel box empty$")
    public void user_search_hotels_but_with_hotel_box_empty() throws InterruptedException {
        closeDateGuestRoom();
        rocketmilesPage.rewardInput.sendKeys("United MileagePlus" + Keys.DOWN + Keys.ENTER);
        rocketmilesPage.searchButton.click();
    }

    @When("^User search hotels in \"([^\"]*)\" but with reward box empty$")
    public void user_search_hotels_in_but_with_reward_box_empty(String city) throws InterruptedException {
        closeDateGuestRoom();
        rocketmilesPage.cityHotelInput.sendKeys(city);
        rocketmilesPage.cityChicago.click();
        rocketmilesPage.searchButton.click();
    }

    @Then("^User will get city box message$")
    public void user_will_get_city_box_message() {
        String expNoticeHotel = "Unknown location. Please type the city name again slowly and wait for the drop down options, or double-check the spelling.";
        Assert.assertEquals(expNoticeHotel, rocketmilesPage.emptyHotelInputMessage.getText());
    }

    @Then("^User will get reward box message$")
    public void user_will_get_reward_box_message() {
        String expNoticeReward = "Reward program is required.";
        Assert.assertEquals("", expNoticeReward, rocketmilesPage.emptyRewardInputMessage.getText());
    }

    @When("^User search with empty hotel and reward program$")
    public void user_search_with_empty_hotel_and_reward_program() throws InterruptedException {
        closeDateGuestRoom();
        rocketmilesPage.searchButton.click();
    }

    @Then("^User will get hotel box message$")
    public void user_will_get_hotel_box_message() {
        String expNoticeHotel = "Unknown location. Please type the city name again slowly and wait for the drop down options, or double-check the spelling.";
        Assert.assertEquals("Empty hotel verification failure", expNoticeHotel, rocketmilesPage.emptyHotelInputMessage.getText());
    }


    public void closeDateGuestRoom() throws InterruptedException {
        rocketmilesPage.okButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(rocketmilesPage.closeButton)).click();
        Thread.sleep(1000); //smth weird here
        rocketmilesPage.startDateInput.click();
        datePickUp("30");
        rocketmilesPage.guestClick.click();
        rocketmilesPage.oneGuest.click();
        rocketmilesPage.roomClick.click();
        rocketmilesPage.twoRooms.click();
    }

    public void datePickUp(String date) {
        WebElement dateWidget = rocketmilesPage.dateChooseTable;
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
        for (WebElement cell : columns) {
            if (cell.getText().equals(date)) {
                cell.findElement(By.linkText(date)).click();
                break;
            }
        }
    }
}
