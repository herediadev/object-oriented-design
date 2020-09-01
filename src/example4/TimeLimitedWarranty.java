package example4;

import java.time.Duration;
import java.time.LocalDate;

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
    public boolean isValidOn(LocalDate date) {
        return dateIssued.compareTo(date) <= 0 && getExpiredDate().compareTo(date) > 0;
    }
}
