package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	/* written to get test data from excel file
	 * takes sheetname as a parameter
	 * returns 2D array object
	 * 
	 */
	static Workbook book;
	static Sheet sheet;
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\johar\\eclipse-workspace\\UI-Framework\\src\\main\\java\\utils\\TestData.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		}
		catch (InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i = 0; i < sheet.getLastRowNum(); i++)
		{
			for(int j = 0; j < sheet.getRow(0).getLastCellNum(); j++)
			{
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
}
