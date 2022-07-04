package fr.univlyon1.stratificationdatalog.parser;

import fr.univlyon1.stratificationdatalog.models.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public Parser() throws IOException {}

    List<Literal> literals = new ArrayList<>();
    List<Rule> rules = new ArrayList<>();

    public void parseProgramRules(Tokenizer tokenizer) throws IOException {
        while (tokenizer.checkNext()) {
            parseRule(tokenizer);
        }
    }


    public void parseRule(Tokenizer tokenizer) throws IOException {
        Atom head = parseAtom(tokenizer);
        List<Literal> body = new ArrayList<>();
        if (!tokenizer.checkToken().equals(".")) {
            tokenizer.saveToken(":-");
            while (true) {
                body.add(parseLiteral(tokenizer));
                if (tokenizer.checkToken().equals(".")) {
                    break;
                }
                tokenizer.saveToken(",");
            }
        }
        tokenizer.saveToken(".");

        if(body.size() == 0){
            literals.add(parseHead(head, false));
        }
        else {
            rules.add(new Rule(head, body));
        }

    }

    public List<Literal> getLiterals() {
        return literals;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Literal parseHead(Atom head, boolean isNegated) {
        return new Literal(head, false);
    }


    public Atom parseAtom(String name, Tokenizer tokenizer) throws IOException {
        ArrayList<Term> terms = new ArrayList<>();
        if (!Character.isUpperCase(name.charAt(0))) {
            System.out.println("Le predicat doit commencer par une lettre majuscule !.");
        }
        if (tokenizer.checkToken().equals("(")) {
            tokenizer.saveToken("(");
            while (true) {
                if (tokenizer.checkToken().equals("'")) {
                    tokenizer.saveToken("'");
                }
                terms.add(new Variable(tokenizer.getNext()));
                if (tokenizer.checkToken().equals(")")) {
                    break;
                }
                if (tokenizer.checkToken().equals("'")) {
                    tokenizer.saveToken("'");
                }
                tokenizer.saveToken(",");
            }
            tokenizer.saveToken(")");
        }

        return new Atom(name, terms);
    }

    public Atom parseAtom(Tokenizer tokenizer) throws IOException {
        return parseAtom(tokenizer.getNext(), tokenizer);
    }

    public Literal parseLiteral(Tokenizer tokenizer) throws IOException {
        String id = tokenizer.getNext();
        if (id.equals("not")) {
            return new Literal(parseAtom(tokenizer), true);
        }
        return new Literal(parseAtom(id, tokenizer), false);
    }
}
