package dev.kirro.extendedcombat;

import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.payload.*;
import dev.kirro.extendedcombat.item.ModDataComponents;
import dev.kirro.extendedcombat.item.ModItems;
import dev.kirro.extendedcombat.item.payload.HideHoodClientPayload;
import dev.kirro.extendedcombat.item.payload.HideHoodServerPayload;
import dev.kirro.extendedcombat.villager.ModPoi;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ExtendedCombat.MOD_ID)
public class ExtendedCombat {

    public static final String MOD_ID = "extendedcombat";

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXTENDEDCOMBAT_ITEMS = CREATIVE_MODE_TABS.register("extended_combat_items",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extended_combat_items")).withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.NETHER_STEEL_GREATSWORD.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(ModItems.NETHER_STEEL_INGOT);
        output.accept(ModItems.ECHO_STEEL_INGOT);
        output.accept(ModItems.HANDLE);
        output.accept(ModItems.NETHER_STEEL_UPGRADE);
        output.accept(ModItems.ECHO_STEEL_UPGRADE);

        output.accept(ModBlocks.NETHER_STEEL_BLOCK);
        output.accept(ModBlocks.ECHO_STEEL_BLOCK);
        output.accept(ModBlocks.WARDING_STONE);
        output.accept(ModBlocks.FRAMED_GLASS_PANEL);

        output.accept(ModItems.WOODEN_GREATSWORD);
        output.accept(ModItems.STONE_GREATSWORD);
        output.accept(ModItems.IRON_GREATSWORD);
        output.accept(ModItems.GOLDEN_GREATSWORD);
        output.accept(ModItems.DIAMOND_GREATSWORD);
        output.accept(ModItems.NETHERITE_GREATSWORD);
        output.accept(ModItems.NETHER_STEEL_GREATSWORD);
        output.accept(ModItems.ECHO_STEEL_GREATSWORD);

        output.accept(ModItems.NETHER_STEEL_PICKAXE);
        output.accept(ModItems.ECHO_STEEL_PICKAXE);

        output.accept(ModItems.WOODEN_HAMMER);
        output.accept(ModItems.STONE_HAMMER);
        output.accept(ModItems.IRON_HAMMER);
        output.accept(ModItems.GOLDEN_HAMMER);
        output.accept(ModItems.DIAMOND_HAMMER);
        output.accept(ModItems.NETHERITE_HAMMER);
        output.accept(ModItems.NETHER_STEEL_HAMMER);
        output.accept(ModItems.ECHO_STEEL_HAMMER);

        output.accept(ModItems.HUNTER_MASK);
        output.accept(ModItems.HUNTER_CLOAK);
        output.accept(ModItems.HUNTER_LEGGINGS);
        output.accept(ModItems.HUNTER_BOOTS);
        output.accept(ModItems.NETHER_STEEL_HELMET);
        output.accept(ModItems.NETHER_STEEL_CHESTPLATE);
        output.accept(ModItems.NETHER_STEEL_LEGGINGS);
        output.accept(ModItems.NETHER_STEEL_BOOTS);
        output.accept(ModItems.NETHER_STEEL_MASK);
        output.accept(ModItems.NETHER_STEEL_CLOAK);
        output.accept(ModItems.NETHER_STEEL_HUNTER_LEGGINGS);
        output.accept(ModItems.NETHER_STEEL_HUNTER_BOOTS);
        output.accept(ModItems.ECHO_STEEL_HELMET);
        output.accept(ModItems.ECHO_STEEL_CHESTPLATE);
        output.accept(ModItems.ECHO_STEEL_LEGGINGS);
        output.accept(ModItems.ECHO_STEEL_BOOTS);
        output.accept(ModItems.ECHO_STEEL_MASK);
        output.accept(ModItems.ECHO_STEEL_CLOAK);
        output.accept(ModItems.ECHO_STEEL_HUNTER_LEGGINGS);
        output.accept(ModItems.ECHO_STEEL_HUNTER_BOOTS);
        output.accept(ModItems.ECHO_REINFORCED_ELYTRA);

        output.accept(ModItems.BLACK_APPLE);
        output.accept(ModItems.GOLDEN_STEAK);
        output.accept(ModItems.HONEY_BREAD);

        output.accept(ModItems.MILK_BOTTLE);
        output.accept(ModItems.SWEET_BERRY_MILK_BOTTLE);
        output.accept(ModItems.CHOCOLATE_MILK_BOTTLE);

    }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ExtendedCombat(IEventBus eventBus, ModContainer modContainer) throws InstantiationException, IllegalAccessException {
        // Register the commonSetup method for modloading
        eventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(ModEvents.class);
        //NeoForge.EVENT_BUS.addListener(ModEvents::registerBindings);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModEnchantmentEffects.register(eventBus);
        ModDataComponents.register(eventBus);
        ModDataAttachments.register(eventBus);
        ModPoi.register(eventBus);
        registerPayloads();

        // Register the item to a creative tab
        eventBus.addListener(ModItems::addCreative);
        CREATIVE_MODE_TABS.register(eventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void registerPayloads() {
        //server
        PayloadTypeRegistry.playS2C().register(AirJumpParticlePayload.ID, AirJumpParticlePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(DashParticlePayload.ID, DashParticlePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BlinkParticlePayload.ID, BlinkParticlePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BlinkSyncPayload.ID, BlinkSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(HideHoodServerPayload.ID, HideHoodServerPayload.CODEC);
        // client
        PayloadTypeRegistry.playC2S().register(AirJumpPayload.ID, AirJumpPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(DashPayload.ID, DashPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(BlinkPayload.ID, BlinkPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(HideHoodClientPayload.ID, HideHoodClientPayload.CODEC);
        // server receivers
        ServerPlayNetworking.registerGlobalReceiver(AirJumpPayload.ID, new AirJumpPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(DashPayload.ID, new DashPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(BlinkPayload.ID, new BlinkPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(HideHoodClientPayload.ID, new HideHoodClientPayload.Reciever());
        // client receivers
        ClientPlayNetworking.registerGlobalReceiver(AirJumpParticlePayload.ID, new AirJumpParticlePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(DashParticlePayload.ID, new DashParticlePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(BlinkParticlePayload.ID, new BlinkParticlePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(BlinkSyncPayload.ID, BlinkSyncPayload::handle);
        ClientPlayNetworking.registerGlobalReceiver(HideHoodServerPayload.ID, new HideHoodServerPayload.Receiver());
    }
}