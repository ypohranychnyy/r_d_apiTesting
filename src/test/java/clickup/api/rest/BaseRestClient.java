package clickup.api.rest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

import org.junit.runner.Request;

public abstract class BaseRestClient {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
protected RequestSpecification requestSpec;
    public void setUpRestAssured() {
        String token = dotenv.get("TOKEN", System.getenv("TOKEN") != null ? System.getenv("TOKEN") : "defaultTokenValue");
        String baseUrl = dotenv.get("BASE_URL", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "BASE_URL");
        System.out.println(baseUrl);
        this.requestSpec = SerenityRest.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("Authorization", token);
    }

    ;
}
