package example4;

import java.time.LocalDate;

//Null Object Pattern
public class VoidWarranty implements Warranty {
    @Override
    public boolean isValidOn(LocalDate date) {
        return false;
    }
}
