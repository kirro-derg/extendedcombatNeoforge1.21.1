package dev.kirro.extendedcombat.item;

import com.mojang.serialization.Codec;
import dev.kirro.extendedcombat.ExtendedCombat;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents extends DataComponents {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ExtendedCombat.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> AIR_MOBILITY = REGISTRAR.registerComponentType("air_mobility", builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> HIDDEN = REGISTRAR.registerComponentType("hidden", builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CHARGE = REGISTRAR.registerComponentType("charge", builder -> builder.persistent(Codec.intRange(0, 8)).networkSynchronized(ByteBufCodecs.INT));

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }
}
