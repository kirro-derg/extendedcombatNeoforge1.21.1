package dev.kirro.extendedcombat;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    public static final String client = "client";

    @Entry
    public static boolean disableDurability = true;

    @Entry(min = 0, max = 100)
    public static int wardingStoneRadius = 55;

    @Entry
    public static boolean airMovementActive = true;

    static {
        MidnightConfig.init(ExtendedCombat.MOD_ID, ModConfig.class);
    }
}
