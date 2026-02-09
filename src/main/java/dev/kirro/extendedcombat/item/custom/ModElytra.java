package dev.kirro.extendedcombat.item.custom;

import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;

public class ModElytra extends ModArmorItem implements FabricElytraItem {
    public ModElytra(Holder<ArmorMaterial> material, Type type, Properties settings) {
        super(material, type, settings);
    }
}
