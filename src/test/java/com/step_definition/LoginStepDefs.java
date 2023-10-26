package com.step_definition;

import com.pages.DashboardPage;
import com.pages.LoginPage;
import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import com.utilities.UserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));


    }
    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String userType) throws InterruptedException {
        UserUtils.UserGenerator(userType);
        Thread.sleep(2000);
        loginPage.login(UserUtils.username , UserUtils.password);

    }

    @Then("the user should see following options")
    public void the_user_should_see_following_options(List<String> menuOptions) {
        new DashboardPage().waitUntilLoaderScreenDisappear();
        //get the list of webelement and convert them to list of string and assert
        List<String> actualOptions = BrowserUtils.getElementsText(new DashboardPage().menuOptions);

        Assert.assertEquals(menuOptions, actualOptions);
        System.out.println("menuOptions = " + menuOptions);
        System.out.println("actualOptions = " + actualOptions);
    }


}
