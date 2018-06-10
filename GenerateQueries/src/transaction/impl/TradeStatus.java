package transaction.impl;

import transaction.Transaction;

public class TradeStatus implements Transaction {

    private static final String ROLE = "9";

    @Override
    public void generateTransaction() {

    }

    private void frame1(){
        String query1 = ROLE + "," +"select\n" +
                "T_ID,\n" +
                "T_DTS,\n" +
                "ST_NAME,\n" +
                "TT_NAME,\n" +
                "T_S_SYMB,\n" +
                "T_QTY,\n" +
                "T_EXEC_NAME,\n" +
                "T_CHRG,\n" +
                "S_NAME,\n" +
                "EX_NAME\n" +
                "from\n" +
                "TRADE,\n" +
                "STATUS_TYPE,\n" +
                "TRADE_TYPE,\n" +
                "SECURITY,\n" +
                "EXCHANGE\n" +
                "where\n" +
                "T_CA_ID = acct_id and\n" +
                "ST_ID = T_ST_ID and\n" +
                "TT_ID = T_TT_ID and\n" +
                "S_SYMB = T_S_SYMB and\n" +
                "EX_ID = S_EX_ID\n" +
                "order by\n" +
                "T_DTS desc\n";

                String query2 = ROLE + "," +"select\n" +
                "C_L_NAME,\n" +
                "C_F_NAME,\n" +
                "B_NAME\n" +
                "from\n" +
                "CUSTOMER_ACCOUNT,\n" +
                "CUSTOMER,\n" +
                "BROKER\n" +
                "where\n" +
                "CA_ID = acct_id and\n" +
                "C_ID = CA_C_ID and\n" +
                "B_ID = CA_B_ID\n";
    }
}
