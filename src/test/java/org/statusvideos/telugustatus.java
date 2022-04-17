package org.statusvideos;

import java.awt.RenderingHints.Key;
import java.io.IOException;

import org.baseclass1.Baseclass;
import org.openqa.selenium.Keys;
import org.pogoclass.LoginPojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class telugustatus extends Baseclass {
	
	@Given("User has given chrome browser and telugustatus.com url")
	public void user_has_given_chrome_browser_and_telugustatus_com_url() {
		browserLaunch("chrome");
		windowMaximize();
		sourceUrl("https://telugustatus.com/login");
		
	}

	@When("User has to pass values to username and password fields")
	public void user_has_to_pass_values_to_username_and_password_fields() throws InterruptedException {
		LoginPojo log = new LoginPojo();
		toPassKeys(log.getUser(), "priya");
		
	}

	@When("then click on the login button")
	public void then_click_on_the_login_button() throws InterruptedException {

	}

	@Then("user has to nagivate to home page")
	public void user_has_to_nagivate_to_home_page() throws IOException {
		toTakeScreenShot("one");

	}

}
