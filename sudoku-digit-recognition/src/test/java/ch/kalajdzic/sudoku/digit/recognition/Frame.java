package ch.kalajdzic.sudoku.digit.recognition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JPanel implements MouseMotionListener, MouseListener {

    private final List<Integer> color = new ArrayList<>();
    private int lastButton;
    private NeuralNetwork neuralNetwork;

    private Frame() {
        addMouseListener(this);
        addMouseMotionListener(this);
        for (int i = 0; i < 784; i++) {
            color.add(0);
        }

        neuralNetwork = NeuralNetworkGenerator.loadFromFile();
    }

    public static void main(final String[] args) {
        final JFrame f = new JFrame();
        f.setSize(560, 700);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        final Frame frame = new Frame();
        f.add(frame);
        final JPanel bottom = new JPanel();
        final JButton erase = new JButton("ERASE");
        final JButton play = new JButton("PLAY");
        erase.addActionListener((e) -> frame.erase());
        play.addActionListener((e) -> frame.play());
        bottom.add(erase);
        bottom.add(play);
        f.add(bottom, BorderLayout.SOUTH);
        f.setVisible(true);
    }

    private void play() {
        List<Float> floats = new ArrayList<>();
        for (Integer integer : color) {
            floats.add(integer.floatValue());
        }
        System.out.println(neuralNetwork.test(floats));


    }

    @Override
    public void paint(final Graphics g) {
        for (int x = 0; x < 28; x++) {
            for (int y = 0; y < 28; y++) {
                final int rgb = 255 - color.get(y * 28 + x);
                g.setColor(new Color(rgb, rgb, rgb));
                g.fillRect(x * 20, y * 20, 20, 20);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        click(e.getX(), e.getY(), lastButton);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastButton = e.getButton();
        click(e.getX(), e.getY(), e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        click(e.getX(), e.getY(), e.getButton());
    }

    private void click(final int X, final int Y, final int button) {
        if (button == 3) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    draw(x * 20 + X, y * 20 + Y, 0);
                }
            }
            repaint();
        } else if (button == 1) {
//            for (int x = 0; x <= 1; x++) {
//                for (int y = 0; y <= 1; y++) {
//                    if (x != 0 || y != 0) {
//                        draw(x * 20 + X, y * 20 + Y, 20);
//                    } else {
//                        draw(x * 20 + X, y * 20 + Y, 20);
//                    }
//                }
//            }
            draw(X, Y, 255);
            repaint();
        }
    }

    private void draw(final int x, final int y, final int color) {
        final int X = x / 20;
        if (X < 0 || X >= 28) {
            return;
        }
        final int Y = y / 20;
        if (Y < 0 || Y >= 28) {
            return;
        }
        int integer = this.color.get(Y * 28 + X) + color;
        if (integer > 255) {
            integer = 255;
        }
        this.color.set(Y * 28 + X, integer);
    }

    private void erase() {
        for (int i = 0; i < color.size(); i++) {
            color.set(i, 0);
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}