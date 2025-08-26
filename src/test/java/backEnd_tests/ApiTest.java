package backEnd_tests;

import com.configurations.BaseTestApi;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.Assert;
import org.junit.Test;

import static com.utilities.HeaderConstants.*;

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

    @Test
    public void testPostRequest() {
        APIResponse response = request.post("/test/post/aPI",
                RequestOptions.create()
                        .setHeader(CONTENT_TYPE_KEY, JSON_TYPE)
                        .setData("{test:1}")
        );

        System.out.println("POST Status: " + response.status());
        System.out.println("POST Body: " + response.text());

        Assert.assertEquals(response.status(), 201);
        Assert.assertTrue(response.text().contains("test:1"));
    }
}
