//=====================================================================================================
//	Name: Charlie Newcomer
//	Emp#: 102576
//	Contact: Newcomercr1@gmail.com
//=====================================================================================================

package keywords;

import java.awt.Robot;

import org.sikuli.script.Screen;

import com.sun.glass.events.KeyEvent;

public class AutoDesktop {
	private Screen s = new Screen();
	/**
	 * This function performs desktop automation ussing snippings of .png<br>
	 * to locate items on the desktop and applies 1 of 4 action<br>
	 * 1) Click- click on item<br>
	 * 2) Settxt - sends a text to a input <br>
	 * 3) Gettext - gets the value of input<br>
	 * 4) Enter - applies enter key to input
	 * @throws Exception
	 *             on any errors.
	 * 
	 * @example web.inputText(1,null, "click");<br>
	 *          
	 */
	public void autoStep(String file, String name, String text,String Action) {

		try {
			Robot robot = new Robot();

			String path = file;
		
			Thread.sleep(1000);
			
			switch (Action.toUpperCase()) {
			case "CLICK":
				// Perform click
				Thread.sleep(500);
				s.click(path + "\\" + name);
				Thread.sleep(500);
				break;

			case "SETTEXT":
				// Set text on control
				Thread.sleep(500);
				s.type(text);
				Thread.sleep(500);
				break;
			
			case "ENTER":
				// Enter text of an element
				Thread.sleep(500);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(500);
				break;
			case "GETTEXT":
				// Get text of an element
				
				break;

			default:
				break;

			}

		} catch (Exception e) {
			System.out.println("--ERROR:  NOT FOUND--\n" + e.getStackTrace());

		}

	}
}
