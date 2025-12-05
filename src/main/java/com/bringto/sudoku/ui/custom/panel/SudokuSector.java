package com.bringto.sudoku.ui.custom.panel;

import com.bringto.sudoku.ui.custom.input.NumberText;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SudokuSector extends JPanel {

    public SudokuSector(List<NumberText> fields) {
        setBorder(new LineBorder(Color.BLACK, 2));
        setLayout(new GridLayout(3, 3));

        for (NumberText f : fields) {
            add(f);
        }
    }
}
