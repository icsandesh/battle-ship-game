public enum BattleShipType {
    Q(2), P(1);

    private int health;

    BattleShipType(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
