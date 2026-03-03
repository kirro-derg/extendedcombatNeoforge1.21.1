package dev.kirro.extendedcombat;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Set;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = ExtendedCombat.MOD_ID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue DISABLE_DURABILITY = BUILDER
            .comment("Whether to disable item durability")
            .define("disableDurability", true);

    private static final ModConfigSpec.IntValue WARDING_STONE_RADIUS = BUILDER
            .comment("Radius in which the Warding Stone will disable mob spawns")
            .defineInRange("wardingStoneActiveRadius", 55, 0, 100);

    private static final ModConfigSpec.BooleanValue AIR_MOVEMENT = BUILDER
            .comment("Whether to increase mobility while in air")
            .define("airMovement", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean disableDurability;
    public static int wardingStoneActiveRadius;
    public static boolean airMovementActive;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        disableDurability = DISABLE_DURABILITY.get();
        wardingStoneActiveRadius = WARDING_STONE_RADIUS.get();
        airMovementActive = AIR_MOVEMENT.get();
    }
}
