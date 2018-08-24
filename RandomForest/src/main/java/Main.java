import enums.QueryType;
import enums.Table;
import enums.Tables;

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
            int tableIndex = getIndexFromPrefix(value);
            featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
            setAttributesElement(featureVector, value);
            index++;
        }
        index++;
        while(index < splitedQuery.length && !splitedQuery[index].equals("WHERE")){
            String value = splitedQuery[index];
            if(value.equals("(SELECT"))
                index = subSelect(splitedQuery, index, featureVector);
            else{
                featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
                int tableIndex = getIndexFromPrefix(value);
                featureVector.setProjections(featureVector.getProjections() + 2^tableIndex);
            }
            if(index == splitedQuery.length)
                break;
            index++;
        }
        index = whereSequence(splitedQuery, index, featureVector);
        if(index < splitedQuery.length && splitedQuery[index].equals("GROUP"))
            index = groupOperation(splitedQuery, index, featureVector);
        if(index < splitedQuery.length && splitedQuery[index].equals("ORDER"))
            orderOperation(splitedQuery, index, featureVector);
    }

    private static int subSelect(String[] splitedQuery, int index, FeatureVector featureVector){
        while(index < splitedQuery.length && !splitedQuery[index].equals("FROM")){
            String value = splitedQuery[index];
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            int tableIndex = getIndexFromPrefix(value);
            featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
            setAttributesElement(featureVector, value);
            index++;
        }
        while(index < splitedQuery.length && !splitedQuery[index].equals("WHERE")){
            String value = splitedQuery[index];
            featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
            int tableIndex = getIndexFromPrefix(value);
            featureVector.setProjections(featureVector.getProjections() + 2^tableIndex);
            index++;
        }
        index = whereSequence(splitedQuery, index, featureVector);
        return index;

    }

    private static void updateOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.UPDATE);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 2;
        String value = splitedQuery[index];
        featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
        int tableIndex = getIndexFromPrefix(value);
        featureVector.setProjections(featureVector.getProjections() + 2^tableIndex);
        index = index + 2;
        while(!splitedQuery[index].equals("WHERE") && index < splitedQuery.length){
            value = splitedQuery[index];
            tableIndex = getIndexFromPrefix(value);
            featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
            setAttributesElement(featureVector, value);

            index = index + 2;
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            if(value.matches("-?\\d+(\\.\\d+)?")){
                featureVector.setNumberOfNumericValues(featureVector.getNumberOfNumericValues()+1);
            }
            else if(value.startsWith("\"") && value.endsWith("\"")){
                featureVector.setStringValues(featureVector.getStringValues()+1);
                featureVector.setLengthOfStringValues(featureVector.getLengthOfStringValues()+value.length()-2);
            }
            index++;

        }
        if(splitedQuery[index].equals("WHERE"))
            whereSequence(splitedQuery, index, featureVector);
    }

    private static void insertOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.INSERT);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 2;
        String value = splitedQuery[index];
        featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
        int tableIndex = getIndexFromPrefix(value);
        featureVector.setProjections(featureVector.getProjections() + 2^tableIndex);
        index++;
        while(!value.endsWith(")")){
            value = splitedQuery[index];
            if(value.startsWith("("))
                value = value.substring(1);
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);

            tableIndex = getIndexFromPrefix(value);
            featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
            setAttributesElement(featureVector, value);
            index++;
        }
        if(value.endsWith(")")) {
            value = value.substring(0, value.length() - 1);

            tableIndex = getIndexFromPrefix(value);
            featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex] + 1;
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes() + 1);
            setAttributesElement(featureVector, value);

        }
        index = index+2;

        while(!value.endsWith(")")){
            value = splitedQuery[index];
            if(value.startsWith("("))
                value = value.substring(1);
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            if(value.matches("-?\\d+(\\.\\d+)?")){
                featureVector.setNumberOfNumericValues(featureVector.getNumberOfNumericValues()+1);
            }
            else if(value.startsWith("\"") && value.endsWith("\"")){
                featureVector.setStringValues(featureVector.getStringValues()+1);
                featureVector.setLengthOfStringValues(featureVector.getLengthOfStringValues()+value.length()-2);
            }
            index++;

        }
        if(value.endsWith(")")) {
            value = value.substring(0, value.length() - 1);
            if(value.matches("-?\\d+(\\.\\d+)?")){
                featureVector.setNumberOfNumericValues(featureVector.getNumberOfNumericValues()+1);
            }
            else if(value.startsWith("\"") && value.endsWith("\"")){
                featureVector.setStringValues(featureVector.getStringValues()+1);
                featureVector.setLengthOfStringValues(featureVector.getLengthOfStringValues()+value.length()-2);
            }
        }

    }

    private static void deleteOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.DELETE);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 2;
        String value = splitedQuery[index];
        featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
        int tableIndex = getIndexFromPrefix(value);
        featureVector.setProjections(featureVector.getProjections() + 2^tableIndex);
        index++;
        whereSequence(splitedQuery, index, featureVector);
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
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
            int tableIndex = getIndexFromPrefix(value);
            featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;
            index++;
            value = splitedQuery[index];
        }
        value = value.substring(0, value.length() - 1);
        featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
        int tableIndex = getIndexFromPrefix(value);
        featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;

        return index;

    }

    private static int whereSequence(String[] splitedQuery, int index, FeatureVector featureVector){
        index=index+1;
        int i;
        for(i=index; i<splitedQuery.length; i=i+2){
            String value = splitedQuery[i];
            setSelectionElement(featureVector, value);
            i = i+2;
            if(value.equals("(SELECT"))
                i = subSelect(splitedQuery, i, featureVector);
            if(value.matches("-?\\d+(\\.\\d+)?")){
                featureVector.setNumberOfNumericValues(featureVector.getNumberOfNumericValues()+1);
            }
            else if(value.startsWith("\"") && value.endsWith("\"")){
                featureVector.setStringValues(featureVector.getStringValues()+1);
                featureVector.setLengthOfStringValues(featureVector.getLengthOfStringValues()+value.length()-2);
            }
            if(splitedQuery[i+1].equals("AND")||splitedQuery[i+1].equals("OR"))
                featureVector.setNumberOfAndOr(featureVector.getNumberOfAndOr()+1);
            if(i+1<splitedQuery.length && (splitedQuery[i+1].equals("GROUP") || splitedQuery[i+1].equals("ORDER")))
                break;
        }
        return i;
    }

    private static int orderOperation(String[] splitedQuery, int index, FeatureVector featureVector){
        index=index+2;
        while(index < splitedQuery.length && !splitedQuery[index].equals("ORDER")){
            String value = splitedQuery[index];
            if(value.equals("DESC") || value.equals("ASC")){
                index++;
                continue;
            }
            else{
                if(value.endsWith(","))
                    value = value.substring(0, value.length()-1);
                featureVector.setNumberOfOrder(featureVector.getNumberOfOrder()+1);
                setOrderElement(featureVector, value);
            }
            index++;
        }
        return index;
    }

    private static int groupOperation(String[] splitedQuery, int index, FeatureVector featureVector){
        index=index+2;
        while(index < splitedQuery.length){
            String value = splitedQuery[index];
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            featureVector.setNumberOfGroup(featureVector.getNumberOfGroup() + 1);
            setGroupElement(featureVector, value);
            index++;
        }
        return index;
    }

    private static int getIndexFromPrefix(String value){
        int index = 0;
        String substring = "";
        while(!substring.endsWith("_")){
            substring = value.substring(0, index+1);
            index++;
        }
        return Tables.indexByPrefix(substring);
    }

    private static Tables findTable(String value){
        int index = 0;
        String substring = "";
        while(!substring.endsWith("_")){
            substring = value.substring(0, index+1);
            index++;
        }
        return Tables.tableByPrefix(substring);
    }

    private static void setAttributesElement(FeatureVector featureVector, String columnName){
        Tables tables = findTable(columnName);
        int tableIndex = tables.getPosition();
        int columnIndex = 0;
        for(Table table : tables.getTable()){
            if(table.toString().equals(columnName)){
                columnIndex = table.getIndex();
                break;
            }
        }
        featureVector.getPositionOfAttributes()[tableIndex] = featureVector.getPositionOfAttributes()[tableIndex] + 2^columnIndex;
    }

    private static void setSelectionElement(FeatureVector featureVector, String columnName){
        Tables tables = findTable(columnName);
        int tableIndex = tables.getPosition();
        int columnIndex = 0;
        for(Table table : tables.getTable()){
            if(table.toString().equals(columnName)){
                columnIndex = table.getIndex();
                break;
            }
        }
        featureVector.getPositionOfSelection()[tableIndex] = featureVector.getPositionOfSelection()[tableIndex] + 2^columnIndex;
    }

    private static void setGroupElement(FeatureVector featureVector, String columnName){
        Tables tables = findTable(columnName);
        int tableIndex = tables.getPosition();
        int columnIndex = 0;
        for(Table table : tables.getTable()){
            if(table.toString().equals(columnName)){
                columnIndex = table.getIndex();
                break;
            }
        }
        featureVector.getPositionOfGroup()[tableIndex] = featureVector.getPositionOfGroup()[tableIndex] + 2^columnIndex;
    }

    private static void setOrderElement(FeatureVector featureVector, String columnName){
        Tables tables = findTable(columnName);
        int tableIndex = tables.getPosition();
        int columnIndex = 0;
        for(Table table : tables.getTable()){
            if(table.toString().equals(columnName)){
                columnIndex = table.getIndex();
                break;
            }
        }
        featureVector.getPositionOfOrder()[tableIndex] = featureVector.getPositionOfOrder()[tableIndex] + 2^columnIndex;
    }
}
