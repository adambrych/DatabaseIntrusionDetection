package enums;

public enum CUSTOMER_ACCOUNT implements Table {
    CA_ID(0),
    CA_B_ID(1),
    CA_C_ID(2),
    CA_NAME(3),
    CA_TAX_ST(4),
    CA_BAL(5);

    int index;

    CUSTOMER_ACCOUNT(int index){
        this.index = index;
    }
}
