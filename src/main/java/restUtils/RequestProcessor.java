package restUtils;

import entity.HttpMethod;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;
import template.IServiceEndpoint;

import static io.restassured.RestAssured.given;

public class RequestProcessor {

    public Response processRequest(IServiceEndpoint iServiceEndpoint) {

        String endpoint = iServiceEndpoint.getClass().getSimpleName().replaceAll("Endpoint", ""); //weather

        Response response = processIserviceEndpoint(iServiceEndpoint);

        printResponseDetails( endpoint, response);
        return response;
    }
    private void printResponseHeaders(String endpointName, Response response) {
        String responseHeaders = response.headers().toString();
        Reporter.log(String.format("%s Response headers --- \n%s", endpointName, responseHeaders), true);
    }

    private void printResponseDetails(String endpointName, Response response) {
        Reporter.log(String.format(endpointName + " Response Status Code --- (%s)", response.getStatusCode()), true);

        Reporter.log(String.format(endpointName + " Response --- %s", response.asString()), true);
        printResponseHeaders(endpointName, response);
    }

    private Response processIserviceEndpoint(IServiceEndpoint iServiceEndpoint) {
        RestAssured.registerParser("application/grpc", Parser.JSON);
        RestAssured.registerParser("test/html", Parser.JSON);
        RestAssured.registerParser("test/plain", Parser.JSON);

        RequestSpecification requestSpecification = formRequestSpecification(iServiceEndpoint);

        String endpoint = iServiceEndpoint.getClass().getSimpleName().replaceAll("Endpoint", "");

        String url = iServiceEndpoint.url();

        HttpMethod httpMethod = iServiceEndpoint.httpMethod();

        Response response = makeApiRequest(url, httpMethod, requestSpecification);

        return response;
    }

    private RequestSpecification formRequestSpecification(IServiceEndpoint iServiceEndpoint) {
        RestAssuredConfig config = RestAssured.config().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RequestSpecification requestSpecification = given().config(config);

        if (iServiceEndpoint.body() != null) {
            requestSpecification.body(iServiceEndpoint.body().getBodyString());
        }

        if (iServiceEndpoint.headers() != null) {
            iServiceEndpoint.headers().forEach(h -> requestSpecification.header(h.getKey(), h.getValue()));
        }

        if (iServiceEndpoint.pathParam() != null) {
            iServiceEndpoint.pathParam().forEach(h -> requestSpecification.pathParam(h.getKey(), h.getValue()));
        }

        if (iServiceEndpoint.queryParam() != null) {
            iServiceEndpoint.queryParam().forEach(h -> requestSpecification.queryParam(h.getKey(), h.getValue()));
        }
        return requestSpecification;
    }

    private Response makeApiRequest(String url, HttpMethod httpMethod, RequestSpecification requestSpecification) {
        Response response = null;

        switch (httpMethod) {
            case GET:
                response = requestSpecification.get(url);
                break;
            case POST:
                response = requestSpecification.post(url);
                break;
            case PUT:
                response = requestSpecification.post(url);
                break;
        }
        return response;
    }
}
