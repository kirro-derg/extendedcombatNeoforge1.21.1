package dev.kirro.extendedcombat;

import com.mojang.blaze3d.platform.InputConstants;
import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.enchantment.payload.AirJumpParticlePayload;
import dev.kirro.extendedcombat.enchantment.payload.BlinkParticlePayload;
import dev.kirro.extendedcombat.enchantment.payload.BlinkSyncPayload;
import dev.kirro.extendedcombat.enchantment.payload.DashParticlePayload;
import dev.kirro.extendedcombat.item.payload.HideHoodServerPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.NeoForge;
import org.checkerframework.checker.units.qual.K;
import org.lwjgl.glfw.GLFW;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = ExtendedCombat.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = ExtendedCombat.MOD_ID, value = Dist.CLIENT)
public class ExtendedCombatClient {
    public ExtendedCombatClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        ExtendedCombat.LOGGER.info("HELLO FROM CLIENT SETUP");
        ExtendedCombat.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FRAMED_GLASS_PANEL.get(), RenderType.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLACK_APPLE_BUSH.get(), RenderType.CUTOUT);
    }

    public static final Lazy<KeyMapping> DASH = Lazy.of(
            new KeyMapping(
                    "key.extendedcombat.dash",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_LEFT_SHIFT,
                    "key.categories.movement"
            ));
    public static final Lazy<KeyMapping> BLINK = Lazy.of(
            new KeyMapping(
                    "key.extendedcombat.blink",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_B,
                    "key.categories.movement"
            ));

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(DASH.get());
        event.register(BLINK.get());
    }


}
