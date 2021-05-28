package weatherTests;

import clients.WeatherClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.util.Iterator;

public class GetDetailsNew extends BaseTest {

    String city = System.getProperties().getProperty("cityName");
    //WeatherClient weatherClient = new WeatherClient();

    @Test(groups = {"unit"})
    public void getWeatherDetails4() {
        Response response = getThread().getWeatherApiCity(city);

        JsonUtils jsonUtils = new JsonUtils();
        JsonArray jsonArray = jsonUtils.getValueAsArray(response, "forecast");

        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonObject object = (JsonObject) iterator.next();
            if (object.get("day").isJsonNull() ? false : object.get("day").getAsString().equals("2")) {
                //Assert.assertTrue(object.get("wind").getAsString().equals("13 km/h"));
            }
        }
    }

    @Test(groups = {"unit"})
    public void getWeatherDetails5() {

        getThread().getWeatherApiCity("mumbai");
    }
}
