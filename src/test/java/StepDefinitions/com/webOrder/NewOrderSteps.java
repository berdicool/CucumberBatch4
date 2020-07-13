package StepDefinitions.com.webOrder;

import Pages.WebOrderPages.AllOrdersPage;
import Pages.WebOrderPages.HomePage;
import Pages.WebOrderPages.OrderPage;
import Utils.BrowserUtils;
import Utils.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class NewOrderSteps {
    WebDriver driver= Driver.getDriver();
    HomePage homePage=new HomePage(driver);
    OrderPage orderPage=new OrderPage(driver);
    AllOrdersPage allOrdersPage=new AllOrdersPage(driver);

    @Then("the user enters product info {string} and {string}")
    public void the_user_enters_product_info_and(String product, String quantity) {
        homePage.orderButton.click();
        Select select=new Select(orderPage.productName);
        select.selectByVisibleText(product);
        orderPage.quantity.sendKeys(Keys.BACK_SPACE,quantity);
    }

    @Then("the user enter address info {string}, {string}, {string}, {string}, {string}")
    public void the_user_enter_address_info(String customerName, String street,
                                            String city, String state, String zipCode) {
        orderPage.customerName.sendKeys(customerName);
        orderPage.street.sendKeys(street);
        orderPage.city.sendKeys(city);
        orderPage.state.sendKeys(state);
        orderPage.zipCode.sendKeys(zipCode);
    }

    @Then("the user enter payment info {string}, {string}, {string}")
    public void the_user_enter_payment_info(String card, String cardNumber, String expiration) {
        switch (card){
            case "Visa":
                orderPage.visa.click();
                break;
            case "MasterCard":
                orderPage.masterCard.click();
                break;
            case "American Express":
                orderPage.amex.click();
                break;
        }
        orderPage.cardNumber.sendKeys(cardNumber);
        orderPage.expiration.sendKeys(expiration);
        orderPage.processButton.click();
    }

    @Then("the user validate success message")
    public void the_user_validate_success_message() {
        Assert.assertTrue(orderPage.successMessage.isDisplayed());
    }
    @Then("the user validate title new order details {string},{string},{string}, {string}, {string}," +
            " {string}, {string},{string}, {string}, {string}")
    public void the_user_validate_title_new_order_details(String productName, String quantity, String name,
                                                          String address, String city, String state, String zipCode,
                                                          String cardType, String cardNum, String expDate) {
        homePage.viewAllOrders.click();
        List<WebElement> orderDetails= allOrdersPage.newOrderDetails;
        Assert.assertEquals(orderDetails.get(1).getText(),name);
        Assert.assertEquals(orderDetails.get(2).getText(),productName);
        Assert.assertEquals(orderDetails.get(3).getText(),quantity);
        String todaysDate=BrowserUtils.todaysDate("MM/dd/yyyy");
        Assert.assertEquals(orderDetails.get(4).getText(),todaysDate);
        Assert.assertEquals(orderDetails.get(5).getText(),address);
        Assert.assertEquals(orderDetails.get(6).getText(),city);
        Assert.assertEquals(orderDetails.get(7).getText(),state);
        Assert.assertEquals(orderDetails.get(8).getText(),zipCode);
        Assert.assertEquals(orderDetails.get(9).getText(),cardType);
        Assert.assertEquals(orderDetails.get(10).getText(),cardNum);
        Assert.assertEquals(orderDetails.get(11).getText(),expDate);
    }
    @Then("the user goes to new order page")
    public void the_user_goes_to_new_order_page() {
        homePage.orderButton.click();
    }

    @Then("the user validate the product headers")
    public void the_user_validate_the_product_headers(List<String> productHeaders) {
        List<String> actualProductHeaders=BrowserUtils.getTextOfListOfWebElements(orderPage.productDetails);
        Assert.assertEquals(actualProductHeaders,productHeaders);
    }
    @Then("the user validate the address headers")
    public void the_user_validate_the_address_headers(DataTable dataTable) {
        List<String> expected=dataTable.asList();
        List<String> actual=BrowserUtils.getTextOfListOfWebElements(orderPage.addressDetails);
        Assert.assertEquals(expected,actual);
    }
    @Then("the user clicks the all products button")
    public void the_user_clicks_the_all_products_button() {
        homePage.allProductsButton.click();
    }

    @Then("the user validate the product details")
    public void the_user_validate_the_product_details(DataTable dataTable) {
        List<WebElement> pTable=homePage.productTable;
        for (int i=0;i<dataTable.asList().size();i++){
            Assert.assertEquals(dataTable.asList().get(i),pTable.get(i).getText());
        }
    }
}
