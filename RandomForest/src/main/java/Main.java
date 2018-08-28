import enums.QueryType;
import enums.Table;
import enums.Tables;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.pmml.consumer.NeuralNetwork;
import weka.classifiers.pmml.consumer.Regression;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.pmml.jaxbbindings.DecisionTree;

import java.io.*;
import java.util.*;

public class Main {

    private static final String QUERY_FILE = "queries10.txt";
    private static final String SELECT = "SELECT";
    private static final String UPDATE = "UPDATE";
    private static final String INSERT = "INSERT";
    private static final String DELETE = "DELETE";
    private static final int type = 1;

    public static void main(String[] args) {
        try {
            if(type == 1) {
                readQueriesFromFile();
                process();
            }
            else
                process();
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
        List<Query> queries = new ArrayList<Query>();

        while((query = br.readLine()) != null) {
            query = query.toUpperCase();
            String[] splitedQuery = query.split(" ");
            splitedQuery = joinString(splitedQuery);
            String firstWord = splitedQuery[0];
            String[] splitedFirstWord = firstWord.split(",");
            String role = splitedFirstWord[0];
            String type = splitedFirstWord[1];
            Query preparedQuery = prepareOperation(splitedQuery, type, query);
            preparedQuery.setRole(role);
            queries.add(preparedQuery);
        }
        generateAffr(queries);
        generateTextAffr(queries);
    }

    private static Query prepareOperation(String[] splitedQuery, String type, String query){
        if(type.equals(SELECT))
            return selectOperation(splitedQuery, query);
        else if(type.equals(UPDATE))
            return updateOperation(splitedQuery, query);
        else if(type.equals(INSERT))
            return insertOperation(splitedQuery, query);
        else if(type.equals(DELETE))
            return deleteOperation(splitedQuery, query);
        return new Query(QueryType.SELECT);
    }

    private static Query selectOperation(String[] splitedQuery, String queryString){
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

            if(!value.equals("COUNT(*)")) {
                int tableIndex = getIndexFromPrefix(value);
                featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex] + 1;
                featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes() + 1);
                setAttributesElement(featureVector, value);
            }
            index++;
        }
        index++;
        while(index < splitedQuery.length && !splitedQuery[index].equals("WHERE")){
            String value = splitedQuery[index];
            if(value.equals("(SELECT"))
                index = subSelect(splitedQuery, index, featureVector);
            else{
                featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
                int tableIndex = findTableIndex(value);
                featureVector.setProjections(featureVector.getProjections() + (int)Math.pow(2,tableIndex));
            }
            if(index == splitedQuery.length)
                break;
            if(!value.endsWith(",") && index+1 < splitedQuery.length && splitedQuery[index+1].endsWith(","))
                index++;
            index++;
        }
        index = whereSequence(splitedQuery, index, featureVector);
        if(index < splitedQuery.length && splitedQuery[index].equals("GROUP"))
            index = groupOperation(splitedQuery, index, featureVector);
        if(index < splitedQuery.length && splitedQuery[index].equals("ORDER"))
            orderOperation(splitedQuery, index, featureVector);

        return query;
    }

    private static int subSelect(String[] splitedQuery, int index, FeatureVector featureVector){
        index++;
        while(index < splitedQuery.length && !splitedQuery[index].equals("FROM")){
            String value = splitedQuery[index];
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            if(value.equals("AS")){
                index = index+2;
                continue;
            }
            if(!value.equals("ROWNUM")) {
                int tableIndex = getIndexFromPrefix(value);
                featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex] + 1;
                featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes() + 1);
                setAttributesElement(featureVector, value);
            }
            index++;
        }
        while(index < splitedQuery.length && !splitedQuery[index].equals("WHERE")){
            String value = splitedQuery[index];
            featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
            int tableIndex = findTableIndex(value);
            featureVector.setProjections(featureVector.getProjections() + (int)Math.pow(2,tableIndex));
            index++;
        }
        index = whereSequence(splitedQuery, index, featureVector);
        return index;

    }

    private static Query updateOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.UPDATE);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 1;
        String value = splitedQuery[index];
        featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
        int tableIndex = findTableIndex(value);
        featureVector.setProjections(featureVector.getProjections() + (int)Math.pow(2,tableIndex));
        index = index + 2;
        while(index < splitedQuery.length && !splitedQuery[index].equals("WHERE")){
            value = splitedQuery[index];
            tableIndex = getIndexFromPrefix(value);
            featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;
            featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
            setAttributesElement(featureVector, value);

            index = index + 2;
            value = splitedQuery[index];
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
        if(index < splitedQuery.length && splitedQuery[index].equals("WHERE"))
            whereSequence(splitedQuery, index, featureVector);

        return query;
    }

    private static Query insertOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.INSERT);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 2;
        String value = splitedQuery[index];
        featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
        int tableIndex = findTableIndex(value);
        featureVector.setProjections(featureVector.getProjections() + (int)Math.pow(2,tableIndex));
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
        return query;
    }

    private static Query deleteOperation(String[] splitedQuery, String queryString){
        Query query = new Query(QueryType.DELETE);
        FeatureVector featureVector = query.getFeatureVector();
        featureVector.setLength(queryString.length());
        int index = 2;
        String value = splitedQuery[index];
        featureVector.setNumberOfProjections(featureVector.getNumberOfProjections() + 1);
        int tableIndex = findTableIndex(value);
        featureVector.setProjections(featureVector.getProjections() + (int)Math.pow(2, tableIndex));
        index++;
        whereSequence(splitedQuery, index, featureVector);

        return query;
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
            setAttributesElement(featureVector, value);
            int tableIndex = getIndexFromPrefix(value);
            featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;
            index++;
            value = splitedQuery[index];
        }
        value = value.substring(0, value.length() - 1);
        featureVector.setNumberOfAttributes(featureVector.getNumberOfAttributes()+1);
        setAttributesElement(featureVector, value);
        int tableIndex = getIndexFromPrefix(value);
        featureVector.getAttributesElements()[tableIndex] = featureVector.getAttributesElements()[tableIndex]+1;

        return index;

    }

    private static int whereSequence(String[] splitedQuery, int index, FeatureVector featureVector){
        index=index+1;
        int i;
        for(i=index; i<splitedQuery.length; i=i+2){
            String value = splitedQuery[i];
            featureVector.setNumberOfSelection(featureVector.getNumberOfSelection() + 1);
            setSelectionElement(featureVector, value);
            i = i+2;
            value = splitedQuery[i];
            if(value.equals("(SELECT"))
                i = subSelect(splitedQuery, i, featureVector);
            if(value.matches("-?\\d+(\\.\\d+)?")){
                featureVector.setNumberOfNumericValues(featureVector.getNumberOfNumericValues()+1);
            }
            else if(value.startsWith("\"") && value.endsWith("\"")){
                featureVector.setStringValues(featureVector.getStringValues()+1);
                featureVector.setLengthOfStringValues(featureVector.getLengthOfStringValues()+value.length()-2);
            }
            if(i+1<splitedQuery.length && (splitedQuery[i+1].equals("AND")||splitedQuery[i+1].equals("OR")))
                featureVector.setNumberOfAndOr(featureVector.getNumberOfAndOr()+1);
            if(i+1<splitedQuery.length && (splitedQuery[i+1].equals("GROUP") || splitedQuery[i+1].equals("ORDER")))
                break;
            if(i+1<splitedQuery.length && splitedQuery[i+1].equals("LIMIT"))
                break;

        }
        return i+1;
    }

    private static int orderOperation(String[] splitedQuery, int index, FeatureVector featureVector){
        index=index+2;
        while(index < splitedQuery.length && !splitedQuery[index].equals("ORDER")){
            String value = splitedQuery[index];
            if(value.equals("DESC") || value.equals("ASC") || value.equals("DESC)") || value.equals("ASC)") || value.equals("DESC,") || value.equals("ASC,")){

            }
            else if(value.equals("LIMIT"))
                break;
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
            if(splitedQuery[index].equals("ORDER"))
                break;
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

    private static int findTableIndex(String value){
        if(value.endsWith(","))
            value = value.substring(0, value.length()-1);
        for(Tables table : Tables.values()){
            if(table.toString().equals(value)){
                return table.getPosition();
            }
        }
        return 0;
    }

    private static void setAttributesElement(FeatureVector featureVector, String columnName){
        while(columnName.contains(".")){
            columnName = columnName.substring(1, columnName.length());
        }
        Tables tables = findTable(columnName);
        int tableIndex = tables.getPosition();
        int columnIndex = 0;
        for(Table table : tables.getTable()){
            if(table.toString().equals(columnName)){
                columnIndex = table.getIndex();
                break;
            }
        }
        featureVector.getPositionOfAttributes()[tableIndex] = featureVector.getPositionOfAttributes()[tableIndex] + (int)Math.pow(2,columnIndex);
    }

    private static void setSelectionElement(FeatureVector featureVector, String columnName){
        while(columnName.contains(".")){
            columnName = columnName.substring(1, columnName.length());
        }
        Tables tables = findTable(columnName);
        int tableIndex = tables.getPosition();
        int columnIndex = 0;
        for(Table table : tables.getTable()){
            if(table.toString().equals(columnName)){
                columnIndex = table.getIndex();
                break;
            }
        }
        featureVector.getNumberOfAttributesFromSelection()[tableIndex] = featureVector.getNumberOfAttributesFromSelection()[tableIndex] + 1;
        featureVector.getPositionOfSelection()[tableIndex] = featureVector.getPositionOfSelection()[tableIndex] + (int)Math.pow(2,columnIndex);
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
        featureVector.getNumberOfAttributesFromGroup()[tableIndex] = featureVector.getNumberOfAttributesFromGroup()[tableIndex] + 1;
        featureVector.getPositionOfGroup()[tableIndex] = featureVector.getPositionOfGroup()[tableIndex] + (int)Math.pow(2,columnIndex);
    }

    private static void setOrderElement(FeatureVector featureVector, String columnName){
        if(columnName.matches("-?\\d+(\\.\\d+)?"))
            return;
        Tables tables = findTable(columnName);
        int tableIndex = tables.getPosition();
        int columnIndex = 0;
        for(Table table : tables.getTable()){
            if(table.toString().equals(columnName)){
                columnIndex = table.getIndex();
                break;
            }
        }
        featureVector.getNumberOfAttributesFromOrder()[tableIndex] = featureVector.getNumberOfAttributesFromOrder()[tableIndex] + 1;
        featureVector.getPositionOfOrder()[tableIndex] = featureVector.getPositionOfOrder()[tableIndex] + (int)Math.pow(2,columnIndex);
    }

    private static String[] joinString(String[] splitedQuery){
        List<String> newSplitedQuery = new ArrayList<String>();
        int index = 0;
        while(index < splitedQuery.length){
            StringBuilder builder = new StringBuilder();
            builder.append(splitedQuery[index]);
            if(splitedQuery[index].startsWith("\"") && (!splitedQuery[index].endsWith("\"") && !splitedQuery[index].endsWith("\",") && !splitedQuery[index].endsWith("\")"))){
                do{
                    index++;
                    builder.append(splitedQuery[index]);
                }
                while(!splitedQuery[index].endsWith("\"") && !splitedQuery[index].endsWith("\",") && !splitedQuery[index].endsWith("\")"));
            }
            newSplitedQuery.add(builder.toString());
            index++;
        }
        String[] array = Arrays.copyOf(newSplitedQuery.toArray(), newSplitedQuery.size(), String[].class);
        return array ;
    }

    private static void generateAffr(List<Query> queries){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("trainingSet.affr", false));
            writer.append("@Relation test");
            writer.newLine();
            writer.append("@attribute queryType {1,2,3,4}");
            writer.newLine();
            writer.append("@attribute length numeric");
            writer.newLine();
            writer.append("@attribute numberOfProjections numeric");
            writer.newLine();
            writer.append("@attribute projections numeric");
            writer.newLine();
            writer.append("@attribute numberOfAttributes numeric");
            writer.newLine();
            for(int i=0; i<33; i++){
                writer.append("@attribute numberOfAttributes").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }
            for(int i=0; i<33; i++){
                writer.append("@attribute positionOfAttributes").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }

            writer.append("@attribute numberOfSelection numeric");
            writer.newLine();
            for(int i=0; i<33; i++){
                writer.append("@attribute numberOfAttributesFromSelection").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }
            for(int i=0; i<33; i++){
                writer.append("@attribute positionOfSelection").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }



            writer.append("@attribute numberOfOrder numeric");
            writer.newLine();
            for(int i=0; i<33; i++){
                writer.append("@attribute numberOfAttributesFromOrder").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }
            for(int i=0; i<33; i++){
                writer.append("@attribute positionOfOrder").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }


            writer.append("@attribute numberOfGroup numeric");
            writer.newLine();
            for(int i=0; i<33; i++){
                writer.append("@attribute numberOfAttributesFromGroup").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }
            for(int i=0; i<33; i++){
                writer.append("@attribute positionOfGroup").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }

            writer.append("@attribute stringValues numeric");
            writer.newLine();

            writer.append("@attribute lengthOfStringValues numeric");
            writer.newLine();

            writer.append("@attribute numberOfNumericValues numeric");
            writer.newLine();

            writer.append("@attribute numberOfJoins numeric");
            writer.newLine();

            writer.append("@attribute numberOfAndOr numeric");
            writer.newLine();
            writer.append("@attribute role {1,2,4,5,6,7,8,9,10,11}");
            writer.newLine();

            writer.append("@data");
            writer.newLine();
            for(Query query : queries){
                writer.append(query.toString());
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateTextAffr(List<Query> queries){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("testSet.affr", false));
            writer.append("@Relation test");
            writer.newLine();
            writer.append("@attribute queryType {1,2,3,4}");
            writer.newLine();
            writer.append("@attribute length numeric");
            writer.newLine();
            writer.append("@attribute numberOfProjections numeric");
            writer.newLine();
            writer.append("@attribute projections numeric");
            writer.newLine();
            writer.append("@attribute numberOfAttributes numeric");
            writer.newLine();
            for(int i=0; i<33; i++){
                writer.append("@attribute numberOfAttributes").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }
            for(int i=0; i<33; i++){
                writer.append("@attribute positionOfAttributes").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }

            writer.append("@attribute numberOfSelection numeric");
            writer.newLine();
            for(int i=0; i<33; i++){
                writer.append("@attribute numberOfAttributesFromSelection").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }
            for(int i=0; i<33; i++){
                writer.append("@attribute positionOfSelection").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }



            writer.append("@attribute numberOfOrder numeric");
            writer.newLine();
            for(int i=0; i<33; i++){
                writer.append("@attribute numberOfAttributesFromOrder").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }
            for(int i=0; i<33; i++){
                writer.append("@attribute positionOfOrder").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }


            writer.append("@attribute numberOfGroup numeric");
            writer.newLine();
            for(int i=0; i<33; i++){
                writer.append("@attribute numberOfAttributesFromGroup").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }
            for(int i=0; i<33; i++){
                writer.append("@attribute positionOfGroup").append(Integer.toString(i)).append(" numeric");
                writer.newLine();
            }

            writer.append("@attribute stringValues numeric");
            writer.newLine();

            writer.append("@attribute lengthOfStringValues numeric");
            writer.newLine();

            writer.append("@attribute numberOfNumericValues numeric");
            writer.newLine();

            writer.append("@attribute numberOfJoins numeric");
            writer.newLine();

            writer.append("@attribute numberOfAndOr numeric");
            writer.newLine();
            writer.append("@attribute role {1,2,4,5,6,7,8,9,10,11}");
            writer.newLine();

            writer.append("@data");
            writer.newLine();
            for(Query query : queries){
                writer.append(query.getFeatureVector().toString());
                writer.append(",?");
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Instances getDataSet(String fileName) throws IOException {
        ArffLoader loader = new ArffLoader();
        InputStream inputStream = new FileInputStream(fileName);
        loader.setSource(inputStream);
        Instances dataSet = loader.getDataSet();
        dataSet.setClassIndex(dataSet.numAttributes()-1);
        return dataSet;
    }

    public static void process() {
        try {
            Instances trainingDataSet = getDataSet("trainingSet.affr");
            //Instances testingDataSet = getDataSet("testSet.affr");


            RandomForest forest=new RandomForest();
            forest.buildClassifier(trainingDataSet);
            //for(int i=0; i<testingDataSet.numInstances(); i++)
            //    System.out.println(forest.classifyInstance(testingDataSet.get(i))+1);
            Evaluation evalRandomForest = new Evaluation(trainingDataSet);
            evalRandomForest.crossValidateModel(forest, trainingDataSet, 10, new Random(10));

            System.out.println("** Decision Tress Evaluation with Datasets **");
            System.out.println(evalRandomForest.toSummaryString());
            System.out.print(" the expression for the input data as per alogorithm is ");
            System.out.println(forest);
            System.out.println(evalRandomForest.toMatrixString());
            System.out.println(evalRandomForest.toClassDetailsString());


            /** Classifier here is Linear Regression */
            Classifier classifierJ48 = new J48();
            //J48,Id3
            /** */
            classifierJ48.buildClassifier(trainingDataSet);
            /**
             * train the alogorithm with the training data and evaluate the
             * algorithm with testing data
             */
            Evaluation evalJ48 = new Evaluation(trainingDataSet);
            evalJ48.crossValidateModel(classifierJ48, trainingDataSet, 10, new Random(10));
            /** Print the algorithm summary */
            System.out.println("** Decision Tress Evaluation with Datasets **");
            System.out.println(evalJ48.toSummaryString());
            System.out.print(" the expression for the input data as per alogorithm is ");
            System.out.println(classifierJ48);
            System.out.println(evalJ48.toMatrixString());
            System.out.println(evalJ48.toClassDetailsString());

            Classifier ibk = new IBk(1);
            ibk.buildClassifier(trainingDataSet);

            System.out.println(ibk);

            Evaluation evalKNN = new Evaluation(trainingDataSet);
            evalKNN.crossValidateModel(ibk, trainingDataSet, 10, new Random(10));
            /** Print the algorithm summary */
            System.out.println("** KNN Demo  **");
            System.out.println(evalKNN.toSummaryString());
            System.out.println(evalKNN.toClassDetailsString());
            System.out.println(evalKNN.toMatrixString());




            Classifier classifierNB = new NaiveBayes();
            /** */
            classifierNB.buildClassifier(trainingDataSet);
            /**
             * train the alogorithm with the training data and evaluate the
             * algorithm with testing data
             */
            Evaluation evalNB= new Evaluation(trainingDataSet);
            evalNB.crossValidateModel(classifierNB, trainingDataSet, 10, new Random(10));
            /** Print the algorithm summary */
            System.out.println("** Linear Regression Evaluation with Datasets **");
            System.out.println(evalNB.toSummaryString());
            System.out.print(" the expression for the input data as per alogorithm is ");
            System.out.println(classifierNB);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
