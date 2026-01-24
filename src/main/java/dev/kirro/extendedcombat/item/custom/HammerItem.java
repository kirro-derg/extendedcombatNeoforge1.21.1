package dev.kirro.extendedcombat.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier material, Properties settings) {
        super(material, BlockTags.MINEABLE_WITH_PICKAXE, settings);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initialBlockPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.pick(20, 0, false);
        boolean sneaking = player.isCrouching();

        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;

            if (blockHit.getDirection() == Direction.DOWN || blockHit.getDirection() == Direction.UP) {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        if (sneaking) {
                            positions.add(new BlockPos(initialBlockPos));
                        } else {
                            positions.add(new BlockPos(initialBlockPos.getX() + x, initialBlockPos.getY(), initialBlockPos.getZ() + y));
                        }
                    }
                }
            }

            if (blockHit.getDirection() == Direction.NORTH || blockHit.getDirection() == Direction.SOUTH) {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        if (sneaking) {
                            positions.add(new BlockPos(initialBlockPos));
                        } else {
                            positions.add(new BlockPos(initialBlockPos.getX() + x, initialBlockPos.getY() + y, initialBlockPos.getZ()));
                        }
                    }
                }
            }

            if (blockHit.getDirection() == Direction.EAST || blockHit.getDirection() == Direction.WEST) {
                for (int x = -range; x <= range; x++) {
                    for (int y = -range; y <= range; y++) {
                        if (sneaking) {
                            positions.add(new BlockPos(initialBlockPos));
                        } else {
                            positions.add(new BlockPos(initialBlockPos.getX(), initialBlockPos.getY() + y, initialBlockPos.getZ() + x));
                        }
                    }
                }
            }
        }

        return positions;
    }
}
