package com.github.hhhzzzsss.chathacks;

import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

public class ChathacksUtilItsCalledThisToPreventConflicts {
    public static String styleAsString(Style style) {
        if (style.isEmpty()) {
            return "";
        }
        else {
            StringBuilder sb = new StringBuilder();
            if (style.getColor() != null) {
                Formatting formatting = Formatting.byName(style.getColor().getName());
                if (formatting != null) {
                    sb.append(formatting);
                }
            }
            if (style.isBold()) {
                sb.append(Formatting.BOLD);
            }
            if (style.isItalic()) {
                sb.append(Formatting.ITALIC);
            }
            if (style.isUnderlined()) {
                sb.append(Formatting.UNDERLINE);
            }
            if (style.isObfuscated()) {
                sb.append(Formatting.OBFUSCATED);
            }
            if (style.isStrikethrough()) {
                sb.append(Formatting.STRIKETHROUGH);
            }
            return sb.toString();
        }
    }
}
