package page;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginDataSet {
    @DataProvider(name = "LoginCSVData")
    public Object[][] getCSVData() throws IOException {
        String csvFilePath = "./src/test/resources/users.csv";
        List<Object[]> data = new ArrayList<>();
        CSVParser csvParser = new CSVParser(new FileReader(csvFilePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord csvRecord : csvParser) {
            String email = csvRecord.get("email");
            String password = csvRecord.get("password");
            data.add(new Object[]{email, password});
        }
        return data.toArray(new Object[0][]);
    }
}
