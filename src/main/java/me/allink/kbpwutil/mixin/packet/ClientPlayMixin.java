package me.allink.kbpwutil.mixin.packet;

import com.github.hhhzzzsss.chathacks.ChatHacksItsCalledThisToPreventConflicts;
import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.listener.ChatMessageListener;
import me.allink.kbpwutil.modules.AutoCSpyModule;
import me.allink.kbpwutil.modules.AutoCreativeModule;
import me.allink.kbpwutil.modules.AutoSkinModule;
import me.allink.kbpwutil.modules.AutoVanishModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayMixin {

    @Inject(method = "onPlayerPositionLook(Lnet/minecraft/network/packet/s2c/play/PlayerPositionLookS2CPacket;)V", at = @At("TAIL"))

    public void onPlayerPositionLook(PlayerPositionLookS2CPacket packet, CallbackInfo ci) {
        KbpwUtil.icuDetector[0] = packet.getTeleportId();
    }


    @Inject(method = "onEntityStatus(Lnet/minecraft/network/packet/s2c/play/EntityStatusS2CPacket;)V", at = @At("TAIL"))

    public void onEntityStatus(EntityStatusS2CPacket packet, CallbackInfo ci) {
        switch (packet.getStatus()) {
            case 24:
                KbpwUtil.queueMessage("/op @s[type=player]");
                break;
        }
    }

    @Inject(method = "onGameJoin(Lnet/minecraft/network/packet/s2c/play/GameJoinS2CPacket;)V", at = @At("TAIL"))

    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        KbpwUtil.lastServer = MinecraftClient.getInstance().getCurrentServerEntry();
        AutoVanishModule.onGameJoin(packet);
        AutoCSpyModule.onGameJoin(packet);
        AutoSkinModule.onGameJoin(packet);
    }

    @Inject(method = "onGameStateChange(Lnet/minecraft/network/packet/s2c/play/GameStateChangeS2CPacket;)V", at = @At("INVOKE"), cancellable = true)

    public void onGameStateChange(GameStateChangeS2CPacket packet, CallbackInfo ci) {
        if(packet.getReason() == GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT) {
            ci.cancel();
        } else {
            if(AutoCreativeModule.toggled) {
                ci.cancel();
            }
            AutoCreativeModule.onGameStateChange(packet);
        }

    }

    @Inject(method = "onGameMessage(Lnet/minecraft/network/packet/s2c/play/GameMessageS2CPacket;)V", at = @At("HEAD"), cancellable = true)
    public void onGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
        if(ChatHacksItsCalledThisToPreventConflicts.isSpam(packet.getMessage())) {
            ci.cancel();
        } else {
            ChatMessageListener.onReceivedChatMessage(packet.getMessage().getString(), packet.getSender());
        }
    }

    @Inject(method = "onParticle(Lnet/minecraft/network/packet/s2c/play/ParticleS2CPacket;)V", at = @At("INVOKE"), cancellable = true)
    public void onParticle(ParticleS2CPacket packet, CallbackInfo ci) {
        if(packet.getCount() > 20) {
            ci.cancel();
        }
    }
}
