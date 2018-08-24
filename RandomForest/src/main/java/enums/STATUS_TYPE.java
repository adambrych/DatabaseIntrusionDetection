package enums;

public enum STATUS_TYPE  implements Table{
    ST_ID(0),
    ST_NAME(1);

    int index;

    STATUS_TYPE(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
