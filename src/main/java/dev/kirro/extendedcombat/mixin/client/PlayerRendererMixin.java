package dev.kirro.extendedcombat.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.entity.ModModelLayers;
import dev.kirro.extendedcombat.entity.render.ArmorSleeveLayer;
import dev.kirro.extendedcombat.entity.render.ModElytraLayer;
import dev.kirro.extendedcombat.item.ModItems;
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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Map;

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

    @Inject(method = "renderHand", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/renderer/RenderType;entityTranslucent(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"), cancellable = true)
    private void renderSleeve(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, AbstractClientPlayer player, ModelPart rendererArm, ModelPart rendererArmwear, CallbackInfo ci) {
        ItemStack stack = extendedcombat$getArmor(player);
        IClientItemExtensions extensions = IClientItemExtensions.of(stack);
        int i = extensions.getDefaultDyeColor(stack);
        if (stack.is(ModItemTags.SLEEVES)) {
            ci.cancel();
            rendererArmwear.
                    render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(extendedcombat$getTextureId(stack))),
                            combinedLight, OverlayTexture.NO_OVERLAY, i);
        }
    }

    @Unique
    private ResourceLocation extendedcombat$getTextureId(ItemStack stack) {
        ResourceLocation texturePath = BuiltInRegistries.ITEM.getKey(stack.getItem());
        ResourceLocation truncatedPath = ResourceLocation.parse(texturePath.getPath().replace("_chestplate", ""));

        if (!(stack.is(ModItems.WOOL_SLEEVE))) {
            return ExtendedCombat.id("textures/models/armor/" + texturePath.getPath() + "_overlay.png");
        } else {
            return ExtendedCombat.id("textures/models/armor/wool.png");
        }
    }

    @Unique
    private ItemStack extendedcombat$getArmor(LivingEntity entity) {
        if (CuriosApi.getCuriosInventory(entity).isPresent()) {
            var curiosInventory = CuriosApi.getCuriosInventory(entity).get();
            var curios = curiosInventory.getCurios();
            ICurioStacksHandler inventory = curios.get("right_sleeve");
            if (inventory != null) return inventory.getStacks().getStackInSlot(0);
        }
        return new ItemStack(ModItems.HANDLE.get());
    }
}
