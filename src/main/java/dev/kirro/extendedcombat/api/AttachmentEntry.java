package dev.kirro.extendedcombat.api;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;

import java.util.function.Supplier;

public class AttachmentEntry<T extends Attachment & TickingAttachment> {
    private final Supplier<AttachmentType<T>> type;

    public AttachmentEntry(Supplier<AttachmentType<T>> type) {
        this.type = type;
    }

    public void serverTick(IAttachmentHolder holder) {
        T attachment = holder.getData(type.get());
        attachment.serverTick();
    }

    public void clientTick(IAttachmentHolder holder) {
        T attachment = holder.getData(type.get());
        attachment.clientTick();
    }
}
