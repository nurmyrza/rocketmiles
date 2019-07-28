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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.*;
import utilities.Configuration;
import utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

import java.util.ArrayList;
import java.util.List;

public class SearchStepDef {
    WebDriver driver = Driver.getDriver();
    RocketmilesPage rocketmilesPage = new RocketmilesPage();
    SearchResultPage searchResultPage = new SearchResultPage();
    WebDriverWait wait = new WebDriverWait(driver, 7);
    static final Logger LOGGER = LogManager.getLogger(SearchStepDef.class);
    Actions move = new Actions(driver);

    @Given("^User is on Rocketmiles website$")
    public void user_is_on_Rocketmiles_website() {
        driver.get(Configuration.getProperty("rocketUrl"));
    }

    @When("^User search hotels in \"([^\"]*)\" for July 30, 2019 - July 31, 2019 with one guest")
    public void user_search_hotels_in_for(String city) throws InterruptedException {
        LOGGER.info("Starting to search hotels in Chicago, IL fo July 30 - 31, 2019");
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
        Assert.assertEquals("Range Date failure", expRangeDate, searchResultPage.rangeDate.getText());
        Assert.assertTrue("City validation failure", searchResultPage.validateCity.getText().contains(expCity));
        Assert.assertTrue("Reward validattion failure", searchResultPage.validateRewardProgram.getText().contains(expRewardProgram));
    }

    @When("^User search hotels in \"([^\"]*)\" for July 30, 2019 - July 31, 2019$")
    public void user_search_hotels_in_for_July_July(String city) throws InterruptedException {
        LOGGER.info("Starting hotel list verification");
        closeDateGuestRoom();
        rocketmilesPage.cityHotelInput.sendKeys(city);
        rocketmilesPage.cityChicago.click();
        rocketmilesPage.rewardInput.sendKeys("United MileagePlus" + Keys.DOWN + Keys.ENTER);
        rocketmilesPage.searchButton.click();
    }

    @When("^User select up to \"([^\"]*)\" with \"([^\"]*)\"$")
    public void user_select_up_to_with(String maxPrice, String sliderPercentage)  {
        int sliderInt = Integer.parseInt(sliderPercentage);
        wait.until(ExpectedConditions.elementToBeClickable(searchResultPage.slider));
        slider(sliderInt);
    }

    @Then("^User will see only that up to \"([^\"]*)\" range$")
    public void user_will_see_only_that_up_to_range(String arg1) {
       LOGGER.info("Starting validation range price");
        Assert.assertTrue("Not in that range",
               isInThatRange(searchResultPage.minimumSlider, searchResultPage.maximumSlider, searchResultPage.listOfHotels));
    }

    @When("^User search hotels but with hotel box empty$")
    public void user_search_hotels_but_with_hotel_box_empty() throws InterruptedException {
        LOGGER.info("Starting validation with empty hotel");
        closeDateGuestRoom();
        rocketmilesPage.rewardInput.sendKeys("Aeroplan" + Keys.DOWN + Keys.ENTER);
        rocketmilesPage.searchButton.click();
    }

    @When("^User search hotels in \"([^\"]*)\" but with reward box empty$")
    public void user_search_hotels_in_but_with_reward_box_empty(String city) throws InterruptedException {
        LOGGER.info("Starting to search with empty reward box");
        closeDateGuestRoom();
        rocketmilesPage.cityHotelInput.sendKeys(city);
        rocketmilesPage.cityChicago.click();
        rocketmilesPage.searchButton.click();
    }

    @Then("^User will get city box message$")
    public void user_will_get_city_box_message() {
        String expNoticeHotel = "Unknown location. Please type the city name again slowly and wait for the drop down options, or double-check the spelling.";
        Assert.assertEquals("Hotel message verification failure", expNoticeHotel, rocketmilesPage.emptyHotelInputMessage.getText());
    }

    @Then("^User will get reward box message$")
    public void user_will_get_reward_box_message() {
        String expNoticeReward = "Reward program is required.";
        Assert.assertEquals("Reward message failure", expNoticeReward, rocketmilesPage.emptyRewardInputMessage.getText());
    }

    @When("^User search with empty hotel and reward program$")
    public void user_search_with_empty_hotel_and_reward_program() throws InterruptedException {
        LOGGER.info("Starting to search with both hotel and reward empty boxes");
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

    public void slider(int x) {
        WebElement slider = searchResultPage.slider;
        int width = slider.getSize().getWidth();
        move.moveToElement(slider, ((width * x) / 100), 0).click();
        move.build().perform();
    }

    public boolean isInThatRange(WebElement min, WebElement max, List<WebElement> list) {
        List<Integer> listIntPrice = new ArrayList<>();
        Integer minInt = Integer.valueOf(min.getText().substring(1, 3));
        Integer maxInt = Integer.valueOf(max.getText().substring(1, 4));
        for (WebElement element : list) {
            listIntPrice.add(Integer.valueOf(element.getText()));
        }

        for (int i = 0; i < listIntPrice.size(); i++) {
            if (listIntPrice.get(i) >= minInt && listIntPrice.get(i) <= maxInt) {
                return true;
            }
        }
        return false;
    }
}



