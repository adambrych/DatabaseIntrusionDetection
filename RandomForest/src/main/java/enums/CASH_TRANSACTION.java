package enums;

public enum CASH_TRANSACTION {
    CT_T_ID(0),
    CT_DTS(1),
    CT_AMT(2),
    CT_NAME(3);

    int index;

    CASH_TRANSACTION(int index){
        this.index = index;
    }
}
