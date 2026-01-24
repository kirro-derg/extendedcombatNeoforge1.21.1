package dev.kirro.extendedcombat.item;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.item.custom.*;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModItems {
    DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExtendedCombat.MOD_ID);

    DeferredItem<Item> NETHER_STEEL_INGOT = registerItem("nether_steel_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    DeferredItem<Item> ECHO_STEEL_INGOT = registerItem("echo_steel_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    DeferredItem<Item> HANDLE = registerItem("handle",
            () -> new Item(new Item.Properties().fireResistant()));
    DeferredItem<Item> NETHER_STEEL_UPGRADE = registerItem("nether_steel_upgrade",
            () -> new Item(new Item.Properties().fireResistant()));
    DeferredItem<Item> ECHO_STEEL_UPGRADE = registerItem("echo_steel_upgrade",
            () -> new Item(new Item.Properties().fireResistant()));

    DeferredItem<Item> WOODEN_GREATSWORD = registerItem("wooden_greatsword",
            () -> new GreatswordItem(Tiers.WOOD, new Item.Properties()
                    .attributes(GreatswordItem.createAttributes(Tiers.WOOD, 7, -2.5f, 0.75f))));
    DeferredItem<Item> STONE_GREATSWORD = registerItem("stone_greatsword",
            () -> new GreatswordItem(Tiers.STONE, new Item.Properties().durability(Tiers.STONE.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.STONE, 7, -2.5f, 0.75f))));
    DeferredItem<Item> IRON_GREATSWORD = registerItem("iron_greatsword",
            () -> new GreatswordItem(Tiers.IRON, new Item.Properties().durability(Tiers.IRON.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.IRON, 7, -2.5f, 0.75f))));
    DeferredItem<Item> GOLDEN_GREATSWORD = registerItem("golden_greatsword",
            () -> new GreatswordItem(Tiers.GOLD, new Item.Properties().durability(Tiers.GOLD.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.GOLD, 7, -2.5f, 0.75f))));
    DeferredItem<Item> DIAMOND_GREATSWORD = registerItem("diamond_greatsword",
            () -> new GreatswordItem(Tiers.DIAMOND, new Item.Properties().durability(Tiers.DIAMOND.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.DIAMOND, 7, -2.5f, 0.75f))));
    DeferredItem<Item> NETHERITE_GREATSWORD = registerItem("netherite_greatsword",
            () -> new GreatswordItem(Tiers.NETHERITE, new Item.Properties().durability(Tiers.NETHERITE.getUses())
                    .attributes(GreatswordItem.createAttributes(Tiers.NETHERITE, 7, -2.5f, 0.75f))));
    DeferredItem<Item> NETHER_STEEL_GREATSWORD = registerItem("nether_steel_greatsword",
            () -> new PickSwordItem(ModToolTiers.NETHER_STEEL, new Item.Properties().fireResistant()
                    .attributes(PickSwordItem.createAttributes(ModToolTiers.NETHER_STEEL, 7, -2.4f, 0.75f))));
    DeferredItem<Item> ECHO_STEEL_GREATSWORD = registerItem("echo_steel_greatsword",
            () -> new AxeSwordItem(ModToolTiers.ECHO_STEEL, new Item.Properties().fireResistant()
                    .attributes(PickSwordItem.createAttributes(ModToolTiers.ECHO_STEEL, 7, -2.4f, 0.75f))));

    DeferredItem<Item> NETHER_STEEL_PICKAXE = registerItem("nether_steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.NETHER_STEEL, new Item.Properties().fireResistant()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.NETHER_STEEL, 8, -2.8f))));

    DeferredItem<Item> WOODEN_HAMMER = registerItem("wooden_hammer",
            () -> new HammerItem(Tiers.WOOD, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.WOOD, 1f, -3.2f))));
    DeferredItem<Item> STONE_HAMMER = registerItem("stone_hammer",
            () -> new HammerItem(Tiers.STONE, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.STONE, 1f, -3.2f))));
    DeferredItem<Item> IRON_HAMMER = registerItem("iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.IRON, 1f, -3.2f))));
    DeferredItem<Item> GOLDEN_HAMMER = registerItem("golden_hammer",
            () -> new HammerItem(Tiers.GOLD, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.GOLD, 1f, -3.2f))));
    DeferredItem<Item> DIAMOND_HAMMER = registerItem("diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(DiggerItem.createAttributes(Tiers.DIAMOND, 1f, -3.2f))));
    DeferredItem<Item> NETHERITE_HAMMER = registerItem("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties().fireResistant()
                    .attributes(DiggerItem.createAttributes(Tiers.NETHERITE, 1f, -3.2f))));
    DeferredItem<Item> NETHER_STEEL_HAMMER = registerItem("nether_steel_hammer",
            () -> new HammerItem(ModToolTiers.NETHER_STEEL,  new Item.Properties().fireResistant()
                    .attributes(DiggerItem.createAttributes(ModToolTiers.NETHER_STEEL, 1f, -3.2f))));
    DeferredItem<Item> ECHO_STEEL_HAMMER = registerItem("echo_steel_hammer",
            () -> new HammerItem(ModToolTiers.ECHO_STEEL, new Item.Properties().fireResistant()
                    .attributes(DiggerItem.createAttributes(ModToolTiers.ECHO_STEEL, 1f, -3.2f))));

    DeferredItem<Item> NETHER_STEEL_HELMET = registerItem("nether_steel_helmet",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().stacksTo(1)));
    DeferredItem<Item> NETHER_STEEL_CHESTPLATE = registerItem("nether_steel_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().stacksTo(1)));
    DeferredItem<Item> NETHER_STEEL_LEGGINGS = registerItem("nether_steel_leggings",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().stacksTo(1)));
    DeferredItem<Item> NETHER_STEEL_BOOTS = registerItem("nether_steel_boots",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().stacksTo(1)));

    DeferredItem<Item> ECHO_STEEL_HELMET = registerItem("echo_steel_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().stacksTo(1)));
    DeferredItem<Item> ECHO_STEEL_CHESTPLATE = registerItem("echo_steel_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().stacksTo(1)));
    DeferredItem<Item> ECHO_STEEL_LEGGINGS = registerItem("echo_steel_leggings",
            () -> new ModArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().stacksTo(1)));
    DeferredItem<Item> ECHO_STEEL_BOOTS = registerItem("echo_steel_boots",
            () -> new ModArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().stacksTo(1)));

    DeferredItem<Item> HUNTER_MASK = registerItem("hunter_mask", () -> new HunterMaskItem(ModArmorMaterials.WOOL, ArmorItem.Type.HELMET, new Item.Properties().durability(512)));
    DeferredItem<Item> NETHER_STEEL_MASK = registerItem("nether_steel_mask", () -> new HunterMaskItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.HELMET, new Item.Properties().durability(8124)));
    DeferredItem<Item> ECHO_STEEL_MASK = registerItem("echo_steel_mask", () -> new HunterMaskItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.HELMET, new Item.Properties().durability(9124)));

    DeferredItem<Item> HUNTER_CLOAK = registerItem("hunter_cloak", () -> new WoolArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(512)));
    DeferredItem<Item> NETHER_STEEL_CLOAK = registerItem("nether_steel_cloak", () -> new WoolArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(8124)));
    DeferredItem<Item> ECHO_STEEL_CLOAK = registerItem("echo_steel_cloak", () -> new WoolArmorItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(9124)));

    DeferredItem<Item> HUNTER_LEGGINGS = registerItem("hunter_leggings", () -> new HunterMaskItem(ModArmorMaterials.WOOL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(512)));
    DeferredItem<Item> NETHER_STEEL_HUNTER_LEGGINGS = registerItem("nether_steel_hunter_leggings", () -> new HunterMaskItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(8124)));
    DeferredItem<Item> ECHO_STEEL_HUNTER_LEGGINGS = registerItem("echo_steel_hunter_leggings", () -> new HunterMaskItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(9124)));

    DeferredItem<Item> HUNTER_BOOTS = registerItem("hunter_boots", () -> new HunterMaskItem(ModArmorMaterials.WOOL, ArmorItem.Type.BOOTS, new Item.Properties().durability(512)));
    DeferredItem<Item> NETHER_STEEL_HUNTER_BOOTS = registerItem("nether_steel_hunter_boots", () -> new HunterMaskItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().durability(8124)));
    DeferredItem<Item> ECHO_STEEL_HUNTER_BOOTS = registerItem("echo_steel_hunter_boots", () -> new HunterMaskItem(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().durability(9124)));

    DeferredItem<Item> ECHO_REINFORCED_ELYTRA = registerItem("echo_reinforced_elytra",
            () -> new ModElytra(ModArmorMaterials.ECHO_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(864).rarity(Rarity.RARE)));

    DeferredItem<Item> BLACK_APPLE = registerItem("black_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BLACK_APPLE)));
    DeferredItem<Item> BLACK_APPLE_SEED = registerItem("black_apple_seed",
            () -> new BlockItem(ModBlocks.BLACK_APPLE_BUSH.get(), new Item.Properties().food(ModFoodProperties.BLACK_APPLE_SEED)));
    DeferredItem<Item> GOLDEN_STEAK = registerItem("golden_steak",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GOLDEN_STEAK)));
    DeferredItem<Item> HONEY_BREAD = registerItem("honey_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.HONEY_BREAD)));

    DeferredItem<Item> MILK_BOTTLE = registerItem("milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(32), MilkBottleItem.MilkType.PLAIN));
    DeferredItem<Item> SWEET_BERRY_MILK_BOTTLE = registerItem("sweet_berry_milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(32), MilkBottleItem.MilkType.SWEET_BERRY));
    DeferredItem<Item> CHOCOLATE_MILK_BOTTLE = registerItem("chocolate_milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(32), MilkBottleItem.MilkType.CHOCOLATE));

    private static <T extends Item> DeferredItem<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
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
            entries.insertAfter(NETHER_STEEL_PICKAXE.toStack(), NETHER_STEEL_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_HAMMER.toStack(), ECHO_STEEL_HAMMER.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(entries.getTabKey() == CreativeModeTabs.COMBAT) {
            entries.insertAfter(Items.WOODEN_SWORD.getDefaultInstance(), WOODEN_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.STONE_SWORD.getDefaultInstance(), STONE_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.IRON_SWORD.getDefaultInstance(), IRON_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.GOLDEN_SWORD.getDefaultInstance(), GOLDEN_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.DIAMOND_SWORD.getDefaultInstance(), DIAMOND_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.NETHERITE_SWORD.getDefaultInstance(), NETHERITE_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHERITE_GREATSWORD.toStack(), NETHER_STEEL_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_GREATSWORD.toStack(), ECHO_STEEL_GREATSWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.NETHERITE_BOOTS.getDefaultInstance(), NETHER_STEEL_HELMET.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_HELMET.toStack(), NETHER_STEEL_CHESTPLATE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_CHESTPLATE.toStack(), NETHER_STEEL_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_LEGGINGS.toStack(), NETHER_STEEL_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_BOOTS.toStack(), ECHO_STEEL_HELMET.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_HELMET.toStack(), ECHO_STEEL_CHESTPLATE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_CHESTPLATE.toStack(), ECHO_STEEL_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_LEGGINGS.toStack(), ECHO_STEEL_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(Items.TURTLE_HELMET.getDefaultInstance(), HUNTER_MASK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(HUNTER_MASK.toStack(), NETHER_STEEL_MASK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_MASK.toStack(), ECHO_STEEL_MASK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_MASK.toStack(), HUNTER_CLOAK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(HUNTER_CLOAK.toStack(), NETHER_STEEL_CLOAK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_CLOAK.toStack(), ECHO_STEEL_CLOAK.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_CLOAK.toStack(), HUNTER_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(HUNTER_LEGGINGS.toStack(), NETHER_STEEL_HUNTER_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_HUNTER_LEGGINGS.toStack(), ECHO_STEEL_HUNTER_LEGGINGS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(ECHO_STEEL_HUNTER_LEGGINGS.toStack(), HUNTER_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(HUNTER_BOOTS.toStack(), NETHER_STEEL_HUNTER_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.insertAfter(NETHER_STEEL_HUNTER_BOOTS.toStack(), ECHO_STEEL_HUNTER_BOOTS.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
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

}
