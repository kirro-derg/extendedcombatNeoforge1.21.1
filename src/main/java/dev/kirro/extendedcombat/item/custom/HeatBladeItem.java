package dev.kirro.extendedcombat.item.custom;

import dev.kirro.extendedcombat.item.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class HeatBladeItem extends SwordItem {
    private final int MAX_CHARGE = 8;
    private int CHARGE;
    private int COOLDOWN;

    public HeatBladeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public int getMaxCharge() {
        return this.MAX_CHARGE;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (entity instanceof Player player) {
            ItemStack handStack = player.getMainHandItem();
            if (player.isInLava() && stack.is(this) && !player.getCooldowns().isOnCooldown(stack.getItem()) && getCharge(stack) < getMaxCharge()) {
                stack.set(ModDataComponents.CHARGE, Math.min(getCharge(stack) + 1, this.MAX_CHARGE));
                player.getCooldowns().addCooldown(stack.getItem(), 20);
            }
            this.CHARGE = handStack.getOrDefault(ModDataComponents.CHARGE, 0);
            if (this.COOLDOWN > 0) this.COOLDOWN--;
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    public int getCharge(ItemStack stack) {
        return Mth.clamp(stack.getOrDefault(ModDataComponents.CHARGE, 0), 0, this.getMaxCharge());
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        /*ItemStack handStack = attacker.getMainHandItem();
        if (attacker.getMainHandItem().is(this)) {
            if (getCharge(stack) >= 5) {
                target.setSharedFlagOnFire(true);
                target.setRemainingFireTicks(getCharge(stack));
            }
            handStack.set(ModDataComponents.CHARGE, Math.max(getCharge(stack) - 1, 0));
        }*/
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        return super.onEntitySwing(stack, entity, hand);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ItemStack handStack = attacker.getMainHandItem();
        if (attacker.getMainHandItem().is(this)) {
            if (getCharge(stack) >= 5) {
                target.setSharedFlagOnFire(true);
                target.setRemainingFireTicks(getCharge(stack));
            }
            handStack.set(ModDataComponents.CHARGE, Math.max(getCharge(stack) - 1, 0));
        }
        return true;
    }

    @Override
    public float getAttackDamageBonus(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return super.getAttackDamageBonus(target, baseAttackDamage, damageSource) + this.CHARGE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack stack = user.getItemInHand(hand);
        if (user.isCrouching()) {
            stack.set(ModDataComponents.CHARGE, Math.min(getCharge(stack) + 1, this.MAX_CHARGE));
        } else {
            stack.set(ModDataComponents.CHARGE, Math.max(getCharge(stack) - 1, 0));
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.min(Math.max(getCharge(stack) + getCharge(stack) - 3, 0), 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return super.getBarColor(stack) + getCharge(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("charge" + ":" + getCharge(stack)));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
