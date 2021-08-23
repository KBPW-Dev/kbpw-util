package me.allink.kbpwutil.modules;

import me.allink.kbpwutil.modules.base.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;

public class KeyModule extends Module {
    public static boolean toggled;
    public static String name;

    public KeyModule(String name, boolean toggled) {
        super(name, toggled);
        toggled = toggled;
    }

    public static void onKeyPress(GameOptions options) {
        if (!toggled) return;
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (options.keyForward.isPressed()) {
            player.sendChatMessage(options.keyForward.getBoundKeyLocalizedText().asString());
        } else if (options.keyLeft.isPressed()) {
            player.sendChatMessage(options.keyLeft.getBoundKeyLocalizedText().asString());
        } else if (options.keyRight.isPressed()) {
            player.sendChatMessage(options.keyRight.getBoundKeyLocalizedText().asString());
        } else if (options.keyBack.isPressed()) {
            player.sendChatMessage(options.keyBack.getBoundKeyLocalizedText().asString());
        } else if (options.keyJump.isPressed()) {
            player.sendChatMessage((options.keyJump.getBoundKeyLocalizedText().asString().isEmpty() || options.keyJump.getBoundKeyLocalizedText().asString().isEmpty()) ? "space" : options.keyJump.getBoundKeyLocalizedText().asString());
        }
    }
}
