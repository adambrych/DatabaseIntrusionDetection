package enums;

public enum CUSTOMER  implements Table{
    C_ID(0),
    C_TAX_ID(1),
    C_ST_ID(2),
    C_L_NAME(3),
    C_F_NAME(4),
    C_M_NAME(5),
    C_GNDR(6),
    C_TIER(7),
    C_DOB(8),
    C_AD_ID(9),
    C_CTRY_1(10),
    C_AREA_1(11),
    C_LOCAL_1(12),
    C_EXT_1(13),
    C_CTRY_2(14),
    C_AREA_2(15),
    C_LOCAL_2(16),
    C_EXT_2(17),
    C_CTRY_3(18),
    C_AREA_3(19),
    C_LOCAL_3(20),
    C_EXT_3(21),
    C_EMAIL_1(22),
    C_EMAIL_2(23);

    int index;

    CUSTOMER(int index){
        this.index = index;
    }
}
