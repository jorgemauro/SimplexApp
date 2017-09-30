package com.simplexapp.model;

import com.simplexapp.model.util.Change;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fases extends Table {


    public int firstDotOne(){
        int lineAcc= -1;
        for (int i=1; i < matrix.length;i++){
            if(matrix[i][colML].getCellSup().compareTo(BigDecimal.ZERO)<0){
                lineAcc=i;
                break;
            }
        }
        return lineAcc;
    }

    public int firstDotTwo(int line){
        int colAcc = -1;
        for (int i=1; i < matrix.length;i++){
            if(matrix[line][i].getCellSup().compareTo(BigDecimal.ZERO)<0){
                colAcc=i;
                break;
            }
        }
        return colAcc;
    }
public int firstDotThree(int colAccpt){
        int lineAccept=-1;
        BigDecimal lessQ= new BigDecimal(Integer.MAX_VALUE);
        BigDecimal actQ= new BigDecimal(0);

    for (int i=0;i<matrix.length;i++){
        BigDecimal mLchoose=matrix[i][colML].getCellSup();
        BigDecimal colAcceptChoose=matrix[i][colAccpt].getCellSup();

        if(Change.signalComp(mLchoose,colAcceptChoose) && colAcceptChoose.compareTo(BigDecimal.ZERO)>0){
            actQ=matrix[i][colML].getCellSup().divide(matrix[i][colAccpt].getCellSup(),4, RoundingMode.HALF_UP);
            if(actQ.compareTo(lessQ)<0){
                lessQ=actQ;
                lineAccept=i;
                matrix[i][colAccpt].setAceptCell(true);
            }
        }
    }
    return lineAccept;

}

public int secondDotOne(){
    int colAccpt = -1;
    for(int i=0; i<matrix[1].length;i++){
        if(matrix[linhaFO][i].getCellSup().compareTo(BigDecimal.ZERO)>0){
            colAccpt=i;
        }
    }

    return colAccpt;
}
}
