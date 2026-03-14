package dev.kirro.extendedcombat.data;

import com.mojang.serialization.Codec;
import dev.kirro.extendedcombat.ExtendedCombat;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents extends DataComponents {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ExtendedCombat.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> HIDDEN = register(
            "hidden", Codec.BOOL, ByteBufCodecs.BOOL);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CHARGE = register(
            "charge", Codec.intRange(0, 8), ByteBufCodecs.INT);

    public static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, Codec<T> codec, StreamCodec<ByteBuf, T> streamCodec) {
        return REGISTRAR.registerComponentType(name, builder -> builder.persistent(codec).networkSynchronized(streamCodec));
    }

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }
}
