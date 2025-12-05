package com.bringto.sudoku.ui.custom.frame;

import com.bringto.sudoku.service.BoardService;
import com.bringto.sudoku.service.EventEnum;
import com.bringto.sudoku.service.NotifierService;
import com.bringto.sudoku.ui.custom.panel.SudokuBoardPanel;
import com.bringto.sudoku.ui.custom.panel.SudokuLogPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame extends JFrame {

    public MainFrame(Map<String, String> gameConfig) {
        this.setTitle("Sudoku");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BoardService boardService = new BoardService(gameConfig);

        SudokuLogPanel logPanel = new SudokuLogPanel();
        SudokuBoardPanel boardPanel = new SudokuBoardPanel(boardService);

        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                boardPanel,
                logPanel
        );

        split.setResizeWeight(0.8);
        split.setDividerLocation(450);
        split.setBorder(null);

        this.add(split, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        SwingUtilities.invokeLater(this::iniciarAutomatico);
    }

    private void iniciarAutomatico() {
        NotifierService.getInstance().notify(EventEnum.NEW_GAME, null);

        int velocidade = 150;

        javax.swing.Timer timer = new javax.swing.Timer(velocidade, e -> {
            NotifierService.getInstance().notify(EventEnum.SOLVE_STEP, null);
        });

        timer.start();
    }
}
