package enums;

public enum HOLDING_HISTORY  implements Table{
    HH_H_T_ID(0),
    HH_T_ID(1),
    HH_BEFORE_QTY(2),
    HH_AFTER_QTY(3);

    int index;

    HOLDING_HISTORY(int index){
        this.index = index;
    }

}
