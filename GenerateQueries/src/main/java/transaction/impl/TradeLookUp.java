package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class TradeLookUp implements Transaction {

    private static final String ROLE = "6";

    @Override
    public String generateTransaction() {
        return frameOne() + frameTwo() + frameThree() + frameFour();
    }

    private String frameOne(){
        String query = ROLE + "," +
                "select " +
                "T_BID_PRICE, " +
                "T_EXEC_NAME, " +
                "T_IS_CASH, " +
                "TT_IS_MRKT, " +
                "T_TRADE_PRICE " +
                "from " +
                "TRADE, " +
                "TRADE_TYPE " +
                "where " +
                "T_ID = " + ColumnValue.getValue("T_ID") +
                " and " +
                "T_TT_ID = TT_ID";
        String query2 = ROLE + "," +
                "select " +
                "SE_AMT, " +
                "SE_CASH_DUE_DATE, " +
                "SE_CASH_TYPE " +
                "from " +
                "SETTLEMENT " +
                "where " +
                "SE_T_ID = " + ColumnValue.getValue("SE_T_ID");
        String query3 = ROLE + "," +
                "select " +
                "CT_AMT," +
                "CT_DTS, " +
                "CT_NAME " +
                "from " +
                "CASH_TRANSACTION " +
                "where " +
                "CT_T_ID = " + ColumnValue.getValue("CT_T_ID");

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator();
    }

    private String frameTwo(){
        String query1 = ROLE + "," +
                "select " +
                "T_BID_PRICE, " +
                "T_EXEC_NAME, " +
                "T_IS_CASH, " +
                "T_ID, " +
                "T_TRADE_PRICE " +
                "from " +
                "TRADE " +
                "where " +
                "T_CA_ID = " + ColumnValue.getValue("CT_T_ID") +
                " and " +
                "T_DTS >= " + ColumnValue.getValue("T_DTS") +
                " and " +
                "T_DTS <= " + ColumnValue.getValue("T_DTS") +
                " " +
                "order by " +
                "T_DTS asc LIMIT 1";
        String query2 = ROLE + "," +
                "select " +
                "SE_AMT, " +
                "SE_CASH_DUE_DATE, " +
                "SE_CASH_TYPE " +
                "from " +
                "SETTLEMENT " +
                "where " +
                "SE_T_ID = " + ColumnValue.getValue("SE_T_ID");

        return query1 + System.lineSeparator() +
                query2 + System.lineSeparator();
    }

    private String frameThree(){
        String query1 = ROLE + "," +
                "select " +
                "T_CA_ID, " +
                "T_EXEC_NAME, " +
                "T_IS_CASH, " +
                "T_TRADE_PRICE, " +
                "T_QTY, " +
                "T_DTS, " +
                "T_ID, " +
                "T_TT_ID " +
                "from " +
                "TRADE " +
                "where " +
                "T_S_SYMB = " + ColumnValue.getValue("T_S_SYMB") +
                " and " +
                "T_DTS >= " + ColumnValue.getValue("T_DTS") +
                " and " +
                "T_DTS <= " + ColumnValue.getValue("T_DTS") +
                " "+
                "order by " +
                "T_DTS asc";

        return query1 + System.lineSeparator();
    }

    private String frameFour(){
        String query1 = ROLE + "," +
                "select " +
                "HH_H_T_ID, " +
                "HH_T_ID, " +
                "HH_BEFORE_QTY, " +
                "HH_AFTER_QTY " +
                "from " +
                "HOLDING_HISTORY " +
                "where " +
                "HH_H_T_ID in " +
                "(select " +
                "HH_H_T_ID " +
                "from " +
                "HOLDING_HISTORY " +
                "where " +
                "HH_T_ID = " + ColumnValue.getValue("HH_T_ID") +
                ")";

        return  query1 + System.lineSeparator();
    }
}
