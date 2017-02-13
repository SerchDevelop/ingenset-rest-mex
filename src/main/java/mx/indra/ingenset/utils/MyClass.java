package mx.indra.ingenset.utils;

import org.springframework.beans.factory.annotation.Autowired;

public class MyClass {

    @Autowired
    private java.util.Properties properties;


    public void myMethod() {
        String a = properties.getProperty("config.userTomcat");
        System.out.println("username :: " + a);;
    }
}