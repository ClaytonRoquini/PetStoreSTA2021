// 1 - Pacote
package petstore;

// 2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

// 3 - Classe
public class Pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet



    //3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - Post
    @Test  // Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        given()
                .contentType("application/json")  // comum em API REST
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Rintintim"))
                .body("status", is("available"))
                .body("category.name", is ("dog"))
                .body("tags.name", contains("sta"))
        ;

    }

    @Test
    public void consultarPet(){
        String petId = "1988090723" ;

        given()
                .contentType("application/json")
                .log().all()

        .when()
                .get(uri+"/"+petId)

        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Rintintim"))
                .body("status", is("available"))
                .body("category.name", is ("dog"))
                .body("tags.name", contains("sta"))


        ;


    }



}
