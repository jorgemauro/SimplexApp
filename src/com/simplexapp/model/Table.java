package com.simplexapp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void mulLineInverse(int lineAccept, BigDecimal inversAccept){
        Cell cell;
        for(int i=0;i<matrix[1].length; i++){
            cell=matrix[lineAccept][i];
            if(cell.getCellInf()==null){
                cell.setCellInf(cell.getCellSup().stripTrailingZeros().multiply(inversAccept.setScale(4, RoundingMode.HALF_UP)));
            }
        }

    }

    public void mulColInverse(int colAccept, BigDecimal inversAccept){
        Cell cell;
        inversAccept=inversAccept.multiply(new BigDecimal(-1).setScale(4,RoundingMode.HALF_UP));
        for(int i=0;i<matrix[1].length; i++){
            cell=matrix[i][colAccept];
            if(cell.getCellInf()==null){
                cell.setCellInf(cell.getCellSup().stripTrailingZeros().multiply(inversAccept.setScale(4,RoundingMode.HALF_UP)));
            }
        }

    }

    public void chkCellColInf(int colAccept){
        Cell cell;
        for(int i=0;i<matrix[i].length;i++){
            cell=matrix[i][colAccept];
            cell.setChkCellInf(true);
        }
    }
    public void chkCellLineSup(int lineAccept){
        Cell cell;
        for(int i=0;i<matrix[i].length;i++){
            cell=matrix[lineAccept][i];
            cell.setChkCellSup(true);
        }
    }

    public void mulSupInf(){
        Cell cell;
        BigDecimal chkCellSup = null;
        BigDecimal chkCellInf = null;
        for(int i=0; i<matrix[1].length;i++){
            for(int j=0;j<matrix[1].length;i++){
                cell=matrix[i][j];
                if(cell.getCellInf()==null) {
                    for (int k=0;k<matrix.length;k++)
                        if (matrix[k][j].isChkCellSup())
                            chkCellSup=matrix[k][j].getCellSup();

                    for (int k=0;k<matrix.length;k++)
                        if (matrix[i][k].isChkCellInf())
                            chkCellInf=matrix[i][k].getCellInf();

                    cell.setCellInf(chkCellSup.stripTrailingZeros().multiply(chkCellInf.stripTrailingZeros()).setScale(4,RoundingMode.HALF_UP));
                }
                }
            }
        }

    }



