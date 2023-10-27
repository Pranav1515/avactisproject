package avactis.testcases;

import org.testng.annotations.Test;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import org.testng.annotations.AfterMethod;

public class RegistrationTest {

	RegistrationPO regi;
	
	@Test(dataProvider = "RegistrationValidDataXLS")
	public void verifyRegistrationWithValidDetails(String mail, String host, String pass, String repass, String fname,
			String lname, String pin, String city, String st1, String st2, String ph) {

		regi.registration(mail, host, pass, repass, fname, lname, pin, city, st1, st2, ph);

		String actualmsgtext = regi.getRegiSuccesfullTextMsg();
		String expectedMessage = "Account created successfully. You are now registered.";
		assertEquals(actualmsgtext, expectedMessage, "Message not matched");
		//Invalid data in field 'E-mail'.
	}
	
	@Test(dataProvider = "RegistrationInvalidDataXLS")
	public void verifyRegistrationWithInvalidDetails(String mail, String host, String pass, String repass, String fname,
			String lname, String pin, String city, String st1, String st2, String ph) {

		regi.registration(mail, host, pass, repass, fname, lname, pin, city, st1, st2, ph);

		String actualmsgtext = regi.getInvalidMailTextMsg();
		String expectedMessage = "Invalid data in field 'E-mail'.";
		assertEquals(actualmsgtext, expectedMessage, "Message not matched");
		//Invalid data in field 'E-mail'.
	}

	@DataProvider(name = "RegistrationValidDataXLS")
	public Object[][] validRegistrationDataFromXLS() {
		Object[][] obj = readDataFromXLS("test//resources/data/Registration.xls", "Valid", "NTStartEnd");
		return obj;
	}
	
	@DataProvider(name = "RegistrationInvalidDataXLS")
	public Object[][] invalidRegistrationDataFromXLS() {
		Object[][] obj = readDataFromXLS("test//resources/data/Registration.xls", "Invalid", "NTStartEnd");
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
		regi = new RegistrationPO();
	}

	@AfterMethod
	public void afterMethod() {
		regi.closeBrowser();
	}

}
