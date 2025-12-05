package com.bringto.sudoku.service;


public class EventData {

    public final Integer oldValue;
    public final Integer newValue;
    public final int row;
    public final int col;

    public EventData(Integer oldValue, Integer newValue, int row, int col) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.row = row;
        this.col = col;
    }
}
