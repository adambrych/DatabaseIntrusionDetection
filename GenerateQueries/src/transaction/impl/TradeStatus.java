package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class TradeStatus implements Transaction {

    private static final String ROLE = "9";

    @Override
    public String generateTransaction() {
        return frame1();
    }

    private String frame1(){
        String query1 = ROLE + "," +"select " +
                "T_ID, " +
                "T_DTS, " +
                "ST_NAME, " +
                "TT_NAME, " +
                "T_S_SYMB, " +
                "T_QTY, " +
                "T_EXEC_NAME, " +
                "T_CHRG, " +
                "S_NAME, " +
                "EX_NAME " +
                "from " +
                "TRADE, " +
                "STATUS_TYPE, " +
                "TRADE_TYPE, " +
                "SECURITY, " +
                "EXCHANGE " +
                "where " +
                "T_CA_ID = " + ColumnValue.getValue("T_CA_ID") +
                " and " +
                "ST_ID = T_ST_ID" +
                " and " +
                "TT_ID = T_TT_ID" +
                " and " +
                "S_SYMB = T_S_SYMB" +
                " and " +
                "EX_ID = S_EX_ID " +
                "order by " +
                "T_DTS desc";

                String query2 = ROLE + "," +"select " +
                "C_L_NAME, " +
                "C_F_NAME, " +
                "B_NAME " +
                "from " +
                "CUSTOMER_ACCOUNT, " +
                "CUSTOMER, " +
                "BROKER " +
                "where " +
                "CA_ID = " + ColumnValue.getValue("CA_ID") +
                        " and " +
                "C_ID = CA_C_ID" +
                        " and " +
                "B_ID = CA_B_ID";

        return 
                query1 + System.lineSeparator() +
                query2 + System.lineSeparator();
    }
}
