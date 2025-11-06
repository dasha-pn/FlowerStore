package flower.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FlowerSpec {
    private final FlowerType type;
    private final FlowerColor color;
    private final double sepalLength;

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
