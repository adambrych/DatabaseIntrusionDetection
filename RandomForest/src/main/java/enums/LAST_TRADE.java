package enums;

public enum LAST_TRADE  implements Table{
    LT_S_SYMB(0),
    LT_DTS(1),
    LT_PRICE(2),
    LT_OPEN_PRICE(3),
    LT_VOL(4);

    int index;

    LAST_TRADE(int index){
        this.index = index;
    }
}
