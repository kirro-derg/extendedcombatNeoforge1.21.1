package dev.kirro.extendedcombat.loot;

import com.mojang.serialization.MapCodec;
import dev.kirro.extendedcombat.ExtendedCombat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ExtendedCombat.MOD_ID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ECHO_STEEL_UPGRADE =
            LOOT_MODIFIERS.register("echo_steel_upgrade", () -> AddItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }
}
