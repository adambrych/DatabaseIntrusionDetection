package enums;

public enum EXCHANGE  implements Table{
    EX_ID(0),
    EX_NAME(1),
    EX_NUM_SYMB(2),
    EX_OPEN(3),
    EX_CLOSE(4),
    EX_DESC(5),
    EX_AD_ID(6);

    int index;

    EXCHANGE(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
