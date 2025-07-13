package dev.kirro.extendedcombat.misc;

import com.mojang.blaze3d.platform.InputConstants;
import dev.kirro.extendedcombat.ExtendedCombat;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@Mod(value = ExtendedCombat.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = ExtendedCombat.MOD_ID, value = Dist.CLIENT)
public class ModKeybinds {
    public static KeyMapping DASH_KEY;



    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        DASH_KEY = new KeyMapping(
                "key.extendedcombat.dash",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_SHIFT,
                "key.categories.movement"
        );
        event.register(DASH_KEY);
    }

}

