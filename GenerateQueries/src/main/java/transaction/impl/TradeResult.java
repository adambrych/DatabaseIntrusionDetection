package transaction.impl;

import transaction.Transaction;
import values.ColumnValue;

public class TradeResult implements Transaction {

    private static final String ROLE = "8";

    @Override
    public String generateTransaction() {
        return frame1() + frame2() + frame3() + frame4() + frame5() + frame6();
    }

    private String frame1(){
        String query =  ROLE + "," +"select " +
                "T_CA_ID, " +
                "T_TT_ID, " +
                "T_S_SYMB, " +
                "T_QTY, " +
                "T_CHRG, " +
                "T_LIFO, " +
                "T_IS_CASH " +
                "from " +
                "TRADE " +
                "where " +
                "T_ID = " + ColumnValue.getValue("T_ID");

                String query2 =  ROLE + "," +"select " +
                "TT_NAME, " +
                "TT_IS_SELL, " +
                "TT_IS_MRKT " +
                "from " +
                "TRADE_TYPE " +
                "where " +
                "TT_ID = " + ColumnValue.getValue("TT_ID");

                String query3 =  ROLE + "," +"select " +
                "HS_QTY " +
                "from " +
                "HOLDING_SUMMARY " +
                "where " +
                "HS_CA_ID = " + ColumnValue.getValue("HS_CA_ID") +
                        " and " +
                "HS_S_SYMB = " + ColumnValue.getValue("HS_S_SYMB");

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator();
    }

