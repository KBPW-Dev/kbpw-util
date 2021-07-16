package me.allink.kbpwutil.mixin.network;

import net.minecraft.client.network.MultiplayerServerListPinger;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerServerListPinger.class)
public class MultiplayerServerListPingerMixin {

    @Inject(at = @At("INVOKE"), method = "add(Lnet/minecraft/client/network/ServerInfo;Ljava/lang/Runnable;)V", cancellable = true)
    public void add(ServerInfo entry, Runnable runnable, CallbackInfo ci) {
        entry.online = true;
        entry.label = new TranslatableText("kbpw.ping");
        entry.playerCountLabel = (Text.of("§7?/?"));
        entry.ping = -1;
        ci.cancel();
    }

    @Inject(at = @At("INVOKE"), method = "ping(Lnet/minecraft/client/network/ServerInfo;)V", cancellable = true)
    public void ping(ServerInfo serverInfo, CallbackInfo ci) {
        ci.cancel();
    }
}
