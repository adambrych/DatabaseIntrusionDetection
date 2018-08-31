package transaction;

import transaction.impl.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class QueriesGenerator {

    private int transactionNumber = 50;

    private BrokerVolume brokerVolume = new BrokerVolume();
    private CustomerPosition customerPosition = new CustomerPosition();
    private DataMaintence dataMaintence = new DataMaintence();;
    private MarketWatch marketWatch = new MarketWatch();
    private SecurityDetail securityDetail = new SecurityDetail();
    private TradeLookUp tradeLookUp = new TradeLookUp();
    private TradeOrder tradeOrder = new TradeOrder();
    private TradeResult tradeResult = new TradeResult();
    private TradeStatus tradeStatus = new TradeStatus();
    private TradeUpdate tradeUpdate = new TradeUpdate();
    
    public void generateQueries(){
        List<Transaction> transactions = Arrays.asList(brokerVolume, customerPosition, dataMaintence, marketWatch,
                securityDetail, tradeLookUp, tradeOrder, tradeResult, tradeStatus, tradeUpdate);
        generateTransaction(transactions);
    }

    private void generateTransaction(List<Transaction> transactions){
        for(int i=1; i<=transactionNumber; i++) {
            int index = 1;
            System.out.println(i + "/" + Integer.toString(transactionNumber));
            for (Transaction transaction : transactions) {
                System.out.println("    " + index + "/10");
                index++;
                String queries = transaction.generateTransaction();
                writeToFile(queries);
            }
        }
    }

    private void writeToFile(String queries){
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            fw = new FileWriter("queries50.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.print(queries);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }
}
