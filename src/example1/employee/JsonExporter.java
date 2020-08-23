package example1.employee;

public class JsonExporter implements Employee.Exporter {
    private String name;

    @Override
    public void storeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        //json representation (low level details)
        return "{\"name\":\"" + name + "\"}";
    }
}
