package com.traveloffice.TainReservation;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TrainSeatsChecking {
	@Test
		public void Checkseatsintrain() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement fromtext = driver.findElementById("txtStationFrom");
		fromtext.clear();
		fromtext.sendKeys("Egmore");
		driver.findElementByXPath("//div[@class='selected']/div").click();

		WebElement totext = driver.findElementById("txtStationTo");
		totext.clear();
		totext.sendKeys("TPJ");
		driver.findElementByXPath("(//div[@class='selected']/div)[3]").click();

		driver.findElementByXPath("//input[@title='Select Departure date for availability']").click();
		driver.findElementByXPath("(((//table[@class='Month'])[2]//tr)[4]//td)[3]").click();
		WebElement checkbox = driver.findElementById("chkSelectDateOnly");
		if (checkbox.isSelected())
			checkbox.click();
		Thread.sleep(3000);
		// driver.findElementByXPath("//input[@value='Get Trains']").click();

		WebElement table = driver.findElementByXPath("//table[@class='DataTable TrainList']");
		List<WebElement> trainrows = table.findElements(By.tagName("tr"));

		System.out.println("Number of available trains" + trainrows.size());
		for (int i = 0; i < trainrows.size(); i++) {
			WebElement eachrow = trainrows.get(i);

			List<WebElement> columns = eachrow.findElements(By.tagName("td"));
			String trainname = columns.get(1).getText();
			String vacancy = columns.get(20).getText();
			System.out.println("No of available seats in "+trainname+" is "+vacancy);
			/*if ( columns != null && !columns.isEmpty() && columns.get(1).getText()
					.equalsIgnoreCase("pallavan exp")) {
				String seats = columns.get(20).getText();
				System.out.println("No of available seats in Pallavan are : " + seats);
			}*/
			/*
			 * if (columns != null && !columns.isEmpty())
			 * System.out.println(columns.get(1).getText());
			 */
		}

		System.out.println("process completed");
		driver.quit();
	}
}
