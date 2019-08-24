package oakley.Helper;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	XSSFSheet ExcelWSheet;
	XSSFWorkbook ExcelWBook;
	int temp;
	int colIndex = -1;
	public XSSFRow row = null;
	public String cell = null;
	String text = null;
	String celltype;

	Excel() throws Exception {
		FileInputStream ExcelFile = new FileInputStream("C:/Users/Gopal/Desktop/Inprogress/Writesheet.xlsx");
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet("Sheet1");

	}

	public int GetTestCount() {
		try {
			return ExcelWSheet.getLastRowNum();
		} catch (Exception e) {
			System.out.println("Error in GetTestCount function");
		}
		return 0;
	}

	public String GetTestName(int rowNumber) {
		try {
			celltype = ExcelWSheet.getRow(rowNumber).getCell(1).getStringCellValue();
		}

		catch (Exception e) {
			System.out.println("Error in functionOne");
		}

		return celltype;
	}

	public String GetTestCaseID(int rowNumber) {
		try {
			celltype = ExcelWSheet.getRow(rowNumber).getCell(0).getStringCellValue();
		}

		catch (Exception e) {
			System.out.println("Error in functionOne");
		}

		return celltype;
	}

	public String GetTestCaseData(String TestCaseId, String type) {
		try {
			temp = getRowIndex(TestCaseId);
			colIndex = getColumnIndex(type);
			celltype = ExcelWSheet.getRow(temp).getCell(colIndex).getStringCellValue();

			// System.out.println(celltype);
		}

		catch (Exception e) {
			System.out.println("Error in functionOne");
		}

		return celltype;
	}

	public int getRowIndex(String TestCaseId) {
		try {

			for (int i = 0; i <= ExcelWSheet.getLastRowNum(); i++) {
				Row temprow = ExcelWSheet.getRow(i);
				for (int j = 0; j < ExcelWSheet.getRow(0).getLastCellNum(); j++) {
					Cell cell = temprow.getCell(j);
					try {
						if (cell.getStringCellValue().equals(TestCaseId)) {
							temp = i;
							break;
						}
					}

					catch (Exception e) {
						System.out.println("Row is empty");
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return temp;
	}

	public int getColumnIndex(String browser) throws Exception {
		try {
			row = ExcelWSheet.getRow(0);
			for (int i = 0; i <= row.getLastCellNum(); i++) {
				Cell c = row.getCell(i);
				text = c.getStringCellValue();
				if (browser.equals(text)) {
					colIndex = i;

					break;
				}
			}
		}

		catch (Exception e) {
			System.out.println("Error");
		}
		return colIndex;
	}

}
