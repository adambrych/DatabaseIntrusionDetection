package enums;

public enum CHARGE {
    CH_TT_ID(0),
    CH_C_TIER(1),
    CH_CHRG(2);

    int index;

    CHARGE(int index){
        this.index = index;
    }
}
