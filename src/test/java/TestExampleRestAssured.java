import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("REST API Regression Testing using JUnit4")
@Feature("Verify CRUID Operations on Employee module")
public class TestExampleRestAssured {

    String BaseURL = "http://dummy.restapiexample.com/api";

    @Test
    @Story("GET Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the details of employee of id-2")
    public void verifyUser() {

        // Given
        given()
                .filter(new AllureRestAssured())

                // WHEN
                .when()
                .get(BaseURL + "/v1/employee/2")

                // THEN
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                // To verify booking id at index 2
                .body("data.employee_name", equalTo("Garrett Winters"))
                .body("message", equalTo("Successfully! Record has been fetched."));
    }

    @Test
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the creation of a new employee")
    public void createUser() {

        JSONObject data = new JSONObject();

        // Map<String, String> map = new HashMap<String, String>();

        data.put("employee_name", "APITest");
        data.put("employee_salary", "99999");
        data.put("employee_age", "30");

        // GIVEN
        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(data.toString())

                // WHEN
                .when()
                .post(BaseURL + "/v1/create")

                // THEN
                .then()
                .statusCode(200)
                .body("data.employee_name", equalTo("APITest"))
                .body("message", equalTo("Successfully! Record has been added."));

    }

}
