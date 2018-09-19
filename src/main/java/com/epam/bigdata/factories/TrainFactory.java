package com.epam.bigdata.factories;

import com.epam.bigdata.entities.Train;
import lombok.AllArgsConstructor;
import org.apache.avro.Schema;

import java.util.List;

@AllArgsConstructor
public class TrainFactory implements IFactory {

    Train train;

    @Override
    public Schema getSchema() {
        return train.getSchema();
    }

    @Override
    public Object generate(List<String> fields) {
        Train train = new Train();
        for (int i = 0; i < fields.size(); i++) {
            train.put(i, fields.get(i));
        }
        return train;
    }
}
