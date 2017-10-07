package com.simplexapp.control;

import com.simplexapp.control.util.Change;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fases extends Table {


    public int firstDotOne(){
        int lineAcc= -1;
        for (int i=1; i < matrix[1].length;i++){
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

public int secondDotOne(){
    int colAccpt = -1;
    for(int i=0; i<matrix[1].length;i++){
        if(matrix[linhaFO][i].getCellSup().compareTo(BigDecimal.ZERO)>0){
            colAccpt=i;
        }
    }

    return colAccpt;
}
public int secondDotTwo(int colAccept){
    int lineAccept=-1;
    System.out.println(matrix.length);
    for (int i=0;i<matrix.length;i++){
        if(matrix[i][colML].getCellSup().compareTo(BigDecimal.ZERO)>0){
            return lineAccept=i;
        }
    }
    return lineAccept;
}

public int firstDotThree(int colAccept){
    BigDecimal LessQ= new BigDecimal(Integer.MAX_VALUE);
    BigDecimal ActQ= new BigDecimal(0);
    int lineAccept=-1;
    for(int i=1;i<matrix.length;i++){
        BigDecimal MLSup=matrix[i][colML].getCellSup();
        BigDecimal ColAcceptSup= matrix[i][colAccept].getCellSup();
        if(ColAcceptSup.floatValue()>0 && Change.signalComp(MLSup,ColAcceptSup)){
            ActQ=matrix[i][colML].getCellSup().divide(matrix[i][colAccept].getCellSup(),4,RoundingMode.HALF_UP);
            if(ActQ.compareTo(LessQ)<0){
                LessQ=ActQ;
                lineAccept=i;
                matrix[i][colAccept].setAceptCell(true);
            }
        }
    }
    return lineAccept;
}
    public int SecondDotThree(int colAccpt){
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

    public Fases changePostion( int line, int col){
        Fases newTable= new Fases();
        newTable.setMatrix(matrix);

        int BVar = bVar[line];
        int NbVar =nbVar[col];

        int[] NewBVars = bVar;
        int[] NewNbVars = nbVar;
        NewBVars[line]=NbVar;
        NewNbVars[col]=BVar;

        newTable.setbVar(NewBVars);
        newTable.setNbVar(NewNbVars);

        return newTable;
    }
}
