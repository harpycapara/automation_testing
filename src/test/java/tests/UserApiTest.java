package tests;

import base.TestBase;
import base.UserTestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends UserTestBase {
    @Test
    public void testGetUsers() {
        Response response = getRequest("/users");

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP status 200");
        Assert.assertTrue(response.getBody().asString().contains("Leanne Graham"), "User should exist");
    }

    @Test
    public void testCreateUser() {
        String payload = "{ \"name\": \"John Doe\", \"username\": \"johndoe\", \"email\": \"john@example.com\" }";
        Response response = postRequest("/users", payload);

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201, "Expected HTTP status 201");
        Assert.assertTrue(response.getBody().asString().contains("John Doe"), "User should be created");
    }
}
