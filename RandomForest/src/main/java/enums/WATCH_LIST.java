package enums;

public enum WATCH_LIST implements Table {
    WL_ID(0),
    WL_C_ID(1);

    int index;

    WATCH_LIST(int index){
        this.index = index;
    }
}
