package com.epam.bigdata;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@AllArgsConstructor
@SpringBootApplication
public class ScvToAvroApplication {

    private List<IConverter> converters;

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring.xml");
    }

    public void convertAll() {
        converters.parallelStream().forEach(IConverter::proc);
    }
}
