package dev.kirro.extendedcombat;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.kirro.extendedcombat.behavior.enchantment.*;
import dev.kirro.extendedcombat.behavior.item.HideWoolHoodBehavior;
import dev.kirro.extendedcombat.behavior.item.XPRepairTracker;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.item.custom.HammerItem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
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

    @SubscribeEvent
    public static void HudRender(RenderGuiLayerEvent.Post event) {
        GuiGraphics guiGraphics = event.getGuiGraphics();
        Minecraft minecraft = Minecraft.getInstance();
        airJumpHud(guiGraphics, minecraft);
        blinkHud(guiGraphics, minecraft);
        dashHud(guiGraphics, minecraft);
    }

    public static void airJumpHud(GuiGraphics guiGraphics, Minecraft minecraft) {
        if (minecraft != null) {
            AirJumpBehavior airJump = minecraft.cameraEntity.getData(ModDataAttachments.AIR_JUMP);
            if (airJump.getCanUse() && Minecraft.renderNames()) {
                int jumpAmount = airJump.getJumpsLeft();
                if (jumpAmount < airJump.getMaxJumps()) {
                    RenderSystem.enableBlend();
                    ResourceLocation first = getTexture(jumpAmount + 1);
                    ResourceLocation second = getTexture(jumpAmount);
                    int x = guiGraphics.guiWidth() / 2 - 7, y = guiGraphics.guiHeight() / 2 + 14;
                    if (airJump.getCooldown() < airJump.getLastCooldown()) {
                        guiGraphics.blitSprite(first, x, y, 15, 6);
                        guiGraphics.blitSprite(second, 15, 6, 0, 0, x, y, (int) ((airJump.getCooldown() / (float) airJump.getLastCooldown()) * 15), 6);
                    } else {
                        guiGraphics.blitSprite(second, x, y, 15, 6);
                    }
                    guiGraphics.setColor(1, 1, 1, 1);
                    RenderSystem.disableBlend();
                }
            }
        }
    }

    private static final ResourceLocation[] AIR_JUMP_TEXTURES = new ResourceLocation[4];
    static {
        for(int i = 0; i < AIR_JUMP_TEXTURES.length; i++) {
            AIR_JUMP_TEXTURES[i] = ExtendedCombat.id("hud/air_jump_" + i);
        }
    }

    private static ResourceLocation getTexture(int i) {
        i %= AIR_JUMP_TEXTURES.length;
        if (i < 0) {
            i += AIR_JUMP_TEXTURES.length;
        }
        return AIR_JUMP_TEXTURES[i];
    }

    private static final ResourceLocation BLINK_BACKGROUND_TEXTURE = ExtendedCombat.id("hud/blink_background");
    private static final ResourceLocation BLINK_PROGRESS_TEXTURE = ExtendedCombat.id("hud/blink_progress");
    private static final ResourceLocation DASH_BACKGROUND_TEXTURE = ExtendedCombat.id("hud/dash_background");
    private static final ResourceLocation DASH_PROGRESS_TEXTURE = ExtendedCombat.id("hud/dash_progress");

    public static void blinkHud(GuiGraphics guiGraphics, Minecraft minecraft) {
        if (minecraft != null) {
            BlinkBehavior blink = minecraft.cameraEntity.getData(ModDataAttachments.BLINK);
            if (blink.hasBlink() && blink.getCooldown() > 0 && Minecraft.renderNames()) {
                RenderSystem.enableBlend();
                int x = guiGraphics.guiWidth() / 2 - 14, y = guiGraphics.guiHeight() / 2 - 7;
                guiGraphics.blitSprite(BLINK_PROGRESS_TEXTURE, x, y, 6, 15);
                if (blink.getCooldown() < blink.getLastCooldown()) {
                    guiGraphics.blitSprite(BLINK_BACKGROUND_TEXTURE, 6, 15, 0, 0, x, y, 6, (int) ((blink.getCooldown() / (float) blink.getLastCooldown()) * 15));
                } else {
                    guiGraphics.blitSprite(BLINK_BACKGROUND_TEXTURE, x, y, 6, 15);
                }
                guiGraphics.setColor(1, 1, 1, 1);
                RenderSystem.disableBlend();
            }
        }
    }

    public static void dashHud(GuiGraphics guiGraphics, Minecraft minecraft) {
        if (minecraft != null) {
            DashBehavior dash = minecraft.cameraEntity.getData(ModDataAttachments.DASH);
            if (dash.hasDash() && dash.getCooldown() > 0 && Minecraft.renderNames()) {
                RenderSystem.enableBlend();
                int x = guiGraphics.guiWidth() / 2 - 7, y = guiGraphics.guiHeight() / 2 - 14;
                guiGraphics.blitSprite(DASH_PROGRESS_TEXTURE, x, y, 15, 6);
                if (dash.getCooldown() < dash.getLastCooldown()) {
                    guiGraphics.blitSprite(DASH_BACKGROUND_TEXTURE, 15, 6, 0, 0, x, y, (int) ((dash.getCooldown() / (float) dash.getLastCooldown()) * 15), 6);
                } else {
                    guiGraphics.blitSprite(DASH_BACKGROUND_TEXTURE, x, y, 15, 6);
                }
                guiGraphics.setColor(1, 1, 1, 1);
                RenderSystem.disableBlend();
            }
        }
    }
}
