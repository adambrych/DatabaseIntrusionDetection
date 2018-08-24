package enums;

public enum TRADE_HISTORY implements Table {
    TH_T_ID(0),
    TH_DTS(1),
    TH_ST_ID(2);

    int index;

    TRADE_HISTORY(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
