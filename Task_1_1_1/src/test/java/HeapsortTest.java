
import org.example.Heapsort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class HeapsortTest {
    @ParameterizedTest
    @MethodSource("generateData")
    public void heapsortTest(int[] expected, int[] input){
        Assertions.assertArrayEquals(expected, Heapsort.heapsort(input));
    }

    static Stream<Arguments> generateData(){
        return Stream.of(
            Arguments.arguments(new int[]{1, 2, 3}, new int[]{3, 1, 2}),
            Arguments.arguments(new int[]{}, new int[]{}),
            Arguments.arguments(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{2, 1, 6, 4, 5, 3, 7}),
            Arguments.arguments(new int[]{-3, -2, -1}, new int[]{-1, -2, -3})
        );
    }
}