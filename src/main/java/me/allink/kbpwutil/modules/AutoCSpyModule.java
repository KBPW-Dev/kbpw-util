package me.allink.kbpwutil.modules;

import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.modules.base.Module;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

import java.util.UUID;

public class AutoCSpyModule extends Module {
    public static boolean toggled;
    public static String name;

    public AutoCSpyModule(String name, boolean toggled) {
        super(name, toggled);
        this.toggled = toggled;
    }

    public static void onReceivedChatMessage(String message, UUID senderUUID) {
        if (!toggled) return;
        if (senderUUID.equals(KbpwUtil.blankUUID())) {
            if (message.toLowerCase().contains("commandspy") && message.toLowerCase().contains("disabled")) {
                KbpwUtil.queueMessage("/c on");
            }
        }
    }

    public static void onGameJoin(GameJoinS2CPacket packet) {
        System.out.println("fart");
        if (!toggled) return; KbpwUtil.queueMessage("/c on");
    }
}
