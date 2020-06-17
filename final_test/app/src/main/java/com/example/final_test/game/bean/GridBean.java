package com.example.final_test.game.bean;

/**
 * Created by ic on 2017/11/21.
 */

public class GridBean {

    private int   height = 1080; //手机高
    private int   width = 721; //手机宽

    private int offset = 30 ;       //偏移量，就是间距  上 左 右 间距一样
    private int gridColumnSize;     //每列格子的数量
    private int gridRowSize = 30;   //每行格子的数量
    private int lineLength;         //线(行)的长度
    private int columnLength;       //线(列)的长度
    private int gridWidth;          //格子宽

    public GridBean() {
        lineLength     = width - offset*2;
        columnLength   = height - offset*3;
        gridWidth      = lineLength / gridRowSize;// 格子宽
        gridColumnSize = columnLength/gridWidth;//定义列格子数
    }

    public int getGridColumnSize() {
        return gridColumnSize;
    }

    public void setGridColumnSize(int gridColumnSize) {
        this.gridColumnSize = gridColumnSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getGridSize() {
        return gridRowSize;
    }

    public void setGridSize(int gridSize) {
        this.gridRowSize = gridSize;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public void setColumnLength(int columnLength) {
        this.columnLength = columnLength;
    }

    public int getColumnLength() {
        return columnLength;
    }
}
