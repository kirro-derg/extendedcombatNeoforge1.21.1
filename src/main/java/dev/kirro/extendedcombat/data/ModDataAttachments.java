package dev.kirro.extendedcombat.data;

import com.mojang.serialization.Codec;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.ability.AirJumpBehavior;
import dev.kirro.extendedcombat.behavior.ability.AirMovementBehavior;
import dev.kirro.extendedcombat.behavior.ability.BlinkBehavior;
import dev.kirro.extendedcombat.behavior.ability.DashBehavior;
import dev.kirro.extendedcombat.behavior.enchantment.*;
import dev.kirro.extendedcombat.behavior.item.HideWoolHoodBehavior;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ExtendedCombat.MOD_ID);

    public static final Supplier<AttachmentType<AirJumpBehavior>> AIR_JUMP = register(
            "air_jump", player -> new AirJumpBehavior((Player) player)
    );
    public static final Supplier<AttachmentType<AirMovementBehavior>> AIR_MOVEMENT = register(
            "air_movement", player -> new AirMovementBehavior((Player) player)
    );
    public static final Supplier<AttachmentType<DashBehavior>> DASH = register(
            "dash", player -> new DashBehavior((Player) player)
    );
    public static final Supplier<AttachmentType<BlinkBehavior>> BLINK = register(
            "blink", player -> new BlinkBehavior((Player) player)
    );
    public static final Supplier<AttachmentType<HideWoolHoodBehavior>> HIDE_HOOD = register(
            "hide_hood", player -> new HideWoolHoodBehavior((Player) player)
    );
    public static final Supplier<AttachmentType<FluidMovementBehavior>> FLUID_MOVEMENT = register(
            "fluid_movement", player -> new FluidMovementBehavior((Player) player)
    );
    public static final Supplier<AttachmentType<WatergelBehavior>> WATERGEL = register(
            "watergel", player -> new WatergelBehavior((Player) player)
    );

    public static <T> Supplier<AttachmentType<T>> register(String name, Function<IAttachmentHolder, T> value) {
        return ATTACHMENT_TYPES.register(name, () -> AttachmentType.builder(value).build());
    }

    public static <T> Supplier<AttachmentType<T>> register(String name, Supplier<T> value) {
        return ATTACHMENT_TYPES.register(name, () -> AttachmentType.builder(value).build());
    }

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}