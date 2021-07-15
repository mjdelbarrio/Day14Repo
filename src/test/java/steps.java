import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class steps {

    private Response response;

    @When("I check the details of student {int}")
    public void i_check_the_details_of_student(Integer studentID) {
        given().contentType(ContentType.JSON);
        RestAssured.baseURI = "https://it-foundations.app.ap.assurity.cloud/";
        response = RestAssured.get("people/" + studentID);
        //System.out.println(studentID);


    }
    @Then("I can see that their name is {string}")
    public void i_can_see_that_their_name_is(String studentName) {
        String fname = (String) response.jsonPath().getJsonObject("firstName");
        String lname = (String) response.jsonPath().getJsonObject("lastName");
        //String fname = nameResponse.toString();
        String fullName = fname + " " + lname;
        //System.out.println(fullName);
        assertEquals("Jane Jones", fullName);


    }
    @Then("they have a {string} from {string}")
    public void they_have_a_from(String degree, String university ) {
        String deg = (String) response.jsonPath().getJsonObject("degree");
        String uni = (String) response.jsonPath().getJsonObject("university");
        //System.out.println(deg);
        //System.out.println(uni);
        assertEquals("BSc Computer Science", deg);
        assertEquals("Sydney Tech School", uni);
    }


//    RestAssured.baseURI = "https://it-foundations.app.ap.assurity.cloud/";
//    response = RestAssured.get("people/" + studentId);
//System.out.println(response.asPrettyString());
//System.out.println(response.path("firstName"));

}
