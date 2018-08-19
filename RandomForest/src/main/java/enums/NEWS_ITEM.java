package enums;

public enum NEWS_ITEM  implements Table{
    NI_ID(0),
    NI_HEADLINE(1),
    NI_SUMMARY(2),
    NI_ITEM(3),
    NI_DTS(4),
    NI_SOURCE(5),
    NI_AUTHOR(6);

    int index;

    NEWS_ITEM(int index){
        this.index = index;
    }
}