    private String frame2(){
        String query1 =  ROLE + "," +"select " +
                "CA_B_ID, " +
                "CA_C_ID, " +
                "CA_TAX_ST " +
                "from " +
                "CUSTOMER_ACCOUNT " +
                "where " +
                "CA_ID = " + ColumnValue.getValue("CA_ID");

                String query2 =  ROLE + "," +"insert into " +
                "HOLDING_SUMMARY (" +
                "HS_CA_ID, " +
                "HS_S_SYMB, " +
                "HS_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("HS_CA_ID") + ", " +
                        ColumnValue.getValue("HS_S_SYMB") + ", " +
                        ColumnValue.getValue("HS_QTY") +
                ")";

                String query3 =  ROLE + "," +"update " +
                "HOLDING_SUMMARY " +
                "set " +
                "HS_QTY = " + ColumnValue.getValue("HS_QTY")+ " where " +
                "HS_CA_ID = " + ColumnValue.getValue("HS_CA_ID")+
                        " and " +
                "HS_S_SYMB = " + ColumnValue.getValue("HS_S_SYMB");

                String query4 =  ROLE + "," +"select " +
                "H_T_ID, " +
                "H_QTY, " +
                "H_PRICE " +
                "from " +
                "HOLDING " +
                "where " +
                "H_CA_ID = " + ColumnValue.getValue("H_CA_ID")+
                        " and " +
                "H_S_SYMB = " + ColumnValue.getValue("H_S_SYMB")+
                " order by " +
                "H_DTS desc";

                String query5 =  ROLE + "," +"select " +
                "H_T_ID, " +
                "H_QTY, " +
                "H_PRICE " +
                "from " +
                "HOLDING " +
                "where " +
                "H_CA_ID = " + ColumnValue.getValue("H_CA_ID")+
                        " and " +
                "H_S_SYMB = " + ColumnValue.getValue("H_S_SYMB")+
                        " " +
                "order by " +
                "H_DTS asc";

                String query6 =  ROLE + "," +"insert into " +
                "HOLDING_HISTORY (" +
                "HH_H_T_ID, " +
                "HH_T_ID, " +
                "HH_BEFORE_QTY, " +
                "HH_AFTER_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("HH_H_T_ID")+ ", "+
        ColumnValue.getValue("HH_T_ID")+ ", "+
        ColumnValue.getValue("HH_BEFORE_QTY")+ ", "+
        ColumnValue.getValue("HH_AFTER_QTY")+
                ")";
        

                String query8 =  ROLE + "," +"insert into " +
                "HOLDING_HISTORY (" +
                "HH_H_T_ID, " +
                "HH_T_ID, " +
                "HH_BEFORE_QTY, " +
                "HH_AFTER_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("HH_H_T_ID")+ ", "+
                        ColumnValue.getValue("HH_T_ID")+ ", "+
                        ColumnValue.getValue("HH_BEFORE_QTY")+
                "0" +
                ")";

                String query10 =  ROLE + "," +"insert into " +
                "HOLDING (" +
                "H_T_ID, " +
                "H_CA_ID, " +
                "H_S_SYMB, " +
                "H_DTS, " +
                "H_PRICE, " +
                "H_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("H_T_ID")+ ", "+
                        ColumnValue.getValue("H_CA_ID")+ ", "+
                        ColumnValue.getValue("H_S_SYMB")+ ", "+
                        ColumnValue.getValue("H_DTS")+ ", "+
                        ColumnValue.getValue("H_PRICE")+ ", "+
                        ColumnValue.getValue("H_QTY")+
                ")";

                String query11 =  ROLE + "," +"delete from " +
                "HOLDING_SUMMARY " +
                "where " +
                "HS_CA_ID = " + ColumnValue.getValue("HS_CA_ID")+ " and "+
                "HS_S_SYMB = " + ColumnValue.getValue("HS_S_SYMB");

                String query12 =  ROLE + "," +"insert into " +
                "HOLDING_SUMMARY (" +
                "HS_CA_ID, " +
                "HS_S_SYMB, " +
                "HS_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("HS_CA_ID")+ ", "+
                        ColumnValue.getValue("HS_S_SYMB")+ ", "+
                        ColumnValue.getValue("HS_QTY")+ 
                ")";

                String query13 =  ROLE + "," +"update " +
                "HOLDING_SUMMARY " +
                "set " +
                "HS_QTY = " + ColumnValue.getValue("HS_QTY") + " " +
                "where " +
                "HS_CA_ID = " + ColumnValue.getValue("HS_CA_ID")+ " and "+
                "HS_S_SYMB = " + ColumnValue.getValue("HS_S_SYMB");

                String query14 =  ROLE + "," +"select " +
                "H_T_ID, " +
                "H_QTY, " +
                "H_PRICE " +
                "from " +
                "HOLDING " +
                "where " +
                "H_CA_ID = " + ColumnValue.getValue("H_CA_ID")+ " and "+
                "H_S_SYMB = " + ColumnValue.getValue("H_S_SYMB")+ " "+
                "order by " +
                "H_DTS desc";

                String query15 =  ROLE + "," +"select " +
                "H_T_ID, " +
                "H_QTY, " +
                "H_PRICE " +
                "from " +
                "HOLDING " +
                "where " +
                "H_CA_ID = " + ColumnValue.getValue("H_CA_ID")+ " and "+
                "H_S_SYMB = " + ColumnValue.getValue("H_S_SYMB")+ " "+
                "order by " +
                "H_DTS asc";

                String query16 =  ROLE + "," +"insert into " +
                "HOLDING_HISTORY (" +
                "HH_H_T_ID, " +
                "HH_T_ID, " +
                "HH_BEFORE_QTY, " +
                "HH_AFTER_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("HS_CA_ID")+ ", "+
                        ColumnValue.getValue("HS_CA_ID")+ ", "+
                        ColumnValue.getValue("HS_CA_ID")+
                ")";

                String query17 =  ROLE + "," +"insert into " +
                "HOLDING_HISTORY (" +
                "HH_H_T_ID, " +
                "HH_T_ID, " +
                "HH_BEFORE_QTY, " +
                "HH_AFTER_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("HH_H_T_ID")+ ", "+
                        ColumnValue.getValue("HH_T_ID")+ ", "+
                        ColumnValue.getValue("HH_AFTER_QTY")+ ", "+
                "0" +
                ")";
        

                String query19 =  ROLE + "," +"insert into " +
                "HOLDING_HISTORY (" +
                "HH_H_T_ID, " +
                "HH_T_ID, " +
                "HH_BEFORE_QTY, " +
                "HH_AFTER_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("HH_H_T_ID")+ ", "+
                        ColumnValue.getValue("HH_T_ID")+ ", "+
                "0, " +
                        ColumnValue.getValue("HH_AFTER_QTY")+ 
                ")";

                String query20 =  ROLE + "," +"insert into " +
                "HOLDING (" +
                "H_T_ID, " +
                "H_CA_ID, " +
                "H_S_SYMB, "+
                "H_DTS, " +
                "H_PRICE, " +
                "H_QTY" +
                ") " +
                "values (" +
                        ColumnValue.getValue("H_T_ID")+ ", "+
                        ColumnValue.getValue("H_CA_ID")+ ", "+
                        ColumnValue.getValue("H_S_SYMB")+ ", "+
                        ColumnValue.getValue("H_DTS")+ ", "+
                        ColumnValue.getValue("H_PRICE")+ ", "+
                        ColumnValue.getValue("H_QTY")+
                ")";

                String query21 =  ROLE + "," +"delete from " +
                "HOLDING_SUMMARY " +
                "where " +
                "HS_CA_ID = " + ColumnValue.getValue("HS_CA_ID")+ " and "+
                "HS_S_SYMB = " + ColumnValue.getValue("HS_S_SYMB");

        return 
                query1 + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator() +
                query4 + System.lineSeparator() +
                query5 + System.lineSeparator() +
                query6 + System.lineSeparator() +
      
                query8 + System.lineSeparator() +

                query10 + System.lineSeparator() +
                query11 + System.lineSeparator() +
                query12 + System.lineSeparator() +
                query13 + System.lineSeparator() +
                query14 + System.lineSeparator() +
                query15 + System.lineSeparator() +
                query16 + System.lineSeparator() +
                query17 + System.lineSeparator() +
   
                query19 + System.lineSeparator() +
                query20 + System.lineSeparator() +
                query21 + System.lineSeparator();
    }

