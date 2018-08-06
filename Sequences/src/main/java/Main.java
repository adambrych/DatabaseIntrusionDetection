import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Sequence.*;
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
    }

    private static Transaction prepareTransaction(Transaction lastTransaction, String lastRole, String role, List<Transaction> transactions){
        if(lastRole.equals(role))
            return lastTransaction;
        else
            if(lastTransaction == null)
                return new Transaction(role);
            else{
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
        for(int i=1; i<splitedQuery.length; i++){
            String value = splitedQuery[i];
            if(value.equals("FROM"))
                break;
            Read read = new Read();
            if(value.startsWith("SUM("))
                i = sumOperation(splitedQuery, i, sequence);

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
        for(int j=i; j<splitedQuery.length; j++){

            value = splitedQuery[j];
            Write write = new Write();
            write.setColumn(value);
            sequence.getSequence().add(write);
            while(!value.endsWith(",") && !value.equals("WHERE") && j+1<splitedQuery.length){
                j++;
                value = splitedQuery[j];
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

}
