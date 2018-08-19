package enums;

public enum ZIP_CODE implements Table {
    ZC_CODE(0),
    ZC_TOWN(1),
    ZC_DIV(2);

    int index;

    ZIP_CODE(int index){
        this.index = index;
    }
}
