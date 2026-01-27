package dev.kirro.extendedcombat.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.item.custom.WoolArmorItem;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

@OnlyIn(Dist.CLIENT)
public class ArmorSleeveLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private final A sleeveModel;

    public ArmorSleeveLayer(RenderLayerParent<T, M> renderer, A sleeveModel) {
        super(renderer);
        this.sleeveModel = sleeveModel;
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderArmorPiece(poseStack, buffer, livingEntity, EquipmentSlot.CHEST, packedLight, this.sleeveModel, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    }

    private void renderArmorPiece(PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity, EquipmentSlot slot, int packedLight, A p_model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = livingEntity.getItemBySlot(slot);
        if (stack.getItem() instanceof ArmorItem armoritem && stack.is(ModItemTags.SLEEVED_ARMOR) && (livingEntity.getData(ModDataAttachments.BLINK).getDuration() == 0)) {
            if (armoritem.getEquipmentSlot() == slot) {
                this.getParentModel().copyPropertiesTo(p_model);
                this.setPartVisibility(p_model);
                Model model = this.getArmorModelHook(livingEntity, stack, slot, p_model);
                IClientItemExtensions extensions = IClientItemExtensions.of(stack);
                extensions.setupModelAnimations(livingEntity, stack, slot, model, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
                int i = extensions.getDefaultDyeColor(stack);
                this.renderModel(poseStack, bufferSource, packedLight, model, i, getTextureId(stack));
                if (stack.hasFoil()) this.renderGlint(poseStack, bufferSource, packedLight, model);
            }
        }

    }

    private ResourceLocation getTextureId(ItemStack stack) {
        ResourceLocation texturePath = BuiltInRegistries.ITEM.getKey(stack.getItem());
        ResourceLocation truncatedPath = ResourceLocation.parse(texturePath.getPath().replace("_chestplate", ""));

        if (!(stack.getItem() instanceof WoolArmorItem)) {
            return ExtendedCombat.id("textures/models/armor/" + truncatedPath.getPath() + "_sleeve_overlay.png");
        } else {
            return ExtendedCombat.id("textures/models/armor/wool.png");
        }
    }

    protected void setPartVisibility(A model) {
        model.setAllVisible(false);
        model.rightArm.visible = true;
        model.leftArm.visible = true;
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Model model, int dyeColor, ResourceLocation textureLocation) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(textureLocation));
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, dyeColor);
    }

    private void renderGlint(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Model model) {
        model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.armorEntityGlint()), packedLight, OverlayTexture.NO_OVERLAY);
    }

    protected Model getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model) {
        return ClientHooks.getArmorModel(entity, itemStack, slot, model);
    }
}
