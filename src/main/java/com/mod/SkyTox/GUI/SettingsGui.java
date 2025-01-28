package com.mod.SkyTox.GUI;

import com.mod.SkyTox.Utils.MobSelector;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;

public class SettingsGui extends GuiScreen {

    private GuiScreen parentScreen;
    private Configuration config;

    public SettingsGui(GuiScreen parentScreen, Configuration config) {
        this.parentScreen = parentScreen;
        this.config = config;
    }

    @Override
    public void initGui() {
        int id = 0;
        // Example: Add a button to toggle a feature
        this.buttonList.add(new GuiButton(id++, this.width / 2 - 100, this.height / 2 - 50, 200, 20, "Toggle Feature"));
        // Add more buttons or components as needed
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                // Toggle a feature and save to config
                boolean currentValue = config.getBoolean("featureEnabled", Configuration.CATEGORY_GENERAL, true, "Enable or disable the feature");
                config.get(Configuration.CATEGORY_GENERAL, "featureEnabled", !currentValue).set(!currentValue);
                config.save();
                break;
            // Handle other buttons
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Mod Settings", this.width / 2, this.height / 2 - 70, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        config.save();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
