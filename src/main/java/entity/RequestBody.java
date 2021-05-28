package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.TestUtils;

@Getter
@Setter
@NoArgsConstructor
public class RequestBody {

    String objectClass;
    Object objectInstance;

    public RequestBody(Class objectClass, Object objectInstance){
        this.objectClass = objectClass.getName();
        this.objectInstance = objectInstance;
    }

    public String getBodyString(){
        if(objectInstance.getClass() == String.class)
            return objectInstance.toString();
        return TestUtils.getJsonString(objectClass);

    }
}
