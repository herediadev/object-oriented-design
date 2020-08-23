package example1.employee;

public class XMLExporter implements Employee.Exporter {
    private String name;

    @Override
    public void storeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "<name value=\"" + name + "\">";
    }
}
