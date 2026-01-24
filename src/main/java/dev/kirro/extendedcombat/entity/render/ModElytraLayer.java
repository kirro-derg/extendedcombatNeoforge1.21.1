package dev.kirro.extendedcombat.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation SKIN = ExtendedCombat.id("textures/entity/echo_reinforced_elytra.png");
    private static final ResourceLocation WINGS_LOCATION = ExtendedCombat.id("textures/capes/kirro_cape.png");
    private final ElytraModel<T> elytraModel;

    public ModElytraLayer(RenderLayerParent<T, M> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.elytraModel = new ElytraModel(modelSet.bakeLayer(ModelLayers.ELYTRA));
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (itemstack.is(ModItems.ECHO_REINFORCED_ELYTRA)) {
            ResourceLocation resourcelocation;
            if (livingEntity instanceof AbstractClientPlayer abstractClientPlayer) {
                AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer)livingEntity;
                PlayerSkin playerskin = abstractclientplayer.getSkin();
                if (playerskin.elytraTexture() != null) {
                    if (abstractClientPlayer.getStringUUID().equalsIgnoreCase("9886cd76-bc16-49ee-b22b-6c4a1f3cf53a")) {
                        resourcelocation = WINGS_LOCATION;
                    } else {
                        resourcelocation = playerskin.elytraTexture();
                    }
                } else if (playerskin.capeTexture() != null && abstractclientplayer.isModelPartShown(PlayerModelPart.CAPE)) {
                    if (abstractClientPlayer.getStringUUID().equalsIgnoreCase("9886cd76-bc16-49ee-b22b-6c4a1f3cf53a")) {
                        resourcelocation = WINGS_LOCATION;
                    } else {
                        resourcelocation = playerskin.capeTexture();
                    }
                } else {
                    resourcelocation = SKIN;
                }
            } else {
                resourcelocation = SKIN;
            }

            poseStack.pushPose();
            poseStack.translate(0.0F, 0.0F, 0.125F);
            this.getParentModel().copyPropertiesTo(this.elytraModel);
            this.elytraModel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(
                    buffer, RenderType.armorCutoutNoCull(resourcelocation), itemstack.hasFoil()
            );
            this.elytraModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }
}
