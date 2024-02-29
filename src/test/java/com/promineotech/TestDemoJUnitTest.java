package com.promineotech;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.stream.Stream;

public class TestDemoJUnitTest {

    private TestDemo testDemo;

    @BeforeEach
    public void setUp() {
        testDemo = new TestDemo();
    }
    //Parts 1 & 2 of the assignment
    @Test
    void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
        assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
        assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
    }

    @ParameterizedTest
    @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
    public void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
        if (!expectException) {
            assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
        } else {
            assertThatThrownBy(() -> testDemo.addPositive(a, b))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(String.valueOf(expected));
        }
    }

    private static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
                Arguments.arguments(2, 3, 5, false),  // Test case 1: Both numbers are positive
                Arguments.arguments(1, 3, 4, false),   // Test case 2: First parameter is zero, expecting exception
                Arguments.arguments(2, -3, 0, true),  // Test case 3: Second parameter is negative, expecting exception
                Arguments.arguments(0, -3, 0, true)   // Test case 4: Both parameters are non-positive, expecting exception
        );
    }
    
    /**
     * Part 3 of the assignment, testing my own method.
     * Test method for the calculateBoardFeet method in TestDemo.
     */
    @Test
    void testCalculateBoardFeet() {
        TestDemo testDemo = new TestDemo();

        // Test the board feet for a board with length 96 inches, width 48 inches, and thickness 2 inches
        assertThat(testDemo.calculateBoardFeet(96, 48, 2)).isEqualTo(64.0, offset(0.01));

        // Test the board feet for a board with length 120 inches, width 36 inches, and thickness 1.5 inches
        assertThat(testDemo.calculateBoardFeet(120, 36, 1.5)).isEqualTo(45, offset(0.01));
    }
    
    @ParameterizedTest
    @CsvSource({
            "96, 48, 2, 64.0",
            "120, 36, 1.5, 45"
    })
    void testCalculateBoardFeet(double length, double width, double thickness, double expectedBoardFeet) {
        TestDemo testDemo = new TestDemo();

        double boardFeet = testDemo.calculateBoardFeet(length, width, thickness);

        assertThat(boardFeet).isEqualTo(expectedBoardFeet, offset(0.01));
    }    
    
    /**
     * Part 4 Testing the Mocking of a Class 
     * Test method for the randomNumberSquared method in TestDemo.
     */
    @Test
    void assertThatNumberSquaredIsCorrect() {
        // Create a spy for the TestDemo class
        TestDemo testDemo = new TestDemo();
        TestDemo mockDemo = spy(testDemo);

        // Program the mocked TestDemo object to return 5 when getRandomInt is called
        doReturn(5).when(mockDemo).getRandomInt();

        // Call the method randomNumberSquared on the mocked TestDemo object
        int fiveSquared = mockDemo.randomNumberSquared();

        // Use assertThat to test that the value returned from randomNumberSquared is equal to 5 squared
        assertThat(fiveSquared).isEqualTo(25);
    }
}
