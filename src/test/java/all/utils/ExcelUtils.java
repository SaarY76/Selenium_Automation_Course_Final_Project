package all.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static Object[][] readExcelData(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            int sheetIndex = 0;
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            int rowCount = sheet.getLastRowNum() + 1; // +1 to include the first row
            int columnCount = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rowCount][columnCount];

            for (int i = 0; i < rowCount; i++) { // Start loop from 0 to include the first row
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < columnCount; j++) {
                    if (row != null && row.getCell(j) != null) {
                        data[i][j] = row.getCell(j).toString();
                    }
                }
            }

            workbook.close();
            fis.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}