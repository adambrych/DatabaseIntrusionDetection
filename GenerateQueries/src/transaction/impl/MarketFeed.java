package transaction.impl;

import transaction.Transaction;

public class MarketFeed implements Transaction {

    private static final String ROLE = "3";

    @Override
    public void generateTransaction() {

    }

    private void frameOne(){
        String query = ROLE + "," +
                "update " +
                "LAST_TRADE " +
                "set " +
                "LT_PRICE = price_quote[i], " +
                "LT_VOL = LT_VOL + trade_qty[i], " +
                "LT_DTS = now_dts " +
                "where " +
                "LT_S_SYMB = symbol[i]";

        String query2 = ROLE + "," +
                "select " +
                "TR_T_ID, "+
                "TR_BID_PRICE, " +
                "TR_TT_ID, " +
                "TR_QTY " +
                "from " +
                "TRADE_REQUEST " +
                "where " +
                "TR_S_SYMB = symbol[i] and ( " +
                "(TR_TT_ID = type_stop_loss and " +
                "TR_BID_PRICE >= price_quote[i]) or " +
                "(TR_TT_ID = type_limit_sell and " +
                "TR_BID_PRICE <= price_quote[i]) or " +
                "(TR_TT_ID = type_limit_buy and " +
                "TR_BID_PRICE >= price_quote[i]) " +
                ")";
        String query3 = ROLE + "," +
                "insert into " +
                "TRADE_HISTORY " +
                "values ( " +
                "TH_T_ID = req_trade_id, " +
                "TH_DTS = now_dts, " +
                "TH_ST_ID = status_submitted " +
                ")";
    }
}
