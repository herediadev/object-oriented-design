package example4;

public class Article {

    private final Warranty moneyBackGuarantee;
    private final Warranty expressWarranty;

    private Warranty effectiveExpressWarranty;

    private Article(Warranty moneyBackGuarantee, Warranty expressWarranty,Warranty effectiveExpressWarranty) {
        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
        this.effectiveExpressWarranty = effectiveExpressWarranty;
    }

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
        //constructor Precondition
        if (moneyBackGuarantee == null) throw new IllegalArgumentException();
        if (expressWarranty == null) throw new IllegalArgumentException();

        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
        this.effectiveExpressWarranty = Warranty.VOID;
    }

    public Warranty getMoneyBackGuarantee() {
        return moneyBackGuarantee;
    }

    public Warranty getExpressWarranty() {
        return effectiveExpressWarranty;
    }

    public Article withVisibleDamage() {
        return new Article(Warranty.VOID, expressWarranty, effectiveExpressWarranty);
    }

    public Article notOperational() {
        return new Article(moneyBackGuarantee, expressWarranty, expressWarranty);
    }
}
