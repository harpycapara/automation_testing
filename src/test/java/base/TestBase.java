package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class TestBase {
    protected RequestSpecification request;
    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Example API
        request = given().header("Content-Type", "application/json");
    }

    protected Response getRequest(String endpoint) {
        return request.get(endpoint);
    }

    protected Response postRequest(String endpoint, String payload) {
        return given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post(endpoint);
    }

    protected Response postRequestWithAuth(String endpoint, String payload, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .post(endpoint);
    }

    protected Response getRequestWithAuth(String endpoint, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .get(endpoint);
    }
}
