package transaction.impl;

import transaction.Transaction;

public class SecurityDetail implements Transaction{

    private static final String ROLE = "5";

    @Override
    public void generateTransaction() {

    }

    private void frameOne(){
        String query = ROLE + "," +
                "select " +
                "S_NAME, " +
                "CO_ID, " +
                "CO_NAME, " +
                "CO_SP_RATE " +
                "CO_CEO, " +
                "CO_DESC, " +
                "CO_OPEN_DATE, " +
                "CO_ST_ID, " +
                "CA.AD_LINE1, " +
                "CA.AD_LINE2, " +
                "ZCA.ZC_TOWN, " +
                "ZCA.ZC_DIV, " +
                "CA.AD_ZC_CODE, " +
                "CA.AD_CTRY, " +
                "S_NUM_OUT, " +
                "S_START_DATE, " +
                "S_EXCH_DATE, " +
                "S_PE, " +
                "S_52WK_HIGH, " +
                "S_52WK_HIGH_DATE, " +
                "S_52WK_LOW, " +
                "S_52WK_LOW_DATE, " +
                "S_DIVIDEND, " +
                "S_YIELD, " +
                "ZEA.ZC_DIV, " +
                "EA.AD_CTRY " +
                "EA.AD_LINE1, " +
                "EA.AD_LINE2, " +
                "ZEA.ZC_TOWN, " +
                "EA.AD_ZC_CODE, " +
                "EX_CLOSE, " +
                "EX_DESC, " +
                "EX_NAME, " +
                "EX_NUM_SYMB, " +
                "EX_OPEN " +
                "from " +
                "SECURITY, " +
                "COMPANY, " +
                "ADDRESS CA, " +
                "ADDRESS EA, " +
                "ZIP_CODE ZCA, " +
                "ZIP_CODE ZEA, " +
                "EXCHANGE " +
                "where " +
                "S_SYMB = symbol and " +
                "CO_ID = S_CO_ID and " +
                "CA.AD_ID = CO_AD_ID and " +
                "EA.AD_ID = EX_AD_ID and " +
                "EX_ID = S_EX_ID and " +
                "ca.ad_zc_code = zca.zc_code and " +
                "ea.ad_zc_code =zea.zc_code";
        String query2 = ROLE + "," +
                "select " +
                "CO_NAME, " +
                "IN_NAME " +
                "from " +
                "COMPANY_COMPETITOR, COMPANY, INDUSTRY " +
                "where " +
                "CP_CO_ID = co_id and " +
                "CO_ID = CP_COMP_CO_ID and " +
                "IN_ID = CP_IN_ID ";
        String query3 = ROLE + "," +
                "select " +
                "FI_YEAR, " +
                "FI_QTR, " +
                "FI_QTR_START_DATE, " +
                "FI_REVENUE, " +
                "FI_NET_EARN, " +
                "FI_BASIC_EPS, " +
                "FI_DILUT_EPS, " +
                "FI_MARGIN, " +
                "FI_INVENTORY, " +
                "FI_ASSETS, " +
                "FI_LIABILITY, " +
                "FI_OUT_BASIC, " +
                "FI_OUT_DILUT " +
                "from " +
                "FINANCIAL " +
                "where " +
                "FI_CO_ID = co_id " +
                "order by " +
                "FI_YEAR asc, " +
                "FI_QTR";
    }
}
