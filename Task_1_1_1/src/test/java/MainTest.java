
import org.example.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class MainTest {
    @Test
    public void heapsortTest() {
        Assertions.assertArrayEquals(new int[] {1, 2, 3},Main.heapsort(new int[] {3, 1, 2}));
    }
}