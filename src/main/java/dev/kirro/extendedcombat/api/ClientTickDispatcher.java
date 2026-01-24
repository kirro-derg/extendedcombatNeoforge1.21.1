package dev.kirro.extendedcombat.api;

import dev.kirro.extendedcombat.ExtendedCombat;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = ExtendedCombat.MOD_ID)
public class ClientTickDispatcher {
    private static final List<ClientTickingComponent> CLIENT_TICKING_COMPONENTS = new ArrayList<>();

    public static void register(ClientTickingComponent component) {
        CLIENT_TICKING_COMPONENTS.add(component);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        for (ClientTickingComponent c : CLIENT_TICKING_COMPONENTS) {
            //c.clientTick(minecraft);
        }
    }
}
