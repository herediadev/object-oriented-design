package employee;

/*
Working without getters and setters
 */
public class Employee {

    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(Importer source) {
        this.name = source.fetchName();
    }

    interface Importer {
        String fetchName();
    }

    interface Exporter{
        void storeName(String name);
    }

    public void export(Exporter destination){
        destination.storeName(name);
    }

}
