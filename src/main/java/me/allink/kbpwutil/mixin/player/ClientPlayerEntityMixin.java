package me.allink.kbpwutil.mixin.player;

import me.allink.kbpwutil.listener.ChatMessageListener;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(ClientPlayerEntity.class)

public class ClientPlayerEntityMixin {
    @Inject(method = "sendChatMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)

    public void sendChatMessage(String message, CallbackInfo ci)  {
        try {
            ChatMessageListener.onSentChatMessage(message, ci);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
