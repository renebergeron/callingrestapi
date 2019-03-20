package callingrestapi;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Stepdefs {

	HttpURLConnection con;
    StringBuffer content = new StringBuffer();
	
    @Given("^restapiurl is \"([^\"]*)\"$")
    public void restapiurl_is(String restapiurl) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("restapiurl: " + restapiurl);
        URL url = new URL(restapiurl);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        int status = con.getResponseCode();
        System.out.println("Status = " + status);
        assertEquals(200, status);
    }

    @When("^calling rest api$")
    public void calling_rest_api() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;

        while((inputLine = in.readLine()) != null) {
        	content.append(inputLine);
        }
        in.close();
        con.disconnect();
        
        assertNotNull(content);
    }

    @Then("^server should handle it an return success status$")
    public void server_should_handle_it_an_return_success_status() throws Exception {
    	System.out.println("The content of the API is => " + content);
    }

}