package example5;

import java.time.LocalDate;
import java.util.Optional;

public class LifeTimeWarranty implements Warranty {
    private final LocalDate issuedOn;

    public LifeTimeWarranty(LocalDate issuedOn) {
        this.issuedOn = issuedOn;
    }

    @Override
    public Warranty on(LocalDate date) {
        return date.compareTo(issuedOn) < 0 ? VOID : this;
    }

    @Override
    public Optional<Warranty> filter(LocalDate date) {
        return date.compareTo(this.issuedOn) >= 0 ? Optional.of(this) : Optional.empty();
    }
}
