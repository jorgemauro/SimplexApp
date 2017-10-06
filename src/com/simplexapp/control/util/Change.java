package com.simplexapp.control.util;

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
}
