package me.allink.kbpwutil.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;

public class ModuleScreen extends Screen {
    private boolean debounce = true;

    public ModuleScreen(Text title) {
        super(title);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        //textRenderer.drawTrimmed(StringVisitable.plain("test"), 0, 0, 2, 16777215);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 357) {
            System.out.println("test");
            return true;
        }
        return true;
    }
}
