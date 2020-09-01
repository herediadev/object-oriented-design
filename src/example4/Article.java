package example4;

public class Article {

    private final Warranty moneyBackGuarantee;
    private final Warranty expressWarranty;

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
        //constructor Precondition
        if (moneyBackGuarantee == null) throw new IllegalArgumentException();
        if (expressWarranty == null) throw new IllegalArgumentException();

        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
    }

    public Warranty getMoneyBackGuarantee() {
        return moneyBackGuarantee;
    }

    public Warranty getExpressWarranty() {
        return expressWarranty;
    }
}
