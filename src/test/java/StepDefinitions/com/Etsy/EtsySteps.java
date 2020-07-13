package StepDefinitions.com.Etsy;

import Pages.EtsyPages.EtsyHomePage;
import Utils.ConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class EtsySteps {
    WebDriver driver= Driver.getDriver();
    EtsyHomePage etsyHomePage=new EtsyHomePage(driver);

    @Given("the user goes to Etsy")
    public void the_user_goes_to_Etsy() {
        driver.navigate().to(ConfigReader.getProperty("EtsyUrl"));
    }

    @When("the user search in Etsy with {string}")
    public void the_user_search_in_Etsy_with(String searchValue) {
        etsyHomePage.searchBox.sendKeys(searchValue);
        etsyHomePage.searchButton.click();
    }

    @Then("the user validate title {string} and url {string}")
    public void the_user_validate_title_and_url(String expectedTitle, String searchValue) {
        Assert.assertEquals(driver.getTitle(),expectedTitle);
        Assert.assertTrue(driver.getCurrentUrl().contains(searchValue));
    }

}
