package br.com.hackmovile.recpag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class RecpagServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecpagServiceApplication.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-3"));
    }

}

