package me.allink.kbpwutil.modules;

import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.modules.base.Module;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;

public class AutoCreativeModule extends Module {
    private String name = null;
    public static boolean toggled = false;

    public AutoCreativeModule(String name, boolean toggled) {
        super(name, toggled);
        AutoCreativeModule.toggled = toggled;
    }

    public static void onGameStateChange(GameStateChangeS2CPacket packet) {
        if(!toggled) return;
        if (packet.getReason() == GameStateChangeS2CPacket.GAME_MODE_CHANGED) {
            if (packet.getValue() != 1) {
                KbpwUtil.queueMessage("/gamemode creative");
            }
        }
    }
}
