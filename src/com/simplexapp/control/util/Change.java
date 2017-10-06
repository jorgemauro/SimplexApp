package com.simplexapp.control.util;

import com.simplexapp.control.Cell;
import com.simplexapp.control.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Change {

    public  static BigDecimal Inverse(BigDecimal val){
        BigDecimal Ans;
        BigDecimal one=new BigDecimal(1);
        Ans = one.divide(val,4, RoundingMode.HALF_UP);

        return Ans;
    }

    public static boolean signalComp(BigDecimal val1, BigDecimal val2){
        if(val1.signum()<0&&val2.signum()<0){
            return true;
        }else if(val1.signum()<0&&val2.signum()<0){
            return true;
        }else if(val1.signum()==0&&val2.signum()==0){
            return true;
        }
        return false;
    }
    public static void PrintCell(Cell C){
        System.out.print(" Sup : "+ C.getCellSup()+ "/ inf: "+ C.getCellInf()+ " " );
    }
    public static void PrintTable(Table T){
        for (int i=0;i< T.getMatrix().length;i++){
            for(int j=0;j<T.getMatrix()[1].length;j++){
                PrintCell(T.getMatrix()[i][j]);
            }
            System.out.println(" ");
        }
    }
}
