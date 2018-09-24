# converter
1. mvn clean package
2. put target/converter.jar to folder with example data from BD cource
> Pay attention that directory should contains destinations.csv, sample_submission.csv, test.csv, train.csv files

> You can specify fs.default.name and output_dir_parquet variables
3. java -jar converter.jar
4. *.avro files will appear in the current folder
5. *.parquet files will appear in the HDSF portal
