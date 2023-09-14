
import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class MainTest {
    @Test
    public void heapsortTest() {
        Assertions.assertArrayEquals(new int[] {1, 2, 3},Main.heapsort(new int[] {3, 1, 2}));
        Assertions.assertArrayEquals(new int[] {}, Main.heapsort(new int[] {}));
        Assertions.assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7}, Main.heapsort(new int[] {2, 1, 6, 4, 5, 3, 7}));
        Assertions.assertArrayEquals(new int[] {-3, -2, -1}, Main.heapsort(new int[] {-1, -2, -3}));
    }

}