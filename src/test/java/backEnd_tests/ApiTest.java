package backEnd_tests;

import com.configurations.BaseTestApi;
import com.microsoft.playwright.APIResponse;
import org.junit.Assert;
import org.junit.Test;

public class ApiTest extends BaseTestApi {

    @Test
    public void testManifestJson() {
        APIResponse response = request.get("/manifest.json");
        System.out.println("Status: " + response.status());
        System.out.println("Response: " + response.text());
        Assert.assertEquals("Expected HTTP status 200", 200, response.status());
        Assert.assertTrue("Response should contain 'name' field",
                response.text().contains("name"));
    }
}
