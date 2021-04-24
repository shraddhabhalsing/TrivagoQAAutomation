package com.qa.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.base.Base;
import com.qa.page.TrivagoMainPage;
import com.qa.util.CommonUtil;

public class TrivagoMainTest extends Base {

	//Create Object of MainPage where OR and methods are maintained
	TrivagoMainPage mainPageObj=new TrivagoMainPage();

	public static ExtentHtmlReporter pathHtml;
	public static ExtentReports exReport;
	public static ExtentTest exLog,exLog1,exLog2,exLog3,exLog4,exLog5,exLog6;


	// constructor to call Base class constructor
	TrivagoMainTest(){
		super();
	}

	//Prerequisite steps to be executed prior executing actual TestCases

	@BeforeTest
	public void basicSetUp()
	{
		DriversetUp();
		pathHtml=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\reporting\\ExtentReport.html");
		exReport=new ExtentReports();
		exReport.attachReporter(pathHtml);

	}

	// Launch URL TestCase

	@Test(priority=1)
	public void launchURL(){
		String pageTitle=LaunchBrowser();
		mainPageObj.initializeWebElement();
		exLog=exReport.createTest("Launch URL", "Automation");
		if (pageTitle.equals("trivago Weekend"))
		{
			exLog.log(Status.PASS,"URL is launched successfully") ;
		}

		else

		{
			exLog.log(Status.FAIL,"Failed to launch URL") ;
		}
	}

	//Verify user can enter location

	@Test(priority=2)
	@Parameters({"sLocation"})
	public void enterLocation(String sLocation ){
		String sLocationval=mainPageObj.enterLocation(sLocation);
		exLog1=exReport.createTest("Verify user can enter location", "Automation");

		if (!sLocationval.equals(""))
		{
			exLog1.log(Status.PASS," User has entered location successfully") ;
		}
		else

		{
			exLog1.log(Status.FAIL,"Failed to enter location") ;

		}
	}

	//Verify user has can select Radius

	@Test(priority=3)
	@Parameters({"sradius"})
	public void enterRadius(String sradius){
		String verifyRadius=mainPageObj.selectRadius(sradius);

		exLog2=exReport.createTest("Verify user can select Radius ","Automation");
		if (verifyRadius.equals(sradius))
		{
			exLog2.log(Status.PASS," User has entered radius successfully") ;
		}
		else

		{
			exLog2.log(Status.FAIL,"Failed to enter radius") ;
		}


	}

	//Verify user can select Checkin and chekOut date

	@Test(priority=4)
	@Parameters({"checkInDate","checkOutDate"})
	public void clickGuestAsLink(String checkInDate,String checkOutDate ){
		String verDate= mainPageObj.CheckIn(checkInDate,checkOutDate);
		exLog3=exReport.createTest("Verify user can select Checkin and chekOut date", "Automation");
		if (!verDate.equals(""))
		{
			exLog3.log(Status.PASS,"Checkin and chekOut date entered successfully ") ;
		}
		else

		{
			exLog3.log(Status.FAIL," Failed to enter Checkin and chekOut date") ;
		}
	}


	@DataProvider
	public Object[][] passSheet()
	{
		Object[][] val=CommonUtil.readDataFromExcel("InputData");
		return val;
	}
	//Verify user is able to enter guest details

	@Test(priority=5,dataProvider="passSheet")
	public void enterDetail(String adult,String child,String room,String age){


		String hjk=mainPageObj.selectGuest(adult,child,room,age);

		exLog4=exReport.createTest("Verify user is able to enter guest details", "Automation");
		if (hjk.equals(adult))
		{
			exLog4.log(Status.PASS,"Guest details entered successfully") ;
		}
		else

		{
			exLog4.log(Status.FAIL," Failed to enter guest details") ;

		}



	}

	//Verify user is able to click view deal button

	@Test(priority=6)
	@Parameters({"newPageText"})
	public void insertDatainForm(String newPageText)
	{
		String chkval= mainPageObj.viewDeal();
		exLog5=exReport.createTest("Verify user is able to click view deal button", "Automation");
		if (chkval.equals(newPageText))
		{
			exLog5.log(Status.PASS,"View deal button clicked successfully") ;
		}
		else

		{
			exLog5.log(Status.FAIL," Failed to click view deal button ") ;

		}

	}

	//Close browser

	@AfterTest
	public void closeBrowser() {
		exReport.flush();
		driver.quit();

	}
}
