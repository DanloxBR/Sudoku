package com.bringto.sudoku.service;

import com.bringto.sudoku.model.Board;
import com.bringto.sudoku.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {

    private static final int BOARD_LIMIT = 9;

    private final Board board;

    public BoardService(final Map<String, String> gameConfig) {
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpaces() {
        return board.getSpaces();
    }

    public Board getBoard() {
        return board;
    }

    private List<List<Space>> initBoard(final Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();
        for (int row = 0; row < BOARD_LIMIT; row++) {
            List<Space> rowList = new ArrayList<>();
            spaces.add(rowList);
            for (int col = 0; col < BOARD_LIMIT; col++) {
                String key = String.format("%d,%d", row, col);
                String positionConfig = gameConfig.get(key);
                if (positionConfig == null) {
                    rowList.add(new Space(0, false, row, col));
                    continue;
                }
                String[] parts = positionConfig.split(",");
                int expected = Integer.parseInt(parts[0].trim());
                boolean fixed = Boolean.parseBoolean(parts[1].trim());
                rowList.add(new Space(expected, fixed, row, col));
            }
        }
        return spaces;
    }

    public boolean solve() {
        return solveRecursive(board.getSpaces());
    }

    private boolean solveRecursive(List<List<Space>> spaces) {
        for (int row = 0; row < BOARD_LIMIT; row++) {
            for (int col = 0; col < BOARD_LIMIT; col++) {
                Space s = spaces.get(row).get(col);
                if (s.isFixed() || s.getActual() != null) continue;
                for (int num = 1; num <= 9; num++) {
                    if (isValid(spaces, row, col, num)) {
                        s.setActual(num);
                        if (solveRecursive(spaces)) return true;
                        s.clearSpace();
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValid(List<List<Space>> spaces, int row, int col, int num) {
        for (int c = 0; c < BOARD_LIMIT; c++) {
            int value = spaces.get(row).get(c).isFixed() ? spaces.get(row).get(c).getExpected() : spaces.get(row).get(c).getActual();
            if (value == num) return false;
        }
        for (int r = 0; r < BOARD_LIMIT; r++) {
            int value = spaces.get(r).get(col).isFixed() ? spaces.get(r).get(col).getExpected() : spaces.get(r).get(col).getActual();
            if (value == num) return false;
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                int value = spaces.get(r).get(c).isFixed() ? spaces.get(r).get(c).getExpected() : spaces.get(r).get(c).getActual();
                if (value == num) return false;
            }
        }
        return true;
    }
}
