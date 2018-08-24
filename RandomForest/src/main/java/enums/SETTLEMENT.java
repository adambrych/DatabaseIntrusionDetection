package enums;

public enum SETTLEMENT  implements Table{
    SE_T_ID(0),
    SE_CASH_TYPE(1),
    SE_CASH_DUE_DATE(2),
    SE_AMT(3);

    int index;

    SETTLEMENT(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
