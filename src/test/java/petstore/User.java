package petstore;

import org.testng.annotations.Test;
import utils.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class User {
    String uri = "https://petstore.swagger.io/v2/user";
    Data data;

    // 3.2 - Métodos e Funções

    @Test
    public void incluirUsuario() throws IOException {
        data - new Data; // inicializando sem @before
        String jsonBody = data.lerJson("db/user1.json");

        String userId =
                given()
                        .contentType("application/json")
                        .log().all()
                        .body(jsonBody)
                .when()
                        .post(uri)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("code", is(200))
                        .body("type", is("unknown"))
                        .extract()
                        .path("message")
                ;
        System.out.println("O userID é " + userId);
    }


}