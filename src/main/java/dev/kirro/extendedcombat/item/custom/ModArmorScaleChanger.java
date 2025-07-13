package dev.kirro.extendedcombat.item.custom;

import dev.kirro.extendedcombat.ExtendedCombat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;
import virtuoel.pehkui.api.ScaleType;

@Mod(ExtendedCombat.MOD_ID)
public class ModArmorScaleChanger {

    public static void updatePlayerScale(Player player) {
        if (isWearingFullSet(player)) {
            ScaleTypes.HEIGHT.getScaleData(player).setTargetScale(1.25F);// Grow to 1.25% original size
        } else {
            ScaleTypes.HEIGHT.getScaleData(player).setTargetScale(1.0F); // Reset to normal size
        }

    }

    private static boolean isWearingFullSet(Player player) {
        ItemStack head = player.getInventory().getArmor(3);
        ItemStack chest = player.getInventory().getArmor(2);
        ItemStack legs = player.getInventory().getArmor(1);
        ItemStack feet = player.getInventory().getArmor(0);

        return head.getItem() == ModItems.NETHER_STEEL_HELMET.get()
                && chest.getItem() == ModItems.NETHER_STEEL_CHESTPLATE.get()
                && legs.getItem() == ModItems.NETHER_STEEL_LEGGINGS.get()
                && feet.getItem() == ModItems.NETHER_STEEL_BOOTS.get();
    }



}
