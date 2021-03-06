package example5;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

public class TimeLimitedWarranty implements Warranty {

    private final LocalDate dateIssued;
    private final Duration validFor;

    public TimeLimitedWarranty(LocalDate dateIssued, Duration validFor) {
        this.dateIssued = dateIssued;
        this.validFor = validFor;
    }

    private LocalDate getExpiredDate() {
        return this.dateIssued.plusDays(this.getValidForDays());
    }

    private long getValidForDays() {
        return validFor.toDays();
    }

    @Override
    public Warranty on(LocalDate date) {
        return date.compareTo(dateIssued) < 0 ? VOID
                : date.compareTo(getExpiredDate()) > 0 ? VOID
                : this;
    }

    @Override
    public Optional<Warranty> filter(LocalDate date) {
        return date.compareTo(this.dateIssued) >= 0
                && date.compareTo(this.getExpiredDate()) <= 0
                ? Optional.of(this)
                : Optional.empty();
    }
}
