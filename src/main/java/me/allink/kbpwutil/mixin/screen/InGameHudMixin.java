package me.allink.kbpwutil.mixin.screen;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {


    @Shadow public abstract TextRenderer getFontRenderer();
    @Shadow private int scaledWidth;

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        getFontRenderer().drawWithShadow(matrices, "kbpw-util", this.scaledWidth - this.getFontRenderer().getWidth("kbpw-util") - 4, 4, 0xffFFFFFF);
    }
}
