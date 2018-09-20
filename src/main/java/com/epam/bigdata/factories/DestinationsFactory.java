package com.epam.bigdata.factories;

import com.epam.bigdata.entities.Destinations;
import lombok.AllArgsConstructor;
import org.apache.avro.Schema;

import java.util.List;

@AllArgsConstructor
public class DestinationsFactory implements IFactory {

    Destinations d2;

    @Override
    public Schema getSchema() {
        return d2.getSchema();
    }

    @Override
    public Object generate(List<String> fields) {
        Destinations d = new Destinations();
        d.put(0, Long.parseLong(fields.get(0)));
        for (int i = 1; i < fields.size(); i++) {
            d.put(i, Float.parseFloat(fields.get(i)));
        }
        return d;
    }
}
