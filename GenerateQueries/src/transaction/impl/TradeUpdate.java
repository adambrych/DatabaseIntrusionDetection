package transaction.impl;

import transaction.Transaction;

public class TradeUpdate implements Transaction{
    @Override
    public void generateTransaction() {

    }

    private void frame1(){
        String query="select\n" +
                "T_EXEC_NAME\n" +
                "from\n" +
                "TRADE\n" +
                "where\n" +
                "T_ID = trade_id[i]\n";

                String query2="update\n" +
                "TRADE\n" +
                "set\n" +
                "T_EXEC_NAME = ex_name\n" +
                "where\n" +
                "T_ID = trade_id[i]\n" +
                "num_updated = num_updated + row_count\n";

                String query3="select\n" +
                "T_BID_PRICE,\n" +
                "T_EXEC_NAME,\n" +
                "T_IS_CASH,\n" +
                "TT_IS_MRKT,\n" +
                "T_TRADE_PRICE\n" +
                "from\n" +
                "TRADE,\n" +
                "TRADE_TYPE\n" +
                "where\n" +
                "T_ID = trade_id[i] and\n" +
                "T_TT_ID = TT_ID\n";

                String query4="select\n" +
                "SE_AMT,\n" +
                "SE_CASH_DUE_DATE,\n" +
                "SE_CASH_TYPE\n" +
                "from\n" +
                "SETTLEMENT\n" +
                "where\n" +
                "SE_T_ID = trade_id[i]\n";

                String query5="select\n" +
                "CT_AMT,\n" +
                "CT_DTS,\n" +
                "CT_NAME\n" +
                "from\n" +
                "CASH_TRANSACTION\n" +
                "where\n" +
                "CT_T_ID = trade_id[i]\n";

                String query6="select\n" +
                "TH_DTS,\n" +
                "TH_ST_ID\n" +
                "from\n" +
                "TRADE_HISTORY\n" +
                "where\n" +
                "TH_T_ID = trade_id[i]\n" +
                "order by\n" +
                "TH_DTS";
    }

    private void frame2(){
        String query="select\n" +
                "T_BID_PRICE,\n" +
                "T_EXEC_NAME,\n" +
                "T_IS_CASH,\n" +
                "T_ID,\n" +
                "T_TRADE_PRICE\n" +
                "from\n" +
                "TRADE\n" +
                "where\n" +
                "T_CA_ID = acct_id and\n" +
                "T_DTS >= start_trade_dts and\n" +
                "T_DTS <= end_trade_dts\n" +
                "order by\n" +
                "T_DTS asc\n" +

                "select\n" +
                "SE_CASH_TYPE\n" +
                "from\n" +
                "SETTLEMENT\n" +
                "where\n" +
                "SE_T_ID = trade_list[i]\n" +

                "update\n" +
                "SETTLEMENT\n" +
                "set\n" +
                "SE_CASH_TYPE = cash_type\n" +
                "where\n" +
                "SE_T_ID = trade_list[i]\n" +

                "select\n" +
                "SE_AMT,\n" +
                "SE_CASH_DUE_DATE,\n" +
                "SE_CASH_TYPE\n" +
                "from\n" +
                "SETTLEMENT\n" +
                "where\n" +
                "SE_T_ID = trade_list[i]\n" +

                "select\n" +
                "CT_AMT,\n" +
                "CT_DTS\n" +
                "CT_NAME\n" +
                "from\n" +
                "CASH_TRANSACTION\n" +
                "where\n" +
                "CT_T_ID = trade_list[i]\n" +

                "select\n" +
                "TH_DTS,\n" +
                "TH_ST_ID\n" +
                "from\n" +
                "TRADE_HISTORY\n" +
                "where\n" +
                "TH_T_ID = trade_list[i]\n" +
                "order by\n" +
                "TH_DTS\n";
    }

    private void frame3(){
        String query="select\n" +
                "T_CA_ID,\n" +
                "T_EXEC_NAME,\n" +
                "T_IS_CASH,\n" +
                "T_TRADE_PRICE,\n" +
                "T_QTY,\n" +
                "S_NAME,\n" +
                "T_DTS,\n" +
                "T_ID,\n" +
                "T_TT_ID,\n" +
                "TT_NAME\n" +
                "from\n" +
                "TRADE,\n" +
                "TRADE_TYPE,\n" +
                "SECURITY\n" +
                "where\n" +
                "T_S_SYMB = symbol and\n" +
                "T_DTS >= start_trade_dts and\n" +
                "T_DTS <= end_trade_dts and\n" +
                "TT_ID = T_TT_ID and\n" +
                "S_SYMB = T_S_SYMB\n" +
                "order by\n" +
                "T_DTS asc\n";

                String query2="select\n" +
                "SE_AMT,\n" +
                "SE_CASH_DUE_DATE,\n" +
                "SE_CASH_TYPE\n" +
                "from\n" +
                "SETTLEMENT\n" +
                "where\n" +
                "SE_T_ID = trade_list[i]\n";

                String query3="select\n" +
                "CT_NAME\n" +
                "from\n" +
                "CASH_TRANSACTION\n" +
                "where\n" +
                "CT_T_ID = trade_list[i]\n";

                String query4="update\n" +
                "CASH_TRANSACTION\n" +
                "set\n" +
                "CT_NAME = ct_name\n" +
                "where\n" +
                "CT_T_ID = trade_list[i]\n";

                String query5="select\n" +
                "CT_AMT,\n" +
                "CT_DTS\n" +
                "CT_NAME\n" +
                "from\n" +
                "CASH_TRANSACTION\n" +
                "where\n" +
                "CT_T_ID = trade_list[i]\n";

                String query6="select\n" +
                "TH_DTS,\n" +
                "TH_ST_ID\n" +
                "from\n" +
                "TRADE_HISTORY\n" +
                "where\n" +
                "TH_T_ID = trade_list[i]\n" +
                "order by\n" +
                "TH_DTS asc\n";
    }
}
