package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

public class JsonUtils {

    public JsonArray getValueAsArray(Response response, String key){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response.asString());
        JsonObject object = element.getAsJsonObject();
        JsonArray array = object.getAsJsonArray(key);
        return array;
    }
}
