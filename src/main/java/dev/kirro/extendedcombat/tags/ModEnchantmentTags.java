package dev.kirro.extendedcombat.tags;

import dev.kirro.extendedcombat.ExtendedCombat;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public interface ModEnchantmentTags extends EnchantmentTags {

    TagKey<Enchantment> DURABILITY_EXCLUSIVE_SET = create("exclusive_set/durability");
    TagKey<Enchantment> EXTENDEDCOMBAT_ENCHANTMENTS = create("extendedcombat_enchantments");

    private static TagKey<Enchantment> create(String id) {
        return TagKey.create(Registries.ENCHANTMENT, ExtendedCombat.id(id));
    }
}
