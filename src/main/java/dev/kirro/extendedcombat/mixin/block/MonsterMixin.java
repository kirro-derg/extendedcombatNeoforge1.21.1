package dev.kirro.extendedcombat.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.kirro.extendedcombat.block.custom.WardingStoneBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Monster.class)
public class MonsterMixin {
    @ModifyReturnValue(method = "isDarkEnoughToSpawn", at = @At("RETURN"))
    private static boolean isSpawnDark(boolean original, @Local(argsOnly = true) ServerLevelAccessor level, @Local(argsOnly = true) BlockPos pos) {
        return WardingStoneBlock.isNearby(level, pos, original);
    }

    @ModifyReturnValue(method = "checkMonsterSpawnRules", at = @At("RETURN"))
    private static boolean canSpawnInDark(boolean original, @Local(argsOnly = true) ServerLevelAccessor level, @Local(argsOnly = true) BlockPos pos) {
        return WardingStoneBlock.isNearby(level, pos, original);
    }

    @ModifyReturnValue(method = "checkAnyLightMonsterSpawnRules", at = @At("RETURN"))
    private static boolean canSpawnIgnoreLightLevel(boolean original, @Local(argsOnly = true) LevelAccessor level, @Local(argsOnly = true) BlockPos pos) {
        return WardingStoneBlock.isNearby(level, pos, original);
    }

    @Mixin(Slime.class)
    public static class SlimeMixin {
        @ModifyReturnValue(method = "checkSlimeSpawnRules", at = @At("RETURN"))
        private static boolean canSpawn(boolean original, @Local(argsOnly = true) LevelAccessor level, @Local(argsOnly = true) BlockPos pos) {
            return WardingStoneBlock.isNearby(level, pos, original);
        }
    }

    @Mixin(Bat.class)
    public static class BatMixin {
        @ModifyReturnValue(method = "checkBatSpawnRules", at = @At("RETURN"))
        private static boolean canSpawn(boolean original, @Local(argsOnly = true) LevelAccessor level, @Local(argsOnly = true) BlockPos pos) {
            return WardingStoneBlock.isNearby(level, pos, original);
        }
    }
}