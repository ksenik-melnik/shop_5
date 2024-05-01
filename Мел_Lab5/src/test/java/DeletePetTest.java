import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeletePetTest {
    static RequestSpecification rs;

    @BeforeClass
    public static void setUp() {
        given()
                .contentType(ContentType.JSON)
                .body("""
            {
              "id": 900,
              "name": "vasa",
              "photoUrls": [
                "string"
              ]
            }""")
                .post("https://petstore.swagger.io/v2/pet");
        rs = given()
                .baseUri("https://petstore.swagger.io/v2/pet/900");
    }

    @Test
    public void testResponseCodeOnDelete() {
        given(rs)
                .delete()
                .then()
                .statusCode(oneOf(200,201,202));
    }

}
