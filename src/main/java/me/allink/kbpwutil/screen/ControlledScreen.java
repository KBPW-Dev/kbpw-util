package me.allink.kbpwutil.screen;

import me.allink.kbpwutil.KbpwUtil;
import me.allink.kbpwutil.manager.AccountManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ControlledScreen extends Screen {

    public ControlledScreen(Text title) {
        super(title);
    }

    public ControlledScreen() {
        super(Text.of(""));
    }

    protected void init() {
        super.init();
        this.buttons.clear();
        this.children.clear();
        ButtonWidget ButtonWidgetNotImplemented = new ButtonWidget(this.width / 2 - 50 - 105, this.height / 6 + 96, 100, 20, new TranslatableText("kbpw.icu.bots"), (buttonWidget) -> {

        });
        ButtonWidgetNotImplemented.active = false;
        this.addButton(ButtonWidgetNotImplemented);
        this.addButton(new ButtonWidget(this.width / 2 - 50, this.height / 6 + 96, 100, 20, new TranslatableText("kbpw.icu.rejoin"), (buttonWidget) -> {
            KbpwUtil.rejoin(new TitleScreen());
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 50 + 105, this.height / 6 + 96, 100, 20, new TranslatableText("kbpw.icu.rejoin.diffuser"), (buttonWidget) -> {
            try {
                AccountManager.randomizeUsername();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        try {
            this.renderBackground(matrices);
        } catch (Exception e) {
            e.printStackTrace();
        }
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("kbpw.icu.question"), this.width / 2, 110, 16764108);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
