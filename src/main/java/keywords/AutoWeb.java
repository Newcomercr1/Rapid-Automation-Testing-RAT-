//=====================================================================================================
//	Name: Charlie Newcomer
//	Emp#: 102576
//	Contact: Newcomercr1@gmail.com
//=====================================================================================================

package keywords;

import java.awt.Robot;
import java.io.File;
import java.util.Scanner;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.Screen;

import com.sun.glass.events.KeyEvent;


public class AutoWeb {
	private static WebDriver driver;
	private static Scanner sc = new Scanner(System.in);
	static File chrome = new File("target\\drivers\\chrome\\chromedriver.exe");
	static File firefox = new File("target\\drivers\\firefox\\geckodrive.exe");
	static File edge = new File("target\\drivers\\edge\\MicrosoftWebDriver.exe");
	static File ie = new File("target\\drivers\\ie\\IEDriverServer.exe");
	static File pic = new File("target\\pic");
	
	public static final String Path_chrome = chrome.getAbsolutePath();
	public static final String Path_firefox = firefox.getAbsolutePath();
	public static final String Path_edge = edge.getAbsolutePath();
	public static final String Path_ie = ie.getAbsolutePath();
	
	/**
	 * This function allows the user to select two different browser. The browsers
	 * that available are Chrome and Edge
	 * 
	 * @throws Exception
	 *             on any errors.
	 * @example selectBrowser("chrome"); or key.selectBrowser("edge");
	 */
	public void selectBrowser(String bro) {
		String browser = bro.toLowerCase();
		try {
			if (browser.equals("edge")) {
				System.out.println("-- PASS: EDGE BROWSER STARTING --");
				System.setProperty("webdriver.edge.driver", Path_edge);
				driver = new EdgeDriver();

			} else if (browser.equals("firefox")) {
				System.out.println("-- PASS: FIREFOX BROWSER STARTING--");
				System.setProperty("webdriver.gecko.driver", Path_firefox);
				driver = new FirefoxDriver();

			} else if (browser.equals("ie")) {
				System.out.println("-- PASS: IE BROWSER STARING --");
				System.setProperty("webdriver.ie.driver", Path_ie);
				driver = new InternetExplorerDriver();

			} else if (browser.equals("chrome")) {
				System.out.println("-- PASS: CHOME BROWSER STARTING --");
				System.setProperty("webdriver.chrome.driver", Path_chrome);
				driver = new ChromeDriver();

			} else if (browser.equals("ghost")) {
				System.out.println("-- PASS: GHOST BROWSER STARTING--");
				driver = new HtmlUnitDriver();

			}

		} catch (Exception e) {
			System.out.println("-- FAIL: BROWSER FAILED TO START--\n" + e.getStackTrace());
		}
	}

	/**
	 * This function navigates to selected URL.
	 * 
	 * @throws Exception
	 *             on any errors.
	 * @example web.navTo("google");
	 */
	public void navTo(String url) {
		try {
			driver.get(url);
			System.out.println("-- PASS: NAVIGATING TO " + url + " --");
			//driver.manage().window().maximize();

		} catch (Exception e) {
			System.out.println("-- FAIL: TO NAVIGATE TO " + url + " --\n" + e.getStackTrace());
		}
	}

