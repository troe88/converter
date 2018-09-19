package com.epam.bigdata.factories;

import org.apache.avro.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IFactory {
    default Object generate(Map<String, String> fieldMap) {
        return generate(new ArrayList<>(fieldMap.values()));
    }

    Schema getSchema();

    Object generate(List<String> fields);
}
