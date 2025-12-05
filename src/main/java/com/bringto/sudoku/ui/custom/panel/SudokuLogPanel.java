package com.bringto.sudoku.ui.custom.panel;

import com.bringto.sudoku.service.*;

import javax.swing.*;
import java.awt.*;

public class SudokuLogPanel extends JPanel implements EventListener {

    private final JTextArea logArea = new JTextArea();

    public SudokuLogPanel() {
        this.setLayout(new BorderLayout());

        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(logArea);
        this.add(scrollPane, BorderLayout.CENTER);

        NotifierService notifier = NotifierService.getInstance();
        notifier.subscribe(EventEnum.CHANGE_VALUE, this);
        notifier.subscribe(EventEnum.GAME_COMPLETE, this);
    }

    @Override
    public void update(EventEnum eventType, EventData data) {
        SwingUtilities.invokeLater(() -> {
            switch (eventType) {
                case CHANGE_VALUE:
                    if (data != null) {
                        logArea.append(String.format("Célula [%d,%d] mudou de %s para %s\n",
                                data.row,
                                data.col,
                                data.oldValue != null ? data.oldValue.toString() : "vazio",
                                data.newValue != null ? data.newValue.toString() : "vazio"));
                        logArea.setCaretPosition(logArea.getDocument().getLength());
                    }
                    break;
                case GAME_COMPLETE:
                    logArea.append(" Sudoku completado! Parabéns! ");
                    logArea.setCaretPosition(logArea.getDocument().getLength());
                    break;
                default:
                    break;
            }
        });
    }
}
