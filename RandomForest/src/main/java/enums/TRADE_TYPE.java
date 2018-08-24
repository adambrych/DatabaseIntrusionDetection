package enums;

public enum TRADE_TYPE implements Table {
    TT_ID(0),
    TT_NAME(1),
    TT_IS_SELL(2),
    TT_IS_MRKT(3);

    int index;

    TRADE_TYPE(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
