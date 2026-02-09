package dev.kirro.extendedcombat.enchantment;

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

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ObscurityEnchantmentEffect>> OBSCURITY = REGISTRAR.registerComponentType("obscurity", builder -> builder.persistent(ObscurityEnchantmentEffect.CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<StealthEnchantmentEffect>>STEALTH = REGISTRAR.registerComponentType("stealth", builder -> builder.persistent(StealthEnchantmentEffect.CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ConcussionEnchantmentEffect>>CONCUSSION = REGISTRAR.registerComponentType("concussion", builder -> builder.persistent(ConcussionEnchantmentEffect.CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FluidWalkerEnchantmentEffect>>FLUID_WALKER = REGISTRAR.registerComponentType("fluid_walker", builder -> builder.persistent(FluidWalkerEnchantmentEffect.CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SwiftnessEnchantmentEffect>>SWIFTNESS = REGISTRAR.registerComponentType("swiftness", builder -> builder.persistent(SwiftnessEnchantmentEffect.CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<WaterGelEnchantmentEffect>> WATERGEL = REGISTRAR.registerComponentType("watergel", builder -> builder.persistent(WaterGelEnchantmentEffect.CODEC));

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }
}
