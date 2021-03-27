package oliver.space.steps;

import org.junit.Assert;
import static org.hamcrest.CoreMatchers.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import oliver.space.methods.ShoppingMethods;

public class ShoppingSteps {
	
	//improvement customize reports and add logger tech slf4j to bug fixing

	ShoppingMethods methods = new ShoppingMethods();

	@Given("^the user go to shopping products$")
	public void goToShopping() throws Throwable {
		methods.goToShopping();
	}

	// IS REQUIRED AN ACCOUNT WITH EMPTY CART TO CAN BE RUN THE TEST
	@When("^add the \"([^\"]*)\" to the shopping cart$")
	public void addProductsCart(String products) throws Throwable {
		methods.addProducts(products);
	}

	@Then("^the shopping cart will be appears with the \"([^\"]*)\" desired$")
	public void validateProductsIntoTheCart(String products) throws Throwable {
		Assert.assertThat(methods.productsInTheCart(), is(products.split(",").length));
	}

	@Given("^the user has a shopping cart with the \"([^\"]*)\" added$")
	public void prepareCart(String products) throws Throwable {
		methods.goToShopping();
		methods.addProducts(products);
	}

	@When("^remove the \"([^\"]*)\" to the shopping cart$")
	public void removeProducts(String products) throws Throwable {
		methods.removeProducts(products);
	}

	@When("^give the assambly with checkout information$")
	public void fillAssemblyAndCheckoutInfo() throws Throwable {
		methods.fillCheckout();
	}

	@Then("^the user will have bought the desired product$")
	public void validateHappyBought() throws Throwable {
		Assert.assertThat(methods.validateSuccessBuy(), is("ok"));
	}

}
