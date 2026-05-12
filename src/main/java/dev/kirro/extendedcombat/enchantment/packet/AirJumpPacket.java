package dev.kirro.extendedcombat.enchantment.packet;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.ability.AirJumpBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AirJumpPacket() implements CustomPacketPayload {
    public static final Type<AirJumpPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "air_jump"));
    public static final StreamCodec<ByteBuf, AirJumpPacket> CODEC = StreamCodec.unit(new AirJumpPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
