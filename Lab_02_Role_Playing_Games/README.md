# Gear
* Gear.java
* AbstractGear.java
* GearType.java
* impl
    * Footwear.java
    * HandWear.java
    * HeadWear.java
    
## Gear.java
### Intro
Interface of all gears, declaring those common methods.

Type cannot be changed after creation.

### Methods
* Gear combine(Gear gear) throws Exception;
* GearType getType();
* String getAdj();
* String getNoun();
* int getAttack();
* int getDefense();
* void setAdj(String adj);
* void setNoun(String noun);
* void setAttack(int attack);
* void setDefense(int defense);
* void setHasCombined(boolean hasCombined);
* String getFullName();
* boolean hasCombined();

## AbstractGear.java
### Intro
Abstract class of all gears, implementing Gear.java.

### Variables
* private GearType type;
* private String adj;
* private String noun;
* private int attack;
* private int defense;
* private boolean hasCombined = false;

### Methods
* AbstractGear(GearType type, String adj, String noun, int attack, int defense);
* Gear combine(Gear gear) throws Exception;
* GearType getType();
* String getAdj();
* String getNoun();
* int getAttack();
* int getDefense();
* void setAdj(String adj);
* void setNoun(String noun);
* void setAttack(int attack);
* void setDefense(int defense);
* void setHasCombined(boolean hasCombined);
* String getFullName();
* boolean hasCombined();

## GearType.java
### Intro
Enum of all types

### Types
* FOOT
* HAND
* HEAD

## Footwear.java
### Intro
Class of footwear, inheriting from AbstractGear.

### Methods
* Footwear(String adj, String noun, int attack, int defense)
    * Type is fixed. Cannot be changed.

## HandWear.java
### Intro
Class of hand wear, inheriting from AbstractGear.

### Variables
* DEFAULT_DEFENSE_STRENGTH = 0

### Methods
* HandWear(String adj, String noun, int attack)
    * Type is fixed. Cannot be changed.
    * Defense strength is always 0.
    
## HeadWear.java
### Intro
Class of head wear, inheriting from AbstractGear.

### Variables
* DEFAULT_ATTACK_POWER = 0

### Methods
* HeadWear(String adj, String noun, int defense)
    * Type is fixed. Cannot be changed.
    * Attack power is always 0.
    
## Test Plan
* Test using methods correctly
* IllegalArgumentException
    * Test combining different types of gears
    * Create gears with null or empty string
    * Try to combine with null gear
* IllegalStateException
    * Try to combine excessive gears

# Player
* Player.java

## Player.java
### Intro
Represented as player in battles. 
Players have name, original attack power, original defense strength, and gears.

### Variables
* public static final int FOOTWEAR_MAX = 2;
* public static final int HAND_GEAR_MAX = 2;
* public static final int HEAD_GEAR_MAX = 1;
* private final long playerId;
* private final String name;
* private final int attackBase;
* private final int defenseBase;
* private List<Gear> handGear;
* private List<Gear> footwear;
* private Gear headGear; 

### Methods
* public Player(String name, int attackBase, int defenseBase)
* public Player(String name)
* public long getPlayerId()
* public String getName()
* public int getTotalAttack()
* public int getTotalDefense()
* public boolean canPickHeadGear()
* public boolean canPickHandGear()
* public boolean canPickFootwear()
* public boolean canPick()
* public void pickup(Gear gear)
* private void pickHeadWear(Gear gear)
* private void pickHandWear(Gear gear)
* private void pickFootwear(Gear gear)
* public String toString()

## Test Plan
* Test using those methods correctly
* IllegalArgumentException
    * Try to create players with null/empty argument
    * Try to pickup null gear
* IllegalStateException
    * try to pickup excessive head gears
    * try to pickup excessive hand gears
    * try to pickup excessive foot wears
    
# Battle
* BattleInterface.java
* impl
    * Battle.java

## BattleInterface.java
### Intro
Declared methods of battle
### Methods
* void armPlayers() throws Exception;
* String status();
* String winner();

## Battle.java
### Intro
Implementation of BattleInterface.java

### Variables
* private final long battleId;
* private Player player1;
* private Player player2;
* private List<Gear> gears;

### Methods
* public Battle(Player player1, Player player2, List<Gear> gears)
* public Battle(Player player1, Player player2)
* public void addGear(Gear gear)
* public void addGear(List<Gear> gears)
* public void armPlayers() throws Exception
* private boolean pickGear(Player player) throws Exception
* private void sortGears()
* public String status()
* public String winner()

## Test Plan
* Test using methods correctly
* IllegalArgumentException
    * Try to construct battle with null player
    * Try to construct battle with same player
    * Try to add null gear to battle
    * Try to add duplicate gears to battle