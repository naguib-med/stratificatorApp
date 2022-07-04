package fr.univlyon1.stratificationdatalog.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Tokenizer {
    private final StreamTokenizer streamTokenizer;


    private static final int QUOTE_CHARACTER = '\'';
    private static final int DOUBLE_QUOTE_CHARACTER = '"';
    public Tokenizer(Reader reader) {
        this.streamTokenizer = new StreamTokenizer(reader);
        this.streamTokenizer.commentChar('#');
        this.streamTokenizer.wordChars('0', '9');
        this.streamTokenizer.ordinaryChar('.');
        this.streamTokenizer.ordinaryChars('0', '9');
        this.streamTokenizer.ordinaryChar('/');
        this.streamTokenizer.ordinaryChar('!');
        this.streamTokenizer.ordinaryChar('=');
        this.streamTokenizer.ordinaryChars('_', '_');


    }

    private String getOriginalToken(int currentToken) throws IOException {
        String token;
        if (this.streamTokenizer.ttype == StreamTokenizer.TT_EOF) {
            System.out.println("Fin du fichier");
        } else if (this.streamTokenizer.ttype == StreamTokenizer.TT_EOL) {
            System.out.println("Fin de la ligne");
        }
        if (this.streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
            token = String.valueOf(this.streamTokenizer.nval);
        } else if (streamTokenizer.ttype == StreamTokenizer.TT_WORD || streamTokenizer.ttype == QUOTE_CHARACTER
                || streamTokenizer.ttype == DOUBLE_QUOTE_CHARACTER ) {
            token = this.streamTokenizer.sval;
        } else {
            token = String.valueOf((char) currentToken);
        }
        return token;
    }

    public boolean checkNext() throws IOException {
        this.streamTokenizer.nextToken();
        this.streamTokenizer.pushBack();
        return this.streamTokenizer.ttype != StreamTokenizer.TT_EOF;

    }

    public String checkToken() throws IOException {
        String token = getOriginalToken(this.streamTokenizer.nextToken());
        this.streamTokenizer.pushBack();
        return token;
    }

    public void saveToken(String s) throws IOException {
        StringBuilder token = new StringBuilder();
        while (token.length() < s.length()) {
            token.append(getOriginalToken(this.streamTokenizer.nextToken()));
        }
    }

    public String getNext() throws IOException {
        return getOriginalToken(this.streamTokenizer.nextToken());
    }
}
