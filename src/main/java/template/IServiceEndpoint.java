package template;

import entity.HttpMethod;
import entity.Param;
import entity.RequestBody;

import java.util.List;

public interface IServiceEndpoint {


    String url();

    HttpMethod httpMethod();

    List<Param> pathParam();

    List<Param> queryParam();

    List<Param> headers();

    List<Param> formParam();

    RequestBody body();

    //TODO : Add auth
}
