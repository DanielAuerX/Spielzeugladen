package storage;

import toy_features.Size;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Rack {
    private final int maximumAmountOfLevels = 3;
    private ArrayList<Level> levels;

    public Rack(ArrayList<Level> levels) {
        if (levels.size() > maximumAmountOfLevels){
            throw new InputMismatchException("A rack can only have three levels!");
        }
        this.levels = levels;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public String getLevelWithFreeUnits(Size size) {
        String levelIndex;
        int counter = 0;
        for (Level level : levels) {
            counter++;
            levelIndex = level.getFreeUnits(size);
            if (levelIndex != null) {
                switch (counter) {
                    case 1 -> {
                        return "a" + levelIndex;
                    }
                    case 2 -> {
                        return "b" + levelIndex;
                    }
                    case 3 -> {
                        return "c" + levelIndex;
                    }
                    default -> throw new InputMismatchException("A rack can only have three levels!");
                }
            }
        }
        return null;
    }
}
