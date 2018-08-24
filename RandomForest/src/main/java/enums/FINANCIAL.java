package enums;

public enum FINANCIAL  implements Table{
    FI_CO_ID(0),
    FI_YEAR(1),
    FI_QTR(2),
    FI_QTR_START_DATE(3),
    FI_REVENUE(4),
    FI_NET_EARN(5),
    FI_BASIC_EPS(6),
    FI_DILUT_EPS(7),
    FI_MARGIN(8),
    FI_INVENTORY(9),
    FI_ASSETS(10),
    FI_LIABILITY(11),
    FI_OUT_BASIC(12),
    FI_OUT_DILUT(13);

    int index;

    FINANCIAL(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
