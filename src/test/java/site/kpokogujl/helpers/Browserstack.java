package site.kpokogujl.helpers;

import org.aeonbits.owner.ConfigFactory;
import site.kpokogujl.config.TestsConfig;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    static TestsConfig config = ConfigFactory.create(TestsConfig.class, System.getProperties());

    public static String
            browserstackLogin = config.browserstackLogin(),
            browserstackPassword = config.browserstackPassword(),
            browserstackUrl = config.browserstackUrl(),
            appUrl = config.appUrl();

    public static String videoUrl(String sessionId) {
        String url = format("https://api-cloud.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(browserstackLogin, browserstackPassword)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
