package enums;

public enum COMPANY_COMPETITOR implements Table {
    CP_CO_ID(0),
    CP_COMP_CO_ID(1),
    CP_IN_ID(2);

    int index;

    COMPANY_COMPETITOR(int index){
        this.index = index;
    }
}
