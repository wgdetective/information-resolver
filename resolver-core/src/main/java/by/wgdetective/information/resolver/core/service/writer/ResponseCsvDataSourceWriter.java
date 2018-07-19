package by.wgdetective.information.resolver.core.service.writer;

import by.wgdetective.information.resolver.core.model.response.CsvSerializable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Wladimir Litvinov
 */
public class ResponseCsvDataSourceWriter<Response extends CsvSerializable> implements ResponseDataSourceWriter<Response> {
    private final File outputCsvFile;
    private final String lineDelimiter;

    public ResponseCsvDataSourceWriter(final File outputCsvFile, final String lineDelimiter) {
        this.outputCsvFile = outputCsvFile;
        this.lineDelimiter = lineDelimiter;
    }

    @Override
    public void writeDataResponses(final List<Response> responseList) throws IOException {
        try (final FileWriter fileWriter = new FileWriter(outputCsvFile)) {
            for (Response response : responseList) {
                fileWriter.append(response.toCsv()).append(lineDelimiter);
            }
            fileWriter.flush();
        }
    }
}
