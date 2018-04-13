package test_site;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import keywords.AutoDesktop;
import keywords.AutoFile;
import keywords.AutoWeb;

public class Steps {
	public static AutoWeb web = new AutoWeb();
	public static AutoFile exc = new AutoFile();
	public static AutoDesktop des = new AutoDesktop();
	
	@Given("^nav to demoqa$")
	public void nav_to_demoqa() throws Exception {
		web.selectBrowser("chrome");
		web.navTo("http://demoqa.com/registration/");
		System.out.println("step 1");
	}

	@When("^user fills out info and submits it$")
	public void user_fills_out_info_and_submits_it() throws Throwable {
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
		System.out.println("Step 2");
	}

	@Then("^window should be closed$")
	public void window_should_be_closed() throws Throwable {
		//web.End();
		System.out.println("step 3");
	}
	
}
