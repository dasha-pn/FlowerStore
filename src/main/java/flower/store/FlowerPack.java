package flower.store;

public class FlowerPack {
    private final Flower flower;
    private final int quantity;

    public FlowerPack(Flower flower, int count) {
        this.flower = new Flower(flower);
        this.quantity = count;
    }

    public double getPrice() {
        return quantity * flower.getPrice();
    }

    public Flower getFlower() {
        return new Flower(flower);
    }

    public int getQuantity() {
        return quantity;
    }
}
