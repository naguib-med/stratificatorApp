package fr.univlyon1.stratificationdatalog.models;

public class Variable implements Term {
    private final String name;


    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
