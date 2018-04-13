//=====================================================================================================
//	Name: Charlie Newcomer
//	Emp#: 102576
//	Contact: Newcomercr1@gmail.com
//=====================================================================================================

package pack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.StopWatch;

import keywords.AutoDesktop;
import keywords.AutoFile;
import keywords.AutoWeb;

public class App {
	public static AutoWeb web = new AutoWeb();
	public static AutoFile exc = new AutoFile();
	public static AutoDesktop des = new AutoDesktop();
	public static String filepath = ("C:\\Users\\bts102576\\Desktop\\name list.xlsx");
	public static String testPath = "C:\\Users\\bts102576\\Desktop\\test.xlsx";

	public static void main(String[] args) {

		try {
			StopWatch pageLoad = new StopWatch();
			// This method starts the stop watch.
			pageLoad.start();
			//This method show the error handling for the autoWeb class and how easy it to
			// locate elements without knowing to much of the code on the page.
			// 1) when the browser open type 1 in the console
			//    10 is entered which doesn't exist, so correct it by typing 1
			//    which represent the first text box.
			// 2) then type 1, 2, or 3 for checkbox in the console.
			//demoqaTest();
			adfTest();
			// this is an example of data being scraped off a web page
			// and stored on a excel.
			// excel can be found in target\Excel folder.
			getText();

			// An example of desktop automation use pics
			// in the pic folder
			//desktopTest();
			pageLoad.stop();
			System.out.println("Total Page Load Time: " + pageLoad.getTime() + "milliseconds");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void demoqaTest() {
		// chrome
		web.selectBrowser("chrome");
		web.navTo("http://google.com");
		web.inputText(10, "Charlie", "settext");
		web.inputText(2, "Newcomer", "settext");
		web.radio(2);
		web.checkbox(6);
		web.select("Country", 1, "United States");
		web.select("Date of Birth", 1, "1");
		web.select("Date of Birth", 2, "5");
		web.select("Date of Birth", 3, "1959");
		web.inputText(3, "175999211688", "settext");
		web.inputText(4, "Newcomer", "settext");
		web.inputText(5, "NqfwrwR1@gmail.com", "settext");
		web.password(1, "SGGGDw1234501", "settext");
		web.password(2, "SGGGDw1234501", "settext");
		web.submit("Submit");
		web.End();
	}

	public static void adfTest() {
		web.selectBrowser("chrome");
		web.navTo("https://public.co.pinellas.fl.us/pbinquiry/faces/index.jspx");
		web.inputText(1, "park", "settext");
		web.button("Search");
		web.table("PARK ANNETTE S                ", 1, 1);
		web.End();
	}

	public static void desktopTest() {
		File pic = new File("target\\pic");
		String path = pic.getAbsolutePath();
		des.autoStep(path, "step1.PNG", null, "click");
		des.autoStep(path, null, "calculator", "settext");
		des.autoStep(path, null, null, "enter");
		des.autoStep(path, "step2.PNG", null, "click");
		des.autoStep(path, "step3.PNG", null, "click");
		des.autoStep(path, "step4.PNG", null, "click");
		des.autoStep(path, "step1.PNG", null, "click");
		des.autoStep(path, null, "note", "settext");
		des.autoStep(path, null, null, "enter");
		des.autoStep(path, "step6.PNG", "Hello Dean!!!", "settext");
	}

	public static void excelTest() throws IOException {
		int count = 0;
		Map<String, Object[]> newData = new HashMap<String, Object[]>();

		List<Object> scrape = exc.scrapeExcel(filepath, "Win10 Devices");

		for (int i = 1; i < scrape.size() / 2; i++) {
			newData.put(Integer.toString(i), new Object[] { scrape.get(count++), scrape.get(count++) });
		}
		exc.writeExcel(testPath, "Sheet1", newData);
		exc.readExcel(testPath, "Sheet1");
	}

	public static void getText() throws IOException {
		int counter = 0;
		Map<String, Object[]> newData = new HashMap<String, Object[]>();
		List<Object> dataList = new ArrayList<Object>();
		dataList.add("Ticker");
		dataList.add("Range");
		web.selectBrowser("chrome");
		web.navTo("https://finance.google.com/finance");
		String[] ticker = { "vhc", "rad", "ge", "msft", "nipnf", "covs", "zixi" };
		for (int i = 0; i < ticker.length; i++) {
			web.inputText(2, ticker[i], "settext");
			web.inputText(2, null, "enter");
			dataList.add(web.getText("appbar-snippet-secondary"));
			dataList.add(web.getText("val"));
		}
		web.End();

		for (int k = 0; k < dataList.size() / 2; k++) {
			System.out.println(dataList.get(counter++) + " " + dataList.get(counter++));
		}
		int count = 0;
		for (int j = 0; j < dataList.size() / 2; j++) {
			newData.put(Integer.toString(j), new Object[] { dataList.get(count++), dataList.get(count++) });
		}
		File stock = new File("target\\Excel\\Stock.xlsx");
		String Path_stock = stock.getAbsolutePath();
		exc.writeExcel(Path_stock, "Sheet1", newData);
	}
}
