package org._25_specification.ex02;

public class OrSpec implements Specification {
    private Specification spec1;
    private Specification spec2;

    public OrSpec(Specification spec1, Specification spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(Product item) {
        return spec1.isSatisfiedBy(item)
                || spec2.isSatisfiedBy(item);
    }
}