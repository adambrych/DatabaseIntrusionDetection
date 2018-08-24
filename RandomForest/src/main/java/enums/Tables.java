package enums;

public enum Tables {
    ACCOUNT_PERMISSION(0, "AP_", enums.ACCOUNT_PERMISSION.values()),
    CUSTOMER(1, "C_", enums.CUSTOMER.values()),
    CUSTOMER_ACCOUNT(2, "CA_", enums.CUSTOMER_ACCOUNT.values()),
    CUSTOMER_TAXRATE(3, "CX_", enums.CUSTOMER_TAXRATE.values()),
    HOLDING(4, "H_", enums.HOLDING.values()),
    HOLDING_HISTORY(5, "HH_", enums.HOLDING_HISTORY.values()),
    HOLDING_SUMMARY(6, "HS_", enums.HOLDING_SUMMARY.values()),
    WATCH_ITEM(7, "WI_", enums.WATCH_ITEM.values()),
    WATCH_LIST(8, "WL_", enums.WATCH_LIST.values()),
    BROKER(9, "B_", enums.BROKER.values()),
    CASH_TRANSACTION(10, "CT_", enums.CASH_TRANSACTION.values()),
    CHARGE(11, "CH_", enums.CHARGE.values()),
    COMMISSION_RATE(12, "CR_", enums.COMMISSION_RATE.values()),
    SETTLEMENT(13, "SE_", enums.SETTLEMENT.values()),
    TRADE(14, "T_", enums.TRADE.values()),
    TRADE_HISTORY(15, "TH_", enums.TRADE_HISTORY.values()),
    TRADE_REQUEST(16, "TR_", enums.TRADE_REQUEST.values()),
    TRADE_TYPE(17, "TT_", enums.TRADE_TYPE.values()),
    COMPANY(18, "CO_", enums.COMPANY.values()),
    COMPANY_COMPETITOR(19, "CP_", enums.COMPANY_COMPETITOR.values()),
    DAILY_MARKET(20, "DM_", enums.DAILY_MARKET.values()),
    EXCHANGE(21, "EX_", enums.EXCHANGE.values()),
    FINANCIAL(22, "FI_", enums.FINANCIAL.values()),
    INDUSTRY(23, "IN_", enums.INDUSTRY.values()),
    LAST_TRADE(24, "LT_", enums.LAST_TRADE.values()),
    NEWS_ITEM(25, "NI_", enums.NEWS_ITEM.values()),
    NEWS_XREF(26, "NX_", enums.NEWS_XREF.values()),
    SECTOR(27, "SC_", enums.SECTOR.values()),
    SECURITY(28, "S_", enums.SECURITY.values()),
    ADDRESS(29, "AD_", enums.ADDRESS.values()),
    STATUS_TYPE(30, "ST_", enums.STATUS_TYPE.values()),
    TAXRATE(31, "TX_", enums.TAXRATE.values()),
    ZIP_CODE(32, "ZC_", enums.ZIP_CODE.values());

    private int position;
    private String prefix;
    private Table[] table;

    Tables(int position, String prefix, Table[] table){
        this.setPosition(position);
        this.setPrefix(prefix);
        this.setTable(table);
    }

    public static int indexByPrefix(String prefix){
        for(Tables table : values()){
            if(table.getPrefix().equals(prefix))
                return table.getPosition();
        }
        return 0;
    }

    public static Tables tableByPrefix(String prefix){
        for(Tables table : values()){
            if(table.getPrefix().equals(prefix))
                return table;
        }
        return null;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Table[] getTable() {
        return table;
    }

    public void setTable(Table[] table) {
        this.table = table;
    }
}
