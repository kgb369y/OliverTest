package oliver.space.methods;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import oliver.space.setup.Driver;

public abstract class CommonsMethods {

	public void fillField(WebElement element, String fill) {
		element.sendKeys(fill);
	}

	public void scrollTo(WebElement element) {
		Actions action = new Actions(Driver.driver);
		action.moveToElement(element).perform();
	}

	public void hoverAndClick(WebElement hoverAddToCart) {
		scrollTo(hoverAddToCart);
		wait(300);
		hoverAddToCart.click();
		wait(300);
	}

	// TODO: improve this not good sleep
	protected void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// IMPLICIT WAITS
	protected static void implicitWaitsTo(int i) {
		Driver.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Driver.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Driver.driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	};

	// EXPLICIT WAITS
	public void waitFor(By by, int time, int polling) {
		fluentWait(time, polling, Arrays.asList(NoSuchElementException.class))
				.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForVisibily(WebElement element, int time, int polling) {
		fluentWait(time, polling).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForNumberBeMore(By elements, int quantityCurrent, int time, int pooling) {
		fluentWait(time, pooling).until(ExpectedConditions.numberOfElementsToBeMoreThan(elements, quantityCurrent));
	}

	protected FluentWait<WebDriver> fluentWait(int totalTimeOut, int pullingEvery) {
		return new FluentWait<WebDriver>(Driver.driver).withTimeout(totalTimeOut, TimeUnit.SECONDS)
				.pollingEvery(pullingEvery, TimeUnit.SECONDS);
	}

	protected FluentWait<WebDriver> fluentWait(int totalTimeOut, int pullingEvery,
			List<Class<? extends Throwable>> exception) {
		return new FluentWait<WebDriver>(Driver.driver).ignoreAll(exception).pollingEvery(pullingEvery,
				TimeUnit.SECONDS);
	}

}
