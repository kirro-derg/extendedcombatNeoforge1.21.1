package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHER_STEEL_BLOCK, ModItems.NETHER_STEEL_INGOT);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NETHER_STEEL_INGOT, 4)
                .requires(ModBlocks.NETHER_STEEL_BLOCK)
                .unlockedBy("has_nether_steel_block", has(ModBlocks.NETHER_STEEL_BLOCK))
                .save(recipeOutput, "extendedcombat:nether_steel_ingot_from_block");
    }
}
