package dev.kirro.extendedcombat.enchantment.packet;


import dev.kirro.extendedcombat.ExtendedCombat;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record DashPacket(int entityId) implements CustomPacketPayload {
    public static final Type<DashPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "dash"));
    public static final StreamCodec<ByteBuf, DashPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, DashPacket::entityId,
            DashPacket::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
