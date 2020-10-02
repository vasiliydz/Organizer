package ru.vasiliydz.organizer;

import ru.vasiliydz.organizer.clientbase.*;
import ru.vasiliydz.organizer.console.PrintResponseOutputStream;
import ru.vasiliydz.organizer.console.StreamFieldSetterSource;
import ru.vasiliydz.organizer.console.StreamQueryInputStream;
import ru.vasiliydz.organizer.json.ClientBaseJsonFileLoader;
import ru.vasiliydz.organizer.json.JsonSaverQueryInputStream;

import java.io.IOException;

public class MainApp {

    public static void main(String[] args) throws IOException {
        // load database object
        ClientBase base = new ClientBaseJsonFileLoader("base.json").readClientBase();
        // create console output stream
        ResponseOutputStream consoleStream = new PrintResponseOutputStream(System.out);
        // input stream processor
        QueryProcessor processor = new QueryProcessor(base, consoleStream);
        // where will we get field values
        FieldSetterSource fieldSetterSource = new StreamFieldSetterSource(System.out, System.in);
        // input stream
        QueryInputStream queryInputStream = new StreamQueryInputStream(System.in, fieldSetterSource);
        // autosaving of base after each query
        queryInputStream = new JsonSaverQueryInputStream("base.json", queryInputStream);
        // start processing
        processor.processQueryStream(queryInputStream);
    }
}
