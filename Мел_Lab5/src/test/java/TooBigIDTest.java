import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.oneOf;

public class TooBigIDTest {
    static RequestSpecification rs;

    @BeforeClass
    public static void setUp() {
        rs = given()
                .baseUri("https://petstore.swagger.io/v2/pet/9223372036854775808");
    }

    @Test
    public void testResponseCodeOnGet() {
        given(rs)
                .get()
                .then()
                .statusCode(oneOf(404));
    }

    @Test
    public void testErrorIncluded() {
        given(rs)
                .get()
                .then()
                .body("message", containsString("NumberFormatException"));
    }
}
