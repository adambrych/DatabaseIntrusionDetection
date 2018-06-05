package transaction.impl;

import transaction.Transaction;

public class TradeLookUp implements Transaction {

    private static final String ROLE = "6";

    @Override
    public void generateTransaction() {

    }

    private void frameOne(){
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
                "T_ID = trade_id[i] and " +
                "T_TT_ID = TT_ID ";
        String query2 = ROLE + "," +
                "select " +
                "SE_AMT, " +
                "SE_CASH_DUE_DATE, " +
                "SE_CASH_TYPE " +
                "from " +
                "SETTLEMENT " +
                "where " +
                "SE_T_ID = trade_id[i] ";
        String query3 = ROLE + "," +
                "select " +
                "CT_AMT," +
                "CT_DTS, " +
                "CT_NAME " +
                "from " +
                "CASH_TRANSACTION " +
                "where " +
                "CT_T_ID = trade_id[i] ";
    }

    private void frameTwo(){
        String query1 = ROLE + "," +
                "select first max_trades rows " +
                "T_BID_PRICE, " +
                "T_EXEC_NAME, " +
                "T_IS_CASH, " +
                "T_ID, " +
                "T_TRADE_PRICE " +
                "from " +
                "TRADE " +
                "where " +
                "T_CA_ID = acct_id and " +
                "T_DTS >= start_trade_dts and " +
                "T_DTS <= end_trade_dts " +
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
                "SE_T_ID = trade_list[i]";
    }

    private void frameThree(){
        String query1 = ROLE + "," +
                "select first max_trades rows " +
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
                "T_S_SYMB = symbol and " +
                "T_DTS >= start_trade_dts and " +
                "T_DTS <= end_trade_dts "+
                "order by " +
                "T_DTS asc";
    }

    private void frameFour(){
        String query1 = ROLE + "," +
                "select first 20 rows " +
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
                "HH_T_ID = trade_id)";
    }
}
