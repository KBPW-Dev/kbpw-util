package me.allink.kbpwutil.mixin.resource;

import me.allink.kbpwutil.KbpwUtil;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Inject(method= "get()Ljava/lang/String;", at=@At("RETURN"), cancellable = true)

    public void get(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(KbpwUtil.splashText());
    }
}
