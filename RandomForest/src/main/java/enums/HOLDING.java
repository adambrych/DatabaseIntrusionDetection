package enums;

public enum HOLDING  implements Table{
    H_T_ID(0),
    H_CA_ID(1),
    H_S_SYMB(2),
    H_DTS(3),
    H_PRICE(4),
    H_QTY(5);

    int index;

    HOLDING(int index){
        this.index = index;
    }
}
