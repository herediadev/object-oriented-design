package example4;

import java.time.LocalDate;

//Null Object Pattern
public class VoidWarranty implements Warranty {

    @Override
    public Warranty on(LocalDate date) {
        return VOID;
    }

    @Override
    public void claim(Runnable action) {

    }
}
