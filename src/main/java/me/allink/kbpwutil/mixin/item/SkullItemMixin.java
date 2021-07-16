package me.allink.kbpwutil.mixin.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SkullItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkullItem.class)
public class SkullItemMixin {
    @Inject(method= "getName(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/text/Text;", cancellable = true, at=@At("RETURN"))

    public void getName(ItemStack stack, CallbackInfoReturnable<Text> cir) {
        cir.setReturnValue(Text.of("retarded bullshit"));
    }

    @Inject(method="Lnet/minecraft/item/SkullItem;postProcessNbt(Lnet/minecraft/nbt/NbtCompound;)Z", cancellable = true, at=@At("HEAD"))

    public void postProcessTag(NbtCompound tag, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
        cir.cancel();
    }
}
