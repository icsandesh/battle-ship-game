import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleShipGameManager implements BattleShipGame {


    public static void main(String[] args) throws IOException {
        new BattleShipGameManager().startGame();
    }
    @Override
    public void startGame() throws IOException {

        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter area boundaries: ");
        String boundaries = bufferedInputStream.readLine();
        System.out.println();

        String[] boundarySplit = boundaries.split("\\s+");
        int col_max = Integer.parseInt(boundarySplit[0]);
        int row_max = deduceIndexForCharacter(boundarySplit[1]);

        BattleGround battleGroundA = BattleGround.of(row_max, col_max);
        BattleGround battleGroundB = BattleGround.of(row_max, col_max);

        List<BattleGround> battleGrounds = Arrays.asList(battleGroundA, battleGroundB);


        for (int i = 1; i <= 2; i++) {


            System.out.print("Type for BattleShip " + i + ":");
            String shipType = bufferedInputStream.readLine();
            System.out.println();


            System.out.print("Dimensions for BattleShip " + i + ":");
            String shipSize = bufferedInputStream.readLine();
            System.out.println();

            String[] shipSizeSplit = shipSize.split("\\s+");

            int shipWidth = Integer.parseInt(shipSizeSplit[0]);
            int shipHeight = Integer.parseInt(shipSizeSplit[1]);

            int sizeOfShip = shipWidth * shipHeight;


            for (int j = 0; j < 2; j++) {
                BattleShip battleShip = new BattleShip(sizeOfShip, BattleShipType.valueOf(shipType.trim().toUpperCase()).getHealth());
                System.out.print("Location of BattleShip" + i + "for Player " + ('A' + j) + " :");
                String shipLocation = bufferedInputStream.readLine();
                System.out.println();

                String[] shipLocationSplit = shipLocation.split("\\s+");

                int x = deduceIndexForCharacter(shipLocationSplit[0]);
                int y = Integer.parseInt(shipLocationSplit[1]) - 1;

                int unitCount = 0;
                for (int a = x; a < shipWidth; a++) {
                    for (int b = y; b < shipHeight; b++) {
                        battleGrounds.get(j).addShipLocation(x, y, battleShip.getBattleShipUnits().get(unitCount++));
                    }
                }
            }
        }

        System.out.print("Missiles for Player A: ");
        String missilesPlayerA = bufferedInputStream.readLine();
        System.out.println();

        System.out.print("Missiles for Player B: ");
        String missilesPlayerB = bufferedInputStream.readLine();
        System.out.println();


        String[] missilesASplit = missilesPlayerA.split("\\s+");
        String[] missilesBSplit = missilesPlayerB.split("\\s+");

        List<Location> playerAMissileLocations = convertToLocations(missilesASplit);
        List<Location> playerBMissileLocations = convertToLocations(missilesBSplit);

        for (Location location : playerAMissileLocations) {
            boolean isHit = battleGroundB.hitLocation(location);
            if (isHit) {
                System.out.println("Player A Hit Miss");
            } else {
                System.out.println("Player B Location SuccessFully Hit");
            }
        }


        for (Location location : playerAMissileLocations) {
            boolean isHit = battleGroundB.hitLocation(location);
            if (isHit) {
                System.out.println("Player B Hit Miss");
            } else {
                System.out.println("Player A Location SuccessFully Hit");
            }
        }


    }


    private List<Location> convertToLocations(String[] missilesASplit) {
        List<Location> missilePositions = new ArrayList<>();
        for(String missileA: missilesASplit){
            String[] split = missileA.split("\\s+");
            int x_pos = deduceIndexForCharacter(split[0]);
            int y_pos = Integer.parseInt(split[1])-1;
            missilePositions.add(Location.of(x_pos, y_pos));
        }

        return missilePositions;
    }


    private int deduceIndexForCharacter(String ch){
       return ch.toUpperCase().charAt(1) - 'A';
    }

}
