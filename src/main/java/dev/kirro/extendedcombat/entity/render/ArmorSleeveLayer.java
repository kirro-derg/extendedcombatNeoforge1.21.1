package dev.kirro.extendedcombat.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.data.ModDataComponents;
import dev.kirro.extendedcombat.item.ModItems;
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
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;

@OnlyIn(Dist.CLIENT)
public class ArmorSleeveLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private final A sleeveModel;

    public ArmorSleeveLayer(RenderLayerParent<T, M> renderer, A sleeveModel) {
        super(renderer);
        this.sleeveModel = sleeveModel;
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, @NotNull T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderRightSleeve(poseStack, buffer, livingEntity, packedLight, this.sleeveModel, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        this.renderLeftSleeve(poseStack, buffer, livingEntity, packedLight, this.sleeveModel, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    }

    private void renderRightSleeve(PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity, int packedLight, A rightModel, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        var inventory = CuriosApi.getCuriosInventory(livingEntity).get().getCurios();
        ItemStack rightSleeve = inventory.get("right_sleeve").getStacks().getStackInSlot(0);
        ItemStack chest = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (chest.getOrDefault(ModDataComponents.BLINK, false)) {
            return;
        }

        if (rightSleeve != null) {
            this.getParentModel().copyPropertiesTo(rightModel);
            this.setPartVisibility(rightModel, livingEntity, true);
            Model model = this.getArmorModelHook(livingEntity, rightSleeve, rightModel);
            IClientItemExtensions extensions = IClientItemExtensions.of(rightSleeve);
            extensions.setupModelAnimations(livingEntity, rightSleeve, EquipmentSlot.CHEST, model, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
            int i = extensions.getDefaultDyeColor(rightSleeve);
            this.renderModel(poseStack, bufferSource, packedLight, model, i, getTextureId(rightSleeve));
            if (rightSleeve.hasFoil()) this.renderGlint(poseStack, bufferSource, packedLight, model);
        }
    }

    private void renderLeftSleeve(PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity, int packedLight, A leftModel, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        var inventory = CuriosApi.getCuriosInventory(livingEntity).get().getCurios();
        ItemStack leftSleeve = inventory.get("left_sleeve").getStacks().getStackInSlot(0);
        ItemStack chest = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (chest.getOrDefault(ModDataComponents.BLINK, false)) {
            return;
        }

        if (leftSleeve != null) {
            this.getParentModel().copyPropertiesTo(leftModel);
            this.setPartVisibility(leftModel, livingEntity, false);
            Model model = this.getArmorModelHook(livingEntity, leftSleeve, leftModel);
            IClientItemExtensions extensions = IClientItemExtensions.of(leftSleeve);
            extensions.setupModelAnimations(livingEntity, leftSleeve, EquipmentSlot.CHEST, model, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
            int i = extensions.getDefaultDyeColor(leftSleeve);
            this.renderModel(poseStack, bufferSource, packedLight, model, i, getTextureId(leftSleeve));
            if (leftSleeve.hasFoil()) this.renderGlint(poseStack, bufferSource, packedLight, model);
        }
    }

    private boolean leftSleevePresent(LivingEntity entity, boolean right) {
        var inventory = CuriosApi.getCuriosInventory(entity).get().getCurios();
        if (inventory != null && !right) {
            return !inventory.get("left_sleeve").getStacks().getStackInSlot(0).isEmpty()
                    && inventory.get("left_sleeve").getRenders().getFirst();
        }
        return false;
    }
    private boolean rightSleevePresent(LivingEntity entity, boolean right) {
        var inventory = CuriosApi.getCuriosInventory(entity).get().getCurios();
        if (inventory != null && right) {
            return !inventory.get("right_sleeve").getStacks().getStackInSlot(0).isEmpty()
                    && inventory.get("right_sleeve").getRenders().getFirst();
        }
        return false;
    }

    private ResourceLocation getTextureId(ItemStack stack) {
        ResourceLocation texturePath = BuiltInRegistries.ITEM.getKey(stack.getItem());

        if (!stack.is(ModItems.WOOL_SLEEVE)) {
            return ExtendedCombat.id("textures/models/armor/" + texturePath.getPath() + "_overlay.png");
        } else {
            return ExtendedCombat.id("textures/models/armor/wool.png");
        }
    }

    protected void setPartVisibility(A model, LivingEntity entity, boolean right) {
        model.setAllVisible(false);
        model.rightArm.visible = this.rightSleevePresent(entity, right);
        model.leftArm.visible = this.leftSleevePresent(entity, right);
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Model model, int dyeColor, ResourceLocation textureLocation) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(textureLocation));
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, dyeColor);
    }

    private void renderGlint(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Model model) {
        model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.armorEntityGlint()), packedLight, OverlayTexture.NO_OVERLAY);
    }

    protected Model getArmorModelHook(T entity, ItemStack itemStack, A model) {
        return ClientHooks.getArmorModel(entity, itemStack, EquipmentSlot.CHEST, model);
    }
}
