package oliver.space.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import oliver.space.setup.Driver;

public class ShoppingPage {

	static final String cssAllProducts = "[data-qa='product'";
	static final String cssVariantProduct = ".variant-group";
	static final String cssAllPlans = ".select-plan";

	@FindBy(css = ".banner-copy [href='/products']")
	public WebElement startShopping;

	private WebDriver driver;

	@FindBys(value = { @FindBy(css = "[data-qa='product-detail'] [data-qa='product-name']") })
	public List<WebElement> allProductsName;

	@FindBys(value = { @FindBy(css = cssAllProducts) })
	public List<WebElement> allProducts;

	@FindBy(css = cssVariantProduct)
	public WebElement variantProduct;

	@FindBy(css = ".infinite-status-prompt:nth-child(1)")
	public WebElement loading;

	@FindBy(css = ".modal-container.ModalMessage .modal-title")
	public WebElement titleModal;

	@FindBy(css = ".modal-content button")
	public WebElement btnCloseModal;

	@FindBy(css = "[data-qa='location-zip']")
	public WebElement zipCode;

	@FindBy(css = "[data-qa='submit-location']")
	public WebElement submitLocation;

	@FindBy(css = ".cart-wrapper .product-count")
	public WebElement countInCart;

	@FindBy(css = "[data-qa='link-cart']")
	public WebElement linkCart;
	
	@FindBy(css = "[data-qa='cart-populated']")
	public WebElement populatedCart;

	@FindBy(css = "[data-qa='button-cart-submit']")
	public WebElement checkout;

	public By form = By.tagName("form");

	@FindBy(id = "checkout_first_name")
	public WebElement name;

	@FindBy(id = "checkout_last_name")
	public WebElement lastName;

	@FindBy(id = "checkout_phone")
	public WebElement phone;

	@FindBy(id = "auto-address")
	public WebElement address;

	@FindBy(id = "checkout_city")
	public WebElement city;

	@FindBy(id = "checkout_state")
	public WebElement state;

	@FindBy(id = "checkout_zip")
	public WebElement zipCodeCheckout;

	@FindBy(id = "delivery-slot-0")
	public WebElement date1;

	@FindBys(value = { @FindBy(css = "#delivery-slot-0 .cell.day.highlighted") })
	public List<WebElement> date1AvailableDays;

	@FindBy(css = "#delivery-slot-0 .next")
	public WebElement nextMontDate1;

	@FindBy(css = "#delivery-slot-1 .next")
	public WebElement nextMontDate2;

	@FindBy(id = "delivery-slot-1")
	public WebElement date2;

	@FindBys(value = { @FindBy(css = "#delivery-slot-1 .cell.day.highlighted") })
	public List<WebElement> date2AvailableDays;
	
	@FindBy(css = "[data-qa='button-checkout-submit']")
	public WebElement checkoutContinue;
	
	@FindBys(value = {@FindBy(css = "[data-qa='cart-populated'] > div .product-sku-actions .product-name")})
	public List<WebElement> productsIntoTheCart;

	public ShoppingPage() {
		driver = Driver.driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement variantSelector(int random) {
		return driver.findElements(By.cssSelector(cssVariantProduct + " span")).get(random);
	}

	public By byAllProducts() {
		return By.cssSelector(cssAllProducts);
	}

	public By byVariantProduct() {
		return By.cssSelector(cssVariantProduct);
	}

	public By byPlans() {
		return By.cssSelector(cssAllPlans);
	}

	public WebElement plan(int random) {
		return driver.findElements(By.cssSelector(cssAllPlans)).get(random);
	}

	public WebElement parent(WebElement pr) {
		WebElement parent = pr.findElement(By.xpath("./../../../../../.."))
				.findElement(By.cssSelector("[data-qa='button-add-to-cart']"));
		return parent;
	}
}
