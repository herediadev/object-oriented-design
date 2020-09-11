package example5;

import java.time.LocalDate;

public interface Warranty {

    Warranty on(LocalDate date);

    default void claim(Runnable action){
        action.run();
    };

    Warranty VOID = new VoidWarranty();

    static Warranty lifetime(LocalDate issuedOn) {
        return new LifeTimeWarranty(issuedOn);
    }
}
