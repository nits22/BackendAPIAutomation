package weatherTests;

import restUtils.RequestProcessor;

public class ThreadLocalRegisterUser implements Runnable {

    ThreadLocal<RequestProcessor> requestProcessorThreadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        requestProcessorThreadLocal.set(new RequestProcessor());
    }
}
