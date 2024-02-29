package com.promineotech;

import java.util.Random;

public class TestDemo {

	public int addPositive(int a, int b) {
		if (a > 0 && b > 0) {
			return a + b;
		} else {
			throw new IllegalArgumentException("Both parameters must be positive!");
		}
	}
	
	public static void main(String[] args) {
		TestDemo obj = new TestDemo();
		try {
			int result = obj.addPositive(2, 3);
			System.out.println("Sum: " + result);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	// Part 3,  create my own method
	/**
     * Calculates the board feet based on length, width, and thickness.
     *
     * @param length     The length of the board in inches.
     * @param width      The width of the board in inches.
     * @param thickness  The thickness of the board in inches.
     * @return The board feet of the given board dimensions.
     */
    public double calculateBoardFeet(double length, double width, double thickness) {
        // Formula for calculating board feet: length * width * thickness / 144 (to convert inches to feet)
        return length * width * thickness / 144.0;
    }
    
    // Part 4, Mocking a Class - TestDemo
    /**
     * Generates a random int between 1 and 10 and returns its square.
     *
     * @return The square of a random int between 1 and 10.
     */
    public int randomNumberSquared() {
        int result = getRandomInt();
        return result * result;
    }

    // Package visibility method for getting a random int between 1 and 10
    int getRandomInt() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}
