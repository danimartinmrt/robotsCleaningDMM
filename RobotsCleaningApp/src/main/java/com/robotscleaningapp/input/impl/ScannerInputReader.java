package com.robotscleaningapp.input.impl;

import java.util.Scanner;

import com.robotscleaningapp.input.IInputReader;

public class ScannerInputReader implements IInputReader {
    private final Scanner scanner;

    public ScannerInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
    
    @Override
    public void close() {
		scanner.close();
	}
}

