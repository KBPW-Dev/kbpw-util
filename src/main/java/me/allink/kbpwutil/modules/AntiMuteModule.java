package me.allink.kbpwutil.modules;

import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.modules.base.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

import java.util.UUID;

public class AntiMuteModule extends Module {
    public static boolean toggled;
    public static String name;

    public AntiMuteModule(String name, boolean toggled) {
        super(name, toggled);
        this.toggled = toggled;
    }

    public static void onReceivedChatMessage(String message, UUID senderUUID) {
        if (!toggled) return;
        if (senderUUID.equals(KbpwUtil.blankUUID())) {
            if(message.toLowerCase().contains("you have been muted") &!message.toLowerCase().contains("now")) {
                KbpwUtil.queueMessage("/mute " + MinecraftClient.getInstance().player.getUuidAsString() + " 0s");
            }
        }
    }
}
