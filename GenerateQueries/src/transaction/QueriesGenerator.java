package transaction;

import transaction.Transaction;
import transaction.impl.*;
import values.ColumnValue;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueriesGenerator {

    private int transactionNumber = 1000;

    private BrokerVolume brokerVolume = new BrokerVolume();
    private CustomerPosition customerPosition = new CustomerPosition();
    private DataMaintence dataMaintence = new DataMaintence();
    private MarketFeed marketFeed = new MarketFeed();
    private MarketWatch marketWatch = new MarketWatch();
    private SecurityDetail securityDetail = new SecurityDetail();
    private TradeLookUp tradeLookUp = new TradeLookUp();
    private TradeOrder tradeOrder = new TradeOrder();
    private TradeResult tradeResult = new TradeResult();
    private TradeStatus tradeStatus = new TradeStatus();
    private TradeUpdate tradeUpdate = new TradeUpdate();
    
    public void generateQueries(){
        List<Transaction> transactions = Arrays.asList(brokerVolume, customerPosition, dataMaintence, marketFeed, marketWatch,
                securityDetail, tradeLookUp, tradeOrder, tradeResult, tradeStatus, tradeUpdate);
        generateTransaction(transactions);
    }

    private void generateTransaction(List<Transaction> transactions){
        for(Transaction transaction : transactions){
            String queries = transaction.generateTransaction();
            writeToFile(queries);
        }
    }

    private void writeToFile(String queries){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("queries.txt"), "utf-8"));
            writer.write(queries);
        } catch (IOException ex) {
            // Report
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }
}
