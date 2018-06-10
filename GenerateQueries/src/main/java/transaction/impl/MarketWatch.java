package transaction.impl;

import transaction.Transaction;

public class MarketWatch implements Transaction {

    private static final String ROLE = "4";

    @Override
    public String generateTransaction() {
        return frameOne();
    }

    private String frameOne(){
        String query = ROLE + "," +
                "select " +
                "WI_S_SYMB " +
                "from " +
                "WATCH_ITEM, " +
                "WATCH_LIST " +
                "where " +
                "WI_WL_ID = WL_ID and " +
                "WL_C_ID = cust_id";

        String query2 = ROLE + "," +
                "select " +
                "S_SYMB " +
                "from " +
                "INDUSTRY, " +
                "COMPANY, " +
                "SECURITY " +
                "where " +
                "IN_NAME = industry_name and " +
                "CO_IN_ID = IN_ID and " +
                "CO_ID between (starting_co_id and ending_co_id) and " +
                "S_CO_ID = CO_ID";

        String query3 = ROLE + "," +
                "select " +
                "HS_S_SYMB " +
                "from " +
                "HOLDING_SUMMARY " +
                "where " +
                "HS_CA_ID = acct_id";

        String query4 = ROLE + "," +
                "select " +
                "LT_PRICE " +
                "from " +
                "LAST_TRADE " +
                "where " +
                "LT_S_SYMB = symbol";

         String query5 = ROLE + "," +
                 "select " +
                "S_NUM_OUT " +
                "from " +
                "SECURITY " +
                "where " +
                "S_SYMB = symbol";

        String query6 = ROLE + "," +
                "select " +
                "DM_CLOSE " +
                "from " +
                "DAILY_MARKET " +
                "where " +
                "DM_S_SYMB = symbol and " +
                "DM_DATE = start_date";

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator() +
                query4 + System.lineSeparator() +
                query5 + System.lineSeparator() +
                query6 + System.lineSeparator();
    }
}
