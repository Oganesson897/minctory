package org.minctory;

import org.minctory.loader.library.MinctoryLibrary;

public class Main {
    public static void main(String[] args) {
        MinctoryLibrary.load();

        Minctory.INSTANCE.start();
    }
}