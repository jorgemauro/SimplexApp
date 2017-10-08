package com.simplexapp.control;

import com.simplexapp.control.util.Change;

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
        this.bVar=new int[fx.size()];
        this.bVar[0]= linhaFO;

        int countp=0;
        for (Map.Entry<Integer,BigDecimal[]>pair: fx.entrySet()){
            this.bVar[countp]=pair.getKey();
            countp++;
        }

        this.nbVar=new int[nbVarq];
        for(int i=0; i<nbVarq;i++){
            this.nbVar[i]=i;
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
        for(int i=0;i<matrix.length; i++){
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
        Table T=new Table();
        T.setMatrix(matrix,bVar,nbVar);
        for(int l=0;l<(matrix.length);l++){
            for (int h=0;h<matrix[1].length;h++){
                cell=matrix[l][h];
                if(cell.getCellInf()==null) {
                    for (int k=0;k<matrix.length;k++) {
                        if (matrix[k][h].isChkCellSup()) {
                            chkCellSup = matrix[k][h].getCellSup();
                        }
                    }
                    for (int k=0;k<matrix[1].length;k++) {
                        if (matrix[l][k].isChkCellInf()) {
                            chkCellInf = matrix[l][k].getCellInf();
                        }
                    }
                    if(l==3){
                        Change.PrintTable(T);
                        System.out.println("LIne: "+l);
                        System.out.println("Col "+h);
                        System.out.println("sup: "+chkCellSup.toString());
                        System.out.println("inf: "+chkCellInf.toString());
                    }
                    cell.setCellInf(chkCellSup.stripTrailingZeros().multiply(chkCellInf.stripTrailingZeros()).setScale(4, RoundingMode.HALF_UP));

                }

            }
        }
    }
    public Boolean MlNegative(){
        Boolean ans=false;
        Table t=new Table();
        t.setMatrix(matrix,bVar,nbVar);
        for(int i=1;i<matrix.length;i++){
            if(matrix[i][colML].getCellSup().compareTo(BigDecimal.ZERO)<0){
                ans=true;
                break;
            }
        }
        return ans;
    }
public void infForSup( int colAccept, int lineAccept){
        Cell[][] reMatrix=new Cell[matrix.length][matrix[1].length];
        for(int i =0;i<matrix.length;i++){
            for(int j=0;j<matrix[1].length;j++){
                reMatrix[i][j]= new Cell();
            }
        }

    for(int j=0;j<matrix[1].length;j++){
        reMatrix[lineAccept][j].setCellSup(matrix[lineAccept][j].getCellInf());
    }
    for(int i=0;i<matrix.length;i++){
            reMatrix[i][colAccept].setCellSup(matrix[i][colAccept].getCellInf());
        }

    matrix=reMatrix;

}

public void SumNotSupAndInf(Table T,int colAccept, int lineAccept){
    Cell[][] matrix1 = T.getMatrix();
    for (int i=0;i<matrix.length;i++){
        for(int j=0;j<matrix[1].length;j++){
            if(i!=lineAccept && j!=colAccept){
                matrix[i][j].setCellSup(matrix1[i][j].getCellSup().stripTrailingZeros().add(matrix1[i][j].getCellInf()));
            }
        }
    }
}
    public Cell[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Cell[][] matrix, int[]bVar,int[] nbVar)  {
        this.matrix = matrix;
        this.setNbVar(nbVar);
        this.setbVar(bVar);
    }

    public void setbVar(int[] bVar) {
        this.bVar = bVar;
    }

    public void setNbVar(int[] nbVar) {
        this.nbVar = nbVar;
    }

    public int[] getbVar() {
        return bVar;
    }

    public int[] getNbVar() {
        return nbVar;
    }
}



