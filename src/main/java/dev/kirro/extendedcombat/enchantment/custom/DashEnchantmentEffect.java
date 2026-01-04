package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.MapCodec;
import dev.kirro.extendedcombat.enchantment.ModEnchantments;
import dev.kirro.extendedcombat.misc.ModKeybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record DashEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<DashEnchantmentEffect> CODEC = MapCodec.unit(DashEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {

        Player player = Minecraft.getInstance().player;

        if (ModKeybinds.DASH_KEY.consumeClick() && player != null) {
            ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);  //player.getInventory().getArmor(1);
            Holder<Enchantment> dash = (Holder<Enchantment>) ModEnchantments.DASH;
            if (leggings.isEnchanted()) {
                Vec3 look = player.getLookAngle();
                player.setDeltaMovement(Vec3.directionFromRotation(2, 2));
                player.hurtMarked = true; // force motion sync
            }
        }

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
