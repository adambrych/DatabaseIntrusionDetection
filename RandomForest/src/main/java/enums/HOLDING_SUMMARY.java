package enums;

public enum HOLDING_SUMMARY {
    HS_CA_ID(0),
    HS_S_SYMB(1),
    HS_QTY(2);

    int index;

    HOLDING_SUMMARY(int index){
        this.index = index;
    }
}
