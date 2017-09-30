package com.simplexapp.model;

import java.math.BigDecimal;
import java.util.Map;

public class Table {

    public int[] bVar;
    public int[] nbVar;
    public Cell[][] matrix;

    public static final int colML=0;
    public static final int linhaFO=0;

    public Table(Map<Integer,BigDecimal[]>fx,int nbVarq){
        bVar=new int[fx.size()];
        bVar[0]= linhaFO;

        int countp=0;
        for (Map.Entry<Integer,BigDecimal[]>pair: fx.entrySet()){
            bVar[countp]=pair.getKey();
            countp++;
        }

        nbVar=new int[nbVarq];
        for(int i=0; i<nbVarq;i++){
            nbVar[i]=i;
        }

        matrix=new Cell[fx.size()][nbVarq];

        for (int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                matrix[i][j]=new Cell(fx.get(bVar[i])[j],null);
            }
        }
    }
    public Table(){

    }
}
