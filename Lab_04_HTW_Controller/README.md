## Definition
* pit
    * player will die
    * there will be n pits
* superbat
    * cannot be felt
    * 50% chance that player will be moved to a random place in the maze
    * could exist with pit in a same room. Will do the random pick before falling
    * there will be n superbats
* wumpus
    * player will die
    * there will be only 1 wumpus
* arrow
    * limited number
    * encounter wumpus -- win
    * player will lose if the player runs out of arrows
    * could specify the distance and direction
    * could freely go through tunnels(even crooked ones), but in cave, could only go straight
    * distance must be exact
* player
    * cannot see adjacent caves, only possible directions
    * can feel adjacent wumpus -- smell
    * can feel adjacent pit -- draft
    * cannot feel adjacent superbat

## Program
* Driver
    * specify the maze properties (wrapping/non-wrapping, start position, etc)
    * the difficulty (i.e., number of pits, number of superbats, number of arrows) and any other configuration options as needed
* Controller
    * takes keyboard input
        * navigate the player through the maze
        * shoot an arrow in a given direction
    * output state
    * give clues about the nearby caves and other relevant aspects of current game state