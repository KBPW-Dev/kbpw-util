package me.allink.kbpwutil.modules;

import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.modules.base.Module;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

public class AutoSkinModule extends Module {
    public static boolean toggled;
    public static String name;

    public AutoSkinModule(String name, boolean toggled) {
        super(name, toggled);
        this.toggled = toggled;
    }


    public static void onGameJoin(GameJoinS2CPacket packet) {
        if (!toggled) return; KbpwUtil.queueMessage("/skin G6_");
    }
}
