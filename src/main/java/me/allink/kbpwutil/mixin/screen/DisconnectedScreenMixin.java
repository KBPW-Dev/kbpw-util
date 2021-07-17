package me.allink.kbpwutil.mixin.screen;

import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.manager.AccountManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin extends Screen {
    private final boolean reconnectHandled = false;

    @Shadow private MultilineText reasonFormatted;

    @Shadow private int reasonHeight;

    @Shadow @Final private Screen parent;

    @Shadow @Final private Text reason;

    protected DisconnectedScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init()V", at = @At("TAIL"))

    public void init(CallbackInfo ci) {
        int var10001 = this.reasonFormatted.count();
        int reasonHeight = var10001 * 9;
        int var10003 = this.width / 2 - 100;
        int var10004 = this.height / 2 + reasonHeight / 2;


        this.addButton(new ButtonWidget(var10003, Math.min(var10004 + 36, this.height - 120), 200, 20, new TranslatableText("kbpw.disconnect.randomizeUser"), (buttonWidget) -> {
            try {
                AccountManager.randomizeUsername();
                MinecraftClient.getInstance().openScreen(new ConnectScreen(new MultiplayerScreen(new TitleScreen()), MinecraftClient.getInstance(), KbpwUtil.lastServer));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }));

        this.addButton(new ButtonWidget(var10003, Math.min(var10004 + 63, this.height - 120), 200, 20, new TranslatableText("kbpw.reconnect"), (buttonWidget) -> {
            try {
                MinecraftClient.getInstance().openScreen(new ConnectScreen(new MultiplayerScreen(new TitleScreen()), MinecraftClient.getInstance(), KbpwUtil.lastServer));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        KbpwUtil.countdown = 5;
    }

    @Inject(at=@At("TAIL"), method= "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        int var10003 = this.width / 2 - 100;
        int var10004 = this.height / 2 + this.reasonHeight / 2;

        /*this.textRenderer.drawWithShadow(matrices, new TranslatableText("kbpw.gui.reconnect").append(String.valueOf(KbpwUtil.countdown)), (float)var10003, (float)Math.min(var10004 + 90, this.height - 120), 0xffFFFFFF);

        if(!reconnectHandled && KbpwUtil.countdown == 0) {
            reconnectHandled = true;
            KbpwUtil.rejoin(parent);
        }*/
    }

}
