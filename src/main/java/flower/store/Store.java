package flower.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private final List<FlowerPack> inventory = new ArrayList<>();

    public void addPack(FlowerPack pack) {
        if (pack != null) {
            inventory.add(pack);
        }
    }

    public List<FlowerPack> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public List<FlowerPack> search(FlowerSpec wantedSpec, Integer minAmount) {
        List<FlowerPack> result = new ArrayList<>();

        for (FlowerPack pack : inventory) {
            if (pack == null) {
                continue;
            }

            Flower flower = pack.getFlower();
            if (flower == null || flower.getSpec() == null) {
                continue;
            }

            if (!flower.getSpec().matches(wantedSpec)) {
                continue;
            }

            if (minAmount != null
                    && minAmount > 0
                    && pack.getQuantity() < minAmount) {
                continue;
            }

            result.add(pack);
        }

        return result;
    }

    public List<FlowerPack> search(FlowerSpec wantedSpec) {
        return search(wantedSpec, null);
    }
}
