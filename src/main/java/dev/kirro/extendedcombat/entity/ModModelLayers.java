package dev.kirro.extendedcombat.entity;

import dev.kirro.extendedcombat.ExtendedCombat;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = ExtendedCombat.MOD_ID)
public class ModModelLayers {
    public static final ModelLayerLocation PLAYER_SLIM_SLEEVES = createSleeveLayer("player_slim");
    public static final ModelLayerLocation PLAYER_SLEEVES = createSleeveLayer("player");


    private static ModelLayerLocation register(String path, String model) {
        ModelLayerLocation modellayerlocation = create(path, model);
        if (!ModelLayers.ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    public static ModelLayerLocation create(String id, String layer) {
        return new ModelLayerLocation(ExtendedCombat.id(id), layer);
    }

    private static ModelLayerLocation createSleeveLayer(String id) {
        return register(id, "sleeves");
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PLAYER_SLIM_SLEEVES, () -> LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(0.3f), true), 64, 64));
        event.registerLayerDefinition(PLAYER_SLEEVES, () -> LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(0.3f), false), 64, 64));
    }
}
