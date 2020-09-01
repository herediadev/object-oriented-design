package example4;

import java.time.LocalDate;

public class LifeTimeWarranty implements Warranty {
    private final LocalDate issuedOn;

    public LifeTimeWarranty(LocalDate issuedOn) {
        this.issuedOn = issuedOn;
    }

    @Override
    public boolean isValidOn(LocalDate date) {
        return issuedOn.compareTo(date) <= 0;
    }
}
