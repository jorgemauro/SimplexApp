package com.simplexapp.control;

import com.simplexapp.control.util.Change;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe responsavel pelas fases do Simplez.
 */
public class Fases extends Table {
    /**
     * função responsavel pela avaliação da coluna dos membros livres
     * para encontrar a linha aceita.
     * @return A linha aceita na coluna livre.
     */
    public int firstDotOne() {
        int lineAcc = -1;
        for (int i = 1; i < matrix[1].length; i++) {
            if (matrix[i][colML].getCellSup().compareTo(BigDecimal.ZERO) < 0) {
                lineAcc = i;
                break;
            }

        }
        return lineAcc;
    }

    /**
     * função responsavel por encontrar a coluna do permitida a partir da linha.
     * @param line linha em que buscaremos a coluna permitida.
     * @return coluna permitida
     */
    public int firstDotTwo(int line){
        int colAcc = -1;
        for (int i=1; i < matrix[1].length; i++){
            if(matrix[line][i].getCellSup().compareTo(BigDecimal.ZERO)<0){
                colAcc=i;
                break;
            }
        }
        return colAcc;
    }

    /**
     * parte três da primeira  encontra a linha aonde
     * tem  menor quociente dos memrbos livre e tem o mesmo sinal no denominado e no numerador possuem o mesmo sinal.
     * @param colAccept coluna aceita
     * @return a linha do menor quociente
     */
    public int firstDotThree(int colAccept){
        BigDecimal LessQ= new BigDecimal(Integer.MAX_VALUE);
        BigDecimal ActQ= new BigDecimal(0);
        int lineAccept=-1;

            for (int i = 1; i < matrix.length; i++) {
                BigDecimal MLSup = matrix[i][colML].getCellSup();
                BigDecimal colAcceptSup = matrix[i][colAccept].getCellSup();

                if (colAcceptSup.compareTo(BigDecimal.ZERO) > 0 && Change.signalComp(MLSup, colAcceptSup)) {
                    ActQ = (matrix[i][colML].getCellSup().divide(matrix[i][colAccept].getCellSup(), 4, RoundingMode.HALF_UP));

                    Boolean one=colAcceptSup.compareTo(BigDecimal.ZERO) > 0;

                    if (ActQ.compareTo(LessQ) < 0) {
                        LessQ = ActQ;
                        lineAccept = i;
                        matrix[i][colAccept].setAceptCell(true);
                    }
                }
            }

        System.out.println("line"  + lineAccept) ;
        return lineAccept;
    }
    /**
     * verifica a coluna do elemento positivo
     * @return coluna do elemento positivo
     */
    public int secondDotOne(){
        int colAccpt = -1;
        for(int i=0; i<matrix[1].length;i++){
            if(matrix[linhaFO][i].getCellSup().compareTo(BigDecimal.ZERO)>0){
                colAccpt=i;
            }
        }

        return colAccpt;
    }

    /**
     *
     * segunda fase da segunda parte aonde se busca a linha permitida na coluna escolhida anterior mente
     * @param colAccept coluna permitida
     * @return linha permitida
     */
    public int secondDotTwo(int colAccept){
        int lineAccept=-1;
        for (int i=0;i<matrix.length;i++){
            if(matrix[i][colML].getCellSup().compareTo(BigDecimal.ZERO)>0){
                return lineAccept=i;
            }
        }
        return lineAccept;
    }

    /**
     * verifica se a linha escolhida possui o menor quociente entre os membros livre
     * @param colAccpt coluna permitida
     * @return linha aonde possui a menor quociente
     */
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

    /**
     * Troca entre a variavel basica e a variavel não basica
     * @param line linha do elemento encontrado na primeira fase
     * @param col coluna do elemento encontrado na primeira fase
     * @return fase com as variaves trocadas
     */
    public Fases changePostion( int line, int col){
        Fases newTable= new Fases();
        newTable.setMatrix(matrix,bVar,nbVar);
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
