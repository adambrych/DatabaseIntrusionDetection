package enums;

public enum Tables {
    ACCOUNT_PERMISSION(0, "AP_"),
    CUSTOMER(1, "C_"),
    CUSTOMER_ACCOUNT(2, "CA_"),
    CUSTOMER_TAXRATE(3, "CX_"),
    HOLDING(4, "H_"),
    HOLDING_HISTORY(5, "HH_"),
    HOLDING_SUMMARY(6, "HS_"),
    WATCH_ITEM(7, "WI_"),
    WATCH_LIST(8, "WL_"),
    BROKER(9, "B_"),
    CASH_TRANSACTION(10, "CT_"),
    CHARGE(11, "CH_"),
    COMMISSION_RATE(12, "CR_"),
    SETTLEMENT(13, "SE_"),
    TRADE(14, "T_"),
    TRADE_HISTORY(15, "TH_"),
    TRADE_REQUEST(16, "TR_"),
    TRADE_TYPE(17, "TT_"),
    COMPANY(18, "CO_"),
    COMPANY_COMPETITOR(19, "CP_"),
    DAILY_MARKET(20, "DM_"),
    EXCHANGE(21, "EX_"),
    FINANCIAL(22, "FI_"),
    INDUSTRY(23, "IN_"),
    LAST_TRADE(24, "LT_"),
    NEWS_ITEM(25, "NI_"),
    NEWS_XREF(26, "NX_"),
    SECTOR(27, "SC_"),
    SECURITY(28, "S_"),
    ADDRESS(29, "AD_"),
    STATUS_TYPE(30, "ST_"),
    TAXRATE(31, "TX_"),
    ZIP_CODE(32, "ZC_");

    private int position;
    private String prefix;

    Tables(int position, String prefix){
        this.position = position;
        this.prefix = prefix;
    }

    public static int indexByPrefix(String prefix){
        for(Tables table : values()){
            if(table.prefix.equals(prefix))
                return table.position;
        }
        return 0;
    }
}
