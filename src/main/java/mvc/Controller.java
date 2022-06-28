package mvc;

import javax.swing.*;
import java.awt.event.*;

public class Controller {
    private final View view;
    private final Model model;
    private Timer resizeTimer;
    private Timer scrollUpTimer;
    private Timer scrollDownTimer;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        prepareGui();
    }

    private void prepareGui() {
        resizeTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.processResized(view);
            }
        });
        resizeTimer.setRepeats(false);

        scrollUpTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.processBackward(view);
            }
        });
        scrollUpTimer.setRepeats(false);

        scrollDownTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.processForward(view);
            }
        });
        scrollDownTimer.setRepeats(false);

        view.setSize(400, 400);
        view.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    scrollUpTimer.restart();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    scrollDownTimer.restart();
                }
            }
        });
        view.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeTimer.restart();
            }
        });
        view.setVisible(true);
    }
}
