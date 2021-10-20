package battle;

public interface BattleInterface {

    // invoke one turn of the battle.
    // Each turn a character pick an item based on the rules.
    // returns false if the battle ends
//  boolean oneTurn();

    /**
     * Let players pick gears
     */
    void armPlayers() throws Exception;

    // Print out each character with what they are wearing
    // and their attack and defense strength
    String status();

    // return the winner's name or "tie"
    String winner();
}
