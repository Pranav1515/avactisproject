/*TC3
write a test to login into the store.(positive and negative)
1. Click on SignIn
2. Enter the valid credentials
3. Login Successful*/
package avactis.testcases;

import org.testng.annotations.Test;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class LoginTest {

	LoginPO logi;

	@Test(dataProvider = "LoginValidDataXLS")
	public void verifyLoginWithValidDetails(String mail, String pass) {

		logi.login(mail, pass);

		String actualmsgtext = logi.getLoginSuccesfullTextMsg();
		String expectedmsgtext = "MANAGE ACCOUNT AND VIEW ORDERS";
		assertEquals(actualmsgtext, expectedmsgtext, "Message not matched");

	}
	
	@Test(dataProvider = "LoginInalidDataXLS")
	public void verifyLoginWithInalidDetails(String mail, String pass) {

		logi.login(mail, pass);

		String actualmsgtext = logi.getLoginUnsuccesfullTextMsg();
		String expectedmsgtext = "Account and password could not be identified. Try again or create an account.";
		assertEquals(actualmsgtext, expectedmsgtext, "Message not matched");

	}

	@DataProvider(name = "LoginValidDataXLS")
	public Object[][] validLoginDataFromXLS() {
		Object[][] obj = readDataFromXLS("test//resources/data/Login.xls", "Valid", "NTStartEnd");
		return obj;
	}
	
	@DataProvider(name = "LoginInalidDataXLS")
	public Object[][] invalidLoginDataFromXLS() {
		Object[][] obj = readDataFromXLS("test//resources/data/Login.xls", "Invalid", "NTStartEnd");
		return obj;
	}


	public String[][] readDataFromXLS(String xlFilePath, String sheetName, String marker) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			// Workbook class is provied by jxl.jar
			// WebDriver provided by Selenium
			// File is class provided by Java to read a physical file
			Sheet sheet = workbook.getSheet(sheetName);
			Cell tableStart = sheet.findCell(marker);

			int startRow, startCol, endRow, endCol, ci, cj;

			startRow = tableStart.getRow();// 2
			startCol = tableStart.getColumn();// 1

			Cell tableEnd = sheet.findCell(marker, startCol + 1, startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();// 5
			endCol = tableEnd.getColumn();// 4
			System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", " + "startCol=" + startCol
					+ ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];// 2,2
			ci = 0; // array row
			// ci=0,i=3, j=3,cj=1
			for (int i = startRow + 1; i < endRow; i++, ci++) {// i represents xls row
				cj = 0;// array column
				for (int j = startCol + 1; j < endCol; j++, cj++) {// j represents xls column
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file you specified does not exist");
		} catch (Exception e) {
			System.out.println("Please check if file path, sheet name and tag name are correct");
			e.toString();
		}

		return (tabArray);
	}

	@BeforeMethod
	public void beforeMethod() {

		logi = new LoginPO();

	}

	@AfterMethod
	public void afterMethod() {
		logi.closeBrowser();
	}
}
