package com.epam.bigdata.factories;

import com.epam.bigdata.entities.SampleSubmission;
import lombok.AllArgsConstructor;
import org.apache.avro.Schema;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class SampleSubmissionFactory implements IFactory {

    SampleSubmission sampleSubmission;

    @Override
    public Object generate(Map<String, String> fieldMap) {
        return new SampleSubmission(
                Long.parseLong(fieldMap.get("id")),
                fieldMap.get("hotel_cluster")
        );
    }

    @Override
    public Schema getSchema() {
        return sampleSubmission.getSchema();
    }

    @Override
    public Object generate(List<String> fields) {
        return null;
    }
}
