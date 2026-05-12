package dev.kirro.extendedcombat.enchantment.packet;


import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.ability.DashBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public record DashPacket() implements CustomPacketPayload {
    public static final Type<DashPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "dash"));
    public static final StreamCodec<ByteBuf, DashPacket> CODEC = StreamCodec.unit(new DashPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
