package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class MarketWatch implements Transaction {

    private static final String ROLE = "4";

    @Override
    public String generateTransaction() {
        return frameOne();
    }

    private String frameOne(){
        String query = ROLE + "," +
                "select " +
                "WI_S_SYMB " +
                "from " +
                "WATCH_ITEM, " +
                "WATCH_LIST " +
                "where " +
                "WI_WL_ID = WL_ID and " +
                "WL_C_ID = " + ColumnValue.getValue("WL_C_ID");

                String query2 = ROLE + "," +
                        "select " +
                        "S_SYMB " +
                        "from " +
                        "INDUSTRY, " +
                        "COMPANY, " +
                        "SECURITY " +
                        "where " +
                        "IN_NAME = " + ColumnValue.getValue("IN_NAME") +
                        " and " +
                        "CO_IN_ID = IN_ID";

        String query3 = ROLE + "," +
                "select " +
                "HS_S_SYMB " +
                "from " +
                "HOLDING_SUMMARY " +
                "where " +
                "HS_CA_ID = " + ColumnValue.getValue("HS_CA_ID");

        String query4 = ROLE + "," +
                "select " +
                "LT_PRICE " +
                "from " +
                "LAST_TRADE " +
                "where " +
                "LT_S_SYMB = " + ColumnValue.getValue("LT_S_SYMB");

         String query5 = ROLE + "," +
                 "select " +
                "S_NUM_OUT " +
                "from " +
                "SECURITY " +
                "where " +
                "S_SYMB = " + ColumnValue.getValue("S_SYMB");

        String query6 = ROLE + "," +
                "select " +
                "DM_CLOSE " +
                "from " +
                "DAILY_MARKET " +
                "where " +
                "DM_S_SYMB = " + ColumnValue.getValue("DM_S_SYMB") +
                " and " +
                "DM_DATE = " + ColumnValue.getValue("DM_DATE");

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator() +
                query4 + System.lineSeparator() +
                query5 + System.lineSeparator() +
                query6 + System.lineSeparator();
    }
}
