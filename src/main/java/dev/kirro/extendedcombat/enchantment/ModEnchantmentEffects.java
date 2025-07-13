package dev.kirro.extendedcombat.enchantment;

import com.mojang.serialization.MapCodec;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.enchantment.custom.DashEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ExtendedCombat.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> DASH =
            ENTITY_ENCHANTMENT_EFFECTS.register("dash", () -> DashEnchantmentEffect.CODEC);


    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
