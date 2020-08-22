package employee;

import java.io.Reader;

public class JsonImporter implements Employee.Importer {
    private final Reader in;

    public JsonImporter(Reader in) {
        this.in = in;
    }

    @Override
    public String fetchName() {
        //use the reader "in" field (low level details)
        return "Some name";
    }
}
