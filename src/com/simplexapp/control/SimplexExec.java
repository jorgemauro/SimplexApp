package com.simplexapp.control;

import com.simplexapp.control.util.Change;

import java.math.BigDecimal;

public class SimplexExec {
    Fases T;

    public SimplexExec(){
        this.T=new Fases();
    }

    public static void ExcFirst(Fases T){
        int colAccept=0;
        int lineAccept=0;
        int TheLine=0;

                lineAccept=T.firstDotOne();
                if(lineAccept>0){
                    colAccept=T.firstDotTwo(lineAccept);
                    if(colAccept>0){
                        TheLine=T.firstDotThree(colAccept);
                        /*System.out.println("fim primeira fase");
                        Change.PrintTable(T);
                        System.out.println("--------------------------------------------------------------------");
                       */
                        SimplexExec.ChangeExec(TheLine,colAccept,T);

                    }else{
                        //Sem solução
                    }
                }

    }
    public static void ChangeExec(int TheLine,int colAccept, Fases T ){
        Fases NewT;
        Cell cellAccept=T.getMatrix()[TheLine][colAccept];
        BigDecimal invCellAccept= Change.Inverse(cellAccept.getCellSup());
        cellAccept.setCellInf(invCellAccept);
        T.mulLineInverse(TheLine,invCellAccept);
        T.mulColInverse(colAccept,invCellAccept);
        T.chkCellLineSup(TheLine);
        T.chkCellColInf(colAccept);
        T.mulSupInf();
        NewT=T.changePostion(TheLine,colAccept);

        System.out.println("1");
        Change.PrintTable(NewT);

        System.out.println("--------------------------------------------------------------------");
        NewT.infForSup(colAccept,TheLine);

        System.out.println("2");
        Change.PrintTable(NewT);

        System.out.println("--------------------------------------------------------------------");
        NewT.SumNotSupAndInf(T,colAccept,TheLine);


        System.out.println("3");
        Change.PrintTable(NewT);

        System.out.println("--------------------------------------------------------------------");

        if(NewT.MlNegative()){
            SimplexExec.ExcFirst(NewT);
        }else {
            SimplexExec.ExcSecond(NewT);
        }

    }

    private static void ExcSecond(Fases T) {
        int colAccept=0;
        int lineAccept=0;
        int TheLine=0;

        lineAccept=T.secondDotOne();
        if(lineAccept>0){
            colAccept=T.secondDotTwo(lineAccept);
            if(colAccept>0){
                TheLine=T.SecondDotThree(colAccept);
                SimplexExec.ChangeExec(TheLine,colAccept,T);

            }else{
                System.out.println("não existe solução");
            }
        }else{
            System.out.println("Solução encontrada");
            System.out.println("fim segunda");
            Change.PrintTable(T);
            System.out.println("--------------------------------------------------------------------");
        }
    }
}
