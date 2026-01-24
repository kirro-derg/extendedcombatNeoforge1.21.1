package dev.kirro.extendedcombat.data;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.enchantment.*;
import dev.kirro.extendedcombat.behavior.item.HideWoolHoodBehavior;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.checkerframework.checker.units.qual.A;

import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ExtendedCombat.MOD_ID);

    public static final Supplier<AttachmentType<AirJumpBehavior>> AIR_JUMP = ATTACHMENT_TYPES.register(
            "air_jump", () -> AttachmentType.builder(AirJumpBehavior::new).build()
    );
    public static final Supplier<AttachmentType<AirMovementBehavior>> AIR_MOVEMENT = ATTACHMENT_TYPES.register(
            "air_movement", () -> AttachmentType.builder(AirMovementBehavior::new).build()
    );
    public static final Supplier<AttachmentType<DashBehavior>> DASH = ATTACHMENT_TYPES.register(
            "dash", () -> AttachmentType.builder(DashBehavior::new).build()
    );
    public static final Supplier<AttachmentType<BlinkBehavior>> BLINK = ATTACHMENT_TYPES.register(
            "blink", () -> AttachmentType.builder(BlinkBehavior::new).build()
    );
    public static final Supplier<AttachmentType<HideWoolHoodBehavior>> HIDE_HOOD = ATTACHMENT_TYPES.register(
            "hide_hood", () -> AttachmentType.builder(HideWoolHoodBehavior::new).build()
    );
    public static final Supplier<AttachmentType<FluidMovementBehavior>> FLUID_MOVEMENT = ATTACHMENT_TYPES.register(
            "fluid_movement", () -> AttachmentType.builder(FluidMovementBehavior::new).build()
    );

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
