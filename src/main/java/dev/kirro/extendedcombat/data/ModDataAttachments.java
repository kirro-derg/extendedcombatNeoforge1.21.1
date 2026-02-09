package dev.kirro.extendedcombat.data;

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
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ExtendedCombat.MOD_ID);

    public static final Supplier<AttachmentType<AirJumpBehavior>> AIR_JUMP = ATTACHMENT_TYPES.register(
            "air_jump", () -> AttachmentType.builder(player -> new AirJumpBehavior((Player) player)).build()
    );
    public static final Supplier<AttachmentType<AirMovementBehavior>> AIR_MOVEMENT = ATTACHMENT_TYPES.register(
            "air_movement", () -> AttachmentType.builder(player -> new AirMovementBehavior((Player) player)).build()
    );
    public static final Supplier<AttachmentType<DashBehavior>> DASH = ATTACHMENT_TYPES.register(
            "dash", () -> AttachmentType.builder(player -> new DashBehavior((Player) player)).build()
    );
    public static final Supplier<AttachmentType<BlinkBehavior>> BLINK = ATTACHMENT_TYPES.register(
            "blink", () -> AttachmentType.builder(player -> new BlinkBehavior((Player) player)).build()
    );
    public static final Supplier<AttachmentType<HideWoolHoodBehavior>> HIDE_HOOD = ATTACHMENT_TYPES.register(
            "hide_hood", () -> AttachmentType.builder(player -> new HideWoolHoodBehavior((Player) player)).build()
    );
    public static final Supplier<AttachmentType<FluidMovementBehavior>> FLUID_MOVEMENT = ATTACHMENT_TYPES.register(
            "fluid_movement", () -> AttachmentType.builder(player -> new FluidMovementBehavior((Player) player)).build()
    );
    public static final Supplier<AttachmentType<WatergelBehavior>> WATERGEL = ATTACHMENT_TYPES.register(
            "watergel", () -> AttachmentType.builder(player -> new WatergelBehavior((Player) player)).build()
    );

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
