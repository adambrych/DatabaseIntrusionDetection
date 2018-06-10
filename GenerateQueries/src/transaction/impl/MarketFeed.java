package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class MarketFeed implements Transaction {

    private static final String ROLE = "3";

    @Override
    public String generateTransaction() {
        return frameOne();
    }

    private String frameOne(){
        String query = ROLE + "," +
                "update " +
                "LAST_TRADE " +
                "set " +
                "LT_PRICE = " + ColumnValue.getValue("LT_PRICE") + ", " +
                "LT_VOL = LT_VOL + " + ColumnValue.getValue("LT_VOL") + ", " +
                "LT_DTS = " + ColumnValue.getValue("LT_DTS") + " " +
                "where " +
                "LT_S_SYMB = " + ColumnValue.getValue("LT_S_SYMB");

        String query2 = ROLE + "," +
                "select " +
                "TR_T_ID, "+
                "TR_BID_PRICE, " +
                "TR_TT_ID, " +
                "TR_QTY " +
                "from " +
                "TRADE_REQUEST " +
                "where " +
                "TR_S_SYMB = " + ColumnValue.getValue("TR_S_SYMB") +
                " and ( " +
                "(TR_TT_ID =  " + ColumnValue.getValue("TR_TT_ID") +
                " and " +
                "TR_BID_PRICE >= " + ColumnValue.getValue("TR_BID_PRICE")+
                " or " +
                "(TR_TT_ID = " + ColumnValue.getValue("TR_TT_ID")+
                " and " +
                "TR_BID_PRICE <= " + ColumnValue.getValue("TR_BID_PRICE")+
                " or " +
                "(TR_TT_ID = " + ColumnValue.getValue("TR_TT_ID")+
                " and " +
                "TR_BID_PRICE >= " + ColumnValue.getValue("TR_BID_PRICE")+
                ") " +
                ")";
        String query3 = ROLE + "," +
                "insert into " +
                "TRADE_HISTORY " +
                "values ( " +
                "TH_T_ID = " + ColumnValue.getValue("TH_T_ID") + ", " +
                "TH_DTS = " + ColumnValue.getValue("TH_DTS") + ", " +
                "TH_ST_ID = " + ColumnValue.getValue("TH_ST_ID") + " " +
                ")";

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator();
    }
}
