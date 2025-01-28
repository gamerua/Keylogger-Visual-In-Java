package ge.edu.sangu;

import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.im.InputContext;
import java.util.Locale;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author vakho
 */
public class KeyLogger implements NativeKeyListener {

	private static final Path file = Paths.get("keys.txt");
	private static final Logger logger = LoggerFactory.getLogger(KeyLogger.class);
	private static final InputContext inputContext = InputContext.getInstance();
	private static Map<String, Integer> keyCounter = new HashMap<>();
	private static KeyboardPanel keyboardPanel;
	private static JFrame frame;


	public static void main(String[] args) {

		logger.info("Key logger has been started");

		// задаим Значення
		keyCounter.put("А", 17067);  
		keyCounter.put("Б", 4088);  
		keyCounter.put("В", 11221);  
		keyCounter.put("Г", 2907);  
		keyCounter.put("Ґ", 0);  
		keyCounter.put("Д", 6995);  
		keyCounter.put("Е", 10605);  
		keyCounter.put("Є", 736);  
		keyCounter.put("Ж", 2022);  
		keyCounter.put("З", 4947);  
		keyCounter.put("И", 13193);  
		keyCounter.put("І", 10678);  
		keyCounter.put("Ї", 1107);  
		keyCounter.put("Й", 2417);  
		keyCounter.put("К", 6309);  
		keyCounter.put("Л", 7841);  
		keyCounter.put("М", 6158);  
		keyCounter.put("Н", 12295);  
		keyCounter.put("О", 12295);  
		keyCounter.put("П", 5356);  
		keyCounter.put("Р", 8091);  
		keyCounter.put("С", 7570);  
		keyCounter.put("Т", 9852);  
		keyCounter.put("У", 6692);  
		keyCounter.put("Ф", 216);  
		keyCounter.put("Х", 1919);  
		keyCounter.put("Ц", 1457);  
		keyCounter.put("Ч", 2592);  
		keyCounter.put("Ш", 2169);  
		keyCounter.put("Щ", 1167);  
		keyCounter.put("Ь", 3683);  
		keyCounter.put("Ю", 1817);  
		keyCounter.put("Я", 3785);  
		keyCounter.put(",", 2875);  
		keyCounter.put(".", 5603);  

		init();

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

            try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			logger.error(e.getMessage(), e);
			System.exit(-1);
		}

		GlobalScreen.addNativeKeyListener(new KeyLogger());
	}

	private static void init() {
		
		// Get the logger for "org.jnativehook" and set the level to warning.
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);

		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
}

private String getCurrentLayoutFromInputContext() {
    Locale locale = inputContext.getLocale();
    // logger.debug("Current InputContext locale: {}", locale);
    return locale != null ? locale.getLanguage() : "unknown";
}

private String getSystemLanguage() {
    String language = System.getProperty("user.language");
    // logger.debug("System language: {}", language);
    return language;
}

private void logLayoutInfo() {
    // logger.info("Current keyboard layout - InputContext: {}, System: {}", 
    //     getCurrentLayoutFromInputContext(),
    //     getSystemLanguage());
}

public void nativeKeyPressed(NativeKeyEvent e) {
// logLayoutInfo();
// String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
// char keyCode = e.getKeyChar();
// 		System.out.println(keyCode);

		
// 		try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
// 				StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {
			
// 			if (keyText.length() > 1) {
// 				writer.print("[" + keyText + "]");
// 			} else {
// 				writer.print(keyText);
// 				// System.out.println(keyText);
// 				// System.out.println(keyCounter.get(keyText));
// 				if (keyCounter.get(keyText) == null) { 
// 					keyCounter.put(keyText, 1);
// 				} else {
// 					keyCounter.put(keyText, keyCounter.get(keyText)+1);
// 				}
// 			}
			
// 		} catch (IOException ex) {
// 			logger.error(ex.getMessage(), ex);
// 			System.exit(-1);
// 		}

//             // Оновлюємо візуалізацію
//             SwingUtilities.invokeLater(() -> {
//                 if (keyboardPanel != null) {
//                     keyboardPanel.updateKeyCounter(keyCounter);
//                 }
//             });
// 		for (Map.Entry<String, Integer> entry : keyCounter.entrySet()) {
// 			System.out.println("Ключ: " + entry.getKey() + ", Значення: " + entry.getValue());
// 		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		// Nothing
	}

public void nativeKeyTyped(NativeKeyEvent e) {
    logLayoutInfo();
    char keyChar = e.getKeyChar();
	String keyText = "" + keyChar;
	keyText = keyText.toUpperCase();
	// System.out.println(keyText);
			if (keyText.length() > 1) {
				// writer.print("[" + keyText + "]");
				// logger.debug("Key typed: {}, Layout: {}", keyChar, getCurrentLayoutFromInputContext());
			} else {
				// writer.print(keyText);
				// System.out.println(keyText);
				// System.out.println(keyCounter.get(keyText));
				if (keyCounter.get(keyText) == null) { 
					keyCounter.put(keyText, 1);
				} else {
					keyCounter.put(keyText, keyCounter.get(keyText)+1);
				}
			}
            SwingUtilities.invokeLater(() -> {
                if (keyboardPanel != null) {
                    keyboardPanel.updateKeyCounter(keyCounter);
                }
            });
		for (Map.Entry<String, Integer> entry : keyCounter.entrySet()) {
			System.out.println("Ключ: " + entry.getKey() + ", Значення: " + entry.getValue());
		}
		System.out.println("————————————————————————");
}
}
