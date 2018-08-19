package enums;

public enum INDUSTRY  implements Table{
    IN_ID(0),
    IN_NAME(1),
    IN_SC_ID(2);

    int index;

    INDUSTRY(int index){
        this.index = index;
    }
}
