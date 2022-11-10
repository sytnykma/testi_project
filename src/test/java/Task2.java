import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

public class Task2 {

    @BeforeClass
    private void configureApi(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new ResponseLoggingFilter());

        JsonConfig jsonConfig = JsonConfig.jsonConfig()
                .numberReturnType(JsonPathConfig.NumberReturnType.DOUBLE);
        RestAssured.config = RestAssured.config()
                .jsonConfig(jsonConfig);

        RestAssured.baseURI="http://ip-api.com";
        RestAssured.requestSpecification = given()
                .contentType(ContentType.JSON);
    }

    @Test(description = "Validate current geolocation in Kiev")
    public void checkGetRequest() {
        given()
                .when().get("/json/92.60.179.219")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(new File("src/test/resources/response.schemas/geolocation_response.json")))
                .and()
                .body("lat", is(closeTo(50.453, 0.01)))
                .body("lon", is(closeTo(30.531, 0.01)));
    }
    //I don't see any reasons to deserialize it, we can check all fields we need just in Rest-Assured inbox methods,
    //but if we really need it we may parse it with Pogo library and create response body class with such fields
}
