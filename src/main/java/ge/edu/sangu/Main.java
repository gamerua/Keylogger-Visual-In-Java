package ge.edu.sangu;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

public class Main {
	public static void main(String[] args) {
        setupGlobalLogger();
        Logger logger = LoggerFactory.getLogger(VisualKeyLogger.class);

        VisualKeyLogger keyLogger = new VisualKeyLogger();
        keyLogger.init();

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            logger.error(e.getMessage(), e);
            System.exit(-1);
        }

        GlobalScreen.addNativeKeyListener(keyLogger);
    }

    private static void setupGlobalLogger() {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(
                GlobalScreen.class.getPackage().getName());

        logger.setLevel(Level.WARNING);

        logger.setUseParentHandlers(false);
    }
}
