package dev.kirro.extendedcombat.tags;

import dev.kirro.extendedcombat.ExtendedCombat;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface ModItemTags {
    TagKey<Item> ALWAYS_HAS_DURABILITY = create("always_has_durability");
    TagKey<Item> REPAIRABLE_ITEMS = create("repairable_items");
    TagKey<Item> NETHER_STEEL_WEARABLES = create("nether_steel_wearables");
    TagKey<Item> NETHER_STEEL_ITEMS = create("nether_steel_tools");
    TagKey<Item> ECHO_STEEL_WEARABLES = create("echo_steel_wearables");
    TagKey<Item> ECHO_STEEL_ITEMS = create("echo_items");
    TagKey<Item> GREATSWORDS = create("greatswords");
    TagKey<Item> SLEEVED_ARMOR = create("sleeved_armor");
    TagKey<Item> CLOAK = create("cloak");
    TagKey<Item> MASK = create("mask");
    TagKey<Item> HUNTER_LEGGINGS = create("hunter_leggings");
    TagKey<Item> HUNTER_BOOTS = create("hunter_boots");
    TagKey<Item> FLAME_RESISTANT_ARMOR = create("flame_resistant_armor");
    TagKey<Item> OBSCURITY_ENCHANTABLE = create("enchantable/obscurity");
    TagKey<Item> STEALTH_ENCHANTABLE = create("enchantable/stealth");
    TagKey<Item> CONCUSSION_ENCHANTABLE = create("enchantable/concussion");
    TagKey<Item> FLUID_WALKER_ENCHANTABLE = create("enchantable/fluid_walker");
    TagKey<Item> SWIFTNESS_ENCHANTABLE = create("enchantable/swiftness");
    TagKey<Item> WATERGEL_ENCHANTABLE = create("enchantable/watergel");

    private static TagKey<Item> create(String id) {
        return TagKey.create(Registries.ITEM, ExtendedCombat.id(id));
    }
}
