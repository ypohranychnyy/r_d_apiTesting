package clickup.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.RestAssured.given;

public class SpaceTagsSteps {

    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String SPACE_ID = "90121435817"; // Замініть на ваш Space ID
    private static final String TOKEN = "pk_2144434058_74WQSRIORR0V0XWNQDOT6DSL9UN45KXU"; // Замініть на ваш токен доступу
    private Response response;
    private String tagId;
    private String requestBody;
    private String updatedRequestBody;

    @Given("I have a valid API token for ClickUp")
    public void i_have_a_valid_api_token_for_clickup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Given("I have a valid payload to create a new tag")
    public void i_have_a_valid_payload_to_create_a_new_tag() {
        requestBody = "{\n" +
                "  \"tag\": {\n" +
                "    \"name\": \"new_test_tag\",\n" +
                "    \"tag_fg\": \"#FFFFFF\",\n" +
                "    \"tag_bg\": \"#606060\"\n" +
                "  }\n" +
                "}";
    }

    @Given("I have the ID of an existing tag")
    public void i_have_the_id_of_an_existing_tag() {
        if (tagId == null || tagId.isEmpty()) {
            throw new IllegalStateException("Tag ID не було отримано. Спочатку створіть тег.");
        }
    }

    @Given("I have an updated payload for the tag")
    public void i_have_an_updated_payload_for_the_tag() {
        updatedRequestBody = "{\n" +
                "  \"tag\": {\n" +
                "    \"name\": \"Updated Test Tag\",\n" +
                "    \"tag_fg\": \"#000000\",\n" +
                "    \"tag_bg\": \"#FFFFFF\"\n" +
                "  }\n" +
                "}";
    }

    @Given("I have the ID of the tag to delete")
    public void i_have_the_id_of_the_tag_to_delete() {
        if (tagId == null || tagId.isEmpty()) {
            throw new IllegalStateException("Tag ID не було отримано. Спочатку створіть тег.");
        }
    }

    @When("I send a GET request to fetch all tags for the space")
    public void i_send_a_get_request_to_fetch_all_tags_for_the_space() {
        response = given()
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("/space/" + SPACE_ID + "/tag")
                .then()
                .extract()
                .response();
    }

    @When("I send a POST request to create the tag")
    public void i_send_a_post_request_to_create_the_tag() {
        response = given()
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/space/" + SPACE_ID + "/tag")
                .then()
                .extract()
                .response();

        // Збереження ID створеного тега для подальшого використання
        tagId = response.jsonPath().getString("tag.id");
    }

    @When("I send a PUT request to update the tag")
    public void i_send_a_put_request_to_update_the_tag() {
        response = given()
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .body(updatedRequestBody)
                .when()
                .put("/space/" + SPACE_ID + "/tag/" + tagId)
                .then()
                .extract()
                .response();
    }

    @When("I send a DELETE request to remove the tag")
    public void i_send_a_delete_request_to_remove_the_tag() {
        response = given()
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete("/space/" + SPACE_ID + "/tag/" + tagId)
                .then()
                .extract()
                .response();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode(), "Статус коду не відповідає очікуваному значенню " + statusCode);
    }

    @Then("the list of tags should be returned")
    public void the_list_of_tags_should_be_returned() {
        assertEquals(200, response.getStatusCode(), "Статус коду не відповідає очікуваному значенню 200");
        // Додаткові перевірки можна додати тут
    }

    @Then("the new tag should be present in the response")
    public void the_new_tag_should_be_present_in_the_response() {
        String tagName = response.jsonPath().getString("tag.name");
        assertEquals("new_test_tag", tagName, "Назва тега не відповідає очікуваній");
    }

    @Then("the tag should be updated in the response")
    public void the_tag_should_be_updated_in_the_response() {
        String tagName = response.jsonPath().getString("tag.name");
        assertEquals("Updated Test Tag", tagName, "Назва тега не відповідає оновленому значенню");
    }

    @Then("the tag should no longer be available")
    public void the_tag_should_no_longer_be_available() {
        assertEquals(200, response.getStatusCode(), "Статус коду не відповідає очікуваному значенню 200");
    }
}
