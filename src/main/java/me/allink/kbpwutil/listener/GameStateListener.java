package me.allink.kbpwutil.listener;

import me.allink.kbpwutil.modules.AutoCreativeModule;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;

public class GameStateListener {
    public void onGameStateChange(GameStateChangeS2CPacket packet) {
        AutoCreativeModule.onGameStateChange(packet);
    }
}
