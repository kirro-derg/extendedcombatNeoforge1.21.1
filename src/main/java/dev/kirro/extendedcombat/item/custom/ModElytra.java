package dev.kirro.extendedcombat.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ElytraItem;

public class ModElytra extends ModArmorItem implements IElytraItem  {
    public ModElytra(Holder<ArmorMaterial> material, Type type, Properties settings) {
        super(material, type, settings);
    }
}
