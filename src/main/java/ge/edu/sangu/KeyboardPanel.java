package ge.edu.sangu;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class KeyboardPanel extends JPanel {
    private Map<String, Integer> keyCounter;
    private final int KEY_WIDTH = 40;
    private final int KEY_HEIGHT = 40;
    private final int SPACING = 5;

    public KeyboardPanel(Map<String, Integer> keyCounter) {
        this.keyCounter = keyCounter;
        setPreferredSize(new Dimension(610, 410));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Малюємо клавіші
        String[][] keyboardStandartEnglishLayout = {
            {"1","2","3","4","5","6","7","8","9","0","-","="},
            {"Q","W","E","R","T","Y","U","I","O","P","[","]"},
            {"A","S","D","F","G","H","J","K","L",";","'"},
            {"Z","X","C","V","B","N","M",",",".","/"}
        };
        String[][] keyboardStandartManzharaLayout = {
            {"1","2","3","4","5","6","7","8","9","0","-","="},
            {"Й","Я","У","К","Е","Н","Т","С","Г","Ж","Х","Є"},
            {"Б","І","В","А","И","Р","О","Л","Д","З","Ю","Ґ"},
            {"Ф","Ц","Ч","Ш","М","П","Щ","Ь",",",".","Ї"}
        };

        int startX = 20;
        int startY = 20;

        for (int row = 0; row < keyboardStandartEnglishLayout.length; row++) {
            int currentX = startX + (row * 20); // Зміщення кожного ряду
            for (int col = 0; col < keyboardStandartEnglishLayout[row].length; col++) {
                String key = keyboardStandartEnglishLayout[row][col];
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

        startX = 20;
        startY = 220;

        for (int row = 0; row < keyboardStandartManzharaLayout.length; row++) {
            int currentX = startX + (row * 20); // Зміщення кожного ряду
			if (row > 2) currentX -= 40; 
            for (int col = 0; col < keyboardStandartManzharaLayout[row].length; col++) {
                String key = keyboardStandartManzharaLayout[row][col];
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

    private Color getHeatMapColor(int count) {
        // Визначаємо колір на основі кількості натискань
        if (count == 0) return Color.WHITE;
        float intensity = Math.min(1.0f, count / 15000.0f); // Максимум при 200 натисканнях
        return new Color(1.0f, 1.0f - intensity, 1.0f - intensity);
    }

    public void updateKeyCounter(Map<String, Integer> keyCounter) {
        this.keyCounter = keyCounter;
        repaint(); // Перемалювати панель
    }
}


