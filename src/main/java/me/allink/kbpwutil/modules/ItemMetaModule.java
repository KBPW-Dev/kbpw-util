package me.allink.kbpwutil.modules;

import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.modules.base.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ItemMetaModule extends Module {
    MinecraftClient client = MinecraftClient.getInstance();

    public ItemMetaModule(String name, boolean toggled) {
        super(name, toggled);
    }

    public void getMetadata() {
        client.player.sendSystemMessage(Text.of(String.valueOf(client.player.inventory.main.get(0).getTag())), KbpwUtil.blankUUID());
    }
}
