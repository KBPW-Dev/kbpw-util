package me.allink.kbpwutil.mixin.block;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.item.SkullItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(SkullBlockEntity.class)
public class SkullBlockEntityMixin {
    @Inject(method = "loadProperties(Lcom/mojang/authlib/GameProfile;)Lcom/mojang/authlib/GameProfile;", at = @At("HEAD"), cancellable = true)

    private static void loadProperties(GameProfile profile, CallbackInfoReturnable<GameProfile> cir){
        cir.setReturnValue(new GameProfile(null, "QuadraticKid"));
        cir.cancel();
    }
}
