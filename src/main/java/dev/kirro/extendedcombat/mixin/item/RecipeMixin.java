package dev.kirro.extendedcombat.mixin.item;

import dev.kirro.extendedcombat.item.custom.MilkBottleItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShapelessRecipe.class)
public abstract class RecipeMixin<T extends RecipeInput> implements CraftingRecipe {
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level != null) {
            if (this.getResultItem(minecraft.level.registryAccess()).getItem() instanceof MilkBottleItem) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(input.size(), ItemStack.EMPTY);

                for (int i = 0; i < nonnulllist.size(); ++i) {
                    ItemStack item = input.getItem(i);
                    if (item.hasCraftingRemainingItem()) {
                        nonnulllist.set(i, ItemStack.EMPTY);
                    }
                }
                return nonnulllist;
            }
        }
        return CraftingRecipe.super.getRemainingItems(input);
    }
}
