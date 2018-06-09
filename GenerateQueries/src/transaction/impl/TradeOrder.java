package transaction.impl;

import transaction.Transaction;

public class TradeOrder implements Transaction {
    @Override
    public void generateTransaction() {

    }

    private void frameOne(){
        String query1 = "select\n" +
                "CA_NAME,\n" +
                "CA_B_ID,\n" +
                "CA_C_ID,\n" +
                "CA_TAX_ST\n" +
                "from\n" +
                "CUSTOMER_ACCOUNT\n" +
                "where\n" +
                "CA_ID = ";

        String query2 = "select\n" +
                "C_F_NAME,\n" +
                "C_L_NAME,\n" +
                "C_TIER,\n" +
                "C_TAX_ID\n" +
                "from\n" +
                "CUSTOMER\n" +
                "where\n" +
                "C_ID = \n";

        String query3 = "select\n" +
                "B_NAME\n" +
                "from\n" +
                "BROKER\n" +
                "where\n" +
                "B_ID = ";
    }

    private void frameTwo(){
        String query = "select\n" +
                "AP_ACL\n" +
                "from\n" +
                "ACCOUNT_PERMISSION\n" +
                "where\n" +
                "AP_CA_ID =  and\n" +
                "AP_F_NAME =  and\n" +
                "AP_L_NAME =  and\n" +
                "AP_TAX_ID = ";
    }

    private void frameThree(){
        String query1 = "select\n" +
                "co_id = CO_ID\n" +
                "from\n" +
                "COMPANY\n" +
                "where\n" +
                "CO_NAME = co_name\n";

        String query2 = "select\n" +
                "exch_id = S_EX_ID,\n" +
                "s_name = S_NAME,\n" +
                "symbol = S_SYMB\n" +
                "from\n" +
                "SECURITY\n" +
                "where\n" +
                "S_CO_ID =  and\n" +
                "S_ISSUE = issue\n";

        String query3 = "select\n" +
                "co_id = S_CO_ID,\n" +
                "exch_id = S_EX_ID,\n" +
                "s_name = S_NAME\n" +
                "from\n" +
                "SECURITY\n" +
                "where\n" +
                "S_SYMB = symbol\n";

        String query4 = "select\n" +
                "co_name = CO_NAME\n" +
                "from\n" +
                "COMPANY\n" +
                "where\n" +
                "CO_ID = co_id\n";

        String query5 = "select\n" +
                "market_price = LT_PRICE\n" +
                "from\n" +
                "LAST_TRADE\n" +
                "where\n" +
                "LT_S_SYMB = symbol\n";

        String query6 = "select\n" +
                "type_is_market = TT_IS_MRKT,\n" +
                "type_is_sell = TT_IS_SELL\n" +
                "from\n" +
                "TRADE_TYPE\n" +
                "where\n" +
                "TT_ID = trade_type_id\n";

        String query7 = "select\n" +
                "hs_qty = HS_QTY\n" +
                "from\n" +
                "HOLDING_SUMMARY\n" +
                "where\n" +
                "HS_CA_ID = acct_id and\n" +
                "HS_S_SYMB = symbol\n";

        String query8 = "select\n" +
                "H_QTY,\n" +
                "H_PRICE\n" +
                "from\n" +
                "HOLDING\n" +
                "where\n" +
                "H_CA_ID =  and\n" +
                "H_S_SYMB = \n" +
                "order by\n" +
                "H_DTS desc\n";

        String query9 = "select\n" +
                "H_QTY,\n" +
                "H_PRICE\n" +
                "from\n" +
                "HOLDING\n" +
                "where\n" +
                "H_CA_ID = acct_id and\n" +
                "H_S_SYMB = symbol\n" +
                "order by\n" +
                "H_DTS asc\n";

        String query10 = "Declare tax_rates S_PRICE_T\n" +
                "select\n" +
                "tax_rates = sum(TX_RATE)\n" +
                "from\n" +
                "TAXRATE\n" +
                "where\n" +
                "TX_ID in (\n";

        String query11 = "select\n" +
                "CX_TX_ID\n" +
                "from\n" +
                "CUSTOMER_TAXRATE\n"+
                "where\n" +
                "CX_C_ID = cust_id)\n" +
                "tax_amount = (sell_value – buy_value) * tax_rates\n" +
                "}\n";

        String query12 = "select\n" +
                "comm_rate = CR_RATE\n" +
                "from\n" +
                "COMMISSION_RATE\n" +
                "where\n" +
                "CR_C_TIER = cust_tier and\n" +
                "CR_TT_ID = trade_type_id and\n" +
                "CR_EX_ID = exch_id and\n" +
                "CR_FROM_QTY <= trade_qty and\n" +
                "CR_TO_QTY >= trade_qty\n" ;

        String query13 = "select\n" +
                "charge_amount = CH_CHRG\n" +
                "from\n" +
                "CHARGE\n" +
                "where\n" +
                "CH_C_TIER = cust_tier and\n" +
                "CH_TT_ID = trade_type_id\n" ;

        String query14 = "select\n" +
                "acct_bal = CA_BAL\n" +
                "from\n" +
                "CUSTOMER_ACCOUNT\n" +
                "where\n" +
                "CA_ID = acct_id\n";

        String query15 = "select\n" +
                "hold_assets = sum(HS_QTY * LT_PRICE)\n" +
                "from\n" +
                "HOLDING_SUMMARY,\n" +
                "LAST_TRADE\n" +
                "where\n" +
                "HS_CA_ID = acct_id and\n" +
                "LT_S_SYMB = HS_S_SYMB";
    }

    private void frame4(){
        String query = "insert into\n" +
                "TRADE (\n" +
                "T_ID, T_DTS, T_ST_ID, T_TT_ID, T_IS_CASH,\n" +
                "T_S_SYMB, T_QTY, T_BID_PRICE, T_CA_ID, T_EXEC_NAME,\n" +
                "T_TRADE_PRICE, T_CHRG, T_COMM, T_TAX, T_LIFO\n" +
                ")\n" +
                "values (\n" +
                "trade_id,\n" +
                "now_dts,\n" +
                "status_id,\n" +
                "trade_type_id,\n" +
                "is_cash,\n" +
                "symbol,\n" +
                "trade_qty,\n" +
                "requested_price, \n" +
                "acct_id,\n" +
                "exec_name,\n" +
                "NULL,\n" +
                "charge_amount,\n" +
                "comm_amount\n" +
                "0,\n" +
                "is_lifo\n" +
                ")\n" +

                "insert into\n" +
                "TRADE_REQUEST (\n" +
                "TR_T_ID, TR_TT_ID, TR_S_SYMB,\n" +
                "TR_QTY, TR_BID_PRICE, TR_B_ID\n" +
                ")\n" +
                "values (\n" +
                "trade_id,\n" +
                "trade_type_id,\n" +
                "symbol,\n" +
                "trade_qty,\n" +
                "requested_price,\n" +
                "broker_id\n" +
                ")\n" +
                "}\n" +

                "insert into\n" +
                "TRADE_HISTORY (\n" +
                "TH_T_ID, TH_DTS, TH_ST_ID\n" +
                ")\n" +
                "values (\n" +
                "trade_id,\n" +
                "now_dts,\n" +
                "status_id\n" +
                ")";
    }
}
