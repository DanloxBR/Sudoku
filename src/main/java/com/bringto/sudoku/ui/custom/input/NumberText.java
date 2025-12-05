package com.bringto.sudoku.ui.custom.input;

import com.bringto.sudoku.model.Space;
import com.bringto.sudoku.service.BoardService;
import com.bringto.sudoku.service.EventData;
import com.bringto.sudoku.service.EventEnum;
import com.bringto.sudoku.service.NotifierService;

import javax.swing.*;
import java.awt.*;

public class NumberText extends JTextField {

    private final Space space;
    private final BoardService boardService;

    public NumberText(Space space, BoardService boardService) {
        this.space = space;
        this.boardService = boardService;

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Monospaced", Font.BOLD, 18));

        if (space.isFixed()) {
            setText(String.valueOf(space.getExpected()));
            setEditable(false);
            setForeground(Color.BLACK);
        } else {
            setText(space.getActual() != null ? String.valueOf(space.getActual()) : "");
            setEditable(true);
            setForeground(Color.BLUE);
        }

        getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { notifyChange(); }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { notifyChange(); }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { notifyChange(); }
        });
    }

    public Space getSpace() { return space; }

    private void notifyChange() {
        String text = getText();

        if (text.isEmpty()) {
            space.setActual(null);
            setBackground(Color.WHITE);
            NotifierService.getInstance().notify(
                    EventEnum.CHANGE_VALUE,
                    new EventData(null, null, space.getRow(), space.getCol())
            );
            return;
        }

        try {
            int value = Integer.parseInt(text);
            space.setActual(value);

            NotifierService.getInstance().notify(
                    EventEnum.CHANGE_VALUE,
                    new EventData(value, value, space.getRow(), space.getCol())
            );

        } catch (NumberFormatException e) {
            SwingUtilities.invokeLater(() -> setText(""));
        }
    }

    public void showCorrect() { setBackground(Color.GREEN.brighter()); }

    public void showError() { setBackground(Color.RED.brighter()); }

    public void showVictoryMessage() {
        setBackground(Color.YELLOW);
        setText("Ganhou");
    }
}
