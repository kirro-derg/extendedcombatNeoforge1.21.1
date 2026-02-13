package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NETHER_STEEL_BLOCK, ModItems.NETHER_STEEL_INGOT);
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHO_STEEL_BLOCK, ModItems.ECHO_STEEL_INGOT);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NETHER_STEEL_INGOT, 4)
                .requires(ModBlocks.NETHER_STEEL_BLOCK)
                .unlockedBy("has_nether_steel_block", has(ModBlocks.NETHER_STEEL_BLOCK))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_ingot_from_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ECHO_STEEL_INGOT, 4)
                .requires(ModBlocks.ECHO_STEEL_BLOCK)
                .unlockedBy("has_echo_steel_block", has(ModBlocks.ECHO_STEEL_BLOCK))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_ingot_from_block"));

        // nether steel item recipes
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(Items.NETHERITE_BOOTS), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.NETHER_STEEL_BOOTS.get())
                .unlocks("has_nether_steel_upgrade", has(ModItems.NETHER_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_boots_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(Items.NETHERITE_CHESTPLATE), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.NETHER_STEEL_CHESTPLATE.get())
                .unlocks("has_nether_steel_upgrade", has(ModItems.NETHER_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_chestplate_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(Items.NETHERITE_LEGGINGS), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.NETHER_STEEL_LEGGINGS.get())
                .unlocks("has_nether_steel_upgrade", has(ModItems.NETHER_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_leggings_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(Items.NETHERITE_HELMET), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.NETHER_STEEL_HELMET.get())
                .unlocks("has_nether_steel_upgrade", has(ModItems.NETHER_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_helmet_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(Items.NETHERITE_PICKAXE), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.MISC, ModItems.NETHER_STEEL_PICKAXE.get())
                .unlocks("has_nether_steel_upgrade", has(ModItems.NETHER_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_pickaxe_smithing"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHER_STEEL_INGOT)
                .pattern("MMM")
                .pattern("NGN")
                .pattern("MMM")
                .define('N', Items.NETHERITE_INGOT)
                .define('M', Items.MAGMA_CREAM)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("has_nether_steel_ingot", has(Items.NETHERITE_INGOT))
                .save(recipeOutput);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.BLAZE_POWDER), Ingredient.of(Items.BLACKSTONE), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.MISC, ModItems.NETHER_STEEL_UPGRADE.get())
                .unlocks("has_nether_steel_upgrade", has(ModItems.NETHER_STEEL_INGOT))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_upgrade_smithing"));

        // echo steel item recipes
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_BOOTS), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.NETHER_STEEL_BOOTS.get())
                .unlocks("has_echo_steel_upgrade", has(ModItems.ECHO_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_boots_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_CHESTPLATE), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.ECHO_STEEL_CHESTPLATE.get())
                .unlocks("has_echo_steel_upgrade", has(ModItems.ECHO_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_chestplate_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_LEGGINGS), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.ECHO_STEEL_LEGGINGS.get())
                .unlocks("has_echo_steel_upgrade", has(ModItems.ECHO_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_leggings_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_HELMET), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.ECHO_STEEL_HELMET.get())
                .unlocks("has_echo_steel_upgrade", has(ModItems.ECHO_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_helmet_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_PICKAXE), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.MISC, ModItems.ECHO_STEEL_PICKAXE.get())
                .unlocks(getHasName(ModItems.ECHO_STEEL_UPGRADE), has(ModItems.ECHO_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_pickaxe_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.SCULK_CATALYST), Ingredient.of(ModItems.NETHER_STEEL_INGOT), Ingredient.of(Items.ECHO_SHARD), RecipeCategory.MISC, ModItems.ECHO_STEEL_INGOT.get())
                .unlocks("has_nether_steel_upgrade", has(Items.ECHO_SHARD))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_ingot_smithing"));

        // wool item recipes
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(Items.NETHERITE_CHESTPLATE), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.HUNTER_CLOAK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("wool_cloak_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(ModItems.HUNTER_CLOAK), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.MISC, ModItems.NETHER_STEEL_CLOAK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_cloak_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_CLOAK), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.MISC, ModItems.ECHO_STEEL_CLOAK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_cloak_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(ModItems.NETHER_STEEL_CHESTPLATE), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.NETHER_STEEL_CLOAK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_cloak_chestplate_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(ModItems.ECHO_STEEL_CHESTPLATE), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.ECHO_STEEL_CLOAK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_cloak_chestplate_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(Items.NETHERITE_HELMET), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.HUNTER_MASK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("hunter_mask_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(ModItems.HUNTER_MASK), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.MISC, ModItems.NETHER_STEEL_MASK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_mask_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_MASK), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.MISC, ModItems.ECHO_STEEL_MASK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_mask_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(ModItems.NETHER_STEEL_HELMET), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.NETHER_STEEL_MASK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_mask_helmet_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(ModItems.ECHO_STEEL_HELMET), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.ECHO_STEEL_MASK.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_mask_helmet_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(Items.NETHERITE_LEGGINGS), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.HUNTER_LEGGINGS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("hunter_leggings_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(ModItems.HUNTER_LEGGINGS), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.MISC, ModItems.NETHER_STEEL_HUNTER_LEGGINGS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_hunter_leggings_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_HUNTER_LEGGINGS), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.MISC, ModItems.ECHO_STEEL_HUNTER_LEGGINGS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_hunter_leggings_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(ModItems.NETHER_STEEL_LEGGINGS), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.NETHER_STEEL_HUNTER_LEGGINGS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_hunter_leggings_leggings_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(ModItems.ECHO_STEEL_LEGGINGS), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.ECHO_STEEL_HUNTER_LEGGINGS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_hunter_leggings_leggings_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(Items.NETHERITE_BOOTS), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.HUNTER_BOOTS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("hunter_boots_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(ModItems.HUNTER_BOOTS), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.MISC, ModItems.NETHER_STEEL_HUNTER_BOOTS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_hunter_boots_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_HUNTER_BOOTS), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.MISC, ModItems.ECHO_STEEL_HUNTER_BOOTS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_hunter_boots_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(ModItems.NETHER_STEEL_BOOTS), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.NETHER_STEEL_HUNTER_BOOTS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_hunter_boots_boots_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemTags.WOOL), Ingredient.of(ModItems.ECHO_STEEL_BOOTS), Ingredient.of(ItemTags.WOOL), RecipeCategory.MISC, ModItems.ECHO_STEEL_HUNTER_BOOTS.get())
                .unlocks(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_hunter_boots_boots_smithing"));

        // miscellaneous item recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HANDLE)
                .pattern(" LS")
                .pattern("LSL")
                .pattern("SL ")
                .define('S', Items.STICK)
                .define('L', Items.LEATHER)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHER_STEEL_UPGRADE, 2)
                .pattern("DND")
                .pattern("DUD")
                .pattern("DDD")
                .define('D', Items.DIAMOND)
                .define('N', Items.NETHERITE_SCRAP)
                .define('U', ModItems.NETHER_STEEL_UPGRADE)
                .unlockedBy(getHasName(ModItems.NETHER_STEEL_UPGRADE), has(ModItems.NETHER_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_upgrade_duplication"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ECHO_STEEL_UPGRADE, 2)
                .pattern("DED")
                .pattern("DUD")
                .pattern("DDD")
                .define('D', Items.DIAMOND)
                .define('E', Items.ECHO_SHARD)
                .define('U', ModItems.ECHO_STEEL_UPGRADE)
                .unlockedBy(getHasName(ModItems.ECHO_STEEL_UPGRADE), has(ModItems.ECHO_STEEL_UPGRADE))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_upgrade_duplication"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_BOTTLE, 1)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_BOTTLE, 2)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(recipeOutput, ExtendedCombat.id("milk_bottle_2"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_BOTTLE, 3)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(recipeOutput, ExtendedCombat.id("milk_bottle_3"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_MILK_BOTTLE)
                .requires(ModItems.MILK_BOTTLE)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.MILK_BOTTLE), has(ModItems.MILK_BOTTLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SWEET_BERRY_MILK_BOTTLE)
                .requires(ModItems.MILK_BOTTLE)
                .requires(Items.SWEET_BERRIES)
                .unlockedBy(getHasName(ModItems.MILK_BOTTLE), has(ModItems.MILK_BOTTLE))
                .save(recipeOutput);

        // ∴ᔑ∷↸╎リ⊣  ᓭℸ ̣ 𝙹リᒷ
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WARDING_STONE)
                .pattern("DDD")
                .pattern("DSD")
                .pattern("DSD")
                .define('S', Items.SOUL_SOIL)
                .define('D', Items.COBBLED_DEEPSLATE)
                .unlockedBy(getHasName(Items.SOUL_SOIL), has(Items.SOUL_SOIL))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FRAMED_GLASS_PANEL, 4)
                .pattern("NNN")
                .pattern("NPN")
                .pattern("NNN")
                .define('N', Items.IRON_NUGGET)
                .define('P', Items.GLASS_PANE)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput);

        // weapon recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.WOODEN_GREATSWORD)
                .pattern(" II")
                .pattern("III")
                .pattern("HI ")
                .define('I', ItemTags.PLANKS)
                .define('H', ModItems.HANDLE)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.STONE_GREATSWORD)
                .pattern(" II")
                .pattern("III")
                .pattern("HI ")
                .define('I', ItemTags.STONE_TOOL_MATERIALS)
                .define('H', ModItems.HANDLE)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRON_GREATSWORD)
                .pattern(" II")
                .pattern("III")
                .pattern("HI ")
                .define('I', Items.IRON_INGOT)
                .define('H', ModItems.HANDLE)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.GOLDEN_GREATSWORD)
                .pattern(" II")
                .pattern("III")
                .pattern("HI ")
                .define('I', Items.GOLD_INGOT)
                .define('H', ModItems.HANDLE)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIAMOND_GREATSWORD)
                .pattern(" II")
                .pattern("III")
                .pattern("HI ")
                .define('I', Items.DIAMOND)
                .define('H', ModItems.HANDLE)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(recipeOutput);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ModItems.DIAMOND_GREATSWORD), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.COMBAT, ModItems.NETHERITE_GREATSWORD.get())
                .unlocks(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(recipeOutput, "netherite_greatsword_smithing");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(ModItems.NETHERITE_GREATSWORD), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.NETHER_STEEL_GREATSWORD.get())
                .unlocks(getHasName(ModItems.NETHER_STEEL_INGOT), has(ModItems.NETHER_STEEL_INGOT))
                .save(recipeOutput, "nether_steel_greatsword_smithing");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_GREATSWORD), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.COMBAT, ModItems.ECHO_STEEL_GREATSWORD.get())
                .unlocks(getHasName(ModItems.ECHO_STEEL_INGOT), has(ModItems.ECHO_STEEL_INGOT))
                .save(recipeOutput, "echo_steel_greatsword_smithing");

        // food recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GOLDEN_STEAK)
                .pattern("GGG")
                .pattern("GSG")
                .pattern("GGG")
                .define('G', Items.GOLD_INGOT)
                .define('S', Items.COOKED_BEEF)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_BREAD)
                .requires(Items.BREAD)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(Items.BREAD), has(Items.BREAD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.BREAD)
                .pattern("  O")
                .pattern(" OO")
                .define('O', Items.WHEAT)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(recipeOutput, "bread_qol");

        // QOL recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PAPER, 3)
                .pattern("  O")
                .pattern(" OO")
                .define('O', Items.SUGAR_CANE)
                .unlockedBy(getHasName(Items.SUGAR_CANE), has(Items.SUGAR_CANE))
                .save(recipeOutput, "paper_qol");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STRING, 4)
                .requires(ItemTags.WOOL)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COBWEB, 4)
                .pattern("S S")
                .pattern(" S ")
                .pattern("S S")
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACK_APPLE_SEED, 4)
                .requires(ModItems.BLACK_APPLE)
                .unlockedBy(getHasName(ModItems.BLACK_APPLE), has(ModItems.BLACK_APPLE))
                .save(recipeOutput);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.ROTTEN_FLESH), RecipeCategory.MISC, Items.LEATHER, 0.35f, 200)
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                .save(recipeOutput);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(Items.IRON_BOOTS), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_BOOTS)
                .unlocks(getHasName(Items.IRON_BOOTS), has(Items.IRON_BOOTS))
                .save(recipeOutput, "diamond_boots_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(Items.IRON_LEGGINGS), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_LEGGINGS)
                .unlocks(getHasName(Items.IRON_LEGGINGS), has(Items.IRON_LEGGINGS))
                .save(recipeOutput, "diamond_leggings_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(Items.IRON_CHESTPLATE), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_CHESTPLATE)
                .unlocks(getHasName(Items.IRON_CHESTPLATE), has(Items.IRON_CHESTPLATE))
                .save(recipeOutput, "diamond_chestplate_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(Items.IRON_HELMET), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_HELMET)
                .unlocks(getHasName(Items.IRON_HELMET), has(Items.IRON_HELMET))
                .save(recipeOutput, "diamond_helmet_smithing");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(), Ingredient.of(Items.IRON_SHOVEL), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_SHOVEL)
                .unlocks(getHasName(Items.IRON_SHOVEL), has(Items.IRON_SHOVEL))
                .save(recipeOutput, "diamond_shovel_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(Items.IRON_PICKAXE), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_PICKAXE)
                .unlocks(getHasName(Items.IRON_PICKAXE), has(Items.IRON_PICKAXE))
                .save(recipeOutput, "diamond_pickaxe_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(Items.IRON_AXE), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_AXE)
                .unlocks(getHasName(Items.IRON_AXE), has(Items.IRON_AXE))
                .save(recipeOutput, "diamond_axe_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(Items.IRON_HOE), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_HOE)
                .unlocks(getHasName(Items.IRON_HOE), has(Items.IRON_HOE))
                .save(recipeOutput, "diamond_hoe_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(Items.IRON_SWORD), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, Items.DIAMOND_SWORD)
                .unlocks(getHasName(Items.IRON_SWORD), has(Items.IRON_SWORD))
                .save(recipeOutput, "diamond_sword_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(ModItems.IRON_GREATSWORD), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, ModItems.DIAMOND_GREATSWORD.get())
                .unlocks(getHasName(ModItems.IRON_GREATSWORD), has(ModItems.IRON_GREATSWORD))
                .save(recipeOutput, "diamond_greatsword_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND), Ingredient.of(ModItems.IRON_HAMMER), Ingredient.of(Items.DIAMOND), RecipeCategory.COMBAT, ModItems.DIAMOND_HAMMER.get())
                .unlocks(getHasName(ModItems.IRON_HAMMER), has(ModItems.IRON_HAMMER))
                .save(recipeOutput, "diamond_hammer_smithing");

        // hammer recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WOODEN_HAMMER)
                .pattern("PPP")
                .pattern("PSP")
                .pattern(" S ")
                .define('P', ItemTags.PLANKS)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.OAK_PLANKS), has(Items.OAK_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STONE_HAMMER)
                .pattern("PPP")
                .pattern("PSP")
                .pattern(" S ")
                .define('P', ItemTags.STONE_TOOL_MATERIALS)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.IRON_HAMMER)
                .pattern("PPP")
                .pattern("PSP")
                .pattern(" S ")
                .define('P', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.GOLDEN_HAMMER)
                .pattern("PPP")
                .pattern("PSP")
                .pattern(" S ")
                .define('P', Items.GOLD_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DIAMOND_HAMMER)
                .pattern("PPP")
                .pattern("PSP")
                .pattern(" S ")
                .define('P', Items.DIAMOND)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(recipeOutput);
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ModItems.DIAMOND_HAMMER), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.TOOLS, ModItems.NETHERITE_HAMMER.get())
                .unlocks(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(recipeOutput, ExtendedCombat.id("netherite_hammer_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.NETHER_STEEL_UPGRADE), Ingredient.of(ModItems.NETHERITE_HAMMER), Ingredient.of(ModItems.NETHER_STEEL_INGOT), RecipeCategory.TOOLS, ModItems.NETHER_STEEL_HAMMER.get())
                .unlocks(getHasName(ModItems.NETHER_STEEL_INGOT), has(ModItems.NETHER_STEEL_INGOT))
                .save(recipeOutput, ExtendedCombat.id("nether_steel_hammer_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ECHO_STEEL_UPGRADE), Ingredient.of(ModItems.NETHER_STEEL_HAMMER), Ingredient.of(ModItems.ECHO_STEEL_INGOT), RecipeCategory.TOOLS, ModItems.ECHO_STEEL_HAMMER.get())
                .unlocks(getHasName(ModItems.ECHO_STEEL_INGOT), has(ModItems.ECHO_STEEL_INGOT))
                .save(recipeOutput, ExtendedCombat.id("echo_steel_hammer_smithing"));
    }
}
