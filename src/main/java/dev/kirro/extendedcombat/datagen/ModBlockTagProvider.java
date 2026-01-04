package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ExtendedCombat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.NETHER_STEEL_BLOCK.get())
                .add(ModBlocks.ECHO_STEEL_BLOCK.get())
                .add(ModBlocks.WARDING_STONE.get())
                .add(ModBlocks.FRAMED_GLASS_PANEL.get())
        ;

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.NETHER_STEEL_BLOCK.get())
                .add(ModBlocks.ECHO_STEEL_BLOCK.get())
        ;
    }
}
