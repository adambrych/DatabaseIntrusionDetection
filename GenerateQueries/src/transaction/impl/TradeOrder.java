package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class TradeOrder implements Transaction {

    private static final String ROLE = "7";

    @Override
    public String generateTransaction() {
        return frameOne() + frameTwo() + frameThree() + frame4();
    }

    private String frameOne(){
        String query1 = ROLE + "," +"select " +
                "CA_NAME, " +
                "CA_B_ID, " +
                "CA_C_ID, " +
                "CA_TAX_ST " +
                "from " +
                "CUSTOMER_ACCOUNT " +
                "where " +
                "CA_ID = " + ColumnValue.getValue("CA_ID");

        String query2 = ROLE + "," +"select " +
                "C_F_NAME, " +
                "C_L_NAME, " +
                "C_TIER, " +
                "C_TAX_ID " +
                "from " +
                "CUSTOMER " +
                "where " +
                "C_ID = " + ColumnValue.getValue("CA_ID");

        String query3 = ROLE + "," +"select " +
                "B_NAME " +
                "from " +
                "BROKER " +
                "where " +
                "B_ID = " + ColumnValue.getValue("B_ID");
        return 
                query1 + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator();
    }

    private String frameTwo(){
        String query = ROLE + "," +"select " +
                "AP_ACL " +
                "from " +
                "ACCOUNT_PERMISSION " +
                "where " +
                "AP_CA_ID =" + ColumnValue.getValue("AP_CA_ID") +
                "  and " +
                "AP_F_NAME =" + ColumnValue.getValue("AP_F_NAME")+
                "  and " +
                "AP_L_NAME =" + ColumnValue.getValue("AP_L_NAME")+
                "  and " +
                "AP_TAX_ID = " + ColumnValue.getValue("AP_TAX_ID");
        
        return query + System.lineSeparator();
    }

    private String frameThree(){
        String query1 = ROLE + "," +"select " +
                "CO_ID " +
                "from " +
                "COMPANY " +
                "where " +
                "CO_NAME = " + ColumnValue.getValue("CO_NAME");

        String query2 = ROLE + "," +"select " +
                "S_EX_ID, " +
                "S_NAME, " +
                "S_SYMB " +
                "from " +
                "SECURITY " +
                "where " +
                "S_CO_ID = " + ColumnValue.getValue("S_CO_ID")+
                " and " +
                "S_ISSUE = " + ColumnValue.getValue("CO_NAME");

        String query3 = ROLE + "," +"select " +
                "S_CO_ID, " +
                "S_EX_ID, " +
                "S_NAME " +
                "from " +
                "SECURITY " +
                "where " +
                "S_SYMB = " + ColumnValue.getValue("S_SYMB");

        String query4 = ROLE + "," +"select " +
                "CO_NAME " +
                "from " +
                "COMPANY " +
                "where " +
                "CO_ID = " + ColumnValue.getValue("CO_ID");

        String query5 = ROLE + "," +"select " +
                "LT_PRICE " +
                "from " +
                "LAST_TRADE " +
                "where " +
                "LT_S_SYMB = " + ColumnValue.getValue("LT_S_SYMB");

        String query6 = ROLE + "," +"select " +
                "TT_IS_MRKT, " +
                "TT_IS_SELL " +
                "from " +
                "TRADE_TYPE " +
                "where " +
                "TT_ID = " + ColumnValue.getValue("TT_ID");

        String query7 = ROLE + "," +"select " +
                "HS_QTY " +
                "from " +
                "HOLDING_SUMMARY " +
                "where " +
                "HS_CA_ID = " + ColumnValue.getValue("HS_CA_ID")+
                " and " +
                "HS_S_SYMB = " + ColumnValue.getValue("HS_S_SYMB");

        String query8 = ROLE + "," +"select " +
                "H_QTY, " +
                "H_PRICE " +
                "from " +
                "HOLDING " +
                "where " +
                "H_CA_ID = " + ColumnValue.getValue("H_CA_ID")+
                " and " +
                "H_S_SYMB = " + ColumnValue.getValue("H_S_SYMB")+
                "order by " +
                "H_DTS desc";

        String query9 = ROLE + "," +"select " +
                "H_QTY, " +
                "H_PRICE " +
                "from " +
                "HOLDING " +
                "where " +
                "H_CA_ID = " + ColumnValue.getValue("H_CA_ID")+
                " and " +
                "H_S_SYMB = " + ColumnValue.getValue("H_S_SYMB")+
                "order by " +
                "H_DTS asc";

        String query10 = ROLE + "," +
                "select " +
                "SUM(TX_RATE) " +
                "from " +
                "TAXRATE " +
                "where " +
                "TX_ID = " + ColumnValue.getValue("TX_ID");

        String query12 = ROLE + "," +"select " +
                "CR_RATE " +
                "from " +
                "COMMISSION_RATE " +
                "where " +
                "CR_C_TIER = " + ColumnValue.getValue("CR_C_TIER") +
                " and " +
                "CR_TT_ID = " + ColumnValue.getValue("CR_TT_ID") +
                " and " +
                "CR_EX_ID = " + ColumnValue.getValue("CR_EX_ID") +
                " and " +
                "CR_FROM_QTY <= " + ColumnValue.getValue("CR_FROM_QTY") +
                " and " +
                "CR_TO_QTY >= " + ColumnValue.getValue("CR_TO_QTY");

        String query13 = ROLE + "," +"select " +
                "CH_CHRG " +
                "from " +
                "CHARGE " +
                "where " +
                "CH_C_TIER = " + ColumnValue.getValue("CH_C_TIER") +
                " and " +
                "CH_TT_ID = " + ColumnValue.getValue("CH_TT_ID");

        String query14 = ROLE + "," +"select " +
                "CA_BAL " +
                "from " +
                "CUSTOMER_ACCOUNT " +
                "where " +
                "CA_ID = " + ColumnValue.getValue("CA_ID");

        String query15 = ROLE + "," +"select " +
                "SUM(HS_QTY * LT_PRICE) " +
                "from " +
                "HOLDING_SUMMARY, " +
                "LAST_TRADE " +
                "where " +
                "HS_CA_ID = " + ColumnValue.getValue("HS_CA_ID") +
                " and " +
                "LT_S_SYMB = HS_S_SYMB";

        return 
                query1 + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator() +
                query4 + System.lineSeparator() +
                query5 + System.lineSeparator() +
                query6 + System.lineSeparator() +
                query7 + System.lineSeparator() +
                query8 + System.lineSeparator() +
                query9 + System.lineSeparator() +
                query10 + System.lineSeparator() +
                query12 + System.lineSeparator() +
                query13 + System.lineSeparator() +
                query14 + System.lineSeparator() +
                query15 + System.lineSeparator();
    }

    private String frame4(){
        String query = ROLE + "," +"insert into " +
                "TRADE ( " +
                "T_ID, T_DTS, T_ST_ID, T_TT_ID, T_IS_CASH, " +
                "T_S_SYMB, T_QTY, T_BID_PRICE, T_CA_ID, T_EXEC_NAME, " +
                "T_TRADE_PRICE, T_CHRG, T_COMM, T_TAX, T_LIFO " +
                ") " +
                "values ( " +
                ColumnValue.getValue("T_ID") + ", " +
                ColumnValue.getValue("T_DTS") + ", " +
                ColumnValue.getValue("T_ST_ID") + ", " +
                ColumnValue.getValue("T_TT_ID") + ", " +
                ColumnValue.getValue("T_IS_CASH") + ", " +
                ColumnValue.getValue("T_S_SYMB") + ", " +
                ColumnValue.getValue("T_QTY") + ", " +
                ColumnValue.getValue("T_BID_PRICE") + ", " +
                ColumnValue.getValue("T_CA_ID") + ", " +
                ColumnValue.getValue("T_EXEC_NAME") + ", " +
                "NULL, " +
                ColumnValue.getValue("T_CHRG") + ", " +
                ColumnValue.getValue("T_COMM") + ", " +
                "0, " +
                ColumnValue.getValue("T_LIFO") +
                ")";

                String query2 = ROLE + "," +"insert into " +
                "TRADE_REQUEST ( " +
                "TR_T_ID, TR_TT_ID, TR_S_SYMB, " +
                "TR_QTY, TR_BID_PRICE, TR_B_ID " +
                ") " +
                "values ( " +
                        ColumnValue.getValue("TR_T_ID") + ", " +
                        ColumnValue.getValue("TR_TT_ID") + ", " +
                        ColumnValue.getValue("TR_S_SYMB") + ", " +
                        ColumnValue.getValue("TR_QTY") + ", " +
                        ColumnValue.getValue("TR_BID_PRICE") + ", " +
                        ColumnValue.getValue("TR_B_ID") +
                ")";

                String query3 = ROLE + "," +"insert into " +
                "TRADE_HISTORY ( " +
                "TH_T_ID, TH_DTS, TH_ST_ID " +
                ") " +
                "values ( " +
                        ColumnValue.getValue("TH_T_ID") + ", " +
                        ColumnValue.getValue("TH_DTS") + ", " +
                        ColumnValue.getValue("TH_ST_ID") + ", " +
                ")";

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator();
    }
}
