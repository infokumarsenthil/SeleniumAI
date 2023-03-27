package com.testngAI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SeleniumDemoAI {

	@Test
	public void verifyTitle() throws Exception {
		WebDriver driver;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.get(
				"file:///C:/Users/infok/eclipse-workspace_Git/SeleniumAI/src/test/java/com/webpage/Student_Registration_Page.html");
		driver.manage().window().maximize();

		/*
		 * Split all the input, button, textarea, select tags from the present loaded
		 * webpage and store in Excel sheet
		 */
		WebElement element1 = driver.findElement(By.xpath("//*"));
		Document doc1 = Jsoup.parse(element1.getAttribute("innerHTML"));
		Elements inputTag = doc1.getElementsByTag("input");
		Elements buttonTag = doc1.getElementsByTag("button");
		Elements textareaTag = doc1.getElementsByTag("textarea");
		Elements optionTag = doc1.getElementsByTag("select");
		LocatorsStorage.inputLocator("" + inputTag, "InputLocators", "input");
		LocatorsStorage.inputLocator("" + buttonTag, "ButtonLocators", "button");
		LocatorsStorage.inputLocator("" + textareaTag, "TextareaLocators", "textarea");
		LocatorsStorage.inputLocator("" + optionTag, "DropDownLocators", "select");

		/*
		 * LocatorsPattern.inputTagXpath("variableName") - pull present updated locators from
		 * excel and pass it to the selenium script during execution.
		 */
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_FirstName"))).sendKeys("Senthil");
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_MiddleName"))).sendKeys("Kumar");
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_LastName"))).sendKeys("Paul Suyambu");
		new Select(driver.findElement(By.xpath(LocatorsPattern.optionTagXpath("DropDown_Course")))).selectByVisibleText("BBA");
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_Other"))).click();
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_Phone_new"))).click();
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_Phone_new"))).sendKeys("0987654321");
		driver.findElement(By.xpath(LocatorsPattern.textAreaXpath("TextArea_Address"))).sendKeys("Chennai");
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_Email"))).sendKeys("senthil@kumar.com");
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_Password"))).sendKeys("password8765");
		driver.findElement(By.xpath(LocatorsPattern.inputTagXpath("Input_ReTypePassword"))).sendKeys("password8765");
//	    driver.findElement(By.xpath(LocatorsPattern.buttonXpath("Button_Submit"))).click();

		//driver.quit();
	}

}
