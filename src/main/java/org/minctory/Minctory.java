package org.minctory;

import org.minctory.api.utils.AppDataUtils;
import org.minctory.api.utils.logger.Printer;

import java.io.File;

public class Minctory {

    // Values
    public static final String NAME = "Minctory";
    public static final Minctory INSTANCE = new Minctory();

    public static final File DATA_DIR = new File(AppDataUtils.getAppDataDirectory(NAME));

    public final Printer printer = new Printer();

    private Minctory() {}

    public void start() {
        printer.print("Hello, Welcome to Minctory!");
    }

}
