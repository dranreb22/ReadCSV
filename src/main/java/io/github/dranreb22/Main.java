package io.github.dranreb22;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static private DatabaseManager db = new DatabaseManager();

    public static void main(String[] args) throws IOException, CsvValidationException {
        CsvParser csvP = new CsvParser("src/Data/bookstore_report2.csv");
        csvP.printCsv();

        Gson gson = new Gson();
        JsonReader jread = new JsonReader(new FileReader("src/Data/authors.json"));
        AuthorParser[] authors = gson.fromJson(jread, AuthorParser[].class);

        for (var element : authors) {
            insertJson(element.getName(), element.getEmail(), element.getUrl());
        }
    }

    public static void insertJson(String name, String email, String url){
        db.insertIntoAuthor(name, email, url);
    }
}
