package fr.univlyon1.stratificationdatalog.models;

import java.util.Map;

public class Stratums {
    private static Map<String, Integer> stratums;

    public Stratums(Map<String, Integer> stratums) {
        Stratums.stratums = stratums;
    }

    public Stratums() {

    }

    public void initStratification() {
        for (Literal literal: Literal.getLiterals()) {
            stratums.put(literal.getAtom().getSymbole(), 1);
        }
        for (Atom atom: Atom.getAtoms()) {
            stratums.put(atom.getSymbole(), 1);
        }
    }

    public void stratification() {
        boolean isChanged = false;
        Atom predicateP;
        int stratum = 1, cptPredicates = stratums.size();

        initStratification();

        do {
            for (Rule rule : Rule.getRules()) {
                predicateP = rule.getHead();
                for (Literal subgoal : rule.getBody()) {
                    if (subgoal.isNegated()) {
                        stratums.put(predicateP.getSymbole(),  Integer.max(stratums.get(subgoal.getAtom().getSymbole()) + 1,
                                stratums.get(predicateP.getSymbole())));
                        isChanged = true;
                        stratum = stratums.get(predicateP.getSymbole());
                    }
                    else {
                        stratums.put(predicateP.getSymbole(), Integer.max(stratums.get(subgoal.getAtom().getSymbole()),
                                stratums.get(predicateP.getSymbole())));
                    }
                }
            }

        } while (stratum < cptPredicates || !isChanged);

    }

    public static StringBuilder showStratificationTab() {
        StringBuilder stringBuilder = new StringBuilder();
        stratums.forEach((key, values) -> {
            for (Rule rule : Rule.getRules()) {
                if (rule.getHead().getSymbole().equals(key)) {
                    stringBuilder.append("(").append(key).append(" , ").append(values.toString()).append(") \t");
                }
            }
        });
       return stringBuilder;
    }

    public static Map<String, Integer> getStratums() {
        return stratums;
    }
}
