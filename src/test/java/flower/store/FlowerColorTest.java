package flower.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FlowerColorTest {

    @Test
    void testToStringHex() {
        Assertions.assertEquals("#FF0000", FlowerColor.RED.toString());
        Assertions.assertEquals("#008000", FlowerColor.GREEN.toString());
        Assertions.assertEquals("#0000FF", FlowerColor.BLUE.toString());
    }
}
