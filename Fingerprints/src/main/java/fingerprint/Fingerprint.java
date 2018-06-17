package fingerprint;

import java.util.regex.Pattern;

public class Fingerprint {
    private static final String SELECT = "SELECT";


    public Pattern pattern;
    public String role;

    public Fingerprint(String query){
        role = prepareRole(query);
        generatePattern(query);
    }

    private void generatePattern(String query){
        query = query.toUpperCase();
        query = changeQueryForSelectAll(query);
        query = changeQueryVariables(query);
        query = prepareExpression(query);
        pattern = Pattern.compile(query);
    }

    private String changeQueryForSelectAll(String query){
        String[] words = query.split(" ");
        String editedQuery = words[0];
        for(int i=1; i<words.length; i++){
            String word = words[i];
            String previousWord = words[i-1];
            if(word.equals("*") && previousWord.equals(SELECT))
                word = "\\*";
            if(word.equals("COUNT(*)"))
                word = "\\*";
            editedQuery = editedQuery + " " + word;
        }
        return editedQuery;
    }

    private String changeQueryVariables(String query){
        String[] words = query.split(" ");
        String editedQuery = words[0];
        for(int i=1; i<words.length; i++){
            String word = words[i];
            String previousWord = words[i-1];
            if(previousWord.equals("=")){
                if(word.startsWith("\"") && word.endsWith("\""))
                    word = "[ˆ’]*";
                else if(isNumeric(word))
                    word = "[ˆ’]*";
            }
            editedQuery = editedQuery + " " + word;
        }
        return editedQuery;
    }

    private String prepareRole(String query){
        String[] words = query.split(" ");
        String firstWord = words[0];
        String[] splitedWord = firstWord.split(",");
        return splitedWord[0];
    }

    private String prepareExpression(String query){
        query = "^" + query + "$";
        return query;
    }


    private static Boolean isNumeric(String value){
        return value.matches("-?\\d+(\\.\\d+)?");
    }
}
