package dev.kirro.extendedcombat.enchantment;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.enchantment.custom.*;
import dev.kirro.extendedcombat.tags.ModEnchantmentTags;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> DASH = create("dash");
    public static final ResourceKey<Enchantment> AIR_JUMP = create("air_jump");
    public static final ResourceKey<Enchantment> BLINK = create("blink");
    public static final ResourceKey<Enchantment> OBSCURITY = create("obscurity");
    public static final ResourceKey<Enchantment> VANITY = create("vanity");
    public static final ResourceKey<Enchantment> STEALTH = create("stealth");
    public static final ResourceKey<Enchantment> KEEPSAKE = create("keepsake");
    public static final ResourceKey<Enchantment> CONCUSSION = create("concussion");
    public static final ResourceKey<Enchantment> FLUID_WALKER = create("fluid_walker");
    public static final ResourceKey<Enchantment> SWIFTNESS = create("swiftness");
    public static final ResourceKey<Enchantment> WATERGEL = create("watergel");

    private static ResourceKey<Enchantment> create(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, ExtendedCombat.id(id));
    }

    public interface EffectsAdder {
        void addEffects(Enchantment.Builder builder);
    }

    private static void create(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                               Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

    private static Enchantment create(ResourceLocation id, HolderSet<Item> supportedItems, int maxLevel, EquipmentSlotGroup slot,
                                      EffectsAdder effectsAdder) {
        Enchantment.Builder builder = Enchantment.enchantment(Enchantment.definition(supportedItems, 5, maxLevel,
                Enchantment.dynamicCost(5, 6), Enchantment.dynamicCost(20, 6), 2, slot));
        effectsAdder.addEffects(builder);
        return builder.build(id);
    }

    private static Enchantment createCustom(ResourceLocation id, HolderSet<Item> supportedItems, int weight, int maxLevel, Enchantment.Cost minCost, Enchantment.Cost maxCost, EquipmentSlotGroup slot,
                                      EffectsAdder effectsAdder) {
        Enchantment.Builder builder = Enchantment.enchantment(Enchantment.definition(supportedItems, weight, maxLevel,
                minCost, maxCost, 2, slot));
        effectsAdder.addEffects(builder);
        return builder.build(id);
    }

    public static void bootstrap(BootstrapContext<Enchantment> registerable) {
        var enchantments = registerable.lookup(Registries.ENCHANTMENT);
        var items = registerable.lookup(Registries.ITEM);

        registerable.register(DASH, create(DASH.location(),
                items.getOrThrow(ModItemTags.DASH_ENCHANTABLE),
                3,
                EquipmentSlotGroup.LEGS, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.DASH.get(),
                        new DashEnchantmentEffect(
                                new AddValue(LevelBasedValue.perLevel(1, -0.25f)),
                                new AddValue(LevelBasedValue.perLevel(0.85f, 0.3f))
                        ))/**/));

        registerable.register(AIR_JUMP, create(AIR_JUMP.location(),
                items.getOrThrow(ModItemTags.AIR_JUMP_ENCHANTABLE),
                3,
                EquipmentSlotGroup.FEET, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.AIR_JUMP.get(),
                        new AirJumpEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(1.45f)),
                                new AddValue(LevelBasedValue.constant(0.5f)),
                                new AddValue(LevelBasedValue.constant(0.5f)),
                                new AddValue(LevelBasedValue.perLevel(1))
                        ))));

        registerable.register(BLINK, create(BLINK.location(),
                items.getOrThrow(ModItemTags.BLINK_ENCHANTABLE),
                1,
                EquipmentSlotGroup.CHEST, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.BLINK.get(),
                        new BlinkEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(10)),
                                new AddValue(LevelBasedValue.constant(5))
                        ))));

        registerable.register(OBSCURITY, create(OBSCURITY.location(),
                items.getOrThrow(ModItemTags.OBSCURITY_ENCHANTABLE),
                1,
                EquipmentSlotGroup.HEAD, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.OBSCURITY.get(),
                        new ObscurityEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(1))
                        ))));

        registerable.register(VANITY, create(VANITY.location(),
                items.getOrThrow(ModItemTags.VANITY_ENCHANTABLE),
                1,
                EquipmentSlotGroup.ARMOR, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.VANITY.get(),
                        new VanityEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(1))
                        ))));

        registerable.register(STEALTH, create(STEALTH.location(),
                items.getOrThrow(ModItemTags.STEALTH_ENCHANTABLE),
                1,
                EquipmentSlotGroup.CHEST, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.STEALTH.get(),
                        new StealthEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(1))
                        ))));

        registerable.register(KEEPSAKE, createCustom(KEEPSAKE.location(),
                items.getOrThrow(ModItemTags.KEEPSAKE_ENCHANTABLE),
                2,
                1,
                Enchantment.dynamicCost(25, 10),
                Enchantment.dynamicCost(75, 10),
                EquipmentSlotGroup.ANY, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.KEEPSAKE.get(),
                        new KeepsakeEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(1))
                        )).exclusiveWith(enchantments.getOrThrow(ModEnchantmentTags.DURABILITY_EXCLUSIVE_SET))));


        registerable.register(CONCUSSION, createCustom(CONCUSSION.location(),
                items.getOrThrow(ModItemTags.CONCUSSION_ENCHANTABLE),
                5,
                3,
                Enchantment.dynamicCost(5, 1),
                Enchantment.dynamicCost(10, 1),
                EquipmentSlotGroup.MAINHAND, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.CONCUSSION.get(),
                        new ConcussionEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(1))
                        ))));

        registerable.register(FLUID_WALKER, createCustom(FLUID_WALKER.location(),
                items.getOrThrow(ModItemTags.FLUID_WALKER_ENCHANTABLE),
                5,
                3,
                Enchantment.dynamicCost(5, 5),
                Enchantment.dynamicCost(10, 5),
                EquipmentSlotGroup.FEET, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.FLUID_WALKER.get(),
                        new FluidWalkerEnchantmentEffect(
                                new AddValue(LevelBasedValue.perLevel(1.3f, 0.4f))
                        ))));

        registerable.register(SWIFTNESS, createCustom(SWIFTNESS.location(),
                items.getOrThrow(ModItemTags.SWIFTNESS_ENCHANTABLE),
                5,
                1,
                Enchantment.dynamicCost(5, 5),
                Enchantment.dynamicCost(10, 5),
                EquipmentSlotGroup.LEGS, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.SWIFTNESS.get(),
                        new SwiftnessEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(0.5f))
                        ))));

        registerable.register(WATERGEL, createCustom(WATERGEL.location(),
                items.getOrThrow(ModItemTags.WATERGEL_ENCHANTABLE),
                5,
                1,
                Enchantment.dynamicCost(65, 10),
                Enchantment.dynamicCost(75, 10),
                EquipmentSlotGroup.LEGS, builder -> builder.withSpecialEffect(
                        ModEnchantmentEffects.WATERGEL.get(),
                        new WaterGelEnchantmentEffect(
                                new AddValue(LevelBasedValue.constant(1.0f))
                        ))));

    }


}
