package callingrestapi;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    @Then("^valid the json formant$")
    public void valid_the_json_formant() throws Exception {
    	JsonArray arr = new JsonParser().parse(content.toString()).getAsJsonArray();

    	assertNotNull("The fucking object shouldn't be null", arr);
        for (int i = 0; i < arr.size(); i++) {
          JsonObject obj = arr.get(i).getAsJsonObject();
          String productId = obj.get("idProduit").getAsString();
          String productCode = obj.get("codeProduit").getAsString();
          String codePack = obj.get("codePack").getAsString();
//          String libelleProduit = obj.get("libelleProduit").getAsString();
//          String epjAnnonceur = obj.get("epjAnnonceur").getAsString();
//          String epjNegociateur = obj.get("epjNegociateur").getAsString();
//          String epjBeneficiaire = obj.get("epjBeneficiaire").getAsString();
//          String etatProduit = obj.get("etatProduit").getAsString();
          System.out.println("productId : " + productId);
          System.out.println("codeProduit : " + productCode);
//          System.out.println("codePack : " + codePack);
//          System.out.println("epjAnnonceur : " + epjAnnonceur);
//          System.out.println("epjNegociateur : " + epjNegociateur);
//          System.out.println("epjBeneficiaire : " + epjBeneficiaire);
//          System.out.println("etatProduit : " + etatProduit);

        }
    	System.out.println("The content of the API is => " + content);
    	// Do somme validation on the json format if possible
    }

    @Then("^tester les donnees$")
    public void tester_les_donnees() throws Exception {
    	System.out.println("Voir comment valide les datas si il y a lieu!");
    	// Is it necessairy to tests the values since the data is not fix
    }
}