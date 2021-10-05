package entity.environment;

import entity.birds.abstracts.Bird;

import java.util.List;

/**
 * @author novo
 * @since 2021/10/3
 */
public class Sign {

    /**
     * get description of an aviary
     *
     * @return description
     */
    public static String getDescription(Aviary aviary) {
        if (aviary.isEmpty()) {
            System.out.println("EMPTY");
        }
        StringBuilder description = new StringBuilder();
        // iterate all birds here
        List<Bird> birds = aviary.getAllBirdsAsList();
        for (int i = 1; i <= birds.size(); i++) {
            description.append("Bird ").append(i).append(": ").append("\n")
                    .append(birds.get(i - 1).toString())
                    .append("\n");
        }
        return description.toString();
    }
}
