package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XLUtility {
	
	String path;
	public XLUtility(String path)
	{
		this.path=path;
	}
	
	public String getDataFromExcelFile(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
      FileInputStream fis=new FileInputStream(path);
      Workbook book=WorkbookFactory.create(fis);
      Sheet sheet=book.getSheet(sheetName);
      Row row=sheet.getRow(rowNum);
      Cell cell=row.getCell(cellNum);
      return cell.toString();
	}

}
