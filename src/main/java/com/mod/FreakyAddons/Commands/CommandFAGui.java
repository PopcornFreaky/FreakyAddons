package com.mod.FreakyAddons.Commands;

import com.mod.FreakyAddons.GUI.SettingsGui;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandFAGui extends CommandBase {
    @Override
    public String getCommandName() {
        return "fa";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/fa - to open menu";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender instanceof net.minecraft.entity.player.EntityPlayer) {
            Minecraft.getMinecraft().displayGuiScreen(new SettingsGui());
        }
    }
}
