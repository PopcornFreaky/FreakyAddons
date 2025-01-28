package com.mod.FreakyAddons.GUI;

import com.mod.FreakyAddons.Commands.CommandFreezeTime;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class SettingsGui extends GuiScreen {

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 - 20, "Toggle Night Mode"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 + 20, "Check for Updates"));

        // Release the mouse cursor for free movement
        Mouse.setGrabbed(false);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                // Call the night mode toggle method
                CommandFreezeTime.toggleNightMode(Minecraft.getMinecraft().thePlayer);
                break;
            case 1:
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Update check feature is not implemented yet!"));
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "FreakyAddons Settings", this.width / 2, 20, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        // Ensure the cursor stays released during interaction
        Mouse.setGrabbed(false);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        // Regrab the mouse cursor when the GUI is closed
        Mouse.setGrabbed(true);
    }
}
