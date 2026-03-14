package dev.kirro.extendedcombat.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class StatueItem extends Item {
    public StatueItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        float placementYaw = context.getRotation();
        ItemStack itemStack = context.getItemInHand();
        if (level instanceof ServerLevel serverLevel) {
            //Consumer<StatueEntity> consumer = EntityType.createDefaultStackConfig(serverLevel, itemStack, context.getPlayer());
            //StatueEntity statueEntity = ModEntities.STATUE.create(serverLevel, consumer, pos, MobSpawnType.SPAWN_EGG, true, true);

            if (!level.isClientSide) {
                //StatueEntity statue = new StatueEntity(ModEntities.STATUE, level);
                //statue.setYaw(placementYaw);
                //statue.setBodyYaw(placementYaw);
                //statue.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, placementYaw, 0.0f);
                //level.spawnEntity(statue);
                //level.playSound(
                //        null, statueEntity.x(), statueEntity.y(), statueEntity.z(), SoundEvents.ARMOR_STAND_PLACE, SoundSource.BLOCKS, 0.75F, 0.8F
                //);
            }
        }
        itemStack.shrink(1);
        return InteractionResult.SUCCESS;
    }
}
