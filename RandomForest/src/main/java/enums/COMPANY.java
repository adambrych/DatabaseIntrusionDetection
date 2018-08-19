package enums;

public enum COMPANY implements Table {
    CO_ID(0),
    CO_ST_ID(1),
    CO_NAME(2),
    CO_IN_ID(3),
    CO_SP_RATE(4),
    CO_CEO(5),
    CO_AD_ID(6),
    CO_DESC(7),
    CO_OPEN_DATE(8);

    int index;

    COMPANY(int index){
        this.index = index;
    }
}
