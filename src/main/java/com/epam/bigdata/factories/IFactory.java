package com.epam.bigdata.factories;

import org.apache.avro.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IFactory {

    /**
     * @param fieldMap Collection that represent the fields with name and data
     *                 for the instance of object that should be generated.
     *                 The main disadvantage of this approach is that we have to
     *                 store field names somewhere or using reflection.
     * @return Generated object, depends on the realisation.
     */
    default Object generate(Map<String, String> fieldMap) {
        return generate(new ArrayList<>(fieldMap.values()));
    }

    Schema getSchema();

    /**
     *
     * @param fields Collection that represent the fields with data
     *               for the instance of object that should be generated.
     *
     * @return Generated object, depends on the realisation.
     */
    Object generate(List<String> fields);
}
