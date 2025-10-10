package flower.store;

public enum FlowerColor {
    RED("#FF0000"),
    GREEN("#008000"),
    BLUE("#0000FF");

    private final String hex;
    FlowerColor(String hex) { 
        this.hex = hex; 
    }

    @Override
    public String toString() { 
        return hex; 
    }
}
