package flower.store;

import lombok.Getter;
import lombok.Setter;

@Setter
public class Flower {
    @Getter
    private double sepalLength;

    private FlowerColor color;

    @Getter
    private double price;

    @Getter
    private FlowerType flowerType;

    public Flower() { }

    public Flower(Flower other) {
        if (other == null) {
            throw new IllegalArgumentException("other flower is null");
        }
        this.sepalLength = other.sepalLength;
        this.color = other.color;
        this.price = other.price;
        this.flowerType = other.flowerType;
    }

    public String getColor() {
        if (color == null) {
            return null;
        }
        return color.toString();
    }

    public void setPrice(final double newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("price must be >= 0");
        }
        this.price = newPrice;
    }

    public FlowerSpec getSpec() {
        return new FlowerSpec(flowerType, color, sepalLength);
    }
}
