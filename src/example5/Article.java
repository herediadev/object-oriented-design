package example5;

import java.time.LocalDate;
import java.util.Optional;

public class Article {

    private final Warranty moneyBackGuarantee;
    private final Warranty expressWarranty;
    private Warranty effectiveExpressWarranty;
    private Optional<Part> sensor;
    private Warranty extendedWarranty;

    private Article(Warranty moneyBackGuarantee, Warranty expressWarranty, Warranty effectiveExpressWarranty, Optional<Part> sensor, Warranty extendedWarranty) {
        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
        this.effectiveExpressWarranty = effectiveExpressWarranty;
        this.sensor = sensor;
        this.extendedWarranty = extendedWarranty;
    }

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
        //constructor Precondition
        if (moneyBackGuarantee == null) throw new IllegalArgumentException();
        if (expressWarranty == null) throw new IllegalArgumentException();

        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
        this.effectiveExpressWarranty = Warranty.VOID;
        this.sensor = Optional.empty();
        this.extendedWarranty = Warranty.VOID;
    }

    public Warranty getMoneyBackGuarantee() {
        return moneyBackGuarantee;
    }

    public Warranty getExpressWarranty() {
        return effectiveExpressWarranty;
    }

    public Article withVisibleDamage() {
        return new Article(Warranty.VOID, expressWarranty, effectiveExpressWarranty, sensor, extendedWarranty);
    }

    public Article notOperational() {
        return new Article(moneyBackGuarantee, expressWarranty, expressWarranty, sensor, extendedWarranty);
    }

    public Article install(Part sensor, Warranty extendedWarranty) {
        return new Article(moneyBackGuarantee, expressWarranty, effectiveExpressWarranty, Optional.of(sensor), extendedWarranty);
    }

    public Article sensorNotOperational(LocalDate detectedOn) {
        return this.sensor
                .map(part -> part.defective(detectedOn))
                .map(defective -> this.install(defective,this.extendedWarranty))
                .orElse(this);
    }

    public Warranty getExtendedWarranty() {
        return this.sensor.map(part -> part.apply(this.extendedWarranty)).orElse(Warranty.VOID);
    }
}
