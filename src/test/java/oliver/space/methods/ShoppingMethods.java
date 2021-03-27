package oliver.space.methods;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import oliver.space.pages.ShoppingPage;
import oliver.space.setup.Constants;

public class ShoppingMethods extends CommonsMethods {

	ShoppingPage page = new ShoppingPage();
	boolean askOptionals = false;

	public void goToShopping() {
		page.startShopping.click();
	}

	public void addProducts(String products) {
		List<String> productsList = Arrays.asList(products.split(","));
		if (productsList.size() > 1) {
			waitForVisibily(page.allProductsName.get(0), 3, 1);

			productsList.stream().forEach(product -> {
				add(scrollToTheProduct(product));
			});
		}

	}

	private void add(WebElement productInTheViewPort) {
		hoverAndClick(productInTheViewPort);
		optionals();
	}

	private void optionals() {
		implicitWaitsTo(0);
		if (!askOptionals) {
			chooseVariantIfRequire();
			choosePlanIfRequire();
			giveZipCode();
			askOptionals = true;
		}
		try {
			waitForVisibily(page.titleModal, 3, 1);
			String title = page.titleModal.getText().trim().toLowerCase();
			if (title.contains("updated cart")) {
				// TODO: close

			} else if (title.contains("oops")) {
				// TODO: handling error
				System.out.println("product not added");

			}
			page.btnCloseModal.click();

		} catch (Exception e) {
			if (!(e instanceof NoSuchElementException || e instanceof TimeoutException))
				throw e;
		}

		implicitWaitsTo(Constants.IMPLICITWAITDEF);

	}

	private void giveZipCode() {
		try {
			page.zipCode.sendKeys("90001");
			page.submitLocation.click();
		} catch (Exception e) {
			if (!(e instanceof TimeoutException || e instanceof NoSuchElementException)) {
				throw e;
			}
		}
	}

	private void choosePlanIfRequire() {
		try {
			waitFor(page.byPlans(), 1, 1);
			page.plan(new Random().nextInt(2)).click();
		} catch (Exception e) {
			if (!(e instanceof TimeoutException || e instanceof NoSuchElementException)) {
				throw e;
			}
		}

	}

	private void chooseVariantIfRequire() {
		try {
			waitFor(page.byVariantProduct(), 1, 1);
			page.variantSelector(new Random().nextInt(1)).click();
		} catch (Exception e) {
			if (!(e instanceof TimeoutException || e instanceof NoSuchElementException)) {
				throw e;
			}
		}

	}

	private WebElement scrollToTheProduct(String product) {
		boolean endOfProducts = false;
		WebElement productContainer = null;

		while (productContainer == null && !endOfProducts) {
			productContainer = productInTheViewPort(product);
			if (productContainer == null) {
				endOfProducts = scrollByDemand();
			}
		}
		return productContainer;

	}

	private boolean scrollByDemand() {
		List<WebElement> allProducts = page.allProducts;
		scrollTo(allProducts.get(allProducts.size() - 1));
		return waitLoadingMoreElements(page.allProducts.size() - 1);
	}

	private boolean waitLoadingMoreElements(int quantityCurrent) {
		try {
			waitForNumberBeMore(page.byAllProducts(), quantityCurrent, 3, 1);
		} catch (TimeoutException e) {
			if (page.allProducts.size() == quantityCurrent && page.loading.getAttribute("style") != null
					&& page.loading.getAttribute("style").contains("display: none")) {
				return true;
			}
		}
		return false;

	}

	private WebElement productInTheViewPort(String product) {
		implicitWaitsTo(0);
		List<WebElement> allProductsInTheVP = page.allProductsName;

		try {
			for (WebElement pr : allProductsInTheVP) {
				if (pr.getText().trim().toLowerCase().contains(product.trim().toLowerCase())) {

					return page.parent(pr);
				}
			}

		} catch (Exception e) {
			if (e instanceof NoSuchElementException) {
				// TODO: HandlingError
			} else if (e instanceof StaleElementReferenceException) {
				// TODO: Implement accurate expected condition
			}
		}

		implicitWaitsTo(Constants.IMPLICITWAITDEF);
		return null;
	}

	public int productsInTheCart() {
		return Integer.valueOf(page.countInCart.getText().trim());
	}

	public void fillCheckout() {
		if (!page.checkout.isDisplayed()) {
			page.linkCart.click();
		}
		if (page.checkout.isEnabled()) {
			page.checkout.click();
			waitFor(page.form, 1, 1);
			page.name.sendKeys("Katy");
			page.lastName.sendKeys("Garcia");
			page.phone.sendKeys("15515555551");
			page.address.sendKeys("4170 South Broadway, Los Angeles, CA, USA");
			page.city.sendKeys("Los Angeles");
			page.state.sendKeys("CA");
			page.zipCodeCheckout.sendKeys("90001");

			page.date1.click();
			if (page.date1AvailableDays.size() > 2) {
				page.date1AvailableDays.get(0).click();
				page.date2.click();
				page.date2AvailableDays.get(0).click();
			} else {
				page.nextMontDate1.click();
				page.date1AvailableDays.get(1).click();
				page.date2.click();
				page.nextMontDate2.click();
				page.date2AvailableDays.get(1).click();
			}
			page.checkoutContinue.click();
			// TODO: stop buy flow cause is needed to use credit card

		} else {
			// TODO: pending to implement
		}

	}

	public void removeProducts(String products) {
		if (!page.checkout.isDisplayed()) {
			try {
				page.linkCart.click();
			} catch (ElementClickInterceptedException e) {
				if (page.titleModal.getText().trim().toLowerCase().contains("update"))
					page.btnCloseModal.click();
			}
			Arrays.asList(products.split(",")).forEach(pr -> {
				deleteProduct(pr.trim().toLowerCase());
			});
		}
	}

	private void deleteProduct(String pr) {
		page.productsIntoTheCart.forEach(productInTheCart -> {
			if (productInTheCart.getText().trim().toLowerCase().contains(pr)) {
				productInTheCart.findElement(By.xpath("./.."))
						.findElement(By.cssSelector("[data-qa='cart-clear-product']")).click();
				wait(500);
				return;
			}
		});
	}

	// TODO: if the buy was success the flow should show an OK message to find
	public String validateSuccessBuy() {
		return "ok";
	}

}
