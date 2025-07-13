package dev.kirro.extendedcombat.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier NETHER_STEEL = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            1, 12f, 13f, 28, () -> Ingredient.of(Items.NETHERITE_INGOT));

}
