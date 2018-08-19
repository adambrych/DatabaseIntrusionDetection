package enums;

public enum BROKER implements Table {
    B_ID(0),
    B_ST_ID(1),
    B_NAME(2),
    B_NUM_TRADES(3),
    B_COMM_TOTAL(4);

    int index;

    BROKER(int index){
        this.index = index;
    }
}
