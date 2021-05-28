package restUtils;

import entity.HttpMethod;
import entity.RequestBody;
import groovyjarjarpicocli.CommandLine;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import template.IServiceEndpoint;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


public class RequestHandler {

    public Response processRequest(IServiceEndpoint iServiceEndpoint){
        Response response = processIServiceEndpoint(iServiceEndpoint);

        String endpointName = iServiceEndpoint.getClass().getSimpleName().replaceAll("Endpoint", "");

        return response;
    }

    private Response processIServiceEndpoint(IServiceEndpoint iServiceEndpoint) {
        RestAssured.registerParser("text/plain"
                , Parser.JSON);
        RestAssured.registerParser("text/html"
                , Parser.JSON);
        RestAssured.registerParser(
                "application/grpc"
                , Parser.JSON);

        String endpointName = iServiceEndpoint.getClass().getSimpleName().replaceAll("Endpoint", "");

        String url = iServiceEndpoint.url();

        HttpMethod httpMethod = iServiceEndpoint.httpMethod();

        RequestBody body = iServiceEndpoint.body();

        RequestSpecification requestSpecification = formRequestSpecification(iServiceEndpoint);

        Response response = makeApiRequest(url, httpMethod, requestSpecification);

        return response;


    }

    private RequestSpecification formRequestSpecification(IServiceEndpoint iServiceEndpoint) {
        RestAssuredConfig config = RestAssured.config().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

        RequestSpecification specification = given().config(config);


        if (iServiceEndpoint.headers() != null) {
            iServiceEndpoint.headers().forEach(h -> specification.header(h.getKey(), h.getValue()));
        }


        if (iServiceEndpoint.pathParam() != null) {
            iServiceEndpoint.pathParam().forEach(p -> specification.pathParam(p.getKey(), p.getValue()));
        }

        if (iServiceEndpoint.queryParam() != null) {
            iServiceEndpoint.queryParam().forEach(h -> specification.queryParam(h.getKey(), h.getValue()));
        }

        return specification;
    }



    private Response makeApiRequest(String url, HttpMethod httpMethod , RequestSpecification requestSpecification) {

        Response response = null;

        switch(httpMethod){
            case GET:
                response = requestSpecification.get(url);
                break;
            default:
                response = requestSpecification.get(url);
                break;
        }
        return response;
    }




















}
