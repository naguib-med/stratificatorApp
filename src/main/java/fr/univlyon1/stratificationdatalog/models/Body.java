package fr.univlyon1.stratificationdatalog.models;

import java.util.ArrayList;

public class Body {

    private String name;
    private ArrayList<Term> params;
    public Body(String name, ArrayList<Term> params) {
        this.name = name;
        this.params = params;
    }

    public Body() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParams(ArrayList<Term> params) {
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Term> getParams() {
        return params;
    }
}
