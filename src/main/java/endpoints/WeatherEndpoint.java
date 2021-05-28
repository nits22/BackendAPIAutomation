package endpoints;

import entity.HttpMethod;
import entity.Param;
import entity.RequestBody;
import template.IServiceEndpoint;

import java.util.ArrayList;
import java.util.List;

public class WeatherEndpoint implements IServiceEndpoint {
    public String url() {
        return "https://goweather.herokuapp.com/weather/{city}";
    }

    String city;
    public WeatherEndpoint(String city){
        this.city = city;
    }

    public HttpMethod httpMethod() {
        return HttpMethod.GET;
    }

    public List<Param> pathParam() {
        List<Param> list = new ArrayList<Param>();
        list.add(new Param("city", city));
        return list;
    }

    public List<Param> queryParam() {
        return null;
    }

    public List<Param> headers() {
        return null;
    }

    public List<Param> formParam() {
        return null;
    }

    public RequestBody body() {
        return null;
    }
}
