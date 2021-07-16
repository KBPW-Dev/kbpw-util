package me.allink.kbpwutil;

import me.allink.kbpwutil.manager.AccountManager;
import me.allink.kbpwutil.manager.ModuleManager;
import me.allink.kbpwutil.screen.ControlledScreen;
import me.allink.kbpwutil.screen.ModuleScreen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

public class KbpwUtil implements ModInitializer {

    public static String version = "0.0.1";
    public static int[] icuDetector = {0, 0};
    public static int countdown = -1;
    public static MinecraftClient client = MinecraftClient.getInstance();
    public static boolean reconnecting = false;
    public static ServerInfo lastServer;
    public static Timer icu = new Timer();
    public static Timer t = new Timer();
    public static UUID nilUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private static List<String> commandQueue = new ArrayList<String>();
    private static List<String> chatQueue = new ArrayList<String>();
    private static String[] splashTexts = {"hacked by XxmathiascodeYTLiveXx","when will mathias add support for my samsung smart fridge","pls give me blessing of majong","made by bartoszm77","when will mathias add support for shitty asus laptop","what is this, a pseudo staff system in the form of a handpicked Authoritarian system?","How does he get HBot to customise the messages recieved when attempting to Bypass the Blacklist? Thats cool","icu cousins pole","kabooms...","please merge this, kbwl is retarded... i want to play on kaboom :sob:","kbwl is super annoying","book ban by mantest6","suni29 x bartoszm77","when the what the when the what the","just so you know i'm not stalling, i just haven't shat in a day","is this some kind of inside joke that i just don't get or are you actually insane","totalfreedom literal shitpost moment","I never thought I would see a picture of an admin's literal shit.","If you actually posted your own shit, I would personally suspend you if you were an admin on my server."};
    private static Random random = new Random();
    private Screen lastScreen = null;

    public static void queueMessage(String message) {
        message = message.trim();
        System.out.printf("Adding %s to the queue%n", message);
        if (message.startsWith("/")) {
            commandQueue.add(message);
        } else {
            chatQueue.add(message);
        }
    }

    public static String splashText() {
        return splashTexts[random.nextInt(splashTexts.length)];
    }

    public static boolean isHead(ItemStack stack) {
        return Item.getRawId(stack.getItem()) == 838;
    }

    public static boolean isHead(ItemEntity entity) {
        return isHead(entity.getStack());
    }

    public static void reconnect() {
        client.openScreen(new ConnectScreen(new MultiplayerScreen(new TitleScreen()), client, lastServer));
    }

    public static void rejoin(Screen screen) {
        try {
            //ConnectScreenMixin.connect();
            client.openScreen(new ProgressScreen());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void disconnect() {
        client.world.disconnect();
        client.disconnect();
    }

    public static UUID blankUUID() {
        return nilUUID;
    }

    @Override
    public void onInitialize() {
        KeyBinding moduleScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "kbpw.key.modules",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "kbpw.key.category"
        ));

        ModuleManager.init();
        try {
            String home = System.getProperty("user.home");
            if(Files.exists(Paths.get(home + "/" + ".ku-name"))) {
                String name = Files.readAllLines(Paths.get(home + "/" + ".ku-name"), StandardCharsets.UTF_8).get(0);
                AccountManager.changeUsername(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        icu.schedule(new TimerTask() {
            @Override
            public void run() {
                int difference = icuDetector[0] - icuDetector[1];
                if (difference > 18 && difference < 22) {
                    assert client != null;
                    client.openScreen(new ControlledScreen());
                }
                icuDetector[1] = icuDetector[0];

                if(countdown != -1) {
                    countdown--;
                }
            }
        }, 0, 1000);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (commandQueue.size() != 0) {
                    Logger.getGlobal().info("Executing " + commandQueue.get(0));
                    MinecraftClient.getInstance().player.sendChatMessage(commandQueue.get(0));
                    commandQueue.remove(0);
                }
            }
        }, 0, 200);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (chatQueue.size() != 0) {
                    Logger.getGlobal().info("Saying " + chatQueue.get(0));
                    MinecraftClient.getInstance().player.sendChatMessage(chatQueue.get(0));
                    chatQueue.remove(0);
                }
            }
        }, 0, 50);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (moduleScreen.wasPressed()) {
                if((client.currentScreen != null ? client.currentScreen.getClass() : null) == ModuleScreen.class) {
                    client.openScreen(null);
                }
            }
        });
    }
}
