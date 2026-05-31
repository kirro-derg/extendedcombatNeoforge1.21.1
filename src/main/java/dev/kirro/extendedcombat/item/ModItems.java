package dev.kirro.extendedcombat.item;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.item.custom.*;
import dev.kirro.extendedcombat.tags.ModEnchantmentTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.stream.IntStream;

public interface ModItems {
    float greatswordAttackSpeed = -2.6f;
    float greatswordSweepRatio = 0.55f;
    float halberdAttackSpeed = -2.5f;
    float halberdAttackRange = 1.0f;
    DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExtendedCombat.MOD_ID);

    DeferredItem<Item> NETHER_STEEL_INGOT = register("nether_steel_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    DeferredItem<Item> ECHO_STEEL_INGOT = register("echo_steel_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    DeferredItem<Item> HANDLE = register("handle",
            () -> new Item(new Item.Properties()));
    DeferredItem<Item> POLE = register("pole",
            () -> new Item(new Item.Properties()));
    DeferredItem<Item> NETHER_STEEL_UPGRADE = register("nether_steel_upgrade",
            () -> new Item(new Item.Properties().fireResistant()));
    DeferredItem<Item> ECHO_STEEL_UPGRADE = register("echo_steel_upgrade",
            () -> new Item(new Item.Properties().fireResistant()));

    DeferredItem<Item> WOODEN_GREATSWORD = register("wooden_greatsword",
            () -> new GreatswordItem(Tiers.WOOD, new Item.Properties()
                    .attributes(GreatswordItem.createAttributes(Tiers.WOOD, 7, greatswordAttackSpeed, greatswordSweepRatio))));
    DeferredItem<Item> STONE_GREATSWORD = register("stone_greatsword",
            () -> new GreatswordItem(Tiers.STONE, new Item.Properties().durability(Tiers.STONE.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.STONE, 7, greatswordAttackSpeed, greatswordSweepRatio))));
    DeferredItem<Item> IRON_GREATSWORD = register("iron_greatsword",
            () -> new GreatswordItem(Tiers.IRON, new Item.Properties().durability(Tiers.IRON.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.IRON, 7, greatswordAttackSpeed, greatswordSweepRatio))));
    DeferredItem<Item> GOLDEN_GREATSWORD = register("golden_greatsword",
            () -> new GreatswordItem(Tiers.GOLD, new Item.Properties().durability(Tiers.GOLD.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.GOLD, 7, greatswordAttackSpeed, greatswordSweepRatio))));
    DeferredItem<Item> DIAMOND_GREATSWORD = register("diamond_greatsword",
            () -> new GreatswordItem(Tiers.DIAMOND, new Item.Properties().durability(Tiers.DIAMOND.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.DIAMOND, 7, greatswordAttackSpeed, greatswordSweepRatio))));
    DeferredItem<Item> NETHERITE_GREATSWORD = register("netherite_greatsword",
            () -> new GreatswordItem(Tiers.NETHERITE, new Item.Properties().fireResistant().durability(Tiers.NETHERITE.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.NETHERITE, 7, greatswordAttackSpeed, greatswordSweepRatio))));
    DeferredItem<Item> NETHER_STEEL_GREATSWORD = register("nether_steel_greatsword",
            () -> new GreatswordItem(ModToolTiers.NETHER_STEEL, new Item.Properties().fireResistant()
                    .attributes(GreatswordItem.createAttributes(ModToolTiers.NETHER_STEEL, 7, greatswordAttackSpeed, greatswordSweepRatio))));
    DeferredItem<Item> ECHO_STEEL_GREATSWORD = register("echo_steel_greatsword",
            () -> new GreatswordItem(ModToolTiers.ECHO_STEEL, new Item.Properties().fireResistant()
                    .attributes(GreatswordItem.createAttributes(ModToolTiers.ECHO_STEEL, 7, greatswordAttackSpeed, greatswordSweepRatio))));

    DeferredItem<Item> NETHER_STEEL_PICKAXE = register("nether_steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.NETHER_STEEL, new Item.Properties().fireResistant()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.NETHER_STEEL, 0, -2.8f))));
    DeferredItem<Item> ECHO_STEEL_PICKAXE = register("echo_steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ECHO_STEEL, new Item.Properties().fireResistant()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ECHO_STEEL, 0, -2.8f))));

    DeferredItem<Item> WOODEN_HAMMER = register("wooden_hammer",
            () -> new HammerItem(Tiers.WOOD, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.WOOD, 1f, -3.2f))));
    DeferredItem<Item> STONE_HAMMER = register("stone_hammer",
            () -> new HammerItem(Tiers.STONE, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.STONE, 1f, -3.2f))));
    DeferredItem<Item> IRON_HAMMER = register("iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.IRON, 1f, -3.2f))));
    DeferredItem<Item> GOLDEN_HAMMER = register("golden_hammer",
            () -> new HammerItem(Tiers.GOLD, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.GOLD, 1f, -3.2f))));
    DeferredItem<Item> DIAMOND_HAMMER = register("diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.DIAMOND, 1f, -3.2f))));
    DeferredItem<Item> NETHERITE_HAMMER = register("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties().fireResistant()
                    .attributes(DiggerItem.createAttributes(Tiers.NETHERITE, 1f, -3.2f))));
    DeferredItem<Item> NETHER_STEEL_HAMMER = register("nether_steel_hammer",
            () -> new HammerItem(ModToolTiers.NETHER_STEEL,  new Item.Properties().fireResistant()
                    .attributes(DiggerItem.createAttributes(ModToolTiers.NETHER_STEEL, 1f, -3.2f))));
    DeferredItem<Item> ECHO_STEEL_HAMMER = register("echo_steel_hammer",
            () -> new HammerItem(ModToolTiers.ECHO_STEEL, new Item.Properties().fireResistant()
                    .attributes(DiggerItem.createAttributes(ModToolTiers.ECHO_STEEL, 1f, -3.2f))));

    DeferredItem<Item> NETHER_STEEL_HELMET = register("nether_steel_helmet",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().stacksTo(1).durability(8124)));
    DeferredItem<Item> NETHER_STEEL_CHESTPLATE = register("nether_steel_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().stacksTo(1).durability(8124)));
    DeferredItem<Item> NETHER_STEEL_LEGGINGS = register("nether_steel_leggings",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().stacksTo(1).durability(8124)));
    DeferredItem<Item> NETHER_STEEL_BOOTS = register("nether_steel_boots",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().stacksTo(1).durability(8124)));

    DeferredItem<Item> ECHO_STEEL_HELMET = register("echo_steel_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().stacksTo(1).durability(9124)));
    DeferredItem<Item> ECHO_STEEL_CHESTPLATE = register("echo_steel_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().stacksTo(1).durability(9124)));
    DeferredItem<Item> ECHO_STEEL_LEGGINGS = register("echo_steel_leggings",
            () -> new ModArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().stacksTo(1).durability(9124)));
    DeferredItem<Item> ECHO_STEEL_BOOTS = register("echo_steel_boots",
            () -> new ModArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().stacksTo(1).durability(9124)));

    DeferredItem<Item> HUNTER_MASK = register("hunter_mask", () -> new HunterMaskItem(ModArmorMaterials.WOOL, ArmorItem.Type.HELMET, new Item.Properties().durability(512)));
    DeferredItem<Item> NETHER_STEEL_MASK = register("nether_steel_mask", () -> new HunterMaskItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.HELMET, new Item.Properties().durability(8124).fireResistant()));
    DeferredItem<Item> ECHO_STEEL_MASK = register("echo_steel_mask", () -> new HunterMaskItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.HELMET, new Item.Properties().durability(9124).fireResistant()));

    DeferredItem<Item> HUNTER_CLOAK = register("hunter_cloak", () -> new WoolArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(512)));
    DeferredItem<Item> NETHER_STEEL_CLOAK = register("nether_steel_cloak", () -> new WoolArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(8124).fireResistant()));
    DeferredItem<Item> ECHO_STEEL_CLOAK = register("echo_steel_cloak", () -> new WoolArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(9124).fireResistant()));

    DeferredItem<Item> HUNTER_LEGGINGS = register("hunter_leggings", () -> new WoolArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(512)));
    DeferredItem<Item> NETHER_STEEL_HUNTER_LEGGINGS = register("nether_steel_hunter_leggings", () -> new WoolArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(8124).fireResistant()));
    DeferredItem<Item> ECHO_STEEL_HUNTER_LEGGINGS = register("echo_steel_hunter_leggings", () -> new WoolArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(9124).fireResistant()));

    DeferredItem<Item> HUNTER_BOOTS = register("hunter_boots", () -> new WoolArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.BOOTS, new Item.Properties().durability(512)));
    DeferredItem<Item> NETHER_STEEL_HUNTER_BOOTS = register("nether_steel_hunter_boots", () -> new WoolArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().durability(8124).fireResistant()));
    DeferredItem<Item> ECHO_STEEL_HUNTER_BOOTS = register("echo_steel_hunter_boots", () -> new WoolArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().durability(9124).fireResistant()));

    DeferredItem<Item> ECHO_REINFORCED_ELYTRA = register("echo_steel_reinforced_elytra",
            () -> new ModElytra(new Item.Properties().durability(864).rarity(Rarity.RARE)));

    DeferredItem<Item> BLACK_APPLE = register("black_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BLACK_APPLE)));
    DeferredItem<Item> BLACK_APPLE_SEED = register("black_apple_seed",
            () -> new BlockItem(ModBlocks.BLACK_APPLE_BUSH.get(), new Item.Properties().food(ModFoodProperties.BLACK_APPLE_SEED)));
    DeferredItem<Item> GOLDEN_STEAK = register("golden_steak",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GOLDEN_STEAK)));
    DeferredItem<Item> HONEY_BREAD = register("honey_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.HONEY_BREAD)));

    DeferredItem<Item> MILK_BOTTLE = register("milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(32), MilkBottleItem.MilkType.PLAIN));
    DeferredItem<Item> SWEET_BERRY_MILK_BOTTLE = register("sweet_berry_milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(32), MilkBottleItem.MilkType.SWEET_BERRY));
    DeferredItem<Item> CHOCOLATE_MILK_BOTTLE = register("chocolate_milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(32), MilkBottleItem.MilkType.CHOCOLATE));

    DeferredItem<Item> WOODEN_HALBERD = register("wooden_halberd",
            () -> new HalberdItem(Tiers.WOOD, new Item.Properties().durability(Tiers.WOOD.getUses())
                    .attributes(HalberdItem.createAttributes(Tiers.WOOD, 5, halberdAttackSpeed, halberdAttackRange))));
    DeferredItem<Item> STONE_HALBERD = register("stone_halberd",
            () -> new HalberdItem(Tiers.STONE, new Item.Properties().durability(Tiers.STONE.getUses())
                    .attributes(HalberdItem.createAttributes(Tiers.STONE, 5, halberdAttackSpeed, halberdAttackRange))));
    DeferredItem<Item> IRON_HALBERD = register("iron_halberd",
            () -> new HalberdItem(Tiers.IRON, new Item.Properties().durability(Tiers.IRON.getUses())
                    .attributes(HalberdItem.createAttributes(Tiers.IRON, 5, halberdAttackSpeed, halberdAttackRange))));
    DeferredItem<Item> GOLDEN_HALBERD = register("golden_halberd",
            () -> new HalberdItem(Tiers.GOLD, new Item.Properties().durability(Tiers.GOLD.getUses())
                    .attributes(HalberdItem.createAttributes(Tiers.GOLD, 5, halberdAttackSpeed, halberdAttackRange))));
    DeferredItem<Item> DIAMOND_HALBERD = register("diamond_halberd",
            () -> new HalberdItem(Tiers.DIAMOND, new Item.Properties().durability(Tiers.DIAMOND.getUses())
                    .attributes(HalberdItem.createAttributes(Tiers.DIAMOND, 5, halberdAttackSpeed, halberdAttackRange))));
    DeferredItem<Item> NETHERITE_HALBERD = register("netherite_halberd",
            () -> new HalberdItem(Tiers.NETHERITE, new Item.Properties().fireResistant().durability(Tiers.NETHERITE.getUses())
                    .attributes(HalberdItem.createAttributes(Tiers.NETHERITE, 5, halberdAttackSpeed, halberdAttackRange))));
    DeferredItem<Item> NETHER_STEEL_HALBERD = register("nether_steel_halberd",
            () -> new HalberdItem(ModToolTiers.NETHER_STEEL, new Item.Properties().fireResistant().durability(ModToolTiers.NETHER_STEEL.getUses())
                    .attributes(HalberdItem.createAttributes(ModToolTiers.NETHER_STEEL, 5, halberdAttackSpeed, halberdAttackRange))));
    DeferredItem<Item> ECHO_STEEL_HALBERD = register("echo_steel_halberd",
            () -> new HalberdItem(ModToolTiers.ECHO_STEEL, new Item.Properties().fireResistant().durability(ModToolTiers.ECHO_STEEL.getUses())
                    .attributes(HalberdItem.createAttributes(ModToolTiers.ECHO_STEEL, 5, halberdAttackSpeed, halberdAttackRange))));

    //DeferredItem<Item> REPAIR_CHARM = register("repair_charm",
    //        () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    DeferredItem<Item> CHAINMAIL_SLEEVE = register("chainmail_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));
    DeferredItem<Item> LEATHER_SLEEVE = register("leather_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));
    DeferredItem<Item> IRON_SLEEVE = register("iron_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));
    DeferredItem<Item> GOLD_SLEEVE = register("gold_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));
    DeferredItem<Item> DIAMOND_SLEEVE = register("diamond_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));
    DeferredItem<Item> NETHERITE_SLEEVE = register("netherite_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));
    DeferredItem<Item> NETHER_STEEL_SLEEVE = register("nether_steel_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));
    DeferredItem<Item> ECHO_STEEL_SLEEVE = register("echo_steel_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));
    DeferredItem<Item> WOOL_SLEEVE = register("wool_sleeve",
            () -> new Item(new Item.Properties().stacksTo(1)));

    private static <T extends Item> DeferredItem<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        CREATIVE_MODE_TABS.register(eventBus);
        eventBus.addListener(ModItems::addCreative);
    }

    @SubscribeEvent
    static void addCreative(BuildCreativeModeTabContentsEvent entries) {
        if(entries.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            entries.insertAfter(Items.NETHERITE_INGOT.getDefaultInstance(), NETHER_STEEL_INGOT.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_INGOT.toStack(), ECHO_STEEL_INGOT.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.STICK.getDefaultInstance(), HANDLE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE.getDefaultInstance(), NETHER_STEEL_UPGRADE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_UPGRADE.toStack(), ECHO_STEEL_UPGRADE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(entries.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            entries.insertAfter(Items.WOODEN_HOE.getDefaultInstance(), WOODEN_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.STONE_HOE.getDefaultInstance(), STONE_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.IRON_HOE.getDefaultInstance(), IRON_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.GOLDEN_HOE.getDefaultInstance(), GOLDEN_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.DIAMOND_HOE.getDefaultInstance(), DIAMOND_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.NETHERITE_HOE.getDefaultInstance(), NETHERITE_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHERITE_HAMMER.toStack(), NETHER_STEEL_PICKAXE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_PICKAXE.toStack(), ECHO_STEEL_PICKAXE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_PICKAXE.toStack(), NETHER_STEEL_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_HAMMER.toStack(), ECHO_STEEL_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(entries.getTabKey() == CreativeModeTabs.COMBAT) {
            entries.insertAfter(Items.WOODEN_SWORD.getDefaultInstance(), WOODEN_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(WOODEN_GREATSWORD.toStack(), WOODEN_HALBERD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(Items.STONE_SWORD.getDefaultInstance(), STONE_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(STONE_GREATSWORD.toStack(), STONE_HALBERD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(Items.IRON_SWORD.getDefaultInstance(), IRON_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(IRON_GREATSWORD.toStack(), IRON_HALBERD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(Items.GOLDEN_SWORD.getDefaultInstance(), GOLDEN_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(GOLDEN_GREATSWORD.toStack(), GOLDEN_HALBERD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(Items.DIAMOND_SWORD.getDefaultInstance(), DIAMOND_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(DIAMOND_GREATSWORD.toStack(), DIAMOND_HALBERD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(Items.NETHERITE_SWORD.getDefaultInstance(), NETHERITE_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHERITE_GREATSWORD.toStack(), NETHERITE_HALBERD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(NETHERITE_HALBERD.toStack(), NETHER_STEEL_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_GREATSWORD.toStack(), NETHER_STEEL_HALBERD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(NETHER_STEEL_HALBERD.toStack(), ECHO_STEEL_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_GREATSWORD.toStack(), ECHO_STEEL_HALBERD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(Items.NETHERITE_BOOTS.getDefaultInstance(), NETHER_STEEL_HELMET.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_HELMET.toStack(), NETHER_STEEL_CHESTPLATE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_CHESTPLATE.toStack(), NETHER_STEEL_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_LEGGINGS.toStack(), NETHER_STEEL_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(NETHER_STEEL_BOOTS.toStack(), ECHO_STEEL_HELMET.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_HELMET.toStack(), ECHO_STEEL_CHESTPLATE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_CHESTPLATE.toStack(), ECHO_STEEL_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_LEGGINGS.toStack(), ECHO_STEEL_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(Items.TURTLE_HELMET.getDefaultInstance(), HUNTER_MASK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(HUNTER_MASK.toStack(), HUNTER_CLOAK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(HUNTER_CLOAK.toStack(), HUNTER_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(HUNTER_LEGGINGS.toStack(), HUNTER_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(HUNTER_BOOTS.toStack(), NETHER_STEEL_MASK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_MASK.toStack(), NETHER_STEEL_CLOAK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_CLOAK.toStack(), NETHER_STEEL_HUNTER_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_HUNTER_LEGGINGS.toStack(), NETHER_STEEL_HUNTER_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            entries.insertAfter(NETHER_STEEL_HUNTER_BOOTS.toStack(), ECHO_STEEL_MASK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_MASK.toStack(), ECHO_STEEL_CLOAK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_CLOAK.toStack(), ECHO_STEEL_HUNTER_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_HUNTER_LEGGINGS.toStack(), ECHO_STEEL_HUNTER_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(entries.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            entries.accept(ModBlocks.NETHER_STEEL_BLOCK);
            entries.accept(ModBlocks.ECHO_STEEL_BLOCK);
        }
        if(entries.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            entries.accept(ModBlocks.WARDING_STONE);
        }
        if(entries.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            entries.insertAfter(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance(), GOLDEN_STEAK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(GOLDEN_STEAK.toStack(), BLACK_APPLE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(BLACK_APPLE.toStack(), BLACK_APPLE_SEED.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.BREAD.getDefaultInstance(), HONEY_BREAD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.HONEY_BOTTLE.getDefaultInstance(), MILK_BOTTLE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(MILK_BOTTLE.toStack(), SWEET_BERRY_MILK_BOTTLE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(SWEET_BERRY_MILK_BOTTLE.toStack(), CHOCOLATE_MILK_BOTTLE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(entries.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            entries.insertAfter(Items.PINK_STAINED_GLASS_PANE.getDefaultInstance(), ModBlocks.FRAMED_GLASS_PANEL.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ModBlocks.FRAMED_GLASS_PANEL.toStack(), ModBlocks._STAINED_GLASS_PANEL.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtendedCombat.MOD_ID);

    DeferredHolder<CreativeModeTab, CreativeModeTab> EXTENDEDCOMBAT_ITEMS = CREATIVE_MODE_TABS.register("extended_combat_items",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extended_combat_items")).withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> NETHER_STEEL_GREATSWORD.get().getDefaultInstance()).displayItems((parameters, output) -> {
                        output.accept(NETHER_STEEL_INGOT);
                        output.accept(ECHO_STEEL_INGOT);
                        output.accept(HANDLE);
                        output.accept(POLE);
                        output.accept(NETHER_STEEL_UPGRADE);
                        output.accept(ECHO_STEEL_UPGRADE);

                        output.accept(ModBlocks.NETHER_STEEL_BLOCK);
                        output.accept(ModBlocks.ECHO_STEEL_BLOCK);
                        output.accept(ModBlocks.WARDING_STONE);
                        output.accept(ModBlocks.FRAMED_GLASS_PANEL);

                        output.accept(WOODEN_GREATSWORD);
                        output.accept(STONE_GREATSWORD);
                        output.accept(IRON_GREATSWORD);
                        output.accept(GOLDEN_GREATSWORD);
                        output.accept(DIAMOND_GREATSWORD);
                        output.accept(NETHERITE_GREATSWORD);
                        output.accept(NETHER_STEEL_GREATSWORD);
                        output.accept(ECHO_STEEL_GREATSWORD);

                        output.accept(WOODEN_HALBERD);
                        output.accept(STONE_HALBERD);
                        output.accept(IRON_HALBERD);
                        output.accept(GOLDEN_HALBERD);
                        output.accept(DIAMOND_HALBERD);
                        output.accept(NETHERITE_HALBERD);
                        output.accept(NETHER_STEEL_HALBERD);
                        output.accept(ECHO_STEEL_HALBERD);

                        output.accept(NETHER_STEEL_PICKAXE);
                        output.accept(ECHO_STEEL_PICKAXE);

                        output.accept(WOODEN_HAMMER);
                        output.accept(STONE_HAMMER);
                        output.accept(IRON_HAMMER);
                        output.accept(GOLDEN_HAMMER);
                        output.accept(DIAMOND_HAMMER);
                        output.accept(NETHERITE_HAMMER);
                        output.accept(NETHER_STEEL_HAMMER);
                        output.accept(ECHO_STEEL_HAMMER);

                        output.accept(HUNTER_MASK);
                        output.accept(HUNTER_CLOAK);
                        output.accept(HUNTER_LEGGINGS);
                        output.accept(HUNTER_BOOTS);
                        output.accept(NETHER_STEEL_HELMET);
                        output.accept(NETHER_STEEL_CHESTPLATE);
                        output.accept(NETHER_STEEL_LEGGINGS);
                        output.accept(NETHER_STEEL_BOOTS);
                        output.accept(NETHER_STEEL_MASK);
                        output.accept(NETHER_STEEL_CLOAK);
                        output.accept(NETHER_STEEL_HUNTER_LEGGINGS);
                        output.accept(NETHER_STEEL_HUNTER_BOOTS);
                        output.accept(ECHO_STEEL_HELMET);
                        output.accept(ECHO_STEEL_CHESTPLATE);
                        output.accept(ECHO_STEEL_LEGGINGS);
                        output.accept(ECHO_STEEL_BOOTS);
                        output.accept(ECHO_STEEL_MASK);
                        output.accept(ECHO_STEEL_CLOAK);
                        output.accept(ECHO_STEEL_HUNTER_LEGGINGS);
                        output.accept(ECHO_STEEL_HUNTER_BOOTS);
                        output.accept(ECHO_REINFORCED_ELYTRA);

                        output.accept(CHAINMAIL_SLEEVE);
                        output.accept(LEATHER_SLEEVE);
                        output.accept(IRON_SLEEVE);
                        output.accept(GOLD_SLEEVE);
                        output.accept(DIAMOND_SLEEVE);
                        output.accept(NETHERITE_SLEEVE);
                        output.accept(NETHER_STEEL_SLEEVE);
                        output.accept(ECHO_STEEL_SLEEVE);
                        output.accept(WOOL_SLEEVE);

                        output.accept(BLACK_APPLE);
                        output.accept(GOLDEN_STEAK);
                        output.accept(HONEY_BREAD);

                        output.accept(MILK_BOTTLE);
                        output.accept(SWEET_BERRY_MILK_BOTTLE);
                        output.accept(CHOCOLATE_MILK_BOTTLE);

                        parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantment) -> {
                            generateEnchantmentBookTypes(output, enchantment, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                        });
                    }).build());


    private static void generateEnchantmentBookTypes(CreativeModeTab.Output output, HolderLookup<Enchantment> enchantments, CreativeModeTab.TabVisibility tabVisibility) {
        enchantments.listElements().forEach((reference) -> {
            IntStream stream = IntStream.rangeClosed(reference.value().getMinLevel(), reference.value().getMaxLevel());
            for (int level : stream.toArray()) {
                if (reference.is(ModEnchantmentTags.EXTENDEDCOMBAT_ENCHANTMENTS)) {
                    ItemStack stack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(reference, level));
                    output.accept(stack, tabVisibility);
                }
            }
        });
    }

}
