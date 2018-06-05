package transaction.impl;

import transaction.Transaction;

public class CustomerPosition implements Transaction {

    private static final String ROLE = "2";

    @Override
    public void generateTransaction() {

    }

    private void frameOne(){
        String query1 = ROLE + "," +
                "select " +
                "C_ID " +
                "from " +
                "CUSTOMER " +
                "where " +
                "C_TAX_ID = tax_id";
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
                "C_ID = cust_id ";

        String query3 = ROLE + "," +
                "select" +
                "CA_ID, " +
                "CA_BAL, " +
                "ifnull((sum(HS_QTY * LT_PRICE)),0) " +
                "from " +
                "CUSTOMER_ACCOUNT left outer join " +
                "HOLDING_SUMMARY on HS_CA_ID = CA_ID, " +
                "LAST_TRADE " +
                "where " +
                "CA_C_ID = cust_id and " +
                "LT_S_SYMB = HS_S_SYMB " +
                "group by " +
                "CA_ID, CA_BAL " +
                "order by " +
                "3 asc";
    }

    private void frameTwo(){
        String query = ROLE + "," +
                "select " +
                "trade_id[] = T_ID, " +
                "symbol[] = T_S_SYMB, " +
                "qty[] = T_QTY, " +
                "trade_status[] = ST_NAME, " +
                "hist_dts[] = TH_DTS " +
                "from " +
                "(select first 10 rows " +
                "T_ID as ID " +
                "from " +
                "TRADE"+
                "where " +
                "T_CA_ID = acct_id " +
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
    }
}
