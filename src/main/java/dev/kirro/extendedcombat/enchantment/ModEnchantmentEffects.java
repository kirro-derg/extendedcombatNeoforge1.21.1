package dev.kirro.extendedcombat.enchantment;

import com.mojang.serialization.Codec;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.enchantment.custom.*;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModEnchantmentEffects {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, ExtendedCombat.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ObscurityEnchantmentEffect>> OBSCURITY = register(
            "obscurity", ObscurityEnchantmentEffect.CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<StealthEnchantmentEffect>> STEALTH = register(
            "stealth", StealthEnchantmentEffect.CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ConcussionEnchantmentEffect>> CONCUSSION = register(
            "concussion", ConcussionEnchantmentEffect.CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FluidWalkerEnchantmentEffect>> FLUID_WALKER = register(
            "fluid_walker", FluidWalkerEnchantmentEffect.CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SwiftnessEnchantmentEffect>> SWIFTNESS = register(
            "swiftness", SwiftnessEnchantmentEffect.CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<WaterGelEnchantmentEffect>> WATERGEL = register(
            "watergel", WaterGelEnchantmentEffect.CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<DashEnchantmentEffect>> DASH = register(
            "dash", DashEnchantmentEffect.CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AirJumpEnchantmentEffect>> AIR_JUMP = register(
            "air_jump", AirJumpEnchantmentEffect.CODEC);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlinkEnchantmentEffect>> BLINK = register(
            "blink", BlinkEnchantmentEffect.CODEC);

    public static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, Codec<T> codec) {
        return REGISTRAR.registerComponentType(name, builder -> builder.persistent(codec));
    }

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }
}
