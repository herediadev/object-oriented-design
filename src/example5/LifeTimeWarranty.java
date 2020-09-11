package example5;

import java.time.LocalDate;

public class LifeTimeWarranty implements Warranty {
    private final LocalDate issuedOn;

    public LifeTimeWarranty(LocalDate issuedOn) {
        this.issuedOn = issuedOn;
    }

    @Override
    public Warranty on(LocalDate date) {
        return date.compareTo(issuedOn) < 0 ? VOID : this;
    }
}
