package com.epam.bigdata.factories;

import com.epam.bigdata.entities.Test;
import lombok.AllArgsConstructor;
import org.apache.avro.Schema;

import java.util.List;

@AllArgsConstructor
public class TestFactory implements IFactory {

    Test test;

    @Override
    public Schema getSchema() {
        return test.getSchema();
    }

    @Override
    public Object generate(List<String> fields) {
        Test train = new Test();
        for (int i = 0; i < fields.size(); i++) {
            train.put(i, fields.get(i));
        }
        return train;
    }
}
