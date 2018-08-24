package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class TradeUpdate implements Transaction{

    private static final String ROLE = "10";

    @Override
    public String generateTransaction() {
        return frame1() + frame2() + frame3();
    }

    private String frame1(){
        String query=ROLE + "," +"select " +
                "T_EXEC_NAME " +
                "from " +
                "TRADE " +
                "where " +
                "T_ID = " + ColumnValue.getValue("T_ID");

                String query2=ROLE + "," +"update " +
                "TRADE " +
                "set " +
                "T_EXEC_NAME = ex_name " +
                "where " +
                "T_ID = " + ColumnValue.getValue("T_ID");

                String query3=ROLE + "," +"select " +
                "T_BID_PRICE, " +
                "T_EXEC_NAME, " +
                "T_IS_CASH, " +
                "TT_IS_MRKT, " +
                "T_TRADE_PRICE " +
                "from " +
                "TRADE, " +
                "TRADE_TYPE " +
                "where " +
                "T_ID = " + ColumnValue.getValue("T_ID")+
                        " and " +
                "T_TT_ID = " + ColumnValue.getValue("T_TT_ID");

                String query4=ROLE + "," +"select " +
                "SE_AMT, " +
                "SE_CASH_DUE_DATE, " +
                "SE_CASH_TYPE " +
                "from " +
                "SETTLEMENT " +
                "where " +
                "SE_T_ID = " + ColumnValue.getValue("SE_T_ID");

                String query5=ROLE + "," +"select " +
                "CT_AMT, " +
                "CT_DTS, " +
                "CT_NAME " +
                "from " +
                "CASH_TRANSACTION " +
                "where " +
                "CT_T_ID = " + ColumnValue.getValue("CT_T_ID");

                String query6=ROLE + "," +"select " +
                "TH_DTS, " +
                "TH_ST_ID " +
                "from " +
                "TRADE_HISTORY " +
                "where " +
                "TH_T_ID = " + ColumnValue.getValue("TH_T_ID") + " "+
                "order by " +
                "TH_DTS";

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator() +
                query4 + System.lineSeparator() +
                query5 + System.lineSeparator() +
                query6 + System.lineSeparator();
    }

    private String frame2(){
        String query=ROLE + "," +"select " +
                "T_BID_PRICE, " +
                "T_EXEC_NAME, " +
                "T_IS_CASH, " +
                "T_ID, " +
                "T_TRADE_PRICE " +
                "from " +
                "TRADE " +
                "where " +
                "T_CA_ID = " + ColumnValue.getValue("T_CA_ID") +
                " and " +
                "T_DTS >= " + ColumnValue.getValue("T_DTS") +
                " and " +
                "T_DTS <= " + ColumnValue.getValue("T_DTS") +
                " order by " +
                "T_DTS asc";

                String query2=ROLE + "," +"select " +
                "SE_CASH_TYPE " +
                "from " +
                "SETTLEMENT " +
                "where " +
                "SE_T_ID = " + ColumnValue.getValue("SE_T_ID");

                String query3=ROLE + "," +"update " +
                "SETTLEMENT " +
                "set " +
                "SE_CASH_TYPE = " + ColumnValue.getValue("SE_CASH_TYPE") + " " +
                "where " +
                "SE_T_ID = t" + ColumnValue.getValue("SE_T_ID");

                String query4=ROLE + "," +"select " +
                "SE_AMT, " +
                "SE_CASH_DUE_DATE, " +
                "SE_CASH_TYPE " +
                "from " +
                "SETTLEMENT " +
                "where " +
                "SE_T_ID = " + ColumnValue.getValue("SE_T_ID");

                String query5=ROLE + "," +"select " +
                "CT_AMT, " +
                "CT_DTS " +
                "CT_NAME " +
                "from " +
                "CASH_TRANSACTION " +
                "where " +
                "CT_T_ID = " + ColumnValue.getValue("CT_T_ID");

                String query6=ROLE + "," +"select " +
                "TH_DTS, " +
                "TH_ST_ID " +
                "from " +
                "TRADE_HISTORY " +
                "where " +
                "TH_T_ID = " + ColumnValue.getValue("TH_T_ID") + " " +
                "order by " +
                "TH_DTS";

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator() +
                query4 + System.lineSeparator() +
                query5 + System.lineSeparator() +
                query6 + System.lineSeparator();
    }

    private String frame3(){
        String query=ROLE + "," +"select " +
                "T_CA_ID, " +
                "T_EXEC_NAME, " +
                "T_IS_CASH, " +
                "T_TRADE_PRICE, " +
                "T_QTY, " +
                "S_NAME, " +
                "T_DTS, " +
                "T_ID, " +
                "T_TT_ID, " +
                "TT_NAME " +
                "from " +
                "TRADE, " +
                "TRADE_TYPE, " +
                "SECURITY " +
                "where " +
                "T_S_SYMB = " + ColumnValue.getValue("T_S_SYMB") +
                " and " +
                "T_DTS >= " + ColumnValue.getValue("T_DTS") +
                " and " +
                "T_DTS <= " + ColumnValue.getValue("T_DTS") +
                " and " +
                "TT_ID = T_TT_ID" +
                " and " +
                "S_SYMB = T_S_SYMB " +
                "order by " +
                "T_DTS asc";

                String query2=ROLE + "," +"select " +
                "SE_AMT, " +
                "SE_CASH_DUE_DATE, " +
                "SE_CASH_TYPE " +
                "from " +
                "SETTLEMENT " +
                "where " +
                "SE_T_ID = " + ColumnValue.getValue("SE_T_ID");

                String query3=ROLE + "," +"select " +
                "CT_NAME " +
                "from " +
                "CASH_TRANSACTION " +
                "where " +
                "CT_T_ID = " + ColumnValue.getValue("CT_T_ID");

                String query4=ROLE + "," +"update " +
                "CASH_TRANSACTION " +
                "set " +
                "CT_NAME = ct_name " +
                "where " +
                "CT_T_ID = " + ColumnValue.getValue("CT_T_ID");

                String query5=ROLE + "," +"select " +
                "CT_AMT, " +
                "CT_DTS " +
                "CT_NAME " +
                "from " +
                "CASH_TRANSACTION " +
                "where " +
                "CT_T_ID = " + ColumnValue.getValue("CT_T_ID");

                String query6=ROLE + "," +"select " +
                "TH_DTS, " +
                "TH_ST_ID " +
                "from " +
                "TRADE_HISTORY " +
                "where " +
                "TH_T_ID = " + ColumnValue.getValue("CT_T_ID") + " " +
                "order by " +
                "TH_DTS asc";

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator() +
                query4 + System.lineSeparator() +
                query5 + System.lineSeparator() +
                query6 + System.lineSeparator();
    }
}
