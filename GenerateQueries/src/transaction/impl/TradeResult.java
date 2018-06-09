package transaction.impl;

import transaction.Transaction;

public class TradeResult implements Transaction {
    @Override
    public void generateTransaction() {

    }

    private void frame1(){
        String query = "select\n" +
                "T_CA_ID,\n" +
                "T_TT_ID,\n" +
                "T_S_SYMB,\n" +
                "T_QTY,\n" +
                "T_CHRG,\n" +
                "T_LIFO,\n" +
                "T_IS_CASH\n" +
                "from\n" +
                "TRADE\n" +
                "where\n" +
                "T_ID = \n" +
                "num_found = \n";

                String query2 = "select\n" +
                "TT_NAME,\n" +
                "TT_IS_SELL,\n" +
                "TT_IS_MRKT\n" +
                "from\n" +
                "TRADE_TYPE\n" +
                "where\n" +
                "TT_ID = \n";

                String query3 = "select\n" +
                "HS_QTY\n" +
                "from\n" +
                "HOLDING_SUMMARY\n" +
                "where\n" +
                "HS_CA_ID =  and\n" +
                "HS_S_SYMB = symbol";
    }

    private void frame2(){
        String query1 = "select\n" +
                "CA_B_ID,\n" +
                "CA_C_ID,\n" +
                "CA_TAX_ST\n" +
                "from\n" +
                "CUSTOMER_ACCOUNT\n" +
                "where\n" +
                "CA_ID = \n";

                String query2 = "insert into\n" +
                "HOLDING_SUMMARY (\n" +
                "HS_CA_ID,\n" +
                "HS_S_SYMB,\n" +
                "HS_QTY\n" +
                ")\n" +
                "values (\n" +
                "acct_id,\n" +
                "symbol,\n" +
                "trade_qty\n" +
                ")\n";

                String query3 = "update\n" +
                "HOLDING_SUMMARY\n" +
                "set\n" +
                "HS_QTY = hs_qty – trade_qty\n" +
                "where\n" +
                "HS_CA_ID =  and\n" +
                "HS_S_SYMB = \n";

                String query4 = "select\n" +
                "H_T_ID,\n" +
                "H_QTY,\n" +
                "H_PRICE\n" +
                "from\n" +
                "HOLDING\n" +
                "where\n" +
                "H_CA_ID = acct_id and\n" +
                "H_S_SYMB = symbol\n" +
                "order by\n" +
                "H_DTS desc\n";

                String query5 = "select\n" +
                "H_T_ID,\n" +
                "H_QTY,\n" +
                "H_PRICE\n" +
                "from\n" +
                "HOLDING\n" +
                "where\n" +
                "H_CA_ID =  and\n" +
                "H_S_SYMB = \n" +
                "order by\n" +
                "H_DTS asc\n";

                String query6 = "insert into\n" +
                "HOLDING_HISTORY (\n" +
                "HH_H_T_ID,\n" +
                "HH_T_ID,\n" +
                "HH_BEFORE_QTY,\n" +
                "HH_AFTER_QTY\n" +
                ")\n" +
                "values (\n" +
                "hold_id,\n" +
                "trade_id,\n" +
                "hold_qty,\n" +
                "hold_qty - needed_qty\n" +
                ")\n";

                String query7 = "update\n" +
                "HOLDING\n" +
                "set\n" +
                "H_QTY = hold_qty - needed_qty\n" +
                "where\n" +
                "buy_value += needed_qty * hold_price\n" +
                "sell_value += needed_qty * trade_price\n" +
                "needed_qty = 0\n";

                String query8 = "insert into\n" +
                "HOLDING_HISTORY (\n" +
                "HH_H_T_ID,\n" +
                "HH_T_ID,\n" +
                "HH_BEFORE_QTY,\n" +
                "HH_AFTER_QTY\n" +
                ")\n" +
                "values (\n" +
                "hold_id,\n" +
                "trade_id,\n" +
                "hold_qty,\n" +
                "0\n" +
                ")\n";

                String query9 = "delete from\n" +
                "HOLDING\n" +
                "where\n" +
                "current of hold_list\n" +
                "buy_value += hold_qty * hold_price\n" +
                "sell_value += hold_qty * trade_price\n" +
                "needed_qty = needed_qty - hold_qty\n" +
                "trade_id,\n" +
                "0,\n" +
                "(-1) * needed_qty\n" +
                ")\n";

                String query10 = "insert into\n" +
                "HOLDING (\n" +
                "H_T_ID,\n" +
                "H_CA_ID,\n" +
                "H_S_SYMB,\n" +
                "H_DTS,\n" +
                "H_PRICE,\n" +
                "H_QTY\n" +
                ")\n" +
                "values (\n" +
                "trade_id,\n" +
                "acct_id,\n" +
                "symbol,\n" +
                "trade_dts,\n" +
                "trade_price,\n" +
                "(-1) * needed_qty\n" +
                ")\n";

                String query11 = "delete from\n" +
                "HOLDING_SUMMARY\n" +
                "where\n" +
                "HS_CA_ID = acct_id and\n" +
                "HS_S_SYMB = symbol\n" +
                "}\n";

                String query12 = "insert into\n" +
                "HOLDING_SUMMARY (\n" +
                "HS_CA_ID,\n" +
                "HS_S_SYMB,\n" +
                "HS_QTY\n" +
                ")\n" +
                "values (\n" +
                "acct_id,\n" +
                "symbol,\n" +
                "trade_qty\n" +
                ")\n";

                String query13 = "update\n" +
                "HOLDING_SUMMARY\n" +
                "set\n" +
                "HS_QTY = hs_qty + trade_qty\n" +
                "where\n" +
                "HS_CA_ID = acct_id and\n" +
                "HS_S_SYMB = symbol\n";

                String query14 = "select\n" +
                "H_T_ID,\n" +
                "H_QTY,\n" +
                "H_PRICE\n" +
                "from\n" +
                "HOLDING\n" +
                "where\n" +
                "H_CA_ID = acct_id and\n" +
                "H_S_SYMB = symbol\n" +
                "order by\n" +
                "H_DTS desc\n";

                String query15 = "select\n" +
                "H_T_ID,\n" +
                "H_QTY,\n" +
                "H_PRICE\n" +
                "from\n" +
                "HOLDING\n" +
                "where\n" +
                "H_CA_ID = acct_id and\n" +
                "H_S_SYMB = symbol\n" +
                "order by\n" +
                "H_DTS asc\n";

                String query16 = "insert into\n" +
                "HOLDING_HISTORY (\n" +
                "HH_H_T_ID,\n" +
                "HH_T_ID,\n" +
                "HH_BEFORE_QTY,\n" +
                "HH_AFTER_QTY\n" +
                ")\n" +
                "values (\n" +
                "hold_id,\n" +
                "trade_id,\n" +
                "hold_qty,\n" +
                "hold_qty + needed_qty // H_QTY after update\n" +
                ")\n" +
                "update\n" +
                "HOLDING\n" +
                "set\n" +
                "H_QTY = hold_qty + needed_qty\n" +
                "where\n" +
                "sell_value += needed_qty * hold_price\n" +
                "buy_value += needed_qty * trade_price\n" +
                "needed_qty = 0\n";

                String query17 = "insert into\n" +
                "HOLDING_HISTORY (\n" +
                "HH_H_T_ID,\n" +
                "HH_T_ID,\n" +
                "HH_BEFORE_QTY,\n" +
                "HH_AFTER_QTY\n" +
                ")\n" +
                "values (\n" +
                "hold_id,\n" +
                "trade_id,\n" +
                "hold_qty,\n" +
                "0\n" +
                ")\n";

                String query18 = "delete from\n" +
                "HOLDING\n" +
                "where\n" +
                "hold_qty = -hold_qty\n" +
                "sell_value += hold_qty * hold_price\n" +
                "buy_value += hold_qty * trade_price\n" +
                "needed_qty = needed_qty - hold_qty\n";

                String query19 = "insert into\n" +
                "HOLDING_HISTORY (\n" +
                "HH_H_T_ID,\n" +
                "HH_T_ID,\n" +
                "HH_BEFORE_QTY,\n" +
                "HH_AFTER_QTY\n" +
                ")\n" +
                "values (\n" +
                "trade_id,\n" +
                "trade_id,\n" +
                "0,\n" +
                "needed_qty\n" +
                ")\n";

                String query20 = "insert into\n" +
                "HOLDING (\n" +
                "H_T_ID,\n" +
                "H_CA_ID,\n" +
                "H_S_SYMB,\n"+
                "H_DTS,\n" +
                "H_PRICE,\n" +
                "H_QTY\n" +
                ")\n" +
                "values (\n" +
                "trade_id\n" +
                "acct_id,\n" +
                "symbol,\n" +
                "trade_dts,\n" +
                "trade_price,\n" +
                "needed_qty\n" +
                ")\n";

                String query21 = "delete from\n" +
                "HOLDING_SUMMARY\n" +
                "where\n" +
                "HS_CA_ID =  and\n" +
                "HS_S_SYMB = ";
    }