    private String frame3(){
        String query= ROLE + "," +"select " +
                "SUM(TX_RATE) " +
                "from " +
                "TAXRATE " +
                "where " +
                "TX_ID in (select " +
                "CX_TX_ID " +
                "from " +
                "CUSTOMER_TAXRATE " +
                "where " +
                "CX_C_ID = " + ColumnValue.getValue("CX_C_ID")+
                ")";

        String query2= ROLE + "," +"update " +
                "TRADE " +
                "set " +
                "T_TAX = " + ColumnValue.getValue("T_TAX")+
                " where " +
                "T_ID = " + ColumnValue.getValue("T_ID");

        return query + System.lineSeparator() +
                query2 + System.lineSeparator();
    }

    private String frame4(){
        String query= ROLE + "," +"select " +
                "S_EX_ID, " +
                "S_NAME " +
                "from " +
                "SECURITY " +
                "where " +
                "S_SYMB = " + ColumnValue.getValue("S_SYMB");

                String query2= ROLE + "," +"select " +
                "C_TIER " +
                "from " +
                "CUSTOMER " +
                "where " +
                "C_ID = (" +
                "select " +
                "CR_RATE " +
                "from " +
                "COMMISSION_RATE " +
                "where " +
                "CR_C_TIER = " + ColumnValue.getValue("CR_C_TIER") +
                        " and " +
                "CR_TT_ID = " + ColumnValue.getValue("CR_TT_ID") +
                        " and " +
                "CR_EX_ID = " + ColumnValue.getValue("CR_EX_ID") +
                        " and " +
                "CR_FROM_QTY <= " + ColumnValue.getValue("CR_FROM_QTY") +
                        " and " +
                "CR_TO_QTY >= " + ColumnValue.getValue("CR_TO_QTY") +
                        ") LIMIT 1";

        return query + System.lineSeparator() +
                query2 + System.lineSeparator();
    }

    private String frame5(){
        String query= ROLE + "," +"update " +
                "TRADE " +
                "set " +
                "T_COMM = " + ColumnValue.getValue("T_COMM") + ", "+
                "T_DTS = " + ColumnValue.getValue("T_DTS") + ", "+
                "T_ST_ID = " + ColumnValue.getValue("T_ST_ID") + ", "+
                "T_TRADE_PRICE = " + ColumnValue.getValue("T_TRADE_PRICE") + " "+
                "where " +
                "T_ID = " + ColumnValue.getValue("T_ID");

                String query2= ROLE + "," +"insert into " +
                "TRADE_HISTORY (" +
                "TH_T_ID, " +
                "TH_DTS, " +
                "TH_ST_ID" +
                ") " +
                "values (" +
                        ColumnValue.getValue("TH_T_ID") + ", "+
                        ColumnValue.getValue("TH_DTS") + ", "+
                        ColumnValue.getValue("TH_ST_ID") +
                ")";

                String query3= ROLE + "," +"update " +
                "BROKER " +
                "set " +
                "B_COMM_TOTAL = " + ColumnValue.getValue("B_COMM_TOTAL") + ", "+
                "B_NUM_TRADES = " + ColumnValue.getValue("B_NUM_TRADES") + " " +
                "where " +
                "B_ID = " + ColumnValue.getValue("B_ID");

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator();
    }

    private String frame6(){
        String query= ROLE + "," +"insert into " +
                "SETTLEMENT (" +
                "SE_T_ID, " +
                "SE_CASH_TYPE, " +
                "SE_CASH_DUE_DATE, " +
                "SE_AMT" +
                ") " +
                "values (" +
                ColumnValue.getValue("SE_T_ID") + ", "+
                ColumnValue.getValue("SE_CASH_TYPE") + ", "+
                ColumnValue.getValue("SE_CASH_DUE_DATE") + ", "+
                ColumnValue.getValue("SE_AMT")+
                ")";

        String query2= ROLE + "," +"update " +
                "CUSTOMER_ACCOUNT " +
                "set " +
                "CA_BAL = " + ColumnValue.getValue("SE_AMT") + " " +
                "where " +
                "CA_ID = " + ColumnValue.getValue("CA_ID");

        String query3= ROLE + "," +"insert into " +
                "CASH_TRANSACTION (" +
                "CT_DTS, " +
                "CT_T_ID, " +
                "CT_AMT, " +
                "CT_NAME" +
                ") " +
                "values (" +
                ColumnValue.getValue("CT_DTS") + ", "+
                ColumnValue.getValue("CT_T_ID") + ", "+
                ColumnValue.getValue("CT_AMT") + ", "+
                ColumnValue.getValue("CT_NAME") +
                ")";
        String query4= ROLE + "," +"select " +
                "CA_BAL " +
                "from " +
                "CUSTOMER_ACCOUNT " +
                "where " +
                "CA_ID = " + ColumnValue.getValue("CT_NAME");

        return query + System.lineSeparator() +
                query2 + System.lineSeparator() +
                query3 + System.lineSeparator() +
                query4 + System.lineSeparator();
    }
}
