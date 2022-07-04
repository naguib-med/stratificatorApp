package fr.univlyon1.stratificationdatalog.models;

import java.util.ArrayList;
import java.util.List;

public class Atom {
    private String symbole;
    private List<Term> args;

    private static List<Atom> atoms = new ArrayList<>();

    public Atom(String symbole, List<Term> args) {
        this.symbole = symbole;
        this.args = args;
        atoms.add(this);
    }

    public Atom() {}
    public String getSymbole() {
        return symbole;
    }


    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public void setArgs(List<Term> args) {
        this.args = args;
    }

    public static List<Atom> getAtoms() {
        return atoms;
    }

    public List<Term> getArgs() {
        return args;
    }

    public List<Variable> getAllVariables() {
        List<Variable> container = new ArrayList<>();
        for (Term v: this.getArgs()) {
            if (v instanceof Variable) {
                container.add((Variable) v);
            }
        }
        return container;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.symbole);
        if (this.getAllVariables().size() != 0) {
            stringBuilder.append("(");
            for (int i = 0; i < this.getAllVariables().size(); i++) {
                stringBuilder.append(getAllVariables().get(i));
                if (i < this.getAllVariables().size() - 1) {
                    stringBuilder.append(", ");
                }
            }

            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}
