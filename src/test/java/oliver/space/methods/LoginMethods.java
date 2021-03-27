package oliver.space.methods;

import oliver.space.pages.LoginPage;
import oliver.space.setup.Constants;

public class LoginMethods extends CommonsMethods {

	private LoginPage loginPage = new LoginPage();

	public void login() {
		loginPage.goLogin.click();
		loginPage.alreadyRegistered.click();
		fillField(loginPage.user, Constants.USERDEFAULT);
		fillField(loginPage.password, Constants.PASSWORD);
		loginPage.submit.submit();
		waitFor(loginPage.alreadyLoggd(), 3, 1);
	}

}
