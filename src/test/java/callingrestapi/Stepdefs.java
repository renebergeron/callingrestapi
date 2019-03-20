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

    @Given("^restapiurl is \"([^\"]*)\"$")
    public void restapiurl_is(String restapiurl) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("restapiurl: " + restapiurl);
        URL url = new URL(restapiurl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        int status = con.getResponseCode();
        System.out.println("Status = " + status);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine()) != null) {
        	content.append(inputLine);
        }
        in.close();
        con.disconnect();
        
        System.out.println("result is => " + content);
//        throw new PendingException();
    }

    @When("^calling rest api$")
    public void calling_rest_api() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("On devra caller l'url");

//        throw new PendingException();
    }

    @Then("^server should handle it an return success status$")
    public void server_should_handle_it_an_return_success_status() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Faudra voir quoi faire ici");

//        throw new PendingException();
    }

}