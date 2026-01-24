package dev.kirro.extendedcombat.villager;

import com.google.common.collect.ImmutableSet;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPoi {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, ExtendedCombat.MOD_ID);

    public static final Holder<PoiType> WARDING_STONE_POI = POI_TYPES.register(
            "warding_stone",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.WARDING_STONE.get().getStateDefinition().getPossibleStates()),
                    1,
                    1));


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }
}
