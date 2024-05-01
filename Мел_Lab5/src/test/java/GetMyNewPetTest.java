import io.restassured.specification.RequestSpecification;
import org.junit.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetMyNewPetTest {
    static RequestSpecification rs;

    @BeforeClass
    public static void setUp() {
        rs = given()
                .baseUri("https://petstore.swagger.io/v2/pet/11");
    }

    @Test
    public void testResponseCodeOnGet() {
        given(rs)
                .get()
                .then()
                .statusCode(oneOf(200,201,202));
    }

    @Test
    public void testNoErrorInResponse() {
        given(rs)
                .get()
                .then()
                .body("$", not(containsString("error")));
    }

    @Test
    public void testJsonBodyNotNull() {
        given(rs)
                .get()
                .then()
                .body("$", notNullValue());
    }

    @Test
    public void testNotNullFields() {
        given(rs)
                .get()
                .then()
                .body("name", notNullValue())
                .and()
                .body("photoUrls", notNullValue());
    }

}
