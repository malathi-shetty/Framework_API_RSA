package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven_3 {

	public ArrayList<String> getData(String testcaseName, String sheetName)  throws IOException {

		// Identify testcases column by scanning the entire 1st Row
		// Once column is identified then scan entire testcase column to identify
		// purchase test case row
		// after you grab purchase testcase row = pull all the data of that row and feed
		// into test

		ArrayList<String> a = new ArrayList<String>();
		

		// fileInputStream Argument

		FileInputStream fis = new FileInputStream("DataDemoBook_2.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		System.out.println("Total Sheets: " + sheets);

		for (int i = 0; i < sheets; i++) {
			 sheetName = workbook.getSheetName(i); // Extract the sheet name here
			System.out.println("Sheet Name: " + sheetName);
		//	System.out.println("Sheet exists: " + sheetName);


			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify Testcases column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
				Row firstRow = rows.next();

				Iterator<Cell> ce = firstRow.cellIterator(); // row is collection of Cells

				int k = 0;
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						// desired column
						column = k;

					}
					k++;

				}
				System.out.println("TestCases Column Index: " + column);

				// once column is identified then scan entire testcase column to identify
				// testcase row

				while (rows.hasNext()) {
					Row r = rows.next();

				//	System.out.println("Cell value: " + r.getCell(column).getStringCellValue());

					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							
							Cell c = cv.next();
							if(c.getCellType()==CellType.STRING)
							{
							a.add(c.getStringCellValue());
							}
							else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
							
						}
					}
					
					
				}

			}

		}
		return a;

	}
}