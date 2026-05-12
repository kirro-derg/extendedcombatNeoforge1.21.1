package dev.kirro.extendedcombat.enchantment.packet;

import dev.kirro.extendedcombat.ExtendedCombat;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record BlinkPacket(boolean invisible) implements CustomPacketPayload {
    public static final Type<BlinkPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "blink"));
    public static final StreamCodec<ByteBuf, BlinkPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, BlinkPacket::invisible,
            BlinkPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
