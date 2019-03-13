import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BattleShip {

    private final List<BattleShipUnit> battleShipUnits;

    private boolean isShipAlive = true;

    public BattleShip(int size, int health) {

        List<BattleShipUnit> battleShipUnits = IntStream.range(0, size).
                mapToObj(i -> new BattleShipUnit(health)).collect(Collectors.toList());
        this.battleShipUnits = Collections.unmodifiableList(battleShipUnits);
    }

    public  boolean isBattleShipDown(){
        return !isShipAlive || isAllShipUnitsAlive();
    }

    private boolean isAllShipUnitsAlive() {
        this.isShipAlive = battleShipUnits.stream().anyMatch(BattleShipUnit::isShipAlive);
        return this.isShipAlive;
    }

    public List<BattleShipUnit> getBattleShipUnits() {
        return battleShipUnits;
    }
}
