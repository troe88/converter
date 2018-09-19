package com.epam.bigdata.factories;

import com.epam.bigdata.entities.Destinations2;
import lombok.AllArgsConstructor;
import org.apache.avro.Schema;

import java.util.List;

@AllArgsConstructor
public class Destinations2Factory implements IFactory {

    Destinations2 d2;

    @Override
    public Schema getSchema() {
        return d2.getSchema();
    }

    @Override
    public Object generate(List<String> fields) {
        Destinations2 d = new Destinations2();
        d.put(0, Long.parseLong(fields.get(0)));
        for (int i = 1; i < fields.size(); i++) {
            d.put(i, Float.parseFloat(fields.get(i)));
        }
        return d;
    }
}
