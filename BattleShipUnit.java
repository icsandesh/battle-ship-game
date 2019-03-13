public class BattleShipUnit {

    private int health;

    public BattleShipUnit(int health) {
        this.health = health;
    }

    public void hitShip(){
        health--;
    }

    public boolean isShipAlive(){
        return health > 0;
    }

}
