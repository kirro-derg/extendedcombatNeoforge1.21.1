package dev.kirro.extendedcombat.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.entity.ModModelLayers;
import dev.kirro.extendedcombat.entity.render.ArmorSleeveLayer;
import dev.kirro.extendedcombat.entity.render.ModElytraLayer;
import dev.kirro.extendedcombat.item.custom.WoolArmorItem;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    @Shadow
    protected abstract void setModelProperties(AbstractClientPlayer player);

    public PlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void extendedcombat$armorSleeves(EntityRendererProvider.Context ctx, boolean slim, CallbackInfo ci) {
        this.addLayer(new ArmorSleeveLayer<>(this,
                new HumanoidArmorModel<>(ctx.bakeLayer(slim ? ModModelLayers.PLAYER_SLIM_SLEEVES : ModModelLayers.PLAYER_SLEEVES))));
        this.addLayer(new ModElytraLayer<>(this,
                ctx.getModelSet()));
    }

    @Inject(method = "renderHand", at = @At("HEAD"), cancellable = true)
    private void renderHand(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, AbstractClientPlayer player,
                            ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        ItemStack stack = extendedcombat$getArmor(player);
        IClientItemExtensions extensions = IClientItemExtensions.of(stack);
        int i = extensions.getDefaultDyeColor(stack);
        if (extendedcombat$getArmor(player).is(ModItemTags.SLEEVED_ARMOR)) {
            ci.cancel();
            PlayerModel<AbstractClientPlayer> playermodel = this.getModel();
            this.setModelProperties(player);
            playermodel.attackTime = 0.0F;
            playermodel.crouching = false;
            playermodel.swimAmount = 0.0F;
            playermodel.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            arm.xRot = 0.0F;
            ResourceLocation resourcelocation = extendedcombat$getTextureId(stack);
            arm.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(player.getSkin().texture())), combinedLight, OverlayTexture.NO_OVERLAY);
            sleeve.xRot = 0.0F;
            sleeve.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(resourcelocation)), combinedLight, OverlayTexture.NO_OVERLAY, i);
        }
    }

    @Unique
    private ResourceLocation extendedcombat$getTextureId(ItemStack stack) {
        ResourceLocation texturePath = BuiltInRegistries.ITEM.getKey(stack.getItem());
        ResourceLocation truncatedPath = ResourceLocation.parse(texturePath.getPath().replace("_chestplate", "").replace("_reinforced_elytra", ""));

        if (!(stack.getItem() instanceof WoolArmorItem)) {
            return ExtendedCombat.id("textures/models/armor/" + truncatedPath.getPath() + "_sleeve_overlay.png");
        } else {
            return ExtendedCombat.id("textures/models/armor/wool.png");
        }
    }

    @Unique
    private ItemStack extendedcombat$getArmor(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.CHEST);
    }
}
