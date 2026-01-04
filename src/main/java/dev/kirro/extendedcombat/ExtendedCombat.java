package dev.kirro.extendedcombat;

import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.item.ModItems;
import dev.kirro.extendedcombat.misc.ModKeybinds;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
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
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.beans.EventHandler;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ExtendedCombat.MOD_ID)
public class ExtendedCombat {

    public static final String MOD_ID = "extendedcombat";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("extended_combat_items",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.extended_combat_items")).withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.NETHER_STEEL_GREATSWORD.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(ModItems.NETHER_STEEL_INGOT.get());
        output.accept(ModItems.HANDLE);// Add the example item to the tab. For your own tabs, this method is preferred over the event
        output.accept(ModItems.NETHER_STEEL_UPGRADE);
        // Add the example item to the tab. For your own tabs, this method is preferred over the event
        output.accept(ModBlocks.NETHER_STEEL_BLOCK);// Add the example item to the tab. For your own tabs, this method is preferred over the event
        output.accept(ModBlocks.ECHO_STEEL_BLOCK);// Add the example item to the tab. For your own tabs, this method is preferred over the event
        output.accept(ModBlocks.WARDING_STONE);// Add the example item to the tab. For your own tabs, this method is preferred over the event
        output.accept(ModBlocks.FRAMED_GLASS_PANEL);// Add the example item to the tab. For your own tabs, this method is preferred over the event
    }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ExtendedCombat(IEventBus eventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        eventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModEnchantmentEffects.register(eventBus);

        // Register the item to a creative tab
        eventBus.addListener(this::addCreative);
        CREATIVE_MODE_TABS.register(eventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.NETHER_STEEL_INGOT);
            event.accept(ModItems.NETHER_STEEL_UPGRADE);
            event.accept(ModItems.HANDLE);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.NETHER_STEEL_PICKAXE);
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.NETHER_STEEL_GREATSWORD);
            event.accept(ModItems.NETHER_STEEL_HELMET);
            event.accept(ModItems.NETHER_STEEL_CHESTPLATE);
            event.accept(ModItems.NETHER_STEEL_LEGGINGS);
            event.accept(ModItems.NETHER_STEEL_BOOTS);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.NETHER_STEEL_BLOCK);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.WARDING_STONE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}