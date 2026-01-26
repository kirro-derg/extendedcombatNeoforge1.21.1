package dev.kirro.extendedcombat;

import dev.kirro.extendedcombat.behavior.enchantment.*;
import dev.kirro.extendedcombat.behavior.item.HideWoolHoodBehavior;
import dev.kirro.extendedcombat.behavior.item.XPRepairTracker;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.item.custom.HammerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.*;

public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void afterBreakBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        BlockPos initialPos = event.getPos();
        LevelAccessor level = event.getLevel();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            if (HARVESTED_BLOCKS.contains(initialPos)) {
                return;
            }

            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialPos, serverPlayer)) {
                if (pos == initialPos || !hammer.isCorrectToolForDrops(mainHandItem, level.getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Post event) {
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        if (player != null) {
            AirJumpBehavior airJumpBehavior = player.getData(ModDataAttachments.AIR_JUMP);
            AirMovementBehavior airMovementBehavior = player.getData(ModDataAttachments.AIR_MOVEMENT);
            BlinkBehavior blinkBehavior = player.getData(ModDataAttachments.BLINK);
            DashBehavior dashBehavior = player.getData(ModDataAttachments.DASH);
            FluidMovementBehavior fluidMovementBehavior = player.getData(ModDataAttachments.FLUID_MOVEMENT);
            HideWoolHoodBehavior hideHoodBehavior = player.getData(ModDataAttachments.HIDE_HOOD);

            airJumpBehavior.clientTick(player);
            airMovementBehavior.clientTick(player);
            blinkBehavior.clientTick(player);
            dashBehavior.clientTick(player);
            fluidMovementBehavior.clientTick(player);
            hideHoodBehavior.clientTick(player);
        }
    }

    @SubscribeEvent
    public static void equipmentChange(LivingEquipmentChangeEvent event) {
        EquipmentSlot slot = event.getSlot();
        LivingEntity player = event.getEntity();
        if (slot.isArmor()) {
            if (EnchantmentHelper.has(event.getTo(), ModEnchantmentEffects.AIR_JUMP.get())) {
                AirJumpBehavior airJumpBehavior = player.getData(ModDataAttachments.AIR_JUMP);
                airJumpBehavior.reset((Player) player);
            }
            if (EnchantmentHelper.has(event.getTo(), ModEnchantmentEffects.DASH.get())) {
                DashBehavior dashBehavior = player.getData(ModDataAttachments.DASH);
                dashBehavior.reset((Player) player);
            }

            if (EnchantmentHelper.has(event.getTo(), ModEnchantmentEffects.BLINK.get())) {
                BlinkBehavior blinkBehavior = player.getData(ModDataAttachments.BLINK);
                blinkBehavior.reset((Player) player);
            }
        }
    }

    @SubscribeEvent
    public static void serverTick(ServerTickEvent.Post event) {
        for (ServerPlayer serverPlayer : event.getServer().getPlayerList().getPlayers()) {
            AirJumpBehavior airJumpBehavior = serverPlayer.getData(ModDataAttachments.AIR_JUMP);
            AirMovementBehavior airMovementBehavior = serverPlayer.getData(ModDataAttachments.AIR_MOVEMENT);
            BlinkBehavior blinkBehavior = serverPlayer.getData(ModDataAttachments.BLINK);
            DashBehavior dashBehavior = serverPlayer.getData(ModDataAttachments.DASH);
            FluidMovementBehavior fluidMovementBehavior = serverPlayer.getData(ModDataAttachments.FLUID_MOVEMENT);
            HideWoolHoodBehavior hideHoodBehavior = serverPlayer.getData(ModDataAttachments.HIDE_HOOD);


            airJumpBehavior.serverTick(serverPlayer);
            airMovementBehavior.serverTick(serverPlayer);
            blinkBehavior.serverTick(serverPlayer);
            dashBehavior.serverTick(serverPlayer);
            fluidMovementBehavior.serverTick(serverPlayer);
            hideHoodBehavior.serverTick(serverPlayer);

            XPRepairTracker.tick(serverPlayer);
        }
    }
}
