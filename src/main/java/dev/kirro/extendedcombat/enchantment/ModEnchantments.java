package dev.kirro.extendedcombat.enchantment;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.datagen.ModDataPackProvider;
import dev.kirro.extendedcombat.enchantment.custom.*;
import dev.kirro.extendedcombat.tags.ModEnchantmentTags;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;

public class ModEnchantments extends Enchantments {
    private static final int WEIGHT = 25;
    public static final ResourceKey<Enchantment> OBSCURITY = create("obscurity");
    public static final ResourceKey<Enchantment> STEALTH = create("stealth");
    public static final ResourceKey<Enchantment> CONCUSSION = create("concussion");
    public static final ResourceKey<Enchantment> FLUID_WALKER = create("fluid_walker");
    public static final ResourceKey<Enchantment> SWIFTNESS = create("swiftness");
    public static final ResourceKey<Enchantment> WATERGEL = create("watergel");

    private static ResourceKey<Enchantment> create(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, ExtendedCombat.id(id));
    }

    private static <E> Enchantment create(ResourceLocation id, HolderSet<Item> supportedItems, int maxLevel, EquipmentSlotGroup slot,
                                          DataComponentType<E> component, E value) {
        Enchantment.Builder builder = Enchantment.enchantment(Enchantment.definition(supportedItems, WEIGHT, maxLevel,
                Enchantment.dynamicCost(5, 6), Enchantment.dynamicCost(20, 6), 2, slot))
                .withSpecialEffect(component, value);
        return builder.build(id);
    }

    private static <E> Enchantment createCustom(ResourceLocation id, HolderSet<Item> supportedItems, int maxLevel, Enchantment.Cost minCost, Enchantment.Cost maxCost, EquipmentSlotGroup slot,
                                            DataComponentType<E> component, E value) {
        Enchantment.Builder builder = Enchantment.enchantment(Enchantment.definition(supportedItems, WEIGHT, maxLevel,
                minCost, maxCost, 2, slot))
                .withSpecialEffect(component, value);
        return builder.build(id);
    }

    public static void bootstrap(BootstrapContext<Enchantment> registerable) {
        var enchantments = registerable.lookup(Registries.ENCHANTMENT);
        var items = registerable.lookup(Registries.ITEM);

        registerable.register(OBSCURITY, create(OBSCURITY.location(),
                items.getOrThrow(ModItemTags.OBSCURITY_ENCHANTABLE),
                1,
                EquipmentSlotGroup.HEAD,
                ModEnchantmentEffects.OBSCURITY.get(),
                new ObscurityEnchantmentEffect(
                        new AddValue(LevelBasedValue.constant(1))
                )));

        registerable.register(STEALTH, create(STEALTH.location(),
                items.getOrThrow(ModItemTags.STEALTH_ENCHANTABLE),
                1,
                EquipmentSlotGroup.CHEST,
                ModEnchantmentEffects.STEALTH.get(),
                new StealthEnchantmentEffect(
                        new AddValue(LevelBasedValue.constant(1))
                )));

        registerable.register(CONCUSSION, createCustom(CONCUSSION.location(),
                items.getOrThrow(ModItemTags.CONCUSSION_ENCHANTABLE),
                3,
                Enchantment.dynamicCost(5, 1),
                Enchantment.dynamicCost(10, 1),
                EquipmentSlotGroup.MAINHAND,
                ModEnchantmentEffects.CONCUSSION.get(),
                new ConcussionEnchantmentEffect(
                        new AddValue(LevelBasedValue.constant(1))
                )));

        registerable.register(FLUID_WALKER, createCustom(FLUID_WALKER.location(),
                items.getOrThrow(ModItemTags.FLUID_WALKER_ENCHANTABLE),
                3,
                Enchantment.dynamicCost(5, 5),
                Enchantment.dynamicCost(10, 5),
                EquipmentSlotGroup.FEET,
                ModEnchantmentEffects.FLUID_WALKER.get(),
                new FluidWalkerEnchantmentEffect(
                        new AddValue(LevelBasedValue.perLevel(1.3f, 0.4f))
                )));

        registerable.register(SWIFTNESS, createCustom(SWIFTNESS.location(),
                items.getOrThrow(ModItemTags.SWIFTNESS_ENCHANTABLE),
                1,
                Enchantment.dynamicCost(5, 5),
                Enchantment.dynamicCost(10, 5),
                EquipmentSlotGroup.LEGS,
                ModEnchantmentEffects.SWIFTNESS.get(),
                new SwiftnessEnchantmentEffect(
                        new AddValue(LevelBasedValue.constant(0.5f))
                )));

        registerable.register(WATERGEL, createCustom(WATERGEL.location(),
                items.getOrThrow(ModItemTags.WATERGEL_ENCHANTABLE),
                1,
                Enchantment.dynamicCost(5, 10),
                Enchantment.dynamicCost(10, 10),
                EquipmentSlotGroup.LEGS,
                ModEnchantmentEffects.WATERGEL.get(),
                new WaterGelEnchantmentEffect(
                        new AddValue(LevelBasedValue.constant(1.0f))
                )));
    }


}
