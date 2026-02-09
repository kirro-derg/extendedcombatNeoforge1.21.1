package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.enchantment.ModEnchantments;
import dev.kirro.extendedcombat.tags.ModEnchantmentTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends EnchantmentTagsProvider {
    public ModEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ExtendedCombat.MOD_ID, null);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .addOptional(ModEnchantments.OBSCURITY.location())
                .addOptional(ModEnchantments.STEALTH.location())
                .addOptional(ModEnchantments.CONCUSSION.location())
                .addOptional(ModEnchantments.FLUID_WALKER.location())
                .addOptional(ModEnchantments.SWIFTNESS.location())
                .addOptional(ModEnchantments.WATERGEL.location())
                .remove(Enchantments.UNBREAKING)
                .remove(Enchantments.BANE_OF_ARTHROPODS)
                .remove(Enchantments.SMITE)
                .remove(Enchantments.FIRE_PROTECTION)
                .remove(Enchantments.PROJECTILE_PROTECTION)
                .remove(Enchantments.THORNS)
                .replace(false)
        ;
    }
}
