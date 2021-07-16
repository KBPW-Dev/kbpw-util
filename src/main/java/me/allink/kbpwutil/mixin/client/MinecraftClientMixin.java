package me.allink.kbpwutil.mixin.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at = @At("RETURN"), method = "getWindowTitle()Ljava/lang/String;", cancellable = true)

    public void getWindowTitle(CallbackInfoReturnable<String> cir) {
        String originalTitle = cir.getReturnValue();
        String newTitle = originalTitle;
        newTitle = newTitle.replaceAll("\\*", "");
        newTitle = newTitle.replaceAll("Minecraft", "kbpw-util");
        cir.setReturnValue(newTitle);
    }

}
