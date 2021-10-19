# Item
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

### Methods
* Gear combine(Gear gear) throws Exception;
* GearType getType();
* String getAdj();
* String getNoun();
* int getAttack();
* int getDefense();
* void setType(GearType type);
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
* void setType(GearType type);
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
    
