package enums;

public enum CUSTOMER_TAXRATE  implements Table{
    CX_TX_ID(0),
    CX_C_ID(1);

    int index;

    CUSTOMER_TAXRATE(int index){
        this.index = index;
    }
}
