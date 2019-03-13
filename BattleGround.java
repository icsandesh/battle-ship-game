import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleGround {

    private int row_max;
    private int col_max;
    private Map<Location, BattleShipUnit> positionToShipMap;
    private List<BattleShip> battleShips;

    private BattleGround(int row_max, int col_max) {
        this.row_max = row_max;
        this.col_max = col_max;
        positionToShipMap = new HashMap<>();
        battleShips = new ArrayList<>();
    }

    public static BattleGround of(int row_max, int col_max){
        return new BattleGround(row_max, col_max);
    }

    public void addShip(int width, int height, int maxHealth){
        BattleShip battleShip = new BattleShip(width * height, maxHealth);
        battleShips.add(battleShip);
    }

    public void addShipLocation(int row, int col, BattleShipUnit battleShipUnit){
        positionToShipMap.put(Location.of(row, col), battleShipUnit);
    }

    public boolean hitLocation(Location target){

        BattleShipUnit battleShipUnit = positionToShipMap.get(target);

        if(battleShipUnit != null){
            battleShipUnit.hitShip();
            return true;
        }
        return false;
    }



}
