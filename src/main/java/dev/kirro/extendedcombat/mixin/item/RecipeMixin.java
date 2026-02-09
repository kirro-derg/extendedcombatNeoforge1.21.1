package dev.kirro.extendedcombat.mixin.item;

import dev.kirro.extendedcombat.item.custom.MilkBottleItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ShapelessRecipe.class)
public abstract class RecipeMixin<T extends RecipeInput> implements CraftingRecipe {
    @Shadow
    public abstract ItemStack getResultItem(HolderLookup.Provider registries);

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level != null) {
            if (this.getResultItem(minecraft.level.registryAccess()).getItem() instanceof MilkBottleItem) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(input.size(), ItemStack.EMPTY);

                for (int i = 0; i < nonnulllist.size(); ++i) {
                    ItemStack item = input.getItem(i);
                    if (item.hasCraftingRemainingItem() && item.is(Items.MILK_BUCKET)) {
                        nonnulllist.set(i, new ItemStack(Items.BUCKET));
                    } else if (item.hasCraftingRemainingItem() && item.is(Items.MILK_BUCKET)) {
                        nonnulllist.set(i, ItemStack.EMPTY);
                    }
                }
                return nonnulllist;
            }
        }
        return CraftingRecipe.super.getRemainingItems(input);
    }
}
