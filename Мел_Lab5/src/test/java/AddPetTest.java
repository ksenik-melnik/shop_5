import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddPetTest {
    static RequestSpecification rs;

    @BeforeClass
    public static void setUp() {
        rs = given()
                .baseUri("https://petstore.swagger.io/v2/pet")
                .contentType(ContentType.JSON)
                .body("{\"id\": 11, \"name\": \"vasa\", \"photoUrls\": [\"string\"]}");
    }

    @Test
    public void testResponseCodeInRange() {
        given(rs)
                .post()
                .then()
                .statusCode(oneOf(200, 201, 202));
    }

    @Test
    public void testNoErrorInResponse() {
        given(rs)
                .post()
                .then()
                .body("$", not(containsString("error")));
    }

    @Test
    public void testJsonBodyNotNull() {
        given(rs)
                .post()
                .then()
                .body("$", notNullValue());
    }
}