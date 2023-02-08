package starter.Sirloin.ProductsStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.Sirloin.ProductAPI;
import starter.Sirloin.Utils.Constant;
import starter.Sirloin.Utils.SirloinResponses;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class GetProductStepDef {

    @Steps
    ProductAPI productAPI;

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRob3JpemVkIjp0cnVlLCJ1c2VySUQiOjU0fQ.4OjLwaZl68OM8zHYCSmNhD3S579V8n8JV0iT2GwAD6c";

    @Given("Get display all product")
    public void getDisplayAllProduct() {
        productAPI.getProduct(token);
    }

    @When("Send request getproduct")
    public void sendRequestGetproduct() {
        SerenityRest.when().get(ProductAPI.GET_PRODUCT);
    }

    @And("Validate json schema getProduct")
    public void validateJsonSchemaGetProduct() {
        File jsonSchema = new File(Constant.getPRODUCT_JSON_SCHEMA + "/getAllProductSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Given("Get display product by id {int}")
    public void getDisplayProductById(int id) {
        productAPI.getProductById(id, token);
    }

    @When("Send request getproduct by id")
    public void sendRequestGetproductById() {
        SerenityRest.when().get(ProductAPI.GET_PRODUCT_ByID);
    }

    @And("Response body should be {int}")
    public void responseBodyShouldBe(int id) {
        SerenityRest.then()
                .body(SirloinResponses.ID, equalTo(id));
    }

    @And("Validate json schema getProduct by id")
    public void validateJsonSchemaGetProductById() {
        File jsonSchema = new File(Constant.getPRODUCT_JSON_SCHEMA + "/getProductByIdSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Given("Get display product by invalid {string}")
    public void getDisplayProductByInvalid(String id) {
        productAPI.getProductInvalidID(id, token);
    }

    @When("Send request getproduct by invalid id")
    public void sendRequestGetproductByInvalidId() {
        SerenityRest.when().get(ProductAPI.GET_PRODUCT_INVALID_ID);
    }
    @And("Response body should be {string}")
    public void responseBodyShouldBe(String message) {
        SerenityRest.then()
                .body(SirloinResponses.MESSAGE, equalTo(message));
    }

    @Given("Get display all product with valid token")
    public void getDisplayAllProductWithValidToken() {
        productAPI.getProduct(token);

    }

    @Given("Get display all product with invalid token")
    public void getDisplayAllProductWithInvalidToken() {
        String invalidToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRob3JpemVkIjp5cnVlLCJ1c2VySUQiOjU0fQ.4OjLwaZl68OM8zHYCSmNhD3S579V8n8JV0iT2GwAD6c";
        productAPI.getProduct(invalidToken);
    }

    @And("Validate json schema getProduct negative case")
    public void validateJsonSchemaGetProductNegativeCase() {
        File jsonSchema = new File(Constant.getPRODUCT_JSON_SCHEMA + "/negativeCaseGetProductSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }
}
