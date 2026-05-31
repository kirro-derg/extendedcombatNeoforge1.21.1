package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.item.ModItems;
import dev.kirro.extendedcombat.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExtendedCombat.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("echo_steel_upgrade_to_warden",
                AddItemModifier.of(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/warden")).build()
                }, ModItems.ECHO_STEEL_UPGRADE.get()))
        ;
        this.add("black_apple_to_nether_bridge",
                AddItemModifier.of(new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.NETHER_BRIDGE.location()).build()
                }, ModItems.BLACK_APPLE_SEED.get()))
        ;
        this.add("golden_steak_to_abandoned_mineshaft",
                AddItemModifier.of(new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.ABANDONED_MINESHAFT.location()).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ModItems.BLACK_APPLE_SEED.get()))
        ;
        this.add("halberd_and_greatsword_to_village_weaponsmith",
                AddItemModifier.of(new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.VILLAGE_WEAPONSMITH.location()).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build()
                }, ModItems.IRON_HALBERD.get(), ModItems.IRON_GREATSWORD.get()))
        ;
        this.add("hammer_to_village_toolsmith",
                AddItemModifier.of(new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.VILLAGE_TOOLSMITH.location()).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build()
                }, ModItems.IRON_HAMMER.get()))
        ;
    }
}
