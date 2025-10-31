package com.nt.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.BooksExcelFile;

public class Helper {

    // Check if uploaded file is Excel
    public static boolean checkExcelFile(MultipartFile file) {
        return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<BooksExcelFile> excelFileInsertDB(InputStream io) throws IOException {

        List<BooksExcelFile> list = new ArrayList<>();
        Set<String> uniqeProducts = new HashSet<>();

        XSSFWorkbook work = new XSSFWorkbook(io);

        int numberOfSheets = work.getNumberOfSheets();

        int sheetIndex = 0;
        while (sheetIndex < numberOfSheets) {
            XSSFSheet sheet = work.getSheetAt(sheetIndex);
            Iterator<Row> iteratorRows = sheet.iterator();

            // Skip header
            if (iteratorRows.hasNext()) {
                iteratorRows.next();
            }

            while (iteratorRows.hasNext()) {
                Row iteratorRow = iteratorRows.next();

                if (iteratorRow == null || iteratorRow.getCell(0) == null) {
                    continue;
                }

                BooksExcelFile booksExcelFile = new BooksExcelFile();
                Iterator<Cell> cells = iteratorRow.iterator();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cell.getColumnIndex()) {
                        case 0:
                            booksExcelFile.setTitle(cell.getStringCellValue());
                            break;
                        case 1:
                            booksExcelFile.setAuthor(cell.getStringCellValue());
                            break;
                        case 2:
                            booksExcelFile.setGenre(cell.getStringCellValue());
                            break;
                        case 3:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                booksExcelFile.setYear((int) cell.getNumericCellValue());
                            } else {
                                booksExcelFile.setYear(Integer.parseInt(cell.getStringCellValue()));
                            }
                            break;
                        case 4:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                booksExcelFile.setPrice(cell.getNumericCellValue());
                            } else {
                                booksExcelFile.setPrice(Double.parseDouble(cell.getStringCellValue()));
                            }
                            break;
                            
                        default:
        					break;
                    }
                }

                list.add(booksExcelFile);
            }

            sheetIndex++;
        }

        work.close();
        return list;
    }
}
