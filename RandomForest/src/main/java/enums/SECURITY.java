package enums;

public enum SECURITY  implements Table{
    S_SYMB(0),
    S_ISSUE(1),
    S_ST_ID(2),
    S_NAME(3),
    S_EX_ID(4),
    S_CO_ID(5),
    S_NUM_OUT(6),
    S_START_DATE(7),
    S_EXCH_DATE(8),
    S_PE(9),
    S_52WK_HIGH(10),
    S_52WK_HIGH_DATE(11),
    S_52WK_LOW(12),
    S_52WK_LOW_DATE(13),
    S_DIVIDEND(14),
    S_YIELD(15);

    int index;

    SECURITY(int index){
        this.index = index;
    }
}
