package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class DataMaintence implements Transaction {

    private static final String ROLE = "11";

    @Override
    public String generateTransaction() {
        return frame1();
    }

    private String frame1(){
        String query1 = ROLE + "," + "select " +
                "AP_ACL " +
                "from " +
                "ACCOUNT_PERMISSION " +
                "where " +
                "AP_CA_ID = " + ColumnValue.getValue("AP_CA_ID") + " " +
                "order by AP_ACL";

                String query2 = ROLE + "," + "update " +
                "ACCOUNT_PERMISSION " +
                "set " +
                "AP_ACL = \"1111\" " +
                "where " +
                "AP_CA_ID = " + ColumnValue.getValue("AP_CA_ID") + " and " +
                "AP_ACL = " + ColumnValue.getValue("AP_ACL");

                String query3 = ROLE + "," + "update " +
                "ACCOUNT_PERMISSION " +
                "set " +
                "AP_ACL = \"0011\" " +
                "where " +
                "AP_CA_ID = " + ColumnValue.getValue("AP_CA_ID") + " and " +
                "AP_ACL = " + ColumnValue.getValue("AP_ACL");

                String query4 = ROLE + "," + "select " +
                "AD_LINE2, " +
                "AD_ID " +
                "from " +
                "ADDRESS, CUSTOMER " +
                "where " +
                "AD_ID = C_AD_ID and " +
                "C_ID = " + ColumnValue.getValue("C_ID");

                String query5 = ROLE + "," + "select " +
                "AD_LINE2, " +
                "AD_ID " +
                "from " +
                "ADDRESS, COMPANY " +
                "where " +
                "AD_ID = CO_AD_ID and " +
                "CO_ID = " + ColumnValue.getValue("CO_ID");

                String query6 = ROLE + "," + "update " +
                "ADDRESS " +
                "set " +
                "AD_LINE2 = \"Apt. 10C\" " +
                "where " +
                "AD_ID = " + ColumnValue.getValue("AD_ID");

                String query7 = ROLE + "," + "update " +
                "ADDRESS " +
                "set " +
                "AD_LINE2 = \"Apt. 22\" " +
                "where " +
                "AD_ID = " + ColumnValue.getValue("AD_ID");

                String query8 = ROLE + "," + "select " +
                "CO_SP_RATE " +
                "from " +
                "COMPANY " +
                "where " +
                "CO_ID = " + ColumnValue.getValue("CO_ID");

                String query9 = ROLE + "," + "update " +
                "COMPANY " +
                "set " +
                "CO_SP_RATE = \"ABA\" " +
                "where " +
                "CO_ID = " + ColumnValue.getValue("CO_ID");

                String query10 = ROLE + "," + "update " +
                "COMPANY " +
                "set " +
                "CO_SP_RATE = \"AAA\" " +
                "where " +
                "CO_ID = " + ColumnValue.getValue("CO_ID");

                String query11 = ROLE + "," + "select " +
                "C_EMAIL_2 " +
                "from " +
                "CUSTOMER " +
                "where " +
                "C_ID = " + ColumnValue.getValue("C_ID");

                String query12 = ROLE + "," + "update " +
                "CUSTOMER " +
                "set " +
                "C_EMAIL_2 = " + ColumnValue.getValue("C_EMAIL_2") + " " +
                "where " +
                "C_ID = " + ColumnValue.getValue("C_ID");


                String query14 = ROLE + "," + "select " +
                "CX_TX_ID " +
                "from " +
                "CUSTOMER_TAXRATE " +
                "where " +
                "CX_C_ID = " + ColumnValue.getValue("C_ID");

                String query15 = ROLE + "," + "update " +
                "CUSTOMER_TAXRATE " +
                "set " +
                "CX_TX_ID = " +  ColumnValue.getValue("CX_C_ID") + " " +
                "where " +
                "CX_C_ID = " + ColumnValue.getValue("CX_C_ID") + " and " +
                "CX_TX_ID = " + ColumnValue.getValue("CX_TX_ID");

                String query17 = ROLE + "," + "select " +
                "count(*) " +
                "from " +
                "EXCHANGE " +
                "where " +
                "EX_DESC like \"%LAST UPDATED%\"";

                String query19 = ROLE + "," + "update " +
                "EXCHANGE " +
                "set " +
                "EX_DESC = " + ColumnValue.getValue("EX_DESC");

                String query20 = ROLE + "," + "select " +
                "from " +
                "FINANCIAL " +
                "where " +
                "FI_CO_ID = " + ColumnValue.getValue("FI_CO_ID");

                String query21 = ROLE + "," + "update " +
                "FINANCIAL " +
                "set " +
                "FI_QTR_START_DATE = FI_QTR_START_DATE " +
                "where " +
                "FI_CO_ID = " + ColumnValue.getValue("FI_CO_ID");

                String query22 = ROLE + "," + "update " +
                "FINANCIAL " +
                "set " +
                "FI_QTR_START_DATE = FI_QTR_START_DATE " +
                "where " +
                "FI_CO_ID = " + ColumnValue.getValue("FI_CO_ID");

                String query23 = ROLE + "," + "update " +
                "NEWS_ITEM " +
                "set " +
                "NI_DTS = NI_DTS " +
                "where " +
                "NI_ID = (" +
                "select " +
                "NX_NI_ID " +
                "from " +
                "NEWS_XREF " +
                "where " +
                "NX_CO_ID = " + ColumnValue.getValue("NX_CO_ID") + ")";

                String query24 = ROLE + "," + "update " +
                "SECURITY " +
                "set " +
                "S_EXCH_DATE = S_EXCH_DATE " +
                "where " +
                "S_SYMB = " +  ColumnValue.getValue("S_SYMB");

                String query25 = ROLE + "," + "select " +
                "TX_NAME " +
                "from " +
                "TAXRATE " +
                "where " +
                "TX_ID = " + ColumnValue.getValue("TX_ID");

                String query26 = ROLE + "," + "update " +
                "TAXRATE " +
                "set " +
                "TX_NAME = " + ColumnValue.getValue("TX_NAME") + " " +
                "where " +
                "TX_ID = " + ColumnValue.getValue("TX_ID");

                String query27 = ROLE + "," + "select " +
                "WI_S_SYMB " +
                "from " +
                "(select " +
                "ROWNUM, " +
                "WI_S_SYMB " +
                "from " +
                "WATCH_ITEM, " +
                "WATCH_LIST " +
                "where " +
                "WL_C_ID = " + ColumnValue.getValue("WL_C_ID") + " and " +
                "WI_WL_ID = WL_ID " +
                "order by " +
                "WI_S_SYMB asc" +
                ")";

                String query28 = ROLE + "," + "select " +
                "S_SYMB " +
                "from " +
                "SECURITY " +
                "where " +
                "S_SYMB > " + ColumnValue.getValue("S_SYMB");

                String query29 = ROLE + "," + "select " +
                "WI_S_SYMB " +
                "from " +
                "WATCH_ITEM, " +
                "WATCH_LIST " +
                "where " +
                "WL_C_ID = " + ColumnValue.getValue("WL_C_ID") + " and " +
                "WI_WL_ID = WL_ID" +
                " " +
                "order by " +
                "S_SYMB asc";
        
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
                query11 + System.lineSeparator() +
                query12 + System.lineSeparator() +

                query14 + System.lineSeparator() +
                query15 + System.lineSeparator() +
                query17 + System.lineSeparator() +
                query19 + System.lineSeparator() +
                query20 + System.lineSeparator() +
                query21 + System.lineSeparator() +
                query22 + System.lineSeparator() +
                query23 + System.lineSeparator() +
                query24 + System.lineSeparator() +
                query25 + System.lineSeparator() +
                query26 + System.lineSeparator() +
                query27 + System.lineSeparator() +
                query28 + System.lineSeparator() +
                query29 + System.lineSeparator();
                
                
    }
}
