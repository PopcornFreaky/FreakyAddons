package com.mod.SkyTox.Utils;

import java.util.HashSet;
import java.util.Set;

public class MobSelector {

    // A set to store the names of selected mobs
    private static Set<String> selectedMobs = new HashSet<>();

    // Adds a mob to the selection list
    public static void addMob(String mobName) {
        selectedMobs.add(mobName);
    }

    // Removes a mob from the selection list
    public static void removeMob(String mobName) {
        selectedMobs.remove(mobName);
    }

    // Checks if a mob is selected
    public static boolean isMobSelected(String mobName) {
        return selectedMobs.contains(mobName);
    }
}
