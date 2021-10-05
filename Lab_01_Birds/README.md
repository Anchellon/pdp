# Birds
## Part 1
### Design
Bird and WaterBird will be abstract class
* Type
    * Type will have a default value and cannot be changed after creation.
    * Each classification will have an enum in class, which we could use to create object.
* Id will be auto generated to make sure every bird is unique. -- Because of Part 2
* Food
    * Food will be enum.
* WaterSource
    * WaterSource will be enum.
* Parrot
    * Parrot will contain a variable "words" which is a Set of Strings
    * GetNumOfWords will return the size of "words"
    * We'll have a method to add new word to "words", but if there are already 100 words, then the method will return false.

## Part 2
### Design
* We will store birds by a Map, the key will be the type of birds and value is a List of same kind of birds. Before rescuing, we will check if the bird is distinct or not.
* We will have a list to store aviaries.
* Before rescuing a bird, we have to check if the bird is distinct or not. If it's not distinct, then we could rescue it. While resucing birds, we just need to check if there's already this kind of birds existing in the conservatory. If so, we just add the new bird into the current List of same kind of birds. If not, we add a new List to the map.
* Since we have created classes of Foods, then when we want to get foods requirements, we could return a Map of Food and respective quantity. We just need to iterate all these birds in conservatory, and count the foods.
* When we want to assign a bird to an aviary, we will firstly check the total number of birds already exist there. Then, remove that new bird from the temporary space, and move it to the aviary. And we will also check if there already exisits conlicting birds in that aviary. We use three static methods in PreyBird.class, Flightless.class and Waterfowl.class to do easily check.
    * Since we could only have 20 aviaries, so before we want to add a new aviary, we have to check if the number of aviaries is smaller than 20 or not. If more than 20, we need to throw an IllegalStateException.
    * We provide two ways to search a bird, by Id or Bird.
    * Use a static method in Sign.class to print sign of an aviary.
    * We could use a DTO named "BirdWithLocation" to store bird info with location. Then sort all the DTO by bird.type in alphabetical order.
    
### Test Plan
* Firstly, we need to test all these basic methods, like those setters/ getters.

* Then we need to test some edge cases.
    * Aviary
        * test using null as argument to call those methods. -- IllegalArgumentException
            * addBird
            * isConflict
            * setLocation
        
    * Conservatory
        * test using null as arguments to call those methods. -- IllegalArgumentException
            * addAviary
            * assignBirdToAviary
            * rescueBird
            * searchBird
        * other errors -- IllegalArgumentException & IllegalStateException
            * Add a new bird to an aviary when there are already 5 birds in the aviary.
            * Add a new aviary to conservatory while there are already 20 aviaries in the conservatory.
            * Add conflicting birds to an aviary.
            * Rescue a bird which is distinct.
            * Get food quantities while no birds exist.
            * Print signs while no birds exist.
            * Search a bird that doesn't exist.