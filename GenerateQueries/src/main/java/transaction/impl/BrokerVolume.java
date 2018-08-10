package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class BrokerVolume implements Transaction {

    private static final String ROLE = "1";

    @Override
    public String generateTransaction() {
        return frameOne();
    }

    private String frameOne(){
        String query = ROLE + "," +
                "select " +
                "B_NAME, " +
                "sum(TR_QTY * TR_BID_PRICE) " +
                "from " +
                "TRADE_REQUEST, " +
                "SECTOR, " +
                "INDUSTRY " +
                "COMPANY, " +
                "BROKER, " +
                "SECURITY " +
                "where " +
                "TR_B_ID = B_ID and " +
                "TR_S_SYMB = S_SYMB and " +
                "S_CO_ID = CO_ID and " +
                "CO_IN_ID = IN_ID and " +
                "SC_ID = IN_SC_ID and " +
                //"B_NAME in (broker_list) and " +
                "SC_NAME = " + ColumnValue.getValue("SC_NAME") + " " +
                "group by " +
                "B_NAME " +
                "order by " +
                "2 DESC";
        
        return query + System.lineSeparator();
    }
}
