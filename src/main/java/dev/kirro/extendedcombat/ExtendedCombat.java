package dev.kirro.extendedcombat;

import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.payload.*;
import dev.kirro.extendedcombat.data.ModDataComponents;
import dev.kirro.extendedcombat.item.ModItems;
import dev.kirro.extendedcombat.loot.ModLootModifiers;
import dev.kirro.extendedcombat.villager.ModPoi;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ExtendedCombat.MOD_ID)
public class ExtendedCombat {

    public static final String MOD_ID = "extendedcombat";
    //public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public ExtendedCombat(IEventBus eventBus, ModContainer modContainer) throws InstantiationException, IllegalAccessException {
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModEnchantmentEffects.register(eventBus);
        ModDataComponents.register(eventBus);
        ModDataAttachments.register(eventBus);
        ModPoi.register(eventBus);
        ModLootModifiers.register(eventBus);
        registerPayloads();

        eventBus.addListener(ModItems::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void registerPayloads() {
        //server payloads
        PayloadTypeRegistry.playS2C().register(AirJumpParticlePayload.ID, AirJumpParticlePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(DashParticlePayload.ID, DashParticlePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BlinkParticlePayload.ID, BlinkParticlePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BlinkSyncPayload.ID, BlinkSyncPayload.CODEC);
        // client payloads
        PayloadTypeRegistry.playC2S().register(AirJumpPayload.ID, AirJumpPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(DashPayload.ID, DashPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(BlinkPayload.ID, BlinkPayload.CODEC);
        // server receivers
        ServerPlayNetworking.registerGlobalReceiver(AirJumpPayload.ID, new AirJumpPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(DashPayload.ID, new DashPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(BlinkPayload.ID, new BlinkPayload.Receiver());
        // client receivers
        ClientPlayNetworking.registerGlobalReceiver(AirJumpParticlePayload.ID, new AirJumpParticlePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(DashParticlePayload.ID, new DashParticlePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(BlinkParticlePayload.ID, new BlinkParticlePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(BlinkSyncPayload.ID, BlinkSyncPayload::handle);
    }
}