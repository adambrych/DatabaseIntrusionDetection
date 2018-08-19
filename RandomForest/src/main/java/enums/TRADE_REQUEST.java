package enums;

public enum TRADE_REQUEST implements Table {
    TR_T_ID(0),
    TR_TT_ID(1),
    TR_S_SYMB(2),
    TR_QTY(3),
    TR_BID_PRICE(4),
    TR_B_ID(5);

    int index;

    TRADE_REQUEST(int index){
        this.index = index;
    }
}
