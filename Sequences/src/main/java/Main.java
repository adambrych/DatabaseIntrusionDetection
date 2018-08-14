import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Sequence.*;
import Sequence.impl.ReadSequence;
import Sequence.impl.WriteSequence;
import operation.Operation;
import operation.OperationType;
import operation.impl.Read;
import operation.impl.Write;

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
        Transaction lastTransaction = null;
        List<Transaction> transactions = new ArrayList();

        while((query = br.readLine()) != null) {
            query = query.toUpperCase();
            String[] splitedQuery = query.split(" ");
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
        prepareReadAndWriteSequnces(transactions);
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
            while(!value.endsWith(",") && !value.equals("WHERE") && j+1<splitedQuery.length){
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
        return new Sequence();
    }

    private static Sequence deleteOperation(String[] splitedQuery){
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

    private static RWSequences prepareReadAndWriteSequnces(List<Transaction> transactions){
        RWSequences rwSequences = new RWSequences();
        for(Transaction transaction : transactions){
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
        }
        return rwSequences;
    }
}