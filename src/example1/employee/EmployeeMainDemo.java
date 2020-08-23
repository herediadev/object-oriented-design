package example1.employee;

import java.io.Reader;
import java.io.StringReader;

public class EmployeeMainDemo {

    public static void main(String[] args) {
        String json = "<name value=\"Some name\">";

        Reader in = new StringReader(json);
        Employee.Importer source = new XmlImporter(in);

        Employee.Exporter exporter = new XMLExporter();

        Employee employee = new Employee(source);
        employee.export(exporter);

        String string = exporter.toString();
        System.out.println(string);
    }
}
