package dev.kirro.extendedcombat.api;

import com.mojang.serialization.MapCodec;
import dev.kirro.extendedcombat.ExtendedCombat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EventBusSubscriber(modid = ExtendedCombat.MOD_ID)
public class ServerTickDispatcher {

    private static final List<ServerTickingComponent> SERVER_TICKING_COMPONENTS = new ArrayList<>();

    public static void register(ServerTickingComponent component) {
        SERVER_TICKING_COMPONENTS.add(component);
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        for (ServerTickingComponent c : SERVER_TICKING_COMPONENTS) {
            //c.serverTick(server);
        }
    }
}
