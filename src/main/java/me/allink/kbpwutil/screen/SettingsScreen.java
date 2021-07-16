package me.allink.kbpwutil.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class SettingsScreen extends Screen {
    protected SettingsScreen(Text title) {
        super(title);
    }

    protected void init() {
        this.initWidgets();
    }

    private void initWidgets() {

    }

    public void tick() {
        super.tick();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 16777215);

        super.render(matrices, mouseX, mouseY, delta);
    }
}