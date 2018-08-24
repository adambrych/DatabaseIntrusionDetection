package enums;

public enum WATCH_ITEM  implements Table{
    WI_WL_ID(0),
    WI_S_SYMB(1);

    int index;

    WATCH_ITEM(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
