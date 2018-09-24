package com.epam.bigdata;

import com.epam.bigdata.factories.IFactory;
import de.siegmar.fastcsv.reader.CsvParser;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import lombok.Getter;
import lombok.Setter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class ConverterParquet implements IConverter {
    private Logger logger = LoggerFactory.getLogger(Converter.class);

    private File src;
    private Path targetPath;

    private IFactory factory;

    private CsvReader csvReader;
    private CsvParser csvParser;

    private ParquetWriter writer;
    private Configuration conf;
    private String fsName;

    public void init() {
        try {
            csvParser = csvReader.parse(src, StandardCharsets.UTF_8);
//            conf.set("fs.default.name", fsName);
            conf.set("fs.defaultFS", fsName);
            writer =
                    AvroParquetWriter.builder(targetPath)
                            .withConf(conf)
                            .withWriteMode(ParquetFileWriter.Mode.CREATE)
                            .withRowGroupSize(ParquetWriter.DEFAULT_BLOCK_SIZE)
                            .withPageSize(ParquetWriter.DEFAULT_PAGE_SIZE)
                            .withSchema(factory.getSchema())
                            .withCompressionCodec(CompressionCodecName.SNAPPY)
                            .withValidation(false)
                            .withDictionaryEncoding(false)
                            .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void proc() {
        logger.info(String.format("Converting to PARQUET has started, target: %s", src.getName()));
        convert();
        logger.info(String.format("Converting to PARQUET has finished, result - %s", targetPath.getName()));
    }

    @SuppressWarnings("all")
    private void convert() {
        try {
            CsvRow row;
            while (null != (row = csvParser.nextRow())) {
                writer.write(factory.generate(row.getFieldMap()));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
