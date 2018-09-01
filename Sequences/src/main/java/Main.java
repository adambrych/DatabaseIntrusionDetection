import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Sequence.*;
import Sequence.impl.ReadOnlySequence;
import Sequence.impl.ReadSequence;
import Sequence.impl.WriteSequence;
import operation.Operation;
import operation.OperationType;
import operation.impl.Read;
import operation.impl.Write;

public class Main {

    private static final String QUERY_FILE = "queriesSingle.txt";
    private static final String QUERY_FILE_TEST = "queriesSingle_selectAll.txt";
    private static final String SELECT = "SELECT";
    private static final String UPDATE = "UPDATE";
    private static final String INSERT = "INSERT";
    private static final String DELETE = "DELETE";

    public static void main(String[] args) {
        try {
            List<RWSequences> rwSequences = readQueriesFromFile(QUERY_FILE);
            List<RWSequences> rwSequencesTest = readQueriesFromFile(QUERY_FILE_TEST);
            test(rwSequences, rwSequencesTest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<RWSequences> readQueriesFromFile(String fileName) throws IOException {

        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String query;
        String lastRole = "";
        Transaction lastTransaction = null;
        List<Transaction> transactions = new ArrayList();

        while((query = br.readLine()) != null) {
            query = query.toUpperCase();
            String[] splitedQuery = query.split(" ");
            splitedQuery = joinString(splitedQuery);
            String firstWord = splitedQuery[0];
            String[] splitedFirstWord = firstWord.split(",");
            String role = splitedFirstWord[0];
            Transaction transaction = prepareTransaction(lastTransaction, lastRole, role, transactions);
            String type = splitedFirstWord[1];
            Sequence sequence = prepareOperation(splitedQuery, type);
            transaction.getSequences().add(sequence);
            lastTransaction = transaction;
            lastRole = role;
        }
        writeToFile(transactions);
        return prepareReadAndWriteSequnces(transactions);
    }

    private static Transaction prepareTransaction(Transaction lastTransaction, String lastRole, String role, List<Transaction> transactions){
        if(lastRole.equals(role))
            return lastTransaction;
        else
            if(lastTransaction == null)
                return new Transaction(role);
            else{
                lastTransaction.joinSequences();
                transactions.add(lastTransaction);
                return new Transaction(role);
            }
    }

    private static Sequence prepareOperation(String[] splitedQuery, String type){
        Sequence sequence;
        if(type.equals(SELECT))
            sequence = selectOperation(splitedQuery);
        else if(type.equals(UPDATE))
            sequence = updateOperation(splitedQuery);
        else if(type.equals(INSERT))
            sequence = insertOperation(splitedQuery);
        else if(type.equals(DELETE))
            sequence = deleteOperation(splitedQuery);
        else
            sequence = new Sequence();
        return sequence;
    }

    private static Sequence selectOperation(String[] splitedQuery){
        Sequence sequence = new Sequence();
        int i;
        for(i=1; i<splitedQuery.length; i++){
            String value = splitedQuery[i];
            if(value.equals("FROM")){
                value = splitedQuery[i];
                while(!value.equals("WHERE") && i<splitedQuery.length){
                    if(value.equals("(SELECT"))
                        i = subSelect(splitedQuery, i, sequence);
                    if(i+1>=splitedQuery.length)
                        break;
                    i++;
                    value = splitedQuery[i];
                }
                if(i == splitedQuery.length)
                    break;
                whereSequence(splitedQuery, i, sequence);
                break;
            }
            Read read = new Read();
            if(value.startsWith("SUM(")) {
                i = sumOperation(splitedQuery, i, sequence);
                continue;
            }

            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            read.setColumn(value);
            sequence.getSequence().add(read);
        }
        return sequence;
    }

    private static Sequence updateOperation(String[] splitedQuery){
        Sequence sequence = new Sequence();
        int i = 1;
        String value = splitedQuery[i];
        while(!value.equals("SET")){
            i++;
            value = splitedQuery[i];
        }
        i++;
        int j;
        for(j=i; j<splitedQuery.length; j++){

            value = splitedQuery[j];
            Write write = new Write();
            write.setColumn(value);
            sequence.getSequence().add(write);
            while((!value.endsWith(",") || !value.equals("WHERE")) && j+1<splitedQuery.length){
                j++;
                value = splitedQuery[j];
            }
            if(value.equals("WHERE")) {
                whereSequence(splitedQuery, j, sequence);
                break;
            }
        }
        return sequence;
    }

    private static Sequence insertOperation(String[] splitedQuery){
        Sequence sequence = new Sequence();
        int index=3;
        String value = splitedQuery[index];
        while(!value.endsWith(")")){
            if(value.startsWith("("))
                value = value.substring(1);
            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);

            Write write = new Write();
            write.setColumn(value);
            sequence.getSequence().add(write);
            index++;
            value = splitedQuery[index];
        }
        if(value.endsWith(")")) {
            value = value.substring(0, value.length() - 1);

            Write write = new Write();
            write.setColumn(value);
            sequence.getSequence().add(write);
        }
        return new Sequence();
    }

    private static Sequence deleteOperation(String[] splitedQuery){
        Sequence sequence = new Sequence();
        int index=1;
        while(!splitedQuery[index].equals("WHERE") && index<splitedQuery.length)
            index++;
        if(splitedQuery[index].equals("WHERE"))
            whereSequence(splitedQuery, index, sequence);
        return new Sequence();
    }

    private static int sumOperation(String[] splitedQuery, int index, Sequence sequence){
        String value = splitedQuery[index];
        value = value.substring(4);
        while(!value.endsWith(")")){
            if(value.equals("*")) {
                index++;
                value = splitedQuery[index];
                continue;
            }
            Read read = new Read();
            read.setColumn(value);
            sequence.getSequence().add(read);
            index++;
            value = splitedQuery[index];
        }
        value = value.substring(0, value.length()-1);
        Read read = new Read();
        read.setColumn(value);
        sequence.getSequence().add(read);

        return index;

    }

    private static int subSelect(String[] splitedQuery, int i, Sequence sequence){
        i++;
        String value = "";
        while(i < splitedQuery.length){
            value = splitedQuery[i];
            if(value.equals("AS")){
                i=i+2;
                continue;
            }
            if(value.equals("FROM")){
                value = splitedQuery[i];
                while(!value.equals("WHERE") && i<splitedQuery.length){
                    i++;
                    value = splitedQuery[i];
                }
                if(i == splitedQuery.length)
                    break;
                i = whereSequence(splitedQuery, i, sequence);
                break;
            }
            Read read = new Read();
            if(value.startsWith("SUM(")) {
                i = sumOperation(splitedQuery, i, sequence);
                continue;
            }

            if(value.endsWith(","))
                value = value.substring(0, value.length()-1);
            read.setColumn(value);
            sequence.getSequence().add(read);
            i++;
        }
        while(i < splitedQuery.length && !value.endsWith(")")){
            i++;
            value = splitedQuery[i];
        }
        return i;
    }

    private static int whereSequence(String[] splitedQuery, int index, Sequence sequence){
        index=index+3;
        int i;
        for(i=index; i<splitedQuery.length; i=i+4){
            String value = splitedQuery[i];
            if(value.equals("(SELECT"))
                i = subSelect(splitedQuery, i, sequence);
            if(!value.matches("-?\\d+(\\.\\d+)?") && !value.startsWith("\"") && !value.endsWith("\"")){
                Read read = new Read();
                read.setColumn(value);
                sequence.getSequence().add(read);
            }
            if(i+1<splitedQuery.length && (splitedQuery[i+1].equals("GROUP") || splitedQuery[i+1].equals("ORDER")))
                break;
        }
        return i;
    }

    private static void writeToFile(List<Transaction> transactions) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
        for(Transaction transaction : transactions){
            for(Sequence sequence : transaction.getSequences()){
                writer.write(transaction.getRole() + ": ");
                for(Operation operation : sequence.getSequence()){
                    writer.write(operation.getColumn() + "_");
                    if(operation.getType() == OperationType.READ)
                        writer.write("r ");
                    else
                        writer.write("w ");
                }
                writer.write(System.lineSeparator());
            }
        }
        writer.close();
    }

    private static List<RWSequences> prepareReadAndWriteSequnces(List<Transaction> transactions){
        List<RWSequences> rwSequencesList = new ArrayList<RWSequences>();
        for(Transaction transaction : transactions){
            RWSequences rwSequences = new RWSequences();
            rwSequences.setRole(transaction.getRole());
            List<Operation> readColumn = new ArrayList<Operation>();
            List<Operation> writeColumn = new ArrayList<Operation>();
            for(Operation operation: transaction.getJoinedList()){
                if(operation.getType() == OperationType.READ){
                    readColumn.add(operation);
                }
                else{
                    writeColumn.add(operation);
                    if(readColumn.size() > 0 && writeColumn.size() > 0){
                        ReadSequence sequence = new ReadSequence();
                        sequence.getSequence().addAll(readColumn);
                        sequence.getSequence().add(operation);
                        rwSequences.getSequences().add(sequence);
                    }
                    if(writeColumn.size() > 1){
                        WriteSequence sequence = new WriteSequence();
                        sequence.getSequence().addAll(writeColumn);
                        rwSequences.getSequences().add(sequence);
                    }
                }
            }
            if(rwSequences.getSequences().size() == 0) {
                ReadOnlySequence readOnlySequence = new ReadOnlySequence();
                readOnlySequence.getSequence().addAll(readColumn);
                rwSequences.getSequences().add(readOnlySequence);
                rwSequencesList.add(rwSequences);
            }
            else{
                rwSequencesList.add(rwSequences);
            }
        }
        return rwSequencesList;
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

    private static void test(List<RWSequences> originalSequences, List<RWSequences> testSequences){
        for(RWSequences original : originalSequences) {
            for (RWSequences test : testSequences) {
                if (original.getRole().equals(test.getRole())) {
                    if(test.getSequences().get(0).getSequenceType().equals(SequenceType.READ_ONLY))
                        readOnlyCompare(original, test);
                    else
                        compare(original, test);

                }
            }
        }
    }

    private static void readOnlyCompare(RWSequences original, RWSequences test){
        Sequence originalSequence = original.getSequences().get(0);
        Sequence testSequence = test.getSequences().get(0);

        int previeusIndex = -1;
        for(Operation testOperation : testSequence.getSequence()){
            int index = 0;
            boolean operationFound = false;
            for(Operation originalOperation : originalSequence.getSequence()){
                if(testOperation.getColumn().equals(originalOperation.getColumn())) {
                    operationFound = true;
                    break;
                }
                index++;
            }
            if(!operationFound){
                System.out.println(original.getRole() + " " +  testOperation.getType() + " " + testOperation.getColumn() + " operacja nie znaleziona");
                break;
            }
            if(previeusIndex < index)
                previeusIndex = index;
            else {
                System.out.println(original.getRole() + " " +  testOperation.getType() + " " + testOperation.getColumn() + " operacja wystąpiła za wcześnie");
                break;
            }
        }
    }

    private static void compare(RWSequences original, RWSequences test){
        int previeusIndex = -1;
        int sequence = 0;
        for(Sequence sequenceTest : test.getSequences()) {
            boolean compare = false;
            int index = 0;
            for (Sequence sequenceOriginal : original.getSequences()) {
                compare = compareSequence(sequenceOriginal, sequenceTest);
                if(compare)
                    break;
                index++;
            }
            if(!compare){
                System.out.println(original.getRole() +  " sekwencja " + Integer.toString(sequence) + " nie znaleziona");
                break;
            }
            if(previeusIndex < index)
                previeusIndex = index;
            else {
                System.out.println(original.getRole()  + " operacja " + Integer.toString(sequence) + " wystąpiła za wcześnie");
                break;
            }
            sequence++;
        }
    }

    private static boolean compareSequence(Sequence sequenceOriginal, Sequence sequenceTest){
        boolean compare = true;
        if(sequenceTest.getSequence().size() == sequenceOriginal.getSequence().size()){
            for(int i=0; i<sequenceTest.getSequence().size(); i++){
                Operation operationTest = sequenceTest.getSequence().get(i);
                Operation operationOriginal = sequenceOriginal.getSequence().get(i);
                if(!(operationTest.getType().equals(operationOriginal.getType()) && operationTest.getColumn().equals(operationOriginal.getColumn()))){
                    compare = false;
                    break;
                }
            }
        }
        else
            compare = false;
        return compare;
    }

}