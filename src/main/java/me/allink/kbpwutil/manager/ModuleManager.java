package me.allink.kbpwutil.manager;

import me.allink.kbpwutil.modules.*;

public class ModuleManager {
    public static void init() {
        new AutoCSpyModule("kbpw.module.cspy", true);
        new AutoVanishModule("kbpw.module.vanish", true);
        new AntiMuteModule("kbpw.module.mute", true);
        new AutoCreativeModule("kbpw.module.creative", true);
        new AutoSkinModule("kbpw.module.autoskin", true);
        new KeyModule("kbpw.module.keys", false);
    }
}
