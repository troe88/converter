package com.epam.bigdata;

import com.epam.bigdata.factories.IFactory;
import de.siegmar.fastcsv.reader.CsvParser;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import lombok.Getter;
import lombok.Setter;
import org.apache.avro.file.DataFileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Setter
@Getter
public class Converter implements IConverter {
    private Logger logger = LoggerFactory.getLogger(Converter.class);

    private File src;
    private File targetFile;

    private IFactory factory;

    private CsvReader csvReader;
    private CsvParser csvParser;

    private DataFileWriter avroFile;

    private void init() {
        try {
            csvParser = csvReader.parse(src, StandardCharsets.UTF_8);
            avroFile.create(factory.getSchema(), targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void proc() {
        logger.info(String.format("Converting to AVRO has started, target: %s", src.getName()));
        convert();
        logger.info(String.format("Converting to AVRO has finished, result - %s", targetFile.getName()));
    }

    @SuppressWarnings("all")
    private void convert() {
        try {
            CsvRow row;
            while (null != (row = csvParser.nextRow())) {
                avroFile.append(factory.generate(row.getFieldMap()));
            }
            avroFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