    private void frame3(){
        String query="select\n" +
                "sum(TX_RATE)\n" +
                "from\n" +
                "TAXRATE\n" +
                "where\n" +
                "TX_ID in ( select\n" +
                "CX_TX_ID\n" +
                "from\n" +
                "CUSTOMER_TAXRATE\n" +
                "where\n" +
                "CX_C_ID = )";

        String query2="update\n" +
                "TRADE\n" +
                "set\n" +
                "T_TAX = \n" +
                "where\n" +
                "T_ID = ";
    }

    private void frame4(){
        String query="select\n" +
                "S_EX_ID,\n" +
                "S_NAME\n" +
                "from\n" +
                "SECURITY\n" +
                "where\n" +
                "S_SYMB = \n";

                String query2="select\n" +
                "C_TIER\n" +
                "from\n" +
                "CUSTOMER\n" +
                "where\n" +
                "C_ID = \n" +
                "select\n" +
                "comm_rate = CR_RATE\n" +
                "from\n" +
                "COMMISSION_RATE\n" +
                "where\n" +
                "CR_C_TIER =  and\n" +
                "CR_TT_ID =  and\n" +
                "CR_EX_ID =  and\n" +
                "CR_FROM_QTY <=  and\n" +
                "CR_TO_QTY >=  LIMIT 1";
    }

