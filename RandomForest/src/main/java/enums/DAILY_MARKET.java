package enums;

public enum DAILY_MARKET  implements Table{
    DM_DATE(0),
    DM_S_SYMB(1),
    DM_CLOSE(2),
    DM_HIGH(3),
    DM_LOW(4),
    DM_VOL(5);

    int index;

    DAILY_MARKET(int index){
        this.index = index;
    }
}
