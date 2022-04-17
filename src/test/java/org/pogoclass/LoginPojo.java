package org.pogoclass;

import org.baseclass1.Baseclass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPojo extends Baseclass {

	public LoginPojo() {
		PageFactory.initElements(driver, this);
	}
	
	
		@FindBy(id="username")
		private WebElement user;
		
		public WebElement getUser() {
			return user;
		}

		public WebElement getPwd() {
			return pwd;
		}

		public WebElement getLogingbtn() {
			return logingbtn;
		}


		@FindBy(id="password")
		private WebElement pwd;
		
		@FindBy(xpath="//input[@type='submit']")
		private WebElement logingbtn;
	
}
