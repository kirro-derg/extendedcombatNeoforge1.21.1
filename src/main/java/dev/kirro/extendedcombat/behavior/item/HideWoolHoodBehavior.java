package dev.kirro.extendedcombat.behavior.item;

import dev.kirro.extendedcombat.api.CommonTickingComponent;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

public class HideWoolHoodBehavior implements CommonTickingComponent {
    private final Player player;
    private boolean hoodHidden, hoodUsed;
    private boolean maskHidden, maskUsed;
    private int hoodTick = 0;
    private int maskTick = 0;

    public HideWoolHoodBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        tag.putBoolean("HoodHidden", this.hoodHidden);
        tag.putBoolean("MaskHidden", this.maskHidden);
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        tag.getBoolean("HoodHidden");
        tag.getBoolean("MaskHidden");
    }

    @Override
    public void tick() {
        if (hoodTick > 0) {
            hoodTick--;
        } else if (hoodTick == 0) {
            hoodUsed = false;
        }
        if (maskTick > 0) {
            maskTick--;
        } else if (maskTick == 0) {
            maskUsed = false;
        }

    }

    @Override
    public void clientTick() {
        tick();
    }

    public void useHood() {
        player.playSound(SoundEvents.ARMOR_EQUIP_LEATHER.value(), 1.0f, 1.0f);
        setHoodHidden(!hoodHidden);
        setHoodUsed(true);
        setHoodTick(1);
    }

    public void useMask() {
        player.playSound(SoundEvents.ARMOR_EQUIP_LEATHER.value(), 1.0f, 1.0f);
        setMaskHidden(!maskHidden);
        setMaskUsed(true);
        setMaskTick(1);
    }

    public void setHoodHidden(boolean hidden) {
        this.hoodHidden = hidden;
    }

    public boolean isHoodHidden() {
        return this.hoodHidden;
    }

    public void setMaskHidden(boolean hidden) {
        this.maskHidden = hidden;
    }

    public boolean isMaskHidden() {
        return this.maskHidden;
    }

    public boolean isHoodUsed() {
        return hoodUsed;
    }

    public boolean isMaskUsed() {
        return maskUsed;
    }

    public void setHoodUsed(boolean used) {
        this.hoodUsed = used;
    }

    public void setMaskUsed(boolean used) {
        this.maskUsed = used;
    }

    public void setHoodTick(int ticks) {
        this.hoodTick = ticks;
    }

    public void setMaskTick(int ticks) {
        this.maskTick = ticks;
    }
}
