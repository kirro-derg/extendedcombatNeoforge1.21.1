package dev.kirro.extendedcombat.api;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface Component {
    void readFromNbt(CompoundTag tag, HolderLookup.Provider holderLookup);

    void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup);
}
