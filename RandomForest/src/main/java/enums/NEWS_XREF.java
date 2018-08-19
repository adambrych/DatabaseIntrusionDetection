package enums;

public enum NEWS_XREF implements Table {
    NX_NI_ID(0),
    NX_CO_ID(1);

    int index;

    NEWS_XREF(int index){
        this.index = index;
    }
}
