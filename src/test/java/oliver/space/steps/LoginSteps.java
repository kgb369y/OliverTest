package oliver.space.steps;

import cucumber.api.java.en.Given;
import oliver.space.methods.LoginMethods;

public class LoginSteps {
	
	private LoginMethods loginMethods = new LoginMethods();

	@Given("^the user is logged into the Oliver account$")
	public void login() throws Throwable {
		loginMethods.login();
	}

}
