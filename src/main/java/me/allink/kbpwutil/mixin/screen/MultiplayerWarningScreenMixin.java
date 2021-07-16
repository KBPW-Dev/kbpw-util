package me.allink.kbpwutil.mixin.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerWarningScreen.class)
public class MultiplayerWarningScreenMixin {

    @Shadow private Screen parent;
    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at=@At("HEAD"), method= "init()V")
    private void init(CallbackInfo ci) {
        this.client.options.skipMultiplayerWarning = true;
        this.client.options.write();
        this.client.openScreen(new MultiplayerScreen(this.parent));
    }
}
