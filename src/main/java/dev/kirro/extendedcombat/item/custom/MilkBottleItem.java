package dev.kirro.extendedcombat.item.custom;


import dev.kirro.extendedcombat.ExtendedCombatUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class MilkBottleItem extends Item {
    private static final int MAX_USE_TIME = 16;
    protected final MilkType type;

    public MilkBottleItem(Properties settings, MilkType type) {
        super(settings);
        this.type = type;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity user) {
        if (user instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide) {
            switch (type) {
                case PLAIN ->
                        ExtendedCombatUtil.removeEffectOfType(user, MobEffectCategory.NEUTRAL);
                case SWEET_BERRY ->
                        ExtendedCombatUtil.removeEffectOfType(user, MobEffectCategory.HARMFUL);
                case CHOCOLATE ->
                        ExtendedCombatUtil.removeEffectOfType(user, MobEffectCategory.BENEFICIAL);
            }
        }

        if (user instanceof Player player) {
            return ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE), false);
        } else {
            stack.consume(1, user);
            return stack;
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag type) {
        MilkType milkType = this.type;
        switch (milkType) {
            case PLAIN -> tooltip.add(Component.translatable("tooltip.milk_bottle.milk").withStyle(ChatFormatting.BLUE));
            case SWEET_BERRY -> tooltip.add(Component.translatable("tooltip.milk_bottle.sweet_berry_milk").withStyle(ChatFormatting.BLUE));
            case CHOCOLATE -> tooltip.add(Component.translatable("tooltip.milk_bottle.chocolate_milk").withStyle(ChatFormatting.BLUE));
        }
        super.appendHoverText(stack, context, tooltip, type);
    }


    public MilkType getType() {
        return this.type;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity user) {
        return MAX_USE_TIME;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public @NotNull ItemStack getCraftingRemainingItem(@NotNull ItemStack stack) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
        return super.getTooltipImage(stack);
    }

    @Override
    public @NotNull SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player user, @NotNull InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, user, hand);
    }

    public enum MilkType implements StringRepresentable {
        PLAIN("neutral"),
        SWEET_BERRY("harmful"),
        CHOCOLATE("beneficial");

        private final String name;

        MilkType (final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }
}
