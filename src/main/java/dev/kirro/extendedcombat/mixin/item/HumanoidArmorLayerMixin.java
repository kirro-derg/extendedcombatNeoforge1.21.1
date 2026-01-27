package dev.kirro.extendedcombat.mixin.item;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.item.ModDataComponents;
import dev.kirro.extendedcombat.item.custom.WoolArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    @Shadow
    protected abstract Model getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model);

    @Shadow
    protected abstract boolean usesInnerModel(EquipmentSlot slot);

    @Shadow
    protected abstract void renderModel(PoseStack p_289664_, MultiBufferSource p_289689_, int p_289681_, Model p_289658_, int p_350798_, ResourceLocation p_324344_);

    @Shadow
    protected abstract void renderTrim(Holder<ArmorMaterial> p_323506_, PoseStack p_289687_, MultiBufferSource p_289643_, int p_289683_, ArmorTrim p_289692_, Model p_289663_, boolean p_289651_);

    @Shadow
    protected abstract void renderGlint(PoseStack p_289673_, MultiBufferSource p_289654_, int p_289649_, Model p_289659_);

    public HumanoidArmorLayerMixin(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Inject(method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;FFFFFF)V",
    at = @At("HEAD"), cancellable = true)
    private void getTexture(PoseStack poseStack, MultiBufferSource bufferSource, T livingEntity, EquipmentSlot slot, int packedLight, A p_model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ItemStack itemstack = livingEntity.getItemBySlot(slot);
        if (itemstack.getItem() instanceof WoolArmorItem armorItem) {
            ci.cancel();
            if (armorItem.getEquipmentSlot() == slot) {
                this.getParentModel().copyPropertiesTo(p_model);
                this.setPartVisibility(p_model, slot, itemstack);
                Model model = this.getArmorModelHook(livingEntity, itemstack, slot, p_model);
                boolean flag = this.usesInnerModel(slot);
                ArmorMaterial armormaterial = armorItem.getMaterial().value();
                IClientItemExtensions extensions = IClientItemExtensions.of(itemstack);
                extensions.setupModelAnimations(livingEntity, itemstack, slot, model, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
                int fallbackColor = extensions.getDefaultDyeColor(itemstack);

                for(int layerIdx = 0; layerIdx < armormaterial.layers().size(); ++layerIdx) {
                    ArmorMaterial.Layer armormaterial$layer = armormaterial.layers().get(layerIdx);
                    int j = extensions.getArmorLayerTintColor(itemstack, livingEntity, armormaterial$layer, layerIdx, fallbackColor);
                    if (j != 0) {
                        ResourceLocation texture = ExtendedCombat.id("textures/models/armor/wool" + "_layer_" + (flag ? 2 : 1) + ".png");
                        this.renderModel(poseStack, bufferSource, packedLight, model, j, texture);
                    }
                }

                ArmorTrim armortrim = itemstack.get(DataComponents.TRIM);
                if (armortrim != null) {
                    this.renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, armortrim, model, flag);
                }

                if (itemstack.hasFoil()) {
                    this.renderGlint(poseStack, bufferSource, packedLight, model);
                }
            }
        }
    }

    @Unique
    protected void setPartVisibility(A model, EquipmentSlot slot, ItemStack stack) {
        model.setAllVisible(false);
        switch (slot) {
            case HEAD -> {
                if (!stack.getOrDefault(ModDataComponents.HIDDEN, false)) {
                    model.head.visible = true;
                }
            }
            case CHEST -> {
                if (!stack.getOrDefault(ModDataComponents.HIDDEN, false)) {
                    model.hat.visible = true;
                }
                model.body.visible = true;
                model.rightArm.visible = true;
                model.leftArm.visible = true;
            }
            case LEGS -> {
                model.body.visible = true;
                model.rightLeg.visible = true;
                model.leftLeg.visible = true;
            }
            case FEET -> {
                model.rightLeg.visible = true;
                model.leftLeg.visible = true;
            }
        }
    }
}
