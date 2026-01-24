package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.item.ModItems;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ExtendedCombat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModItemTags.ALWAYS_HAS_DURABILITY)
                .add(Items.MACE)
        ;
        
        tag(ModItemTags.GREATSWORDS)
                .add(ModItems.WOODEN_GREATSWORD.get())
                .add(ModItems.STONE_GREATSWORD.get())
                .add(ModItems.IRON_GREATSWORD.get())
                .add(ModItems.GOLDEN_GREATSWORD.get())
                .add(ModItems.DIAMOND_GREATSWORD.get())
                .add(ModItems.NETHERITE_GREATSWORD.get())
                .add(ModItems.NETHER_STEEL_GREATSWORD.get())
                .add(ModItems.ECHO_STEEL_GREATSWORD.get())
        ;

        tag(ModItemTags.SLEEVED_ARMOR)
                .add(Items.CHAINMAIL_CHESTPLATE)
                .add(Items.LEATHER_CHESTPLATE)
                .add(Items.IRON_CHESTPLATE)
                .add(Items.GOLDEN_CHESTPLATE)
                .add(Items.DIAMOND_CHESTPLATE)
                .add(Items.NETHERITE_CHESTPLATE)
                .add(ModItems.NETHER_STEEL_CHESTPLATE.get())
                .add(ModItems.ECHO_STEEL_CHESTPLATE.get())
                .addTag(ModItemTags.CLOAK)
        ;

        tag(ModItemTags.REPAIRABLE_ITEMS)
                .addTag(ModItemTags.ECHO_STEEL_WEARABLES)
                .addTag(ModItemTags.ECHO_STEEL_ITEMS)
        ;

        tag(ModItemTags.DASH_ENCHANTABLE)
                .addTag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .addTag(ModItemTags.HUNTER_LEGGINGS)
        ;

        tag(ModItemTags.AIR_JUMP_ENCHANTABLE)
                .addTag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .addTag(ModItemTags.HUNTER_BOOTS)
        ;

        tag(ModItemTags.BLINK_ENCHANTABLE)
                .addTag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .addTag(ModItemTags.CLOAK)
        ;

        tag(ModItemTags.OBSCURITY_ENCHANTABLE)
                .addTag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .addTag(ModItemTags.MASK)
        ;

        tag(ModItemTags.VANITY_ENCHANTABLE)
                .addTag(ItemTags.ARMOR_ENCHANTABLE)
        ;

        tag(ModItemTags.CLOAK)
                .add(ModItems.HUNTER_CLOAK.get())
                .add(ModItems.NETHER_STEEL_CLOAK.get())
                .add(ModItems.ECHO_STEEL_CLOAK.get())
        ;

        tag(ModItemTags.MASK)
                .add(ModItems.HUNTER_MASK.get())
                .add(ModItems.NETHER_STEEL_MASK.get())
                .add(ModItems.ECHO_STEEL_MASK.get())
        ;

        tag(ModItemTags.STEALTH_ENCHANTABLE)
                .addTag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .addTag(ModItemTags.CLOAK)
        ;

        tag(ModItemTags.CONCUSSION_ENCHANTABLE)
                .addTag(ModItemTags.GREATSWORDS)
        ;

        tag(ModItemTags.FLUID_WALKER_ENCHANTABLE)
                .addTag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .addTag(ModItemTags.HUNTER_BOOTS)
        ;

        tag(ModItemTags.SWIFTNESS_ENCHANTABLE)
                .addTag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .addTag(ModItemTags.HUNTER_LEGGINGS)
        ;

        tag(ModItemTags.FLAME_RESISTANT_ARMOR)
                .addTag(ModItemTags.NETHER_STEEL_WEARABLES)
                .addTag(ModItemTags.ECHO_STEEL_WEARABLES)
        ;

        tag(ModItemTags.HUNTER_LEGGINGS)
                .add(ModItems.HUNTER_LEGGINGS.get())
                .add(ModItems.NETHER_STEEL_HUNTER_LEGGINGS.get())
                .add(ModItems.ECHO_STEEL_HUNTER_LEGGINGS.get())
        ;

        tag(ModItemTags.HUNTER_BOOTS)
                .add(ModItems.HUNTER_BOOTS.get())
                .add(ModItems.NETHER_STEEL_HUNTER_BOOTS.get())
                .add(ModItems.ECHO_STEEL_HUNTER_BOOTS.get())
        ;

        tag(ModItemTags.NETHER_STEEL_WEARABLES)
                .add(ModItems.NETHER_STEEL_BOOTS.get())
                .add(ModItems.NETHER_STEEL_LEGGINGS.get())
                .add(ModItems.NETHER_STEEL_CHESTPLATE.get())
                .add(ModItems.NETHER_STEEL_HELMET.get())
                .add(ModItems.NETHER_STEEL_CLOAK.get())
                .add(ModItems.NETHER_STEEL_MASK.get())
                .add(ModItems.NETHER_STEEL_HUNTER_LEGGINGS.get())
                .add(ModItems.NETHER_STEEL_HUNTER_BOOTS.get())
        ;

        tag(ModItemTags.NETHER_STEEL_ITEMS)
                .add(ModItems.NETHER_STEEL_GREATSWORD.get())
                .add(ModItems.NETHER_STEEL_PICKAXE.get())
                .add(ModItems.NETHER_STEEL_HAMMER.get())
        ;

        tag(ModItemTags.ECHO_STEEL_WEARABLES)
                .add(ModItems.ECHO_STEEL_BOOTS.get())
                .add(ModItems.ECHO_STEEL_LEGGINGS.get())
                .add(ModItems.ECHO_STEEL_CHESTPLATE.get())
                .add(ModItems.ECHO_STEEL_HELMET.get())
                .add(ModItems.ECHO_REINFORCED_ELYTRA.get())
                .add(ModItems.ECHO_STEEL_CLOAK.get())
                .add(ModItems.ECHO_STEEL_MASK.get())
                .add(ModItems.ECHO_STEEL_HUNTER_LEGGINGS.get())
                .add(ModItems.ECHO_STEEL_HUNTER_BOOTS.get())
        ;

        tag(ModItemTags.ECHO_STEEL_ITEMS)
                .add(ModItems.ECHO_STEEL_HAMMER.get())
                .add(ModItems.ECHO_STEEL_GREATSWORD.get())
        ;

        tag(ModItemTags.KEEPSAKE_ENCHANTABLE)
                .add(ModItems.ECHO_STEEL_BOOTS.get())
                .add(ModItems.ECHO_STEEL_LEGGINGS.get())
                .add(ModItems.ECHO_STEEL_CHESTPLATE.get())
                .add(ModItems.ECHO_STEEL_HELMET.get())
                .add(ModItems.ECHO_REINFORCED_ELYTRA.get())
                .add(ModItems.ECHO_STEEL_HAMMER.get())
                .add(ModItems.ECHO_STEEL_GREATSWORD.get())
                .add(ModItems.ECHO_STEEL_MASK.get())
                .add(ModItems.ECHO_STEEL_CLOAK.get())
                .add(ModItems.ECHO_STEEL_HUNTER_LEGGINGS.get())
                .add(ModItems.ECHO_STEEL_HUNTER_BOOTS.get())
                .addTag(ItemTags.DURABILITY_ENCHANTABLE)
        ;

        tag(ModItemTags.BURST_ENCHANTABLE)
                .add(Items.ELYTRA)
                .add(ModItems.ECHO_REINFORCED_ELYTRA.get())
        ;

        tag(ModItemTags.WATERGEL_ENCHANTABLE)
                .addTag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .addTag(ModItemTags.HUNTER_LEGGINGS)
        ;

        tag(ItemTags.PIGLIN_LOVED)
                .addTag(ModItemTags.NETHER_STEEL_WEARABLES)
                .addTag(ModItemTags.NETHER_STEEL_ITEMS)
                .addTag(ModItemTags.ECHO_STEEL_ITEMS)
                .addTag(ModItemTags.ECHO_STEEL_WEARABLES)
                .replace(false)
        ;

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ModItemTags.NETHER_STEEL_WEARABLES)
                .addTag(ModItemTags.NETHER_STEEL_ITEMS)
                .add(ModItems.WOODEN_HAMMER.get())
                .add(ModItems.STONE_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())
                .add(ModItems.NETHER_STEEL_HAMMER.get())
                .replace(false)
        ;

        tag(ItemTags.TRIMMABLE_ARMOR)
                .addTag(ModItemTags.NETHER_STEEL_WEARABLES)
                .addTag(ModItemTags.ECHO_STEEL_WEARABLES)
                .add(ModItems.HUNTER_BOOTS.get())
                .add(ModItems.HUNTER_LEGGINGS.get())
                .add(ModItems.HUNTER_CLOAK.get())
                .add(ModItems.HUNTER_MASK.get())
                .replace(false)
        ;

        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.NETHER_STEEL_GREATSWORD.get())
                .add(ModItems.NETHER_STEEL_PICKAXE.get())
                .add(ModItems.WOODEN_HAMMER.get())
                .add(ModItems.STONE_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())
                .add(ModItems.NETHER_STEEL_HAMMER.get())
                .add(ModItems.ECHO_STEEL_HAMMER.get())
                .replace(false)
        ;
        tag(ItemTags.PICKAXES)
                .add(ModItems.NETHER_STEEL_GREATSWORD.get())
                .add(ModItems.NETHER_STEEL_PICKAXE.get())
                .add(ModItems.WOODEN_HAMMER.get())
                .add(ModItems.STONE_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())
                .add(ModItems.NETHER_STEEL_HAMMER.get())
                .add(ModItems.ECHO_STEEL_HAMMER.get())
                .replace(false)
        ;
        tag(ItemTags.AXES)
                .add(ModItems.ECHO_STEEL_GREATSWORD.get())
                .replace(false)
        ;
        tag(ItemTags.SHOVELS)
                .replace(false)
        ;
        tag(ItemTags.SWORDS)
                .add(ModItems.WOODEN_GREATSWORD.get())
                .add(ModItems.STONE_GREATSWORD.get())
                .add(ModItems.IRON_GREATSWORD.get())
                .add(ModItems.GOLDEN_GREATSWORD.get())
                .add(ModItems.DIAMOND_GREATSWORD.get())
                .add(ModItems.NETHERITE_GREATSWORD.get())
                .add(ModItems.NETHER_STEEL_GREATSWORD.get())
                .add(ModItems.ECHO_STEEL_GREATSWORD.get())
                .replace(false)
        ;

        tag(ItemTags.HOES)
                .add(ModItems.ECHO_STEEL_GREATSWORD.get())
                .replace(false)
        ;

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.NETHER_STEEL_HELMET.get())
                .add(ModItems.NETHER_STEEL_CHESTPLATE.get())
                .add(ModItems.NETHER_STEEL_LEGGINGS.get())
                .add(ModItems.NETHER_STEEL_BOOTS.get())
                .add(ModItems.ECHO_STEEL_BOOTS.get())
                .add(ModItems.ECHO_STEEL_LEGGINGS.get())
                .add(ModItems.ECHO_STEEL_CHESTPLATE.get())
                .add(ModItems.ECHO_STEEL_HELMET.get())
                .replace(false)
        ;


        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.NETHER_STEEL_HELMET.get())
                .replace(false)
        ;
        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.NETHER_STEEL_CHESTPLATE.get())
                .replace(false)
        ;
        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.NETHER_STEEL_LEGGINGS.get())
                .replace(false)
        ;
        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.NETHER_STEEL_BOOTS.get())
                .replace(false)
        ;

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.NETHER_STEEL_HELMET.get())
                .add(ModItems.ECHO_STEEL_HELMET.get())
                .replace(false)
        ;
        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.NETHER_STEEL_CHESTPLATE.get())
                .add(ModItems.ECHO_STEEL_CHESTPLATE.get())
                .replace(false)
        ;
        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.NETHER_STEEL_LEGGINGS.get())
                .add(ModItems.ECHO_STEEL_LEGGINGS.get())
                .replace(false)
        ;
        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.NETHER_STEEL_BOOTS.get())
                .add(ModItems.ECHO_STEEL_BOOTS.get())
                .replace(false)
        ;
    }
}
