package me.allink.kbpwutil.listener;

import com.google.common.io.Resources;
import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.manager.AccountManager;
import me.allink.kbpwutil.manager.ModuleManager;
import me.allink.kbpwutil.modules.*;
import me.allink.kbpwutil.validation.HBot;
import me.allink.kbpwutil.validation.SaxBot;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.FileWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ChatMessageListener {
    static MinecraftClient client = MinecraftClient.getInstance();

    public static void onReceivedChatMessage(String message, UUID senderUUID) {
        AutoVanishModule.onReceivedChatMessage(message, senderUUID);
        AutoCSpyModule.onReceivedChatMessage(message, senderUUID);
        AntiMuteModule.onReceivedChatMessage(message, senderUUID);
    }

    public static void onSentChatMessage(String message, CallbackInfo info) throws Exception {
        assert client.player != null;
        if (message.startsWith("`")) {
            info.cancel();
            String command = message.split("`")[1].split(" ")[0];
            String[] args = message.split("`")[1].split(" ");
            switch (command) {
                case "help":
                    client.player.sendSystemMessage(Text.of("§eCommands:\n`help - Help message\n`autocspy - Toggle Auto CSPY\n`autovanish - Toggle Auto Vanish\n`antimute - Toggle antimute\n`connect <ip> - Connects to an external server\n`username <new username> - Changes your username and rejoins\n`creeper - Aw man\n§cWarning: This is only a temporary implementation until I flesh out the module system 100%"), UUID.fromString("00000000-0000-0000-0000-000000000000"));
                    break;
                case "join":
                case "server":
                case "connect":
                    KbpwUtil.disconnect();
                    ServerInfo serverInfo = new ServerInfo(args[1],args[1],false);
                    client.openScreen(new ConnectScreen(new MultiplayerScreen(new TitleScreen()), client, serverInfo));
                    break;
                case "name":
                case "username":
                    if(args.length >= 2) {
                        client.player.sendSystemMessage(Text.of("§fChanging username to " + args[1] + "..."), KbpwUtil.blankUUID());
                        AccountManager.changeUsername(args[1]);
                    } else {
                        ModuleManager.init();
                        try {
                            String home = System.getProperty("user.home");
                            if(Files.exists(Paths.get(home + "/" + ".ku-name"))) {
                                String name = Files.readAllLines(Paths.get(home + "/" + ".ku-name"), StandardCharsets.UTF_8).get(0);
                                AccountManager.changeUsername(name);
                            }
                        } catch (Exception e) {
                            client.player.sendSystemMessage(Text.of("§c" + e.getStackTrace().toString()), KbpwUtil.blankUUID());
                        }
                    }

                    break;
                case "creative":
                case "autocreative":
                    AutoCreativeModule.toggled = !AutoCreativeModule.toggled;
                    client.player.sendSystemMessage(Text.of("§fAutoCreative toggled " + ((AutoCreativeModule.toggled) ? "§aON" : "§cOFF")), KbpwUtil.blankUUID());
                    break;
                case "antimute":
                    AntiMuteModule.toggled = !AntiMuteModule.toggled;
                    client.player.sendSystemMessage(Text.of("§fAntiMute toggled " + ((AntiMuteModule.toggled) ? "§aON" : "§cOFF")), KbpwUtil.blankUUID());
                    break;
                case "autocspy":
                    AutoCSpyModule.toggled = !AutoCSpyModule.toggled;
                    client.player.sendSystemMessage(Text.of("§fAutoCSPY toggled " + ((AutoCSpyModule.toggled) ? "§aON" : "§cOFF")), KbpwUtil.blankUUID());
                    break;
                case "autovanish":
                    AutoVanishModule.toggled = !AutoVanishModule.toggled;
                    client.player.sendSystemMessage(Text.of("§fAutoVanish toggled " + ((AutoVanishModule.toggled) ? "§aON" : "§cOFF")),  KbpwUtil.blankUUID());
                    break;
                case "creeper":
                    URL url = Resources.getResource("creeper.txt");
                    String text = Resources.toString(url, StandardCharsets.UTF_8);
                    String[] lines = text.split("\n");
                    for (String line : lines) {
                        KbpwUtil.queueMessage(line);
                    }
                    break;
                case "disconnect":
                    client.world.disconnect();
                    client.disconnect(new DisconnectedScreen(null, Text.of("Disconnected"), Text.of("Disconnected")));
                    break;
                case "nbt":
                    if(client.player.inventory.getMainHandStack().getTag() == null) {
                        client.player.sendSystemMessage(Text.of("§eNo meta found."), KbpwUtil.blankUUID());
                    } else {
                        if(args.length > 1) {
                            client.player.sendSystemMessage(Text.of("§eSaving Item Meta to disk..."), KbpwUtil.blankUUID());
                            Long timeStamp = System.currentTimeMillis();
                            Path dir = Paths.get("nbt");
                            if(Files.notExists(dir)) {
                                Files.createDirectory(dir);
                            }
                            FileWriter fileWriter = new FileWriter(dir + "/" + timeStamp + ".txt");
                            fileWriter.write(client.player.inventory.getMainHandStack().getTag().toString());
                            fileWriter.close();
                            client.player.sendSystemMessage(Text.of("§aFinished saving Item Meta to disk!"), KbpwUtil.blankUUID());
                        } else {
                            client.player.sendSystemMessage(Text.of("§aItem Meta:§e " + client.player.inventory.getMainHandStack().getTag().toString()), KbpwUtil.blankUUID());
                        }
                    }

                    break;
                case "keys":
                    KeyModule.toggled = !KeyModule.toggled;
                    client.player.sendSystemMessage(Text.of("§fKeys toggled " + ((KeyModule.toggled) ? "§aON" : "§cOFF")), KbpwUtil.blankUUID());
                    break;
                default:
                    client.player.sendSystemMessage(Text.of("§eUnknown command \"" + command + "\"."), KbpwUtil.blankUUID());
                    break;
            }
        } else if (message.startsWith("#")) {
            if(message.contains(HBot.currentHash) || HBot.noVeri.contains(message.split(" ")[0].split("#")[1])) return;
            info.cancel();
            client.player.sendChatMessage(HBot.getCommandWithHash(message.replaceAll("§","%"), AccountManager.getUsername()));
        } else if (message.startsWith("~")) {
            if(message.contains(SaxBot.currentHash) || SaxBot.noVeri.contains(message.split(" ")[0].split("~")[1])) return;
            info.cancel();
            client.player.sendChatMessage(SaxBot.getCommandWithHash(message, AccountManager.getUsername()));
        } else if (message.contains("[")) {
            if(message.contains("[i{")) {
                String[] args = message.split(" ");
                for(int i = 0; i < args.length; i++) {
                    if(args[i].startsWith("[i{")) {
                        info.cancel();
                        String[] scriptingArgs = args[i].split("-");
                        Integer[] values = {Integer.parseInt(scriptingArgs[0].replaceAll("\\[i\\{", "")),Integer.parseInt(scriptingArgs[1].replaceAll("}", ""))};
                        for(int x = values[0]; x < values[1]+1; x++) {
                            List<String> args2 = Arrays.asList(args);
                            args2.set(i, String.valueOf(x));
                            String msg = "";
                            for(int j = 0; j < args2.size(); j++) {
                                msg = msg + " " + args2.get(j);
                            }
                            KbpwUtil.queueMessage(msg);
                        }
                    }
                }
            }
        }
    }
}
