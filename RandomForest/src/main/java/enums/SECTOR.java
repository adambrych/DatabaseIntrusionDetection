package enums;

public enum SECTOR  implements Table{
    SC_ID(0),
    SC_NAME(1);

    int index;

    SECTOR(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
