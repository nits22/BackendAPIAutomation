package weatherTests;

import clients.WeatherClient;
import org.testng.annotations.*;

public class BaseTest {

    ThreadLocal<WeatherClient> thread = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void initalSetup(){


        //Set Browser to ThreadLocalMap
        thread.set(new WeatherClient());

    }

    @BeforeMethod
    public void setup () {

    }

    public WeatherClient getThread() {
        //Get driver from ThreadLocalMap
        //thread.set(new WeatherClient());
        System.out.println(thread);
        return thread.get();
    }

    @AfterClass
    public void tearDown() {
        thread.remove();
    }
}
