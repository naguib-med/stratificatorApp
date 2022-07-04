package fr.univlyon1.stratificationdatalog.models;

import java.util.ArrayList;
import java.util.List;

public class Literal {

    private Atom atom;
    private boolean negated;

    static List<Literal> literals = new ArrayList<>();

    public Literal(Atom atom, boolean signe) {
        this.atom = atom;
        this.negated = signe;
        literals.add(this);
    }

    public Literal() {}

    public Literal(Atom atom) {
    }

    public boolean isNegated() {
        return negated;
    }

    public Atom getAtom() {
        return this.atom;
    }

    public void setNegated(boolean negated) {
        this.negated = negated;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    public static List<Literal> getLiterals() {
        return literals;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        if (this.isNegated()) {
            strBuilder.append("not ").append(getAtom().getSymbole());
        }
        else {
            strBuilder.append(getAtom().getSymbole());
        }
        if (getAtom().getAllVariables().size() != 0) {
            strBuilder.append("(");
            for (int i = 0; i < getAtom().getAllVariables().size(); i++) {
                strBuilder.append(getAtom().getAllVariables().get(i));
                if (i < this.getAtom().getAllVariables().size() - 1) {
                    strBuilder.append(", ");
                }
            }

            strBuilder.append(")");
        }
        return strBuilder.toString();
    }
}