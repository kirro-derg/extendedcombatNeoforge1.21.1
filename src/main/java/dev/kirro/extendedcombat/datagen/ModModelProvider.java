package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModModelProvider extends ItemModelProvider {
    public ModModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExtendedCombat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.HANDLE.get());
        basicItem(ModItems.NETHER_STEEL_INGOT.get());
        basicItem(ModItems.NETHER_STEEL_UPGRADE.get());
        handheldItem(ModItems.NETHER_STEEL_PICKAXE.get());
    }
}