    private void frame5(){
        String query="update\n" +
                "TRADE\n" +
                "set\n" +
                "T_COMM = ,\n" +
                "T_DTS = ,\n" +
                "T_ST_ID = ,\n" +
                "T_TRADE_PRICE = \n" +
                "where\n" +
                "T_ID = \n";

                String query2="insert into\n" +
                "TRADE_HISTORY (\n" +
                "TH_T_ID,\n" +
                "TH_DTS,\n" +
                "TH_ST_ID\n" +
                ")\n" +
                "values (\n" +
                "trade_id,\n" +
                "trade_dts,\n" +
                "st_completed_id\n" +
                ")\n";

                String query3="update\n" +
                "BROKER\n" +
                "set\n" +
                "B_COMM_TOTAL = B_COMM_TOTAL + comm_amount,\n" +
                "B_NUM_TRADES = B_NUM_TRADES + 1\n" +
                "where\n" +
                "B_ID = ";
    }

    private void frame6(){
        String query="insert into\n" +
                "SETTLEMENT (\n" +
                "SE_T_ID,\n" +
                "SE_CASH_TYPE,\n" +
                "SE_CASH_DUE_DATE,\n" +
                "SE_AMT\n" +
                ")\n" +
                "values (\n" +
                "trade_id,\n" +
                "cash_type,\n" +
                "due_date,\n" +
                "se_amount\n" +
                ")\n" +

                "update\n" +
                "CUSTOMER_ACCOUNT\n" +
                "set\n" +
                "CA_BAL = CA_BAL + se_amount\n" +
                "where\n" +
                "CA_ID = acct_id\n" +

                "insert into\n" +
                "CASH_TRANSACTION (\n" +
                "CT_DTS,\n" +
                "CT_T_ID,\n" +
                "CT_AMT,\n" +
                "CT_NAME\n" +
                ")\n" +
                "values (\n" +
                "trade_dts,\n" +
                "trade_id,\n" +
                "se_amount,\n" +
                "type_name" +
                ")\n" +

                "select\n" +
                "CA_BAL\n" +
                "from\n" +
                "CUSTOMER_ACCOUNT\n" +
                "where\n" +
                "CA_ID = ";
    }
}
