package com.simplexapp.control;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import com.simplexapp.control.util.Change;
public class testeSimplex {

    public static void main(String[] args) {
        TreeMap<Integer,BigDecimal[]> lines = new TreeMap<Integer, BigDecimal[]>();
        BigDecimal[] Fx= new BigDecimal[]{new BigDecimal(0), new BigDecimal(80),new BigDecimal(60)};
        BigDecimal[] x3= new BigDecimal[]{new BigDecimal(-24), new BigDecimal(-4),new BigDecimal(6)};
        BigDecimal[] x4= new BigDecimal[]{new BigDecimal(16), new BigDecimal(4),new BigDecimal(2)};
        BigDecimal[] x5= new BigDecimal[]{new BigDecimal(3), new BigDecimal(0),new BigDecimal(1)};

        lines.put(0,Fx);
        lines.put(1,x3);
        lines.put(2,x4);
        lines.put(3,x5);

        Fases T= new Fases();
        Table Aux= new Table(lines,3);
        T.setMatrix(Aux.getMatrix());




    }
}
