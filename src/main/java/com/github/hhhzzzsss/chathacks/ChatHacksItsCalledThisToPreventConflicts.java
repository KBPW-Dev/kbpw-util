package com.github.hhhzzzsss.chathacks;

import net.minecraft.text.Text;

public class ChatHacksItsCalledThisToPreventConflicts {
    public static String[] chatBlackList = {
            " *", // Blank spaces
            ".*\n\n\n\n\n\n+.*", // Blank spaces
    };

    public static boolean isSpam(Text chatComponent) {
        String chat = chatComponent.getString();
        for (String s : chatBlackList) {
            if (chat.matches(s)) {
                return true;
            }
        }
        return false;
    }
}
