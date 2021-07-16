package me.allink.kbpwutil.manager;

import bleach.hack.utils.FabricReflect;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerInfo;

import java.util.logging.Logger;

public class AccountManager {
    private static String colors = "4c6e2ab319d5f780";

    /**
     * Set the client's username (cracked)
     *
     * @param username New username
     * @throws Exception Many exceptions may occur if this goes wrong.
     */

    public static void changeUsername(String username) throws Exception {
        ServerInfo currentServer = null;
        try {
            currentServer = MinecraftClient.getInstance().getCurrentServerEntry();
        } catch (Exception ignored) {

        }

        FabricReflect.writeField(MinecraftClient.getInstance().getSession(), username, "field_1982", "username");
        assert MinecraftClient.getInstance().currentScreen != null;
        if (MinecraftClient.getInstance().currentScreen.getClass() != TitleScreen.class) {
            if(MinecraftClient.getInstance().world != null) {
                MinecraftClient.getInstance().world.disconnect();
            }

            if (currentServer != null) {
                MinecraftClient.getInstance().disconnect(new ConnectScreen(new MultiplayerScreen(new TitleScreen()), MinecraftClient.getInstance(), currentServer));
            } else {
                MinecraftClient.getInstance().disconnect(new MultiplayerScreen(new TitleScreen()));
            }
        }
        Logger.getGlobal().info("Change Username Called!");
    }

    /**
     * Randomize the client's username
     */
    public static void randomizeUsername() throws Exception {
        Logger.getGlobal().info("Randomize Username Called!");
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            username.append("§").append(colors.charAt((int) Math.floor(Math.random() * colors.length())));
        }
        changeUsername(username.toString());
    }

    /**
     * Get the current username of the client
     * @throws NullPointerException
     * @return Current username
     */
    public static String getUsername() throws NullPointerException {
        return MinecraftClient.getInstance().player.getName().asString();
    }
}