	/**
	 * This function waits for selected amount of time .
	 * 
	 * @throws Exception
	 *             on any errors.
	 * @example Wait(1000);
	 */
	private void Wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println(e.getStackTrace());
		}
	}

	/**
	 * This function closes the browser.
	 * 
	 * @throws Exception
	 *             on any errors.
	 * @example web.end();
	 */
	public void End() {
		Wait(1000);
		driver.close();
	}

	/**
	 * This function finds an Textbox input and applies 1 of 4 actions.<br>
	 * 1) Click- click on input<br>
	 * 2) Settxt - sends a text to a input <br>
	 * 3) Gettext - gets the value of input<br>
	 * 4) Enter - applies enter key to input
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.inputText(1,null, "click");<br>
	 * 
	 *          web.inputText(1,"My Name", "settext");<br>
	 * 
	 *          web.inputText(1,null, "gettext");<br>
	 *          
	 *          web.inputText(1,null, "enter")
	 *          
	 */
	public void inputText(int n, String text, String Action) {
		try {
			String num = Integer.toString(n);
			switch (Action.toUpperCase()) {
			case "CLICK":
				// Perform click
				driver.findElement(By.xpath("(//input[@type='text'])[position()=" + num + "]")).click();
				System.out.println("-- PASS: FOUND INPUT TEXT TYPE # " + num + " --");
				Wait(1000);
				break;

			case "SETTEXT":
				// Set text on control
				driver.findElement(By.xpath("(//input[@type='text'])[position()=" + num + "]")).sendKeys(text);
				System.out.println("-- PASS: FOUND INPUT TEXT TYPE # " + num + " --");
				Wait(1000);
				break;

			case "GETTEXT":
				// Get text of an element
				driver.findElement(By.xpath("(//input[@type='text'])[position()=" + num + "]")).getText();
				System.out.println("-- PASS: FOUND INPUT TEXT TYPE # " + num + " --");
				Wait(1000);
				break;
			case "ENTER":
				// Get text of an element
				driver.findElement(By.xpath("(//input[@type='text'])[position()=" + num + "]")).sendKeys(Keys.ENTER);
				System.out.println("-- PASS: FOUND INPUT TEXT TYPE # " + num + " --");
				Wait(1000);
				break;

			default:
				break;

			}
		} catch (Exception e) {

			try {
				System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
				System.out.println("TRY AGAIN:  ");
				int retry = sc.nextInt();
				inputText(retry, text, Action);

			} catch (Exception e1) {
				System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
			}

		}
	}
	
	/**
	 * This function finds an password input and applies 1 of 4 actions.<br>
	 * 1) Click- click on input<br>
	 * 2) Settxt - sends a text to a input <br>
	 * 3) Gettext - gets the value of input<br>
	 * 4) Enter - applies enter key to input
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.password(1,null, "click");<br>
	 * 
	 *          web.password(1,"abc123", "settext");<br>
	 * 
	 *          web.password(1,null, "gettext");<br>
	 *          
	 *          web.password(1,null, "enter")
	 *          
	 */
	public void password(int n, String text, String Action) {
		try {
			String num = Integer.toString(n);
			switch (Action.toUpperCase()) {
			case "CLICK":
				// Perform click
				driver.findElement(By.xpath("(//input[@type='password'])[position()=" + num + "]")).click();
				System.out.println("-- PASS: FOUND INPUT PASSWORD TYPE # " + num + " --");
				Wait(1000);
				break;

			case "SETTEXT":
				// Set text on control
				driver.findElement(By.xpath("(//input[@type='password'])[position()=" + num + "]")).sendKeys(text);
				System.out.println("-- PASS: FOUND INPUT PASSWORD TYPE # " + num + " --");
				Wait(1000);
				break;

			case "GETTEXT":
				// Get text of an element
				driver.findElement(By.xpath("(//input[@type='password'])[position()=" + num + "]")).getText();
				System.out.println("-- PASS: FOUND INPUT PASSWORD TYPE # " + num + " --");
				Wait(1000);
				break;
				
			case "ENTER":
				// Get text of an element
				driver.findElement(By.xpath("(//input[@type='password'])[position()=" + num + "]")).sendKeys(Keys.ENTER);
				System.out.println("-- PASS: FOUND INPUT PASSWORD TYPE # " + num + " --");
				Wait(1000);
				break;

			default:
				break;

			}

		} catch (Exception e) {
			try {
				System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
				System.out.println("TRY AGAIN:  ");
				int retry = sc.nextInt();
				password(retry, text, Action);

			} catch (Exception e1) {
				System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
			}

		}

	}
	
	/**
	 * This function finds an email input and applies 1 of 4 actions.<br>
	 * 1) Click- click on input<br>
	 * 2) Settxt - sends a text to a input <br>
	 * 3) Gettext - gets the value of input<br>
	 * 4) Enter - applies enter key to input
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.password(1,null, "click");<br>
	 * 
	 *          web.password(1,"abc@aol.com", "settext");<br>
	 * 
	 *          web.password(1,null, "gettext");<br>
	 *          
	 *          web.password(1,null, "enter")
	 *          
	 */
	public void email(int n, String text, String Action) {
		try {
			String num = Integer.toString(n);
			System.out.println("");

			switch (Action.toUpperCase()) {
			case "CLICK":
				// Perform click
				driver.findElement(By.xpath("(//input[@type='email'])[position()=" + num + "]")).click();
				System.out.println("-- PASS: FOUND INPUT EMAIL TYPE # " + num + " --");
				Wait(1000);
				break;

			case "SETTEXT":
				// Set text on control
				driver.findElement(By.xpath("(//input[@type='email'])[position()=" + num + "]")).sendKeys(text);
				System.out.println("-- PASS: FOUND INPUT EMAIL TYPE # " + num + " --");
				Wait(1000);
				break;

			case "GETTEXT":
				// Get text of an element
				driver.findElement(By.xpath("(//input[@type='email'])[position()=" + num + "]")).getText();
				System.out.println("-- PASS: FOUND INPUT EMAIL TYPE # " + num + " --");
				Wait(1000);
				break;
				
			case "ENTER":
				// Get text of an element
				driver.findElement(By.xpath("(//input[@type='email'])[position()=" + num + "]")).sendKeys(Keys.ENTER);
				System.out.println("-- PASS: FOUND INPUT EMAIL TYPE # " + num + " --");
				Wait(1000);
				break;

			default:
				break;

			}

		} catch (Exception e) {
			try {
				System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
				System.out.println("TRY AGAIN:  ");
				int retry = sc.nextInt();
				email(retry, text, Action);

			} catch (Exception e1) {
				System.out.println("-- FAIL: NOT FOUND --\n" + e1.getStackTrace());
			}

		}

	}
	
	/**
	 * This function finds an search input by two ways<br>
	 * and enters a text.<br>
	 * 1)Find element by position 
	 * 2)Find element by text value
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.search(1,"Deadpool");<br>
	 * 
	 *          web.search("Google Search" ,"Deadpool");<br>
	 *      
	 */
	public void search(Object var, String text) {
		try {
			if (var instanceof Integer) {
				driver.findElement(By.xpath("(//input[@type='search'])[position()=" + var + "]")).sendKeys(text);
				driver.findElement(By.xpath("(//input[@type='search'])[position()=" + var + "]")).sendKeys(Keys.ENTER);
				System.out.println("-- PASS: FOUND INPUT SEARCH TYPE # " + var + " --");
				Wait(1000);

			} else if (var instanceof String) {

				driver.findElement(By.xpath("(//input[@type='search'])[position()=" + var + "]")).sendKeys(text);
				driver.findElement(By.xpath("(//input[@type='search'])[position()=" + var + "]")).sendKeys(Keys.ENTER);
				System.out.println("-- PASS: FOUND INPUT SEARCH TYPE # " + var + " --");
				Wait(1000);
			}

		} catch (Exception e) {
			try {
				if (var instanceof String) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("TRY AGAIN: ");
					String retry = sc.next();
					search(retry, text);

				} else if (var instanceof Integer) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("TRY AGAIN: ");
					int retry = sc.nextInt();
					search(retry, text);
				} else {
					System.out.println("--ERROR: NO DECIMALS --\n" + e.getStackTrace());
				}
			} catch (Exception e1) {
				System.out.println("--ERROR: NOT FOUND --\n" + e1.getStackTrace());
			}

		}
	}

	/**
	 * This function finds an submit input by two ways<br>
	 * and enters a text.<br>
	 * 1)Find element by position 
	 * 2)Find element by text value
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.submit(1);<br>
	 * 
	 *          web.submit("Submit");<br>
	 *      
	 */
	public void submit(Object var) {
		WebElement strXpath = null;
		try {
			if (var instanceof String) {
				strXpath = driver.findElement(By.xpath("//input[contains(@value, '" + var + "')]"));
				System.out.println("-- PASS: FOUND INPUT SUBMIT TYPE # " + var + " --");
				Wait(1000);
			} else if (var instanceof Integer) {
				strXpath = driver.findElement(By.xpath("(//input[@type='submit'])[position()=" + var + "]"));
				System.out.println("-- PASS: FOUND INPUT SUBMIT TYPE # " + var + " --");
				Wait(1000);
			}

			if (strXpath.isDisplayed()) {
				strXpath.click();
			}

		} catch (Exception e) {
			try {
				if (var instanceof String) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("TRY AGAIN: ");
					String retry = sc.next();
					submit(retry);

				} else if (var instanceof Integer) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("TRY AGAIN: ");
					int retry = sc.nextInt();
					submit(retry);
				} else {
					System.out.println("-- ERROR: NO DECIMALS --\n" + e.getStackTrace());
				}
			} catch (Exception e1) {
				System.out.println("-- ERROR: NOT FOUND --\n" + e1.getStackTrace());
			}

		}

	}

	/**
	 * This function finds an button input by two ways<br>
	 * and enters a text.<br>
	 * 1)Find element by position 
	 * 2)Find element by text value
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.button(1);<br>
	 * 
	 *          web.button("add");<br>
	 *      
	 */
	public void button(Object var) {
		WebElement strXpath = null;
		try {
			if (var instanceof String) {
				strXpath = driver.findElement(By.xpath("//button[contains(text(),'" + var + "')]"));
				System.out.println("-- PASS: FOUND INPUT BUTTON TYPE # " + var + " --");
				Wait(1000);
			} else if (var instanceof Integer) {
				strXpath = driver.findElement(By.xpath("(//button[position()=" + var + "]"));
				System.out.println("-- PASS: FOUND INPUT BUTTON TYPE # " + var + " --");
				Wait(1000);
			}

			if (strXpath.isDisplayed()) {
				strXpath.click();
			}
		} catch (Exception e) {
			try {
				if (var instanceof String) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("TRY AGAIN: ");
					String retry = sc.next();
					button(retry);

				} else if (var instanceof Integer) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("TRY AGAIN: ");
					int retry = sc.nextInt();
					button(retry);
				} else {
					System.out.println("--ERROR: NOT FOUND--\n" + e.getStackTrace());
				}
			} catch (Exception e1) {
				System.out.println("--ERROR: NOT FOUND--\n" + e1.getStackTrace());
			}

		}

	}

	/**
	 * This function finds an link input by 4 ways<br>
	 * and enters a text.<br>
	 * 1)Find element by href
	 * 2)Find element by text value
	 * 3)Find element by partial text
	 * 4)Find element by position
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.link("https://mail.google.com/mail/?tab=wm");<br>
	 * 
	 *          web.link("Gmail");<br>
	 *          
	 *          web.link("Gma");<br>
	 *      
	 *      	web.link(6);
	 */
	public void link(Object var) {
		WebElement strXpath = null;
		try {
			if (var instanceof String) {
				try {
					strXpath = driver.findElement(By.xpath("//a[@href = '" + var + "']"));
					System.out.println("-- PASS: FOUND LINK @HREF " + var + " --");
					Wait(1000);
				} catch (Exception e) {
					try {
						String text = var.toString();
						strXpath = driver.findElement(By.linkText(text));
						System.out.println("-- PASS: FOUND LINK TEXT " + var + " --");
						Wait(1000);
					} catch (Exception e1) {
						String text = var.toString();
						strXpath = driver.findElement(By.partialLinkText(text));
						System.out.println("-- PASS: FOUND PARTIAL LINK TEXT " + var + " --");
						Wait(1000);
					}
				}

			} else if (var instanceof Integer) {
				strXpath = driver.findElement(By.xpath("(//a[position()=" + var + "]"));
				System.out.println("-- PASS: FOUND LINK # " + var + " --");
				Wait(1000);
			}
			if (strXpath.isDisplayed()) {
				strXpath.click();
			}

		} catch (Exception e) {
			try {
				if (var instanceof String) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("TRY AGAIN: ");
					String retry = sc.next();
					link(retry);

				} else if (var instanceof Integer) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("TRY AGAIN: ");
					int retry = sc.nextInt();
					link(retry);
				}
			} catch (Exception e1) {
				System.out.println("-- ERROR: NOT FOUND --\n" + e1.getStackTrace());
			}

		}

	}

	/**
	 * This function finds an checkbox input by two ways<br>
	 * and enters a text.<br>
	 * 1)Find element by position 
	 * 2)Find element by text value
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.checkbox(1);<br>
	 * 
	 *          web.checkbox("Married");<br>
	 *      
	 */
	public void checkbox(Object var) {

		WebElement strXpath = null;
		try {
			if (var instanceof String) {
				strXpath = driver.findElement(By.xpath("//input[contains(@value, '" + var + "')]"));
				System.out.println("-- PASS: FOUND INPUT CHECKBOX TYPE " + var + " --");
				Wait(1000);
			} else if (var instanceof Integer) {
				strXpath = driver.findElement(By.xpath("(//input[@type='checkbox'])[position()=" + var + "]"));
				System.out.println("-- PASS: FOUND INPUT CHECKBOX TYPE # " + var + " --");
				Wait(1000);
			} else {
				System.out.println("-- ERROR: DECIMAL WAS ENTER --");
			}
			if (strXpath.isDisplayed()) {
				strXpath.click();
			}

		} catch (Exception e) {
			try {
				if (var instanceof String) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("try again: ");
					String retry = sc.next();
					checkbox(retry);

				} else if (var instanceof Integer) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("try again: ");
					int retry = sc.nextInt();
					checkbox(retry);
				} else {
					System.out.println("-- ERROR: DECIMAL WAS ENTER --\n" + e.getStackTrace());
				}
			} catch (Exception e1) {
				System.out.println("--ERROR: NOT FOUND--\n" + e1.getStackTrace());
			}

		}

	}

	/**
	 * This function finds an radio input by two ways<br>
	 * and enters a text.<br>
	 * 1)Find element by position 
	 * 2)Find element by text value
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.radio(1);<br>
	 * 
	 *          web.radio("Married");<br>
	 *      
	 */
	public void radio(Object var) {
		WebElement strXpath = null;
		try {
			if (var instanceof String) {
				strXpath = driver.findElement(By.xpath("//input[contains(@value, '" + var + "')]"));
				System.out.println("-- PASS: FOUND INPUT RADIO TYPE " + var + " --");
				Wait(1000);
			} else if (var instanceof Integer) {
				strXpath = driver.findElement(By.xpath("(//input[@type='radio'])[position()=" + var + "]"));
				System.out.println("-- PASS: FOUND INPUT RADIO TYPE # " + var + " --");
				Wait(1000);
			} else {
				System.out.println("-- ERROR: DECIMAL WAS ENTER --");
			}

			if (strXpath.isDisplayed()) {
				strXpath.click();
			}
		} catch (Exception e) {
			try {
				if (var instanceof String) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("-- TRY AGAIN: ");
					String retry = sc.next();
					checkbox(retry);

				} else if (var instanceof Integer) {
					System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("-- TRY AGAIN: ");
					int retry = sc.nextInt();
					checkbox(retry);
				} else {
					System.out.println("-- ERROR: DECIMAL WAS ENTER --\n" + e.getStackTrace());
				}
			} catch (Exception e1) {
				System.out.println("-- ERROR: NOT FOUND --\n" + e1.getStackTrace());
			}

		}

	}

	/**
	 * This function finds an select input by Label,<br>
	 * position, and option.<br>
	 * 1)Find element by label text 
	 * 2)then picks the select by position
	 * 3)finally picks option by string
	 * 
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.select("Date of Birth", 3, "1959");<br>
	 *
	 */
	public void select(String label, int select, String opt) {
		try {

			Select dropdown = new Select(driver
					.findElement(By.xpath("(//label[text()='" + label + "']/..//select)[position()=" + select + "]")));
			System.out.println("-- PASS: FOUND INPUT SELECT LABEL " + label + " --");
			System.out.println("-- PASS: FOUND INPUT SELECT SELECT " + select + " --");
			System.out.println("-- PASS: FOUND INPUT SELECT OPT " + opt + " --");
			dropdown.selectByValue(opt);

		} catch (Exception e) {
			try {
				System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
					System.out.println("-- TRY AGAIN: ");
					System.out.println("Label: ");
					String label1 = sc.next();
					System.out.println("Select: ");
					int  select1 = sc.nextInt();
					System.out.println("Otp.: ");
					String opt1 = sc.next();
					
					select(label1,select1,opt1 );
			}catch (Exception e1) {
				System.out.println("-- ERROR: NOT FOUND --\n" + e.getStackTrace());
			}
		}

	}


	public void table(String header, Object row, Object col) {
		try {

			int Row_count = driver.findElements(By.xpath("//*tbody/tr[text()= '" + header + "']")).size();
			System.out.println("Number Of Rows = " + Row_count);

			int Col_count = driver.findElements(By.xpath("//*th[text()=" + header + "/table/tbody/td]")).size();
			System.out.println("Number Of Columns = " + Col_count);

			String first_part = "//*[text()=" + header + "/table/tbody/tr";
			String second_part = "]/td[";
			String third_part = "]";

			for (int i = 1; i <= Row_count; i++) {

				for (int j = 1; j <= Col_count; j++) {

					String final_xpath = first_part + i + second_part + j + third_part;
					// Will retrieve value from located cell and print It.
					String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
					System.out.print(Table_data + "  ");
				}
				System.out.println("");
				System.out.println("");
			}

		} catch (Exception e) {
			System.out.println("-- ERROR: NOT FOUND --\n" + e.getStackTrace());
		}
	}
	/**
	 * This function finds text by class name and,<br>
	 * returns it
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example String text = web.getText("gb1");<br>
	 *
	 */
	public String getText(String classN) {
		String text = "";
		try {
			text = driver.findElement(By.className(classN)).getText();
		}catch(Exception e) {
			System.out.println("-- FAIL: NOT FOUND --\n" + e.getStackTrace());
			System.out.println("-- TRY AGAIN: ");
			String retry = sc.next();
			checkbox(retry);
		}
		return text;
	}
	
	
}
