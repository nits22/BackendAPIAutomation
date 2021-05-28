package clients;

import endpoints.WeatherEndpoint;
import io.restassured.response.Response;
import restUtils.RequestHandler;
import restUtils.RequestProcessor;

public class WeatherClient {

    public Response getWeatherApiCity(String city){
        WeatherEndpoint weatherEndpoint = new WeatherEndpoint(city);
        Response response = new RequestProcessor().processRequest(weatherEndpoint);

        System.out.println(response.asString());

        return response;
    }
}
