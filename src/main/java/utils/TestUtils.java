package utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    public static String getJsonString(Object json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
