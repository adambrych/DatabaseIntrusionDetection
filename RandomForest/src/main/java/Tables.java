import javafx.scene.control.Tab;

public enum Tables {
    ACCOUNT_PERMISSION(0),
    CUSTOMER(1),
    CUSTOMER_ACCOUNT(2),
    CUSTOMER_TAXRATE(3),
    HOLDING(4),
    HOLDING_HISTORY(5),
    HOLDING_SUMMARY(6),
    WATCH_ITEM(7),
    WATCH_LIST(8),
    BROKER(9),
    CASH_TRANSACTION(10),
    CHARGE(11),
    COMMISSION_RATE(12),
    SETTLEMENT(13),
    TRADE(14),
    TRADE_HISTORY(15),
    TRADE_REQUEST(16),
    TRADE_TYPE(17),
    COMPANY(18),
    COMPANY_COMPETITOR(19),
    DAILY_MARKET(20),
    EXCHANGE(21),
    FINANCIAL(22),
    INDUSTRY(23),
    LAST_TRADE(24),
    NEWS_ITEM(25),
    NEWS_XREF(26),
    SECTOR(27),
    SECURITY(28),
    ADDRESS(29),
    STATUS_TYPE(30),
    TAXRATE(31),
    ZIP_CODE(32);

    private int position;

    Tables(int position){
        this.position = position;
    }
}
