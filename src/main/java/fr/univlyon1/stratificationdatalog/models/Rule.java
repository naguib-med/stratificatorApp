package fr.univlyon1.stratificationdatalog.models;

import java.util.*;

public class Rule {

    private  Atom head;
    private  List<Literal> body;

    private static List<Rule> rules =  new ArrayList<>();
    public Rule(Atom head, List<Literal> body) {
        this.head = head;
        this.body = body;
        rules.add(this);
    }

    public Rule() {}

    public Atom getHead() {
        return head;
    }

    public List<Literal> getBody() {
        return body;
    }
    public static List<Rule> getRules() {
        return rules;
    }


    public static void setRules(List<Rule> rules) {
        Rule.rules = rules;
    }

    public void setHead(Atom head) {
        this.head = head;
    }

    public void setBody(List<Literal> body) {
        this.body = body;
    }

    public static ArrayList<Rule> getRuleByHead (String headRule) {
        ArrayList<Rule> rules = new ArrayList<>();
        Stratums.getStratums().forEach((key, val) -> {
            if (key.equals(headRule)) {
                Rule.getRules().forEach((rule) -> {
                    if (rule.getHead().getSymbole().equals(headRule)) {
                        rules.add(rule);
                    }
                });
            }
        });
        return rules;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getHead().toString());
        if (!this.getBody().isEmpty()) {
            sb.append(" :- ");
            for (int i = 0; i < this.getBody().size(); ++i) {
                sb.append(this.getBody().get(i));
                if (i < this.getBody().size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append('.');
        return sb.toString();
    }

}