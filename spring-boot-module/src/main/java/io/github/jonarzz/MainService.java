package io.github.jonarzz;

public class MainService {

    private Service1 service1;
    private Service2 service2;

    public void start() {
        service1.execute();
        service2.execute();
    }

}
