package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class CustomerPosition implements Transaction {

    private static final String ROLE = "2";

    @Override
    public String generateTransaction() {
        return frameOne() + frameTwo();
    }

    private String frameOne(){
        String query1 = ROLE + "," +
                "select " +
                "C_ID " +
                "from " +
                "CUSTOMER " +
                "where " +
                "C_TAX_ID = "+ ColumnValue.getValue("C_TAX_ID");
        String query2 = ROLE + "," +
                "select " +
                "C_ST_ID, " +
                "C_L_NAME, " +
                "C_F_NAME, " +
                "C_M_NAME, " +
                "C_GNDR, " +
                "C_TIER, " +
                "C_DOB, " +
                "C_AD_ID, " +
                "C_CTRY_1, " +
                "C_AREA_1, " +
                "C_LOCAL_1, " +
                "C_EXT_1, " +
                "C_CTRY_2, " +
                "C_AREA_2, " +
                "C_LOCAL_2, " +
                "C_EXT_2, " +
                "C_CTRY_3," +
                "C_AREA_3, " +
                "C_LOCAL_3, " +
                "C_EXT_3, " +
                "C_EMAIL_1, " +
                "C_EMAIL_2 " +
                "from " +
                "CUSTOMER " +
                "where " +
                "C_ID = "+ ColumnValue.getValue("C_ID");

        String query3 = ROLE + "," +
                "select" +
                "CA_ID, " +
                "CA_BAL, " +
                "SUM(HS_QTY * LT_PRICE) " +
                "from " +
                "CUSTOMER_ACCOUNT left outer join " +
                "HOLDING_SUMMARY on HS_CA_ID = CA_ID, " +
                "LAST_TRADE " +
                "where " +
                "CA_C_ID = " + ColumnValue.getValue("CA_C_ID") + " and " +
                "LT_S_SYMB = HS_S_SYMB " +
                "group by " +
                "CA_ID, CA_BAL " +
                "order by " +
                "3 asc";

        return query1 + System.lineSeparator() + query2 + System.lineSeparator() + query3 + System.lineSeparator();
    }

    private String frameTwo(){
        String query = ROLE + "," +
                "select " +
                "T_ID, " +
                "T_S_SYMB, " +
                "T_QTY, " +
                "ST_NAME, " +
                "TH_DTS " +
                "from " +
                "(select " +
                "T_ID as ID " +
                "from " +
                "TRADE "+
                "where " +
                "T_CA_ID = " + ColumnValue.getValue("T_CA_ID") + " " +
                "order by T_DTS desc LIMIT 0, 10) as T, " +
                "TRADE, " +
                "TRADE_HISTORY, " +
                "STATUS_TYPE " +
                "where " +
                "T_ID = ID and " +
                "TH_T_ID = T_ID and " +
                "ST_ID = TH_ST_ID " +
                "order by " +
                "TH_DTS desc "+
                "LIMIT 0, 30";

        return query + System.lineSeparator();
    }
}
