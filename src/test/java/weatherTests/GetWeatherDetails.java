package weatherTests;

import clients.WeatherClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restUtils.RequestProcessor;

import java.io.StringReader;

public class GetWeatherDetails extends BaseTest {

    String city = System.getProperties().getProperty("cityName");

    //String env = System.getenv("cityName");


    @Test(groups = {"smoke"})
    public void getWeatherDetails1(){
        System.out.println(Thread.currentThread().getId());
        Response response = getThread().getWeatherApiCity(city);

        JsonReader reader = new JsonReader(new StringReader(response.asString()));
        reader.setLenient(true);
        JsonParser parser = new JsonParser();

        JsonElement element = parser.parse(reader);
        /*JsonObject object = element.getAsJsonObject();

        JsonArray array = object.getAsJsonArray("forecast");

        System.out.println(array.toString());*/

    }

    @Test(groups = {"unit"})
    public void getWeatherDetails2(){
        System.out.println(Thread.currentThread().getId());
        getThread().getWeatherApiCity("ladakh");
    }

    @Test(groups = {"unit"})
    public void getWeatherDetails3(){
        System.out.println(Thread.currentThread().getId());
        getThread().getWeatherApiCity("delhi");
    }
}
