package StepDefinitions.com.duckduckgo;

import Pages.DuckDuckGoPages.SearchPage;
import Utils.ConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBoxSteps {

    WebDriver driver= Driver.getDriver();
    SearchPage searchPage;
    @Given("the user goes to duckduckgo")
    public void the_user_goes_to_duckduckgo() {
        searchPage=new SearchPage(driver);
        driver.get(ConfigReader.getProperty("url"));
    }

    @When("the user send the selenium keyword")
    public void the_user_send_the_selenium_keyword() {
        searchPage.searchBox.sendKeys(ConfigReader.getProperty("searchValue"));
    }

    @Then("the user click search button")
    public void the_user_click_search_button() {
        searchPage.searchButton.click();
    }

    @Then("the user validate title contains the selenium keyword")
    public void the_user_validate_title_contains_the_selenium_keyword() {
        String actualTitle=driver.getTitle();
        //Assert coming from junit
        Assert.assertTrue(actualTitle.contains(ConfigReader.getProperty("searchValue")));
    }

    @Then("the user validate url contains selenium keyword")
    public void the_user_validate_url_contains_selenium_keyword() {
        String actualUrl=driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(ConfigReader.getProperty("searchValue")));
    }

    @Then("the user validate all results contain selenium keyword")
    public void the_user_validate_all_results_contain_selenium_keyword() {
        for (WebElement element:searchPage.searchResult){
            String actualResult = element.getText();
            Assert.assertTrue(actualResult.contains(ConfigReader.getProperty("searchUpperCase")));
        }
    }
    @Given("the user search with {string}")
    public void the_user_search_with(String searchValue) {
        driver.get(ConfigReader.getProperty("url"));
        searchPage.searchBox.sendKeys(searchValue);
    }
    @When("the user validate title contains {string}")
    public void the_user_validate_title_contains(String titleName) {
        Assert.assertTrue(driver.getTitle().contains(titleName));
    }
    @When("the user validate all results contains {string}")
    public void the_user_validate_all_results_contains(String string) {
        for(WebElement result:searchPage.searchResult){
            Assert.assertTrue(result.getText().contains(string));
        }
    }
    @When("the user validate number of result is {int}")
    public void the_user_validate_number_of_result_is(int expected) {
        Assert.assertEquals(expected,searchPage.searchResult.size());
    }

}
