package dev.kirro.extendedcombat.api;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public interface Component {
    @Contract(mutates = "this")
    void readFromNbt(CompoundTag tag, HolderLookup.Provider holderLookup);

    @Contract(mutates = "param1")
    void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup);
}
