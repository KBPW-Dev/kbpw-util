package me.allink.kbpwutil.modules;

import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.modules.base.Module;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

import java.util.UUID;

public class AutoVanishModule extends Module {
    public static boolean toggled;
    public static String name;

    public AutoVanishModule(String name, boolean toggled) {
        super(name, toggled);
        AutoVanishModule.toggled = toggled;
    }

    public static void onReceivedChatMessage(String message, UUID senderUUID) {
        if (!toggled) return;
        if (senderUUID.equals(KbpwUtil.blankUUID())) {
            if (message.toLowerCase().contains("vanish for") && message.toLowerCase().contains("disabled")) {
                KbpwUtil.queueMessage("/v on");
            }
        }
    }

    public static void onGameJoin(GameJoinS2CPacket packet) {
        if (!toggled) return; KbpwUtil.queueMessage("/v on");
    }
}
