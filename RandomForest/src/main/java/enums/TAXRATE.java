package enums;

public enum TAXRATE  implements Table{
    TX_ID(0),
    TX_NAME(1),
    TX_RATE(2);

    int index;

    TAXRATE(int index){
        this.index = index;
    }
}
