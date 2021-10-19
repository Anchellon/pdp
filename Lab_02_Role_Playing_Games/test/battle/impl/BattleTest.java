package battle.impl;

import item.Gear;
import item.impl.Footwear;
import item.impl.HandWear;
import item.impl.HeadWear;
import org.junit.Before;
import org.junit.Test;
import player.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author novo
 * @since 2021/10/19
 */
public class BattleTest {

    Battle battle;

    @Before
    public void setUp() throws Exception {
        // construct battle
        Player p1 = new Player("zhangsan", 10, 20);
        Player p2 = new Player("lisi");


        List<Gear> gears = new ArrayList<>();

        // head gears
        Gear greenHat = new HeadWear("Green", "Hat", 1);
        gears.add(greenHat);
        Gear waterBubble = new HeadWear("Water", "Bubble", 34);
        gears.add(waterBubble);
        Gear yellowHair = new HeadWear("Yellow", "Hair", 26);
        gears.add(yellowHair);
        Gear greyHelmet = new HeadWear("Grey", "Helmet", 16);
        gears.add(greyHelmet);

        // hand wears
        Gear redGlove = new HandWear("Red", "Glove", 100);
        gears.add(redGlove);
        Gear brownPhone = new HandWear("Brown", "Phone", 59);
        gears.add(brownPhone);
        Gear boiledWater = new HandWear("Boiled", "Water", 76);
        gears.add(boiledWater);
        Gear freshBanana = new HandWear("Fresh", "Banana", 47);
        gears.add(freshBanana);
        Gear awesomeOreo = new HandWear("Awesome", "Oreo", 27);
        gears.add(awesomeOreo);
        Gear bigIpad = new HandWear("Big", "Ipad", 88);
        gears.add(bigIpad);
        Gear pinkKindle = new HandWear("Pink", "Kindle", 12);
        gears.add(pinkKindle);
        Gear blackKeyboard = new HandWear("Black", "Keyboard", 55);
        gears.add(blackKeyboard);



        // foot wears
        Gear blueBoot = new Footwear("Blue", "Boot", 77, 50);
        gears.add(blueBoot);
        Gear whiteSlipper = new Footwear("While", "Slipper", 56, 20);
        gears.add(whiteSlipper);
        Gear niceAj1 = new Footwear("Nice", "Aj1", 25, 40);
        gears.add(niceAj1);
        Gear fireAdidas = new Footwear("Fire", "Adidas", 80, 12);
        gears.add(fireAdidas);
        Gear burningPuma = new Footwear("Burning", "Puma", 60, 17);
        gears.add(burningPuma);
        Gear wetSock = new Footwear("Wet", "Sock", 12, 80);
        gears.add(wetSock);
        Gear ironNike = new Footwear("Iron", "Nike", 99, 99);
        gears.add(ironNike);
        Gear smallBox = new Footwear("Small", "Box", 21, 33);
        gears.add(smallBox);

        battle = new Battle(p1, p2, gears);

    }

    @Test
    public void testBattle() throws Exception{
        battle.armPlayers();
        System.out.println(battle.status());
        System.out.println(battle.winner());
    }

    /**
     * Try to construct battle with null player
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructBattleWithNullPlayer() {
        battle = new Battle(null, null);
    }

    /**
     * Try to construct battle with same player
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructBattleWithSamePlayer() {
        Player p1 = new Player("testPlayer");
        battle = new Battle(p1, p1);
    }

    /**
     * Try to add null gear to battle
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNullGear() {
        battle.addGear((Gear) null);
    }

    /**
     * Try to add duplicate gears to battle
     */
    @Test(expected = IllegalArgumentException.class)
    public void addDuplicateGearToBattle() {
        Gear dupGear = new HeadWear("test", "test", 1);
        battle.addGear(dupGear);
        battle.addGear(dupGear);
    }

}