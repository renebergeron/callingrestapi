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

    @Then("^valid the json format$")
    public void valid_the_json_formant() throws Exception {
    	JsonArray arr = new JsonParser().parse(content.toString()).getAsJsonArray();

    	assertNotNull("La list ne devrait pas être null", arr);
        for (int i = 0; i < arr.size(); i++) {
          JsonObject obj = arr.get(i).getAsJsonObject();
          
          assertTrue("La balise idProduit est absente.", obj.has("idProduit"));
          assertTrue("La balise productCode est absente.", obj.has("codeProduit"));
          assertTrue("La balise codePack est absente.", obj.has("codePack"));
          assertTrue("La balise libelleProduit est absente.", obj.has("libelleProduit"));
          assertTrue("La balise epjAnnonceur est absente.", obj.has("epjAnnonceur"));
          assertTrue("La balise epjNegociateur est absente.", obj.has("epjNegociateur"));
          assertTrue("La balise epjNegociateur est absente.", obj.has("epjNegociateur"));
          assertTrue("La balise etatProduit est absente.", obj.has("etatProduit"));
          assertTrue("La balise dateSouscription est absente.", obj.has("dateSouscription"));
          assertTrue("La balise dateDebutParution est absente.", obj.has("dateDebutParution"));
          assertTrue("La balise dateFinParution est absente.", obj.has("dateFinParution"));
          assertTrue("La balise dateDebutFacturation est absente.", obj.has("dateDebutFacturation"));
          assertTrue("La balise dateFinFacturation est absente.", obj.has("dateFinFacturation"));
          assertTrue("La balise dateDemandeAnnulation est absente.", obj.has("dateDemandeAnnulation"));
          assertTrue("La balise origine est absente.", obj.has("origine"));
          assertTrue("La balise engagement est absente.", obj.has("engagement"));
          assertTrue("La balise periodeEssai est absente.", obj.has("periodeEssai"));

          if (!obj.get("periodeEssai").isJsonNull()) {
        	  JsonObject periodeEssai = obj.get("periodeEssai").getAsJsonObject();
              assertTrue("La balise dateDebut de l'objet periodeEssai est absente.", periodeEssai.has("dateDebut"));
              assertTrue("La balise dateFin de l'objet periodeEssai est absente.", periodeEssai.has("dateFin"));
          }
          
          if (!obj.get("options").isJsonNull()) {
	    	  JsonArray options = obj.get("options").getAsJsonArray();
	    	  
	          for (int j = 0; j < options.size(); i++) {
	              JsonObject option = options.get(i).getAsJsonObject();
	
	              assertTrue("La balise codeOption de l'objet option est absente.", option.has("codeOption"));
	              assertTrue("La balise libelleOption de l'objet option est absente.", option.has("libelleOption"));
	              assertTrue("La balise quantite de l'objet option est absente.", option.has("quantite"));
	          }
	          }
        	}
    }

    @Then("^tester les donnees$")
    public void tester_les_donnees() throws Exception {
    	System.out.println("Voir comment valide les datas si il y a lieu!");
    	// Is it necessairy to tests the values since the data is not fix
    }
}