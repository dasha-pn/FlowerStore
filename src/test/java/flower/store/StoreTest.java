package flower.store;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StoreTest {

    private Store store;

    private Flower makeFlower(double price, FlowerType type, FlowerColor color, double sepalLength) {
        Flower f = new Flower();
        f.setPrice(price);
        f.setFlowerType(type);
        f.setColor(color);
        f.setSepalLength(sepalLength);
        return f;
    }

    private FlowerPack makePack(double price, FlowerType type, FlowerColor color, double sepalLength, int qty) {
        return new FlowerPack(makeFlower(price, type, color, sepalLength), qty);
    }

    @BeforeEach
    void setUp() {
        store = new Store();

        store.addPack(makePack(10.0, FlowerType.ROSE,  FlowerColor.RED,   7.0, 15));
        store.addPack(makePack(12.0, FlowerType.ROSE,  FlowerColor.RED,   7.0, 25));

        store.addPack(makePack(5.5,  FlowerType.TULIP, FlowerColor.YELLOW, 5.0, 40)); // інший тип
        store.addPack(makePack(9.5,  FlowerType.ROSE,  FlowerColor.WHITE,  7.0, 20)); // інший колір
        store.addPack(makePack(8.7,  FlowerType.ROSE,  FlowerColor.RED,    6.0, 30)); // інший sepal
    }

    @Test
    @DisplayName("Повертає всі точні збіги за type+sepal (кейс Erin із двома результатами)")
    void search_exactTypeAndSepal_returnsAllMatches() {
        FlowerSpec wanted = new FlowerSpec(FlowerType.ROSE, FlowerColor.RED, 7.0);

        List<FlowerPack> matches = store.search(wanted);

        assertEquals(2, matches.size(), "має знайти рівно два пакети ROSE з sepal=7.0 і кольором RED");

        for (FlowerPack p : matches) {
            Flower f = p.getFlower();
            assertEquals(FlowerType.ROSE, f.getFlowerType());
            assertEquals(7.0, f.getSepalLength(), 1e-9);
        }
    }

    @Test
    @DisplayName("Ігнорує незадані поля: лише type=ROSE → підходять будь-які кольори/довжини")
    void search_ignoresUnspecifiedFields() {
        FlowerSpec onlyTypeRose = new FlowerSpec(FlowerType.ROSE, null, 0.0);

        List<FlowerPack> matches = store.search(onlyTypeRose);

        assertEquals(4, matches.size(), "має знайти всі пакети з типом ROSE незалежно від кольору/довжини");
    }

    @Test
    @DisplayName("Сувора перевірка sepalLength: 7.0 не дорівнює 6.0")
    void search_strictSepalLength() {
        FlowerSpec wanted = new FlowerSpec(FlowerType.ROSE, FlowerColor.RED, 7.0);
        List<FlowerPack> matches = store.search(wanted);

        boolean containsWrongSepal =
                matches.stream().anyMatch(p -> Math.abs(p.getFlower().getSepalLength() - 6.0) < 1e-9);

        assertFalse(containsWrongSepal, "пак із sepal=6.0 не має потрапити в результати для sepal=7.0");
    }

    @Test
    @DisplayName("Повертає список (а не перший збіг): у результаті ≥ 2 елементи")
    void search_returnsListNotFirstOnly() {
        FlowerSpec wanted = new FlowerSpec(FlowerType.ROSE, FlowerColor.RED, 7.0);
        List<FlowerPack> matches = store.search(wanted);

        assertTrue(matches.size() >= 2, "метод має повертати всі збіги, а не лише перший");
    }

    @Test
    @DisplayName("FlowerPack#getFlower() повертає копію (defensive copy)")
    void flowerPack_getFlower_returnsCopy() {
        FlowerPack p = makePack(10.0, FlowerType.ROSE, FlowerColor.RED, 7.0, 15);

        Flower f1 = p.getFlower();
        Flower f2 = p.getFlower();

        assertNotSame(f1, f2, "getFlower() має повертати копію (різні посилання)");

        assertEquals(FlowerType.ROSE, f1.getFlowerType());
        assertEquals(7.0, f1.getSepalLength(), 1e-9);

        assertEquals(15 * 10.0, p.getPrice(), 1e-9);
    }
}
