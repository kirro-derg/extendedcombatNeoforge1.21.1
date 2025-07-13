package dev.kirro.extendedcombat.item;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.item.custom.ModArmorItem;
import dev.kirro.extendedcombat.item.custom.NetherPickaxeItem;
import dev.kirro.extendedcombat.item.custom.PickSwordItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExtendedCombat.MOD_ID);

    public static final DeferredItem<Item> NETHER_STEEL_INGOT = ITEMS.register("nether_steel_ingot",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> HANDLE = ITEMS.register("handle",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> NETHER_STEEL_UPGRADE = ITEMS.register("nether_steel_upgrade",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<PickSwordItem> NETHER_STEEL_GREATSWORD = ITEMS.register("nether_steel_greatsword",
            () -> new PickSwordItem(ModToolTiers.NETHER_STEEL, new Item.Properties().fireResistant()
                    .attributes(PickSwordItem.createAttributes(ModToolTiers.NETHER_STEEL, 1, -2.4f))));
    public static final DeferredItem<NetherPickaxeItem> NETHER_STEEL_PICKAXE = ITEMS.register("nether_steel_pickaxe",
            () -> new NetherPickaxeItem(ModToolTiers.NETHER_STEEL, new Item.Properties().fireResistant()
                    .attributes(NetherPickaxeItem.createAttributes(ModToolTiers.NETHER_STEEL, -10, -2.8f))));

    public static final DeferredItem<ModArmorItem> NETHER_STEEL_HELMET = ITEMS.register("nether_steel_helmet",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().stacksTo(1)));
    public static final DeferredItem<ModArmorItem> NETHER_STEEL_CHESTPLATE = ITEMS.register("nether_steel_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().stacksTo(1)));
    public static final DeferredItem<ModArmorItem> NETHER_STEEL_LEGGINGS = ITEMS.register("nether_steel_leggings",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().stacksTo(1)));
    public static final DeferredItem<ModArmorItem> NETHER_STEEL_BOOTS = ITEMS.register("nether_steel_boots",
            () -> new ModArmorItem(ModArmorMaterials.NETHER_STEEL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().stacksTo(1)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
