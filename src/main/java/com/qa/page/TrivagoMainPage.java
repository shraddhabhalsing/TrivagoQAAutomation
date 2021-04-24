package com.qa.page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.Base;

public class TrivagoMainPage extends Base {

	SoftAssert sofassrt=new SoftAssert();
	String date_Select;

	Actions actn;
	// Find elements at run time

	@FindBy(xpath="//input[@class='DestinationControl_input__23k7O']")
	WebElement location_Txtfield;


	@FindBy(xpath="//div[@class='Inspiration_searchFormWrapper__3DpiC']//div[@class='fresnel-container fresnel-greaterThanOrEqual-md ']//div[@class='Control_control__2CV0z Control_type-distance__2OkWA']")
	WebElement radius_Button;

	@FindBys(@FindBy(xpath="//span[@class='ControlSelectItem_label__IbRcQ']"))
	List<WebElement> radius_List;


	@FindBy(xpath="//div[@id='onetrust-button-group']//button[contains(text(),'OK ')]")
	WebElement cookieOk_Button;


	@FindBy(xpath="//div[@class='Inspiration_searchFormWrapper__3DpiC']//div[@class='fresnel-container fresnel-greaterThanOrEqual-md ']//div[@class='Control_control__2CV0z Control_type-checkIn__3wYkl']")
	WebElement checkIn_Button;

	@FindBys(@FindBy(xpath="//div[@class='DoubleCalendar_container__2ctMg']//time"))
	List<WebElement> checkInDate_Select;

	@FindBy(xpath="//div[@class='Inspiration_searchFormWrapper__3DpiC']//div[@class='Control_control__2CV0z Control_type-guest__dfPYQ']")
	WebElement guest_Button;

	@FindBy(xpath="//ul[@class='GuestSelector_inputs__TQcfi']/child::li[1]//div//input[@class='NumberInput_value__1i8N5']")
	WebElement adult_TextField;

	@FindBy(xpath="//ul[@class='GuestSelector_inputs__TQcfi']/child::li[2]//div//input[@class='NumberInput_value__1i8N5']")
	WebElement child_TextField;

	@FindBy(xpath="//ul[@class='GuestSelector_inputs__TQcfi']/child::li[3]//div//input[@class='NumberInput_value__1i8N5']")
	WebElement room_TextField;

	@FindBys(@FindBy(xpath="//ul[@class='GuestSelector_ages__3Rkh3']//li//select"))
	List<WebElement> childAge_select;

	@FindBy(xpath="//div[@class='GuestSelect_toolbar__2-PKA']/child::div[2]")
	WebElement apply_Button;

	@FindBys(@FindBy(xpath="//ul[@class='DealTilesDesktop_tiles__27xGH']//li//a"))
	List<WebElement> hotelName_List;

	@FindBys(@FindBy(xpath="//span[contains(text(),'View Deal')]"))
	List<WebElement> viewDeal_List;

	@FindBy(xpath="//div[@class='Control_control__2CV0z Control_type-checkIn__3wYkl']//span[@class='IconLabel_label__rUNtX IconLabel_truncateLabel__PDMe-']")
	WebElement dateSelected_Validation;

	@FindBy(xpath="//div[@class='bui-group bui-button-group bui-group--inline bui-group--align-end bui-group--vertical-align-middle']//div[6]//a//span[@class='bui-button__text']")
	WebElement contentAfterNav_Validation;



	//Initialize WebElements mentioned in the current page
	public void initializeWebElement(){
		PageFactory.initElements(driver, this);
	}

	//Method to enter location 

	public String enterLocation(String location){	


		String actualLocationVal = null;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cookieOk_Button.click();
		try
		{  
			actn=new Actions(driver);
			actn.moveToElement(location_Txtfield).doubleClick().build().perform();
			location_Txtfield.sendKeys(location);
			location_Txtfield.sendKeys(Keys.ENTER);
			radius_Button.click();
			WebDriverWait jh=new WebDriverWait(driver,40);

			jh.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='Header_distance__2cEOq']/span/span/child::span/following::span[contains(text(),'"+location+"')]")));
			WebElement Loc_element=driver.findElement(By.xpath("//div[@class='Header_distance__2cEOq']/span/span/child::span/following::span[contains(text(),'"+location+"')]"));
			actualLocationVal=Loc_element.getText();

		}catch(Exception e)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cookieOk_Button.click();
		}
		return actualLocationVal;


	}

	//Method to select Radius
	public String selectRadius(String radius){

		String radVal = null;
		int i;
		ArrayList<String>ls=new ArrayList<String>();

		for (WebElement radius_Object: radius_List)
		{
			ls.add(radius_Object.getText());

		}

		for (i=0;i<ls.size();i++)
		{
			if (ls.get(i).contains(radius))
			{
				radVal=radius_List.get(i).getText();
				radius_List.get(i).click();

			}

		}

		return radVal;
	}

	//Method to select CheckIn/Checkout dates
	public String CheckIn(String checkinDate,String checkoutDate ){
		String dates[]= {checkinDate,checkoutDate};

		checkIn_Button.click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300);");
		for (int i=0;i<dates.length;i++)
		{
			for (WebElement checkIn_Select:checkInDate_Select)
			{
				date_Select=checkIn_Select.getAttribute("datetime");

				if(date_Select.equals(dates[i]))
				{
					checkIn_Select.click();
					break;
				}
			}
		}


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateSelected_Validation.getText();

	}

	//Method to click on guest button then select no of adults ,no of children and their age and no of rooms

	public String selectGuest(String Adult,String child,String room,String Age)
	{
		guest_Button.click();


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		actn=new Actions(driver);
		actn.moveToElement(adult_TextField).doubleClick().build().perform();
		adult_TextField.sendKeys(Adult);
		actn.moveToElement(child_TextField).doubleClick().build().perform();
		child_TextField.sendKeys(child);
		actn.moveToElement(room_TextField).doubleClick().build().perform();
		room_TextField.sendKeys(room);

		for (WebElement child_selectAge:childAge_select)
		{
			Select chAge_Select=new Select(child_selectAge);
			chAge_Select.selectByVisibleText(Age);
		}

		apply_Button.click();
		WebElement verifyAdultVal=driver.findElement(By.xpath("//div[@class='Inspiration_searchForm__3NLKF']//div[5]//span[contains(text(),'"+Adult+"')]"));
		return verifyAdultVal.getText();
	}

	//Method to click on View deal button

	public String viewDeal()
	{
		for (WebElement viewDeal_lst:viewDeal_List)
		{

			String window_main=driver.getWindowHandle();
			viewDeal_lst.click();
			Set<String>windows_lst=	driver.getWindowHandles();
			Iterator<String>itr=windows_lst.iterator();
			while(itr.hasNext())
			{
				String window_child=itr.next();
				if (!window_child.equals(window_main))
				{
					driver.switchTo().window(window_child);	

				}
			}

			break;
		}
		return contentAfterNav_Validation.getText();

	}
}