package enums;

public enum COMMISSION_RATE {
    CR_C_TIER(0),
    CR_TT_ID(1),
    CR_EX_ID(2),
    CR_FROM_QTY(3),
    CR_TO_QTY(4),
    CR_RATE(5);

    int index;

    COMMISSION_RATE(int index){
        this.index = index;
    }
}
