package ge.edu.sangu;

import javax.swing.*;
import java.util.HashMap;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.Map;

/**
 * @author vakho
 */
public class VisualKeyLogger implements NativeKeyListener {
	private HashMap<String, Integer> keyCounter = new HashMap<>();
	private KeyboardPanel keyboardPanel;
	private JFrame frame;

    public void init() {
        // Створюємо та налаштовуємо вікно
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Keyboard Heat Map");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            keyboardPanel = new KeyboardPanel(keyCounter);
            frame.add(keyboardPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printKeyCounts() {
        clearScreen();
        for (Map.Entry<String, Integer> entry : keyCounter.entrySet()) {
            System.out.println("Key: \"" + entry.getKey() + "\", Value: " + entry.getValue());
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
		
		char keyChar = e.getKeyChar();
		System.out.println((int)keyChar);
		if (((int)keyChar >= 44 && (int)keyChar <= 122) || ((int)keyChar >= 1072 && (int)keyChar <=1169)) {

			String keyString = String.valueOf(keyChar).toUpperCase();

        	keyCounter.put(keyString,
        	        keyCounter.getOrDefault(keyString, 0) + 1);
			keyboardPanel.setMaxPressing(keyCounter.get(keyString));

        	SwingUtilities.invokeLater(() -> {
        	    if (keyboardPanel != null) {
        	        keyboardPanel.updateKeyCounter(keyCounter);
        	    }
        	});

        	printKeyCounts();
		}
    }

    public void nativeKeyPressed(NativeKeyEvent e) { }

    public void nativeKeyReleased(NativeKeyEvent e) { }
}
