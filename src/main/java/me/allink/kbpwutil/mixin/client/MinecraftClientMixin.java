package me.allink.kbpwutil.mixin.client;

import me.allink.kbpwutil.modules.KeyModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    @Final
    public GameOptions options;
    @Shadow
    @Nullable
    public ClientPlayerEntity player;
    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at = @At("RETURN"), method = "getWindowTitle()Ljava/lang/String;", cancellable = true)

    public void getWindowTitle(CallbackInfoReturnable<String> cir) {
        String originalTitle = cir.getReturnValue();
        String newTitle = originalTitle;
        newTitle = newTitle.replaceAll("\\*", "");
        newTitle = newTitle.replaceAll("Minecraft", "kbpw-util");
        cir.setReturnValue(newTitle);
    }


    @Inject(at = @At("INVOKE"), method = "handleInputEvents()V")

    public void handleInputEvents(CallbackInfo ci) {
        assert this.player != null;

        KeyModule.onKeyPress(options);
    }
}
