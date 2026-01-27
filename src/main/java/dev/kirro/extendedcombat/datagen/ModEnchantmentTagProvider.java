package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.enchantment.ModEnchantments;
import dev.kirro.extendedcombat.tags.ModEnchantmentTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends EnchantmentTagsProvider {
    public ModEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .add(ModEnchantments.AIR_JUMP)
                .add(ModEnchantments.BLINK)
                .add(ModEnchantments.DASH)
                .add(ModEnchantments.OBSCURITY)
                .add(ModEnchantments.VANITY)
                .add(ModEnchantments.STEALTH)
                .add(Enchantments.MENDING)
                .add(ModEnchantments.CONCUSSION)
                .add(ModEnchantments.FLUID_WALKER)
                .add(ModEnchantments.SWIFTNESS)
                .add(ModEnchantments.WATERGEL)
                .add(ModEnchantments.KEEPSAKE)
        ;

        tag(ModEnchantmentTags.EXTENDEDCOMBAT_ENCHANTMENTS)
                .add(ModEnchantments.AIR_JUMP)
                .add(ModEnchantments.BLINK)
                .add(ModEnchantments.DASH)
                .add(ModEnchantments.OBSCURITY)
                .add(ModEnchantments.VANITY)
                .add(ModEnchantments.STEALTH)
                .add(ModEnchantments.CONCUSSION)
                .add(ModEnchantments.FLUID_WALKER)
                .add(ModEnchantments.SWIFTNESS)
                .add(ModEnchantments.WATERGEL)
                .add(ModEnchantments.KEEPSAKE)
        ;

        tag(ModEnchantmentTags.DURABILITY_EXCLUSIVE_SET)
                .add(ModEnchantments.KEEPSAKE, Enchantments.MENDING)
        ;
    }
}
