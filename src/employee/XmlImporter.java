package employee;

import java.io.Reader;

public class XmlImporter implements Employee.Importer{
    private final Reader in;

    public XmlImporter(Reader in) {
        this.in = in;
    }

    @Override
    public String fetchName() {
        //call in to read some xml file and extract the name (low level details)
        return "Some Name";
    }
}
