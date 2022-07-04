package fr.univlyon1.stratificationdatalog.models;

public class Constant implements Term {
    public int val;

    public Constant(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Constant constant = (Constant) obj;

        return val == constant.val;
    }

    @Override
    public int hashCode() {
        return val;
    }

    public static Constant parse(String s) {
        return new Constant(Integer.parseInt(s));
    }

}

