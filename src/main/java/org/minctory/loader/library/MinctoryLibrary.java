package org.minctory.loader.library;

import org.minctory.Minctory;

import java.io.File;

public class MinctoryLibrary {

    public static final File DATA_DIR = new File(Minctory.DATA_DIR, "libraries");

    public static void load() {
        DATA_DIR.mkdirs();
    }

}
