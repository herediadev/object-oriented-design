package example5;

import java.time.LocalDate;
import java.util.Optional;

//Null Object Pattern
public class VoidWarranty implements Warranty {

    @Override
    public Warranty on(LocalDate date) {
        return VOID;
    }

    @Override
    public Optional<Warranty> filter(LocalDate date) {
        return Optional.empty();
    }

    @Override
    public void claim(Runnable action) {

    }
}
