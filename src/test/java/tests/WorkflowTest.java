package tests;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkflowTest extends TestBase {
    private static String authToken; // Store token across tests

    @Test(priority = 1)
    public void testUserLogin() {
        String payload = "{ \"username\": \"testuser\", \"password\": \"password123\" }";
        Response response = postRequest("/login", payload);

        Assert.assertEquals(response.getStatusCode(), 200, "Login should be successful");

        // Extract token
        authToken = response.jsonPath().getString("token");
        Assert.assertNotNull(authToken, "Auth token should not be null");
    }

    @Test(priority = 2, dependsOnMethods = "testUserLogin")
    public void testSelectReward() {
        String payload = "{ \"rewardId\": 1 }"; // Selecting reward with ID 1
        Response response = postRequestWithAuth("/rewards/select", payload, authToken);

        Assert.assertEquals(response.getStatusCode(), 200, "Reward selection should be successful");
        Assert.assertEquals(response.jsonPath().getInt("points"), 10, "User should receive +10 points");
    }

    @Test(priority = 3, dependsOnMethods = "testSelectReward")
    public void testCheckUserPoints() {
        Response response = getRequestWithAuth("/user/points", authToken);

        Assert.assertEquals(response.getStatusCode(), 200, "User points should be retrievable");
        Assert.assertTrue(response.jsonPath().getInt("points") >= 10, "Points should be at least 10");
    }
}
