package com.epam.bigdata.factories;

import com.epam.bigdata.entities.Test2;
import lombok.AllArgsConstructor;
import org.apache.avro.Schema;

import java.util.List;

@AllArgsConstructor
public class Test2Factory implements IFactory {

    Test2 test2;

    @Override
    public Schema getSchema() {
        return test2.getSchema();
    }

    @Override
    public Object generate(List<String> fields) {
        Test2 train = new Test2();
        for (int i = 0; i < fields.size(); i++) {
            train.put(i, fields.get(i));
        }
        return train;
    }
}
