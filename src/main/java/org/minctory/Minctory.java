package org.minctory;

import org.minctory.api.save.Save;
import org.minctory.api.save.detect.SaveDetector;
import org.minctory.api.utils.AppDataUtils;
import org.minctory.api.utils.logger.Printer;

import java.io.File;

public class Minctory {

    // Values
    public static final String NAME = "Minctory";
    public static final Minctory INSTANCE = new Minctory();
    public static final File DATA_DIR = new File(AppDataUtils.getAppDataDirectory(NAME));

    private final Printer printer = new Printer();

    public Save currentSave;

    private Minctory() {}

    public void start() {
        printer.print("Hello, Welcome to Minctory!");
        printer.print("Detecting data...");
        SaveDetector.detect();
        SaveDetector.saveSelect(printer);
    }

    public void save() {
        SaveDetector.saveConfig();
    }

}
