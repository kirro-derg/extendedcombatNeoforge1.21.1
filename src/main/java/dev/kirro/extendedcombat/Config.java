package dev.kirro.extendedcombat;

import dev.kirro.extendedcombat.behavior.item.ModRepairManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Objects;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = ExtendedCombat.MOD_ID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue DISABLE_DURABILITY = BUILDER
            .define("disableDurability", true);

    private static final ModConfigSpec.ConfigValue<Integer> WARDING_STONE_RADIUS = BUILDER
            .defineInRange("wardingStoneActiveRadius", 55, 0, 100);

    private static final ModConfigSpec.BooleanValue AIR_MOVEMENT = BUILDER
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
