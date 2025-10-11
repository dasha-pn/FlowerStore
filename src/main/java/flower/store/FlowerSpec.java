package flower.store;

import lombok.Getter;

@Getter
public class FlowerSpec {
    private final FlowerType type;
    private final FlowerColor color;
    private final double sepalLength;

    public FlowerSpec(FlowerType type, 
                    FlowerColor color, 
                    double sepalLength) {
        this.type = type;
        this.color = color;
        this.sepalLength = sepalLength;
    }

    public boolean matches(FlowerSpec other) {
        if (other == null) {
            return true;
        }
        if (other.type != null && this.type != other.type) {
            return false;
        }
        if (other.color != null && this.color != other.color) {
            return false;
        }
        if (other.sepalLength > 0 
                && Double.compare(this.sepalLength, other.sepalLength) != 0) {
            return false;
        }
        return true;
    }
}
