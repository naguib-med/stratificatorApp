package fr.univlyon1.stratificationdatalog.models;

import java.util.Arrays;
import java.util.List;

public class Head extends Atom{
    private String name;
    private List<Term> params;

    public Head(String name, List<Term> params) {
        super(name, params);
        this.name = name;
        this.params = params;
    }

    public Head() {
        super();
    }

    public List<Term> getParams() {
        return params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParams(List<Term> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return name+ "("+ params + ")";

    }
}
