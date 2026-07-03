package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="data")
	public Object[][] getMultipleDataFromExcel()
	        throws EncryptedDocumentException, IOException {
		String path="src/test/resources/RestAssuredExcelData.xlsx";
		String sheetName="Sheet1";

	    // Open the Excel file
	    FileInputStream file = new FileInputStream(path);

	    // Create Workbook instance
	    Workbook book = WorkbookFactory.create(file);

	    // Access the required sheet
	    Sheet sheet = book.getSheet(sheetName);

	    // Get total number of rows in the sheet
	    int noOfRows = sheet.getPhysicalNumberOfRows();

	    // Get total number of cells (columns) from the header row
	    int noOfCells = sheet.getRow(0).getPhysicalNumberOfCells();

	    // Create a 2D Object array
	    // Excluding header row, hence (noOfRows - 1)
	    Object obj[][] = new Object[noOfRows - 1][noOfCells];

	    // Iterate through all data rows (starting from row 1)
	    for (int i = 1; i < noOfRows; i++) {

	        // Iterate through all columns
	        for (int j = 0; j < noOfCells; j++) {

	            // Read cell value and store it in Object array
	            obj[i - 1][j] = sheet.getRow(i).getCell(j).toString();
	        }
	    }
	    book.close();
	    file.close();

	    // Return data in Object[][] format for DataProvider
	    return obj;
	
	}
	
	@DataProvider(name="usernames")
	public String[] getUserNames() throws EncryptedDocumentException, IOException
	{
		String path="src/test/resources/RestAssuredExcelData.xlsx";
		String sheetName="Sheet1";
		FileInputStream file=new FileInputStream(path);
		Workbook book=WorkbookFactory.create(file);
		Sheet sheet=book.getSheet(sheetName);
		int row=sheet.getPhysicalNumberOfRows();
		String[] usernames=new String[row-1];
		for(int i=1;i<row;i++)
		{
			usernames[i-1]=sheet.getRow(i).getCell(1).toString();
		}
		book.close();
		file.close();
		return usernames;
		
	}

}
