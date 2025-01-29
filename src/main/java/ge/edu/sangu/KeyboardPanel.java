package ge.edu.sangu;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class KeyboardPanel extends JPanel {
    private HashMap<String, Integer> keyCounter;
    private final int KEY_WIDTH = 40;
    private final int KEY_HEIGHT = 40;
    private final int SPACING = 5;

    private final int STEP_AFTER_HEADER = 10;
    private final int STEP_AFTER_LAYOUT = 200;

    public KeyboardPanel(HashMap<String, Integer> keyCounter) {
        this.keyCounter = keyCounter;
        setPreferredSize(new Dimension(650, 650));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));

        int startX = 20;
        int startY = 20;

		// Render Latin Layout
        printHeader("QWERTY - ANSI", startX, startY, Color.RED, g2d);
        startY += STEP_AFTER_HEADER;
        renderAnsiLayout(startX, startY, KeyboardLayouts.QWERTY_ANSI, g2d);
        startY += STEP_AFTER_LAYOUT;

		// Render Manzhara Layout
        printHeader("Manzhara - ISO", startX, startY, Color.RED, g2d);
        startY += STEP_AFTER_HEADER;
        renderIsoLayout(startX, startY, KeyboardLayouts.MANZHARA_ISO, g2d);
        startY += STEP_AFTER_LAYOUT;

        // Render Default Cyrilic Layout
        printHeader("ЙЦУКЕН - ANSI", startX, startY, Color.RED, g2d);
        startY += STEP_AFTER_HEADER;
        renderAnsiLayout(startX, startY, KeyboardLayouts.STANDART_UA_ANSI, g2d);
        startY += STEP_AFTER_LAYOUT;
    }

    private void printHeader(String text, int startX, int startY, Color color, Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawString(text, startX, startY);
    }

    private void renderAnsiLayout(int startX, int startY, String[][] keyboardLayout, Graphics2D g2d) {
        for (int row = 0; row < keyboardLayout.length; row++) {
            int currentX = startX + (row * 20); // Зміщення кожного ряду
            for (int col = 0; col < keyboardLayout[row].length; col++) {
                String key = keyboardLayout[row][col];
                int count = keyCounter.getOrDefault(key, 0);

                // Визначаємо колір на основі частоти натискань
                Color keyColor = getHeatMapColor(count);

                // Малюємо клавішу
                g2d.setColor(keyColor);
                g2d.fillRect(currentX + (col * (KEY_WIDTH + SPACING)),
                        startY + (row * (KEY_HEIGHT + SPACING)),
                        KEY_WIDTH, KEY_HEIGHT);

                // Малюємо контур
                g2d.setColor(Color.BLACK);
                g2d.drawRect(currentX + (col * (KEY_WIDTH + SPACING)),
                        startY + (row * (KEY_HEIGHT + SPACING)),
                        KEY_WIDTH, KEY_HEIGHT);

                // Малюємо текст
                g2d.setColor(Color.BLACK);
                g2d.drawString(key,
                        currentX + (col * (KEY_WIDTH + SPACING)) + 15,
                        startY + (row * (KEY_HEIGHT + SPACING)) + 25);
            }
        }
    }

    private void renderIsoLayout(int startX, int startY, String[][] keyboardLayout, Graphics2D g2d) {
        for (int row = 0; row < keyboardLayout.length; row++) {
            int currentX = startX + (row * 20); // Зміщення кожного ряду
            if (row > 2)
                currentX -= 40;
            for (int col = 0; col < keyboardLayout[row].length; col++) {
                String key = keyboardLayout[row][col];
                int count = keyCounter.getOrDefault(key, 0);

                // Визначаємо колір на основі частоти натискань
                Color keyColor = getHeatMapColor(count);

                // Малюємо клавішу
                g2d.setColor(keyColor);
                g2d.fillRect(currentX + (col * (KEY_WIDTH + SPACING)),
                        startY + (row * (KEY_HEIGHT + SPACING)),
                        KEY_WIDTH, KEY_HEIGHT);

                // Малюємо контур
                g2d.setColor(Color.BLACK);
                g2d.drawRect(currentX + (col * (KEY_WIDTH + SPACING)),
                        startY + (row * (KEY_HEIGHT + SPACING)),
                        KEY_WIDTH, KEY_HEIGHT);

                // Малюємо текст
                g2d.setColor(Color.BLACK);
                g2d.drawString(key, currentX + (col * (KEY_WIDTH + SPACING)) + 15,
                        startY + (row * (KEY_HEIGHT + SPACING)) + 25);
            }
        }
    }

    private Color getHeatMapColor(int count) {
        // Визначаємо колір на основі кількості натискань
        if (count == 0) return Color.WHITE;
        float intensity = Math.min(1.0f, count / 500.0f); // Max value when 500 pressed
        return new Color(1.0f, 1.0f - intensity, 1.0f - intensity);
    }

    public void updateKeyCounter(HashMap<String, Integer> keyCounter) {
        this.keyCounter = keyCounter;
        repaint(); // Rewrite panel
    }
}


