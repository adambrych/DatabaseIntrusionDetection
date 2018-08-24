package enums;

public enum TRADE  implements Table{
    T_ID(0),
    T_DTS(1),
    T_ST_ID(2),
    T_TT_ID(3),
    T_IS_CASH(4),
    T_S_SYMB(5),
    T_QTY(6),
    T_BID_PRICE(7),
    T_CA_ID(8),
    T_EXEC_NAME(9),
    T_TRADE_PRICE(10),
    T_CHRG(11),
    T_COMM(12),
    T_TAX(13),
    T_LIFO(14);

    int index;

    TRADE(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
