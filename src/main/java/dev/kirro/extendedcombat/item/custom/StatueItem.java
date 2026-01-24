/*package dev.kirro.extendedcombat.item.custom;

import dev.kirro.extendedcombat.entity.ModEntities;
import dev.kirro.extendedcombat.entity.custom.StatueEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class StatueItem extends Item {
    public StatueItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        float placementYaw = context.getPlayerYaw();
        ItemStack itemStack = context.getStack();
        if (world instanceof ServerWorld serverWorld) {
            Consumer<StatueEntity> consumer = EntityType.copier(serverWorld, itemStack, context.getPlayer());
            StatueEntity statueEntity = ModEntities.STATUE.create(serverWorld, consumer, pos, SpawnReason.SPAWN_EGG, true, true);

            if (!world.isClient) {
                StatueEntity statue = new StatueEntity(ModEntities.STATUE, world);
                statue.setYaw(placementYaw);
                statue.setBodyYaw(placementYaw);
                statue.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, placementYaw, 0.0f);
                world.spawnEntity(statue);
                world.playSound(
                        null, statueEntity.getX(), statueEntity.getY(), statueEntity.getZ(), SoundEvents.ENTITY_ARMOR_STAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F
                );
            }
        }
        itemStack.decrement(1);
        return ActionResult.SUCCESS;
    }
}*/
