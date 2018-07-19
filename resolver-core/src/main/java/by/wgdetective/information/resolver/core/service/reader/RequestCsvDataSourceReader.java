package by.wgdetective.information.resolver.core.service.reader;

import by.wgdetective.information.resolver.core.factory.RequestFromArrayFactory;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wladimir Litvinov
 */
public class RequestCsvDataSourceReader<Request> implements RequestDataSourceReader<Request> {

    private final File inputCsvFile;
    private final String fieldDelimiter;
    private final RequestFromArrayFactory<Request> factory;

    public RequestCsvDataSourceReader(final File inputCsvFile,
                                      final String fieldDelimiter,
                                      final RequestFromArrayFactory<Request> factory) {
        this.inputCsvFile = inputCsvFile;
        this.fieldDelimiter = fieldDelimiter;
        this.factory = factory;
    }

    @Override
    public List<Request> read() throws IOException {
        final List<Request> requests = new ArrayList<>();
        try (final CSVReader reader = new CSVReader(new FileReader(inputCsvFile),
                                                    fieldDelimiter.charAt(0))) {
            String[] line = reader.readNext();
            while (line != null) {
                requests.add(factory.create(line));
                line = reader.readNext();
            }
        }
        return requests;
    }
}
