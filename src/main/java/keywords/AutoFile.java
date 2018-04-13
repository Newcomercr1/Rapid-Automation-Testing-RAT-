//=====================================================================================================
//	Name: Charlie Newcomer
//	Emp#: 102576
//	Contact: Newcomercr1@gmail.com
//=====================================================================================================

package keywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AutoFile {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
	@SuppressWarnings("deprecation")
	/**
	 * This function finds a .xlxs file and reads the data than<br>
	 * displays that data in the console.
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example 
	 * String filepath = "C:\\desktop\\test.xlxs"<br>
	 * String sheetName = "Sheet1"<br>
	 * web.readExcel(filepath, sheetName);
	 */
	public void readExcel(String filePath, String sheetName) throws IOException {
		System.out.println("-- OPENING FILE --");
		File excel = new File(filePath);
		FileInputStream fis = new FileInputStream(excel);
		@SuppressWarnings("resource")
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet(sheetName);
		Iterator<Row> itr = sheet.iterator();
		while (itr.hasNext()) {
			Row row = itr.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.printf("%22s ", cell.getStringCellValue() + "||\t" + "\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.printf("%22s ", cell.getNumericCellValue() + "||\t" + "\t");

					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.printf("%22s", cell.getBooleanCellValue() + "||\t" + "\t");

					break;
				default:
				}
			}
			System.out.println("");
		}
	}

	/**
	 * This function finds a .xlxs file and writes data to<br>
	 * the file by appending a hashmap to the sheets row.
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example 
	 * int count = 0;<br>
     		Map<String, Object[]> newData = new HashMap<String, Object[]>();<br>
     		
     		List<Object> scrape = exc.scrapeExcel(filepath,  "Win10 Devices");<br>
     		
     		for(int i = 1; i < scrape.size()/2;i++) {<br>
     			newData.put(Integer.toString(i), new Object[] { scrape.get(count++),scrape.get(count++)});<br>
     		}<br>
    		exc.writeExcel(testPath, "Sheet1", newData);<br>
    		exc.readExcel(testPath,  "Sheet1");<br>
	 */
	public void writeExcel(String filePath, String sheetName, Map<String, Object[]> newData) throws IOException {
		File excel = new File(filePath);
		FileInputStream fis = new FileInputStream(excel);
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet(sheetName);

		Set<String> newRows = newData.keySet();

		int rownum = sheet.getLastRowNum();
		for (String key : newRows) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = newData.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				}
			}
		}
		FileOutputStream os = new FileOutputStream(excel);
		book.write(os);
		System.out.println("Writing on Excel file Finished ...");
		os.close();
		book.close();
		fis.close();
	}

	/**
	 * This function returns data from a excel in a arrylist<br>
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example 
	 List<Object> scrape = exc.scrapeExcel(filepath,  "Sheet1");
	 */
	public List<Object> scrapeExcel(String filePath, String sheetName) throws IOException {
		List<Object> dataList = new ArrayList<Object>();
		System.out.println("-- OPENING FILE --");
		File excel = new File(filePath);
		FileInputStream fis = new FileInputStream(excel);
		@SuppressWarnings("resource")
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet(sheetName);
		Iterator<Row> itr = sheet.iterator();
		while (itr.hasNext()) {
			Row row = itr.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:

					dataList.add(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:

					dataList.add(cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:

					dataList.add(cell.getBooleanCellValue());
					break;
				default:
				}
			}

		}
		return dataList;
	}

	/*
	 * public HashMap loadExcelLines(File filepath) { // Used the LinkedHashMap and
	 * LikedList to maintain the order HashMap<String, LinkedHashMap<Integer, List>>
	 * outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List>>();
	 * 
	 * LinkedHashMap<Integer, List> hashMap = new LinkedHashMap<Integer, List>();
	 * 
	 * String sheetName = null; // Create an ArrayList to store the data read from
	 * excel sheet. // List sheetData = new ArrayList(); FileInputStream fis = null;
	 * try { fis = new FileInputStream(filepath); // Create an excel workbook from
	 * the file system XSSFWorkbook workBook = new XSSFWorkbook(fis); // Get the
	 * first sheet on the workbook. for (int i = 0; i <
	 * workBook.getNumberOfSheets(); i++) { XSSFSheet sheet =
	 * workBook.getSheetAt(i); // XSSFSheet sheet = workBook.getSheetAt(0);
	 * sheetName = workBook.getSheetName(i);
	 * 
	 * Iterator rows = sheet.rowIterator(); while (rows.hasNext()) { XSSFRow row =
	 * (XSSFRow) rows.next(); Iterator cells = row.cellIterator();
	 * 
	 * List data = new LinkedList(); while (cells.hasNext()) { XSSFCell cell =
	 * (XSSFCell) cells.next(); cell.setCellType(Cell.CELL_TYPE_STRING);
	 * data.add(cell); } hashMap.put(row.getRowNum(), data);
	 * 
	 * // sheetData.add(data); } outerMap.put(sheetName, hashMap); hashMap = new
	 * LinkedHashMap<Integer, List>(); }
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } finally { if (fis != null) {
	 * try { fis.close(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } } }
	 * 
	 * return outerMap;
	 * 
	 * }
	 */

}
