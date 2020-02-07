package io.github.dranreb22;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CsvValidationException {
        CsvParser csvP = new CsvParser("src/Data/bookstore_report2.csv");
        csvP.printCsv();
    }
}
