package enums;

public enum ADDRESS implements Table {
    AD_ID(0),
    AD_LINE1(1),
    AD_LINE2(2),
    AD_ZC_CODE(3),
    AD_CTRY(4);

    int index;

    ADDRESS(int index){
        this.index = index;
    }
}
