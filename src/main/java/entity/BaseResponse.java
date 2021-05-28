package entity;

import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;

@Getter
@Setter
public class BaseResponse {

    public int httpStatusCode;

    public void assertStatus(int httpStatusCode){
        Assert.assertEquals(getHttpStatusCode(), httpStatusCode, "HTTP status code is incorrect");
    }
}
