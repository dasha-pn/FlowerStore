package flower.store;

import java.util.ArrayList;
import java.util.List;

public class FlowerBucket {
    private final List<FlowerPack> packs = new ArrayList<>();

    public void add(final FlowerPack pack) {
        if (pack == null) {
            throw new IllegalArgumentException("pack is null");
        }
        packs.add(pack);
    }

    public double getPrice() {
        double sum = 0.0;
        for (FlowerPack p : packs) {
            sum += p.getPrice();
        }
        return sum;
    }

    public List<FlowerPack> getPacks() {
        return List.copyOf(packs);
    }
}
