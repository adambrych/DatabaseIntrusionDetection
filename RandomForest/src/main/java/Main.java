import java.io.*;

public class Main {

    private static final String QUERY_FILE = "queriesSingle.txt";
    private static final String SELECT = "SELECT";
    private static final String UPDATE = "UPDATE";
    private static final String INSERT = "INSERT";
    private static final String DELETE = "DELETE";

    public static void main(String[] args) {
        try {
            readQueriesFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void readQueriesFromFile() throws IOException {

        File file = new File(QUERY_FILE);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String query;
        String lastRole = "";

        while((query = br.readLine()) != null) {
            query = query.toUpperCase();
            String[] splitedQuery = query.split(" ");
            String firstWord = splitedQuery[0];
            String[] splitedFirstWord = firstWord.split(",");
            String role = splitedFirstWord[0];
            String type = splitedFirstWord[1];
            prepareOperation(splitedQuery, type, query);
        }
    }

    private static void prepareOperation(String[] splitedQuery, String type, String query){
        if(type.equals(SELECT))
            selectOperation(splitedQuery, query);
        else if(type.equals(UPDATE))
            updateOperation(splitedQuery, query);
        else if(type.equals(INSERT))
            insertOperation(splitedQuery, query);
        else if(type.equals(DELETE))
            deleteOperation(splitedQuery, query);
    }

    private static void selectOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.SELECT);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 1;
        while(index < splitedQuery.length && !splitedQuery[index].equals("FROM")){
            String value = splitedQuery[index];
            if(value.startsWith("SUM(")) {
                index = sumOperation(splitedQuery, index, featureVector);
                index++;
                continue;
            }
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            featureVector.getAttributesElements().add(value);
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
            index++;
        }
        index++;
        while(index < splitedQuery.length && !splitedQuery[index].equals("WHERE")){
            String value = splitedQuery[index];
            if(value.equals("(SELECT"))
                index = subSelect(splitedQuery, index, featureVector);
            else{
                featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
                featureVector.getProjectionElements().add(value);
            }
            if(index == splitedQuery.length)
                break;
            index++;
        }
        index = whereSequence(splitedQuery, index, featureVector);
        if(index < splitedQuery.length && splitedQuery[index].equals("GROUP"))
            index = groupOperation(splitedQuery, index, featureVector);
        if(index < splitedQuery.length && splitedQuery[index].equals("ORDER"))
            index = orderOperation(splitedQuery, index, featureVector);
    }

    private static int subSelect(String[] splitedQuery, int index, FeatureVector featureVector){
        while(index < splitedQuery.length && !splitedQuery[index].equals("FROM")){
            String value = splitedQuery[index];
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            featureVector.getAttributesElements().add(value);
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
            index++;
        }
        while(index < splitedQuery.length && !splitedQuery[index].equals("WHERE")){
            String value = splitedQuery[index];
            featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
            featureVector.getProjectionElements().add(value);
            index++;
        }
        index = whereSequence(splitedQuery, index, featureVector);
        return index;

    }

    private static void updateOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.UPDATE);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 1;
    }

    private static void insertOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.INSERT);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 1;
    }

    private static void deleteOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.DELETE);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 1;

    }

    private static int sumOperation(String[] splitedQuery, int index, FeatureVector featureVector){
        String value = splitedQuery[index];
        value = value.substring(4);
        while(!value.endsWith(")")){
            if(value.equals("*")) {
                index++;
                value = splitedQuery[index];
                continue;
            }
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes());
            featureVector.getAttributesElements().add(value);
            index++;
            value = splitedQuery[index];
        }
        value = value.substring(0, value.length() - 1);
        featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes());
        featureVector.getAttributesElements().add(value);

        return index;

    }

    private static int whereSequence(String[] splitedQuery, int index, FeatureVector featureVector){
        index=index+3;
        int i;
        for(i=index; i<splitedQuery.length; i=i+4){
            String value = splitedQuery[i];
            if(value.equals("(SELECT"))
                i = subSelect(splitedQuery, i, featureVector);
            if(value.matches("-?\\d+(\\.\\d+)?")){
                featureVector.setNumberOfNumericValues(featureVector.getNumberOfNumericValues()+1);
            }
            else if(value.startsWith("\"") && value.endsWith("\"")){
                featureVector.setStringValues(featureVector.getStringValues()+1);
                featureVector.setLengthOfStringValues(featureVector.getLengthOfStringValues()+value.length()-2);
            }
            if(i+1<splitedQuery.length && (splitedQuery[i+1].equals("GROUP") || splitedQuery[i+1].equals("ORDER")))
                break;
        }
        return i;
    }

    private static void orderOperation(String[] splitedQuery, int index, FeatureVector featureVector){
        
    }

    private static void groupOperation(String[] splitedQuery, int index, FeatureVector featureVector){

    }
}
