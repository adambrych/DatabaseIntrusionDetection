package transaction.impl;

import transaction.Transaction;

public class DataMaintence implements Transaction {
    @Override
    public void generateTransaction() {

    }

    private void frame1(){
        String query1 = "select\n" +
                " AP_ACL\n" +
                "from\n" +
                "ACCOUNT_PERMISSION\n" +
                "where\n" +
                "AP_CA_ID = acct_id\n" +
                "order by\n";

                String query2 = "update\n" +
                "ACCOUNT_PERMISSION\n" +
                "set\n" +
                "AP_ACL=”1111”\n" +
                "where\n" +
                "AP_CA_ID = acct_id and\n" +
                "AP_ACL = acl\n";

                String query3 = "update\n" +
                "ACCOUNT_PERMISSION\n" +
                "set\n" +
                "AP_ACL = ”0011”\n" +
                "where\n" +
                "AP_CA_ID = acct_id and\n" +
                "AP_ACL = acl\n";

                String query4 = "select\n" +
                " AD_LINE2,\n" +
                "AD_ID\n" +
                "from\n" +
                "ADDRESS, CUSTOMER\n" +
                "where\n" +
                "AD_ID = C_AD_ID and\n" +
                "C_ID = c_id\n";

                String query5 = "select\n" +
                "AD_LINE2,\n" +
                "AD_ID\n" +
                "from\n" +
                "ADDRESS, COMPANY\n" +
                "where\n" +
                "AD_ID = CO_AD_ID and\n" +
                "CO_ID = co_id\n";

                String query = "update\n" +
                "ADDRESS\n" +
                "set\n" +
                "AD_LINE2 = “Apt. 10C”\n" +
                "where\n" +
                "AD_ID = ad_id\n";

                String query7 = "update\n" +
                "ADDRESS\n" +
                "set\n" +
                "AD_LINE2 = “Apt. 22”\n" +
                "where\n" +
                "AD_ID = ad_id\n";

                String query8 = "select\n" +
                "CO_SP_RATE\n" +
                "from\n" +
                "COMPANY\n" +
                "where\n" +
                "CO_ID = co_id\n";

                String query9 = "update\n" +
                "COMPANY\n" +
                "set\n" +
                "CO_SP_RATE = “ABA”\n" +
                "where\n" +
                "CO_ID = co_id\n";

                String query10 = "update\n" +
                "COMPANY\n" +
                "set\n" +
                "CO_SP_RATE = “AAA”\n" +
                "where\n" +
                "CO_ID = co_id\n";

                String query11 = "select\n" +
                "C_EMAIL_2\n" +
                "from\n" +
                "CUSTOMER\n" +
                "where\n" +
                "C_ID = c_id\n";

                String query12 = "update\n" +
                "CUSTOMER\n" +
                "set\n" +
                "C_EMAIL_2 = "+
                "where\n" +
                "C_ID = c_id\n";

                String query13 = "update\n" +
                "CUSTOMER\n" +
                "set\n" +
                "C_EMAIL_2 = \n" +
                "where\n" +
                "C_ID = c_id\n";

                String query14 = "select\n" +
                "CX_TX_ID\n" +
                "from\n" +
                "CUSTOMER_TAXRATE\n" +
                "where\n" +
                "CX_C_ID = c_id and\n";

                String query15 = "update\n" +
                "CUSTOMER_TAXRATE\n" +
                "set\n" +
                "CX_TX_ID = new_tax_rate\n" +
                "where\n" +
                "CX_C_ID = c_id and\n" +
                "CX_TX_ID = old_tax_rate\n";

                String query16 = "update\n" +
                "DAILY_MARKET\n" +
                "set\n" +
                "DM_VOL = DM_VOL + vol_incr\n" +
                "where\n" +
                "DM_S_SYMB = symbol\n";

                String query17 = "select\n" +
                "rowcount = count(*)\n" +
                "from\n" +
                "EXCHANGE\n" +
                "where\n" +
                "EX_DESC like “%LAST UPDATED%”\n";

                String query18 = "update\n" +
                "EXCHANGE\n" +
                "set\n" +
                "EX_DESC = EX_DESC\n";

                String query19 = "update\n" +
                "EXCHANGE\n" +
                "set\n" +
                "EX_DESC = substring(EX_DESC,1,\n" +
                "len(3)\n";

                String query20 = "select\n" +
                "from\n" +
                "FINANCIAL\n" +
                "where\n" +
                "FI_CO_ID = co_id";

                String query21 = "update\n" +
                "FINANCIAL\n" +
                "set\n" +
                "FI_QTR_START_DATE = FI_QTR_START_DATE\n" +
                "where\n" +
                "FI_CO_ID = co_id\n";

                String query22 = "update\n" +
                "FINANCIAL\n" +
                "set\n" +
                "FI_QTR_START_DATE = FI_QTR_START_DATE \n" +
                "where\n" +
                "FI_CO_ID = co_id\n";

                String query23 = "update\n" +
                "NEWS_ITEM\n" +
                "set\n" +
                "NI_DTS = NI_DTS\n" +
                "where\n" +
                "NI_ID = (\n" +
                "select\n" +
                "NX_NI_ID\n" +
                "from\n" +
                "NEWS_XREF\n" +
                "where\n" +
                "NX_CO_ID = co_id)\n";

                String query24 = "update\n" +
                "SECURITY\n" +
                "set\n" +
                "S_EXCH_DATE = S_EXCH_DATE\n" +
                "where\n" +
                "S_SYMB = symbol\n" +
                "tx_name = NULL\n";

                String query25 = "select\n" +
                "tx_name = TX_NAME\n" +
                "from\n" +
                "TAXRATE\n" +
                "where\n" +
                "TX_ID = tx_id\n";

                String query26 = "update\n" +
                "TAXRATE\n" +
                "set\n" +
                "TX_NAME = tx_name\n" +
                "where\n" +
                "TX_ID = tx_id\n";

                String query27 = "select\n" +
                "WI_S_SYMB\n" +
                "from\n" +
                "( select\n" +
                "ROWNUM,\n" +
                "WI_S_SYMB\n" +
                "from\n" +
                "WATCH_ITEM,\n" +
                "WATCH_LIST\n" +
                "where\n" +
                "WL_C_ID = c_id and\n" +
                "WI_WL_ID = WL_ID and\n" +
                "order by\n" +
                "WI_S_SYMB asc\n" +
                ")\n";

                String query28 = "select\n" +
                "S_SYMB\n" +
                "from\n" +
                "SECURITY\n" +
                "where\n" +
                "S_SYMB > old_symbol and\n";

                String query29 = "select\n" +
                "WI_S_SYMB\n" +
                "from\n" +
                "WATCH_ITEM,\n" +
                "WATCH_LIST\n" +
                "where\n" +
                "WL_C_ID = c_id and\n" +
                "WI_WL_ID = WL_ID\n" +
                ")\n" +
                "order by\n" +
                "S_SYMB asc\n";

                String query30 = "update\n" +
                "WATCH_ITEM\n" +
                "set\n" +
                "WI_S_SYMB = new_symbol\n" +
                "from\n" +
                "WATCH_LIST\n" +
                "where\n" +
                "WL_C_ID = c_id and\n" +
                "WI_WL_ID = WL_ID and\n" +
                "WI_S_SYMB = old_symbol";
    }
}