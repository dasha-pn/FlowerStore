package flower.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FlowerPackTest {

    @Test
    void testGetPriceMultiplies() {
        Flower f = new Flower();
        f.setPrice(12.5);
        FlowerPack pack = new FlowerPack(f, 4);
        Assertions.assertEquals(50.0, pack.getPrice());
    }

    @Test
    void testZeroQuantityGivesZeroPrice() {
        Flower f = new Flower();
        f.setPrice(7.0);
        FlowerPack pack = new FlowerPack(f, 0);
        Assertions.assertEquals(0.0, pack.getPrice());
    }

    @Test
    void testDefensiveCopyProtectsFromExternalMutation() {
        Flower original = new Flower();
        original.setPrice(10.0);

        FlowerPack pack = new FlowerPack(original, 3);
        Assertions.assertEquals(30.0, pack.getPrice());

        original.setPrice(2.0);
        Assertions.assertEquals(30.0, pack.getPrice());
    }

    @Test
    void testDefensiveCopyWithMultipleFields() {
        Flower orig = new Flower();
        orig.setFlowerType(FlowerType.ROSE);
        orig.setColor(FlowerColor.BLUE);
        orig.setSepalLength(12.0);
        orig.setPrice(5.5);

        FlowerPack pack = new FlowerPack(orig, 2);
        Assertions.assertEquals(11.0, pack.getPrice());

        orig.setFlowerType(FlowerType.TULIP);
        orig.setColor(FlowerColor.RED);
        orig.setSepalLength(1.0);
        orig.setPrice(100.0);

        Assertions.assertEquals(11.0, pack.getPrice());
    }
}
