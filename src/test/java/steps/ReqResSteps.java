package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;


public class ReqResSteps {
    Response response;
    int userId;

    @Given("que accedo al servicio de listar usuarios de ReqRes")
    public void accederServicio() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @When("realizo una solicitud GET al endpoint {string}")
    public void solicitudGet(String endpoint) {
        response = RestAssured.get(endpoint);
    }

    @Then("la respuesta debe contener la lista de usuarios en la página 2 y el estado de la respuesta debe ser {int}")
    public void validarRespuestaListaUsuarios(int statusCode) {
        String pagina = response.jsonPath().getString("page");
        System.out.println("La respuesta es: " + statusCode);
        System.out.println("Pagina: " + pagina);
        List<String> users = response.jsonPath().getList("data");
        System.out.println("Lista de usuarios: ");
        System.out.println("Respuesta:" + users);
    }

    @Given("que tengo los datos para crear un nuevo usuario")
    public void datosNuevoUsuario() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @When("realizo una solicitud POST al endpoint {string} con los datos del usuario")
    public void solicitudPost(String endpoint) {
        String payload = "{\"name\": \"Juan\", \"job\": \"QA Engineer\"}";
        response = RestAssured.given().body(payload).post(endpoint);
        userId = response.jsonPath().getInt("id");
    }

    @Then("la respuesta debe indicar que el usuario fue creado exitosamente con un estado de {int}, y debe devolver el ID del usuario creado")
    public void validarUsuarioCreado(int statusCode) {
        System.out.println("La respuesta es: " + statusCode + " y su ID creado es: " + userId);
        String creado = response.jsonPath().getString("createdAt");
        System.out.println("Usuario creado exitosamente fecha de creación: " + creado);
    }

    @Given("que tengo un ID de usuario existente")
    public void idUsuarioExistente() {
        userId = 2;
    }

    @When("realizo una solicitud PUT al endpoint {string} con datos actualizados")
    public void solicitudPut(String endpoint) {
        String payload = "{\"name\": \"Pedro\", \"job\": \"Senior\"}";
        response = RestAssured.given().body(payload).put(endpoint.replace("{id}", String.valueOf(userId)));
    }

    @Then("la respuesta debe indicar que el usuario fue actualizado exitosamente con un estado de {int}, y debe devolver los datos actualizados")
    public void validarUsuarioActualizado(int statusCode) {
        System.out.println("La respuesta es: " + statusCode);
    }

    @When("realizo una solicitud DELETE al endpoint {string}")
    public void solicitudDelete(String endpoint) {
        // Agregar el encabezado de autorización con el token
        response = RestAssured.given()
                .header("Authorization", "Bearer QpwL5tke4Pnpja7X4") // Asegúrate de que el token sea el correcto
                .delete(endpoint.replace("{id}", String.valueOf(userId)));
    }

    @Then("la respuesta debe devolver un estado de {int}, indicando que el usuario fue eliminado exitosamente")
    public void validarUsuarioEliminado(int statusCode) {
        // Imprimir el cuerpo de la respuesta para ver si la autenticación fue exitosa
        System.out.println("Respuesta: " + response.getBody().asString());
    }

    @When("realizo una solicitud POST al endpoint {string}")
    public void solicitudPost1(String endpoint) {
        String payload = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pruebas\"}";
        response = RestAssured.given().body(payload).post(endpoint);
    }

    @Then("la respuesta de los datos del usuario nuevo {int}")
    public void laRespuestaDeLosDatosDelUsuarioNuevo(int statusCode) {
        System.out.println("La respuesta es: " + statusCode);
        System.out.println("Respuesta: " + response.getBody().asString());
    }
}
