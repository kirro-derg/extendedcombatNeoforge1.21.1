package dev.kirro.extendedcombat.enchantment.packet;

import dev.kirro.extendedcombat.ExtendedCombat;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record BlinkPacket(int entityId, boolean invisible) implements CustomPacketPayload {
    public static final Type<BlinkPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "blink"));
    public static final StreamCodec<ByteBuf, BlinkPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, BlinkPacket::entityId,
            ByteBufCodecs.BOOL, BlinkPacket::invisible,
            BlinkPacket::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
