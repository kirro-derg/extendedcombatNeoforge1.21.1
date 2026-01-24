package dev.kirro.extendedcombat.item.custom;


import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NonConsumedFoodItem extends Item {
    public NonConsumedFoodItem(Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (stack.has(DataComponents.FOOD)) {
            user.eat(world, stack.copy(), stack.get(DataComponents.FOOD));
        }
        return stack;
    }
}
