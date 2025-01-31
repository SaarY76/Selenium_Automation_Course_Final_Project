package all.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ApiTests {

    @Feature("API testing")
    @Link("https://automationexercise.com/api/productsList")
    @Test(description = "The test checks the API calling of getting all of the products from the website", groups = {"api"})
    public void testGetAllProductsAPI() {
        // Given - Set the base URL
        RestAssured.baseURI = "https://automationexercise.com";

        // When - Make the GET request
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/api/productsList");

        // Then - Verify the response status code
        response.then().statusCode(200);

        // Extract the response JSON and validate structure
        int responseCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 200, "Unexpected response code");

        List<Map<String, Object>> products = response.jsonPath().getList("products");
        Assert.assertNotNull(products, "Products list is null");
        Assert.assertFalse(products.isEmpty(), "Products list is empty");

        // Validate each product in the list
        for (Map<String, Object> product : products) {
            System.out.println(product);
            Assert.assertNotNull(product.get("id"), "Product ID is null");
            Assert.assertNotNull(product.get("name"), "Product name is null");
            Assert.assertNotNull(product.get("price"), "Product price is null");
            Assert.assertNotNull(product.get("brand"), "Product brand is null");

            // Validate nested category structure
            Map<String, Object> category = (Map<String, Object>) product.get("category");
            System.out.println(category);
            Assert.assertNotNull(category, "Product category is null");
            Assert.assertNotNull(category.get("category"), "Product category name is null");

            // Validate usertype inside category
            Map<String, Object> userType = (Map<String, Object>) category.get("usertype");
            System.out.println(userType);
            Assert.assertNotNull(userType, "Product usertype is null");
            Assert.assertNotNull(userType.get("usertype"), "Usertype value is null");
        }
    }
}
