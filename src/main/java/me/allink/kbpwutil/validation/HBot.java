package me.allink.kbpwutil.validation;
import net.minecraft.server.command.FillCommand;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class HBot  {
    public static List<String> noVeri = Arrays.asList("cb","creator","echo","help","loginname","orbit","rainbowify","rtp","stoporbit","uuid","music");
    public static String currentHash = "\r\n";

    public static String getCommandWithHash(String command, String username) throws NoSuchAlgorithmException, IOException {
        String home = System.getProperty("user.home");
        if(Files.exists(Paths.get(home + "/" + ".hkey"))) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            Logger.getGlobal().info(username);
            String time = "" + (System.currentTimeMillis() / 10000);
            String key = new String(Files.readAllBytes(Paths.get(home + "/" + ".hkey")));
            String raw = command.replaceAll("&[0-9a-fklmnor]", "") + ";" + username.replaceAll("§[0-9a-fklmnor]", "") + ";" + time + ";" + key;
            byte[] hash = md.digest(raw.getBytes(StandardCharsets.UTF_8));
            BigInteger big_int = new BigInteger(1, Arrays.copyOfRange(hash, 0, 4));
            String strHash = big_int.toString(Character.MAX_RADIX);
            currentHash = strHash;
            return command + " " + strHash;
        } else {
            return command;
        }
    }
}
