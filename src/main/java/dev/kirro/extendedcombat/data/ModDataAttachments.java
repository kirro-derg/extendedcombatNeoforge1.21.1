package dev.kirro.extendedcombat.data;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.api.AttachmentEntry;
import dev.kirro.extendedcombat.api.TickingAttachment;
import dev.kirro.extendedcombat.behavior.ability.AirJumpBehavior;
import dev.kirro.extendedcombat.behavior.ability.AirMovementBehavior;
import dev.kirro.extendedcombat.behavior.ability.BlinkBehavior;
import dev.kirro.extendedcombat.behavior.ability.DashBehavior;
import dev.kirro.extendedcombat.behavior.enchantment.*;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModDataAttachments {
    public static final List<AttachmentEntry<?>> ENTRIES = new ArrayList<>();
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ExtendedCombat.MOD_ID);

    public static final Supplier<AttachmentType<AirJumpBehavior>> AIR_JUMP = register(
            "air_jump", attachmentHolder -> new AirJumpBehavior((Player) attachmentHolder)
    );
    public static final Supplier<AttachmentType<AirMovementBehavior>> AIR_MOVEMENT = register(
            "air_movement", attachmentHolder -> new AirMovementBehavior((Player) attachmentHolder)
    );
    public static final Supplier<AttachmentType<DashBehavior>> DASH = register(
            "dash", attachmentHolder -> new DashBehavior((Player) attachmentHolder)
    );
    public static final Supplier<AttachmentType<BlinkBehavior>> BLINK = register(
            "blink", attachmentHolder -> new BlinkBehavior((Player) attachmentHolder)
    );
    public static final Supplier<AttachmentType<WatergelBehavior>> WATERGEL = register(
            "watergel", attachmentHolder -> new WatergelBehavior((Player) attachmentHolder)
    );

    public static <T extends TickingAttachment> Supplier<AttachmentType<T>> register(String name, Function<IAttachmentHolder, T> value) {
        Supplier<AttachmentType<T>> type = ATTACHMENT_TYPES.register(name, () -> AttachmentType.builder(value).build());
        ENTRIES.add(new AttachmentEntry<>(type));
        return type;
    }

    public static <T extends TickingAttachment> Supplier<AttachmentType<T>> register(String name, Supplier<T> value) {
        return register(name, holder -> value.get());
    }

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}