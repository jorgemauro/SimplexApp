package com.simplexapp.control;

import java.math.BigDecimal;
/*
* Estrutura da celula da matriz
*
* */
public class Cell {

    private Boolean AceptCell;
    private Boolean ChkCellSup;
    private Boolean ChkCellInf;
    private BigDecimal CellSup;
    private BigDecimal CellInf;

    public Cell(BigDecimal CellSup,BigDecimal CellInf){
    this.CellSup=CellSup;
    this.CellInf=CellInf;
    this.ChkCellInf= false;
    this.ChkCellSup=false;
    this.AceptCell=false;
    }
    public Cell(){

    }
    //is
    public Boolean isAceptCell() {
        return AceptCell;
    }

    public Boolean isChkCellInf() {
        return ChkCellInf;
    }

    public Boolean isChkCellSup() {
        return ChkCellSup;
    }
//get
    public BigDecimal getCellInf() {
        return CellInf;
    }

    public BigDecimal getCellSup() {
        return CellSup;
    }
//set

    public void setAceptCell(Boolean aceptCell) {

    }

    public void setCellInf(BigDecimal cellInf) {
        CellInf = cellInf;
    }

    public void setCellSup(BigDecimal cellSup) {
        CellSup = cellSup;
    }

    public void setChkCellInf(Boolean chkCellInf) {
        ChkCellInf = chkCellInf;
    }

    public void setChkCellSup(Boolean chkCellSup) {
        ChkCellSup = chkCellSup;
    }
}
