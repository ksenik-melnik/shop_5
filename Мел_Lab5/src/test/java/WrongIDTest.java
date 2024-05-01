import io.restassured.specification.RequestSpecification;
import org.junit.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WrongIDTest {
    static RequestSpecification rs;

    @BeforeClass
    public static void setUp() {
        rs = given()
                .baseUri("https://petstore.swagger.io/v2/pet/-1");
    }

    @Test
    public void testResponseCodeOnGet() {
        given(rs)
                .get()
                .then()
                .statusCode(oneOf(404));
    }

    @Test
    public void testNotFoundError() {
        given(rs)
                .get()
                .then()
                .body("message", containsString("Pet not found"));
    }

}
