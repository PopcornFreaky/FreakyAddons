package com.mod.FreakyAddons.Commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class CommandCopyCoords extends CommandBase {

    @Override
    public String getCommandName() {
        return "copycoords";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/copycoords - Copies your current coordinates to the clipboard";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            int x = (int) mc.thePlayer.posX;
            int y = (int) mc.thePlayer.posY;
            int z = (int) mc.thePlayer.posZ;
            String coords = String.format("x: %d, y: %d, z: %d", x, y, z);

            // Copy coordinates to clipboard
            StringSelection selection = new StringSelection(coords);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            // Notify the player
            sender.addChatMessage(new ChatComponentText("Coordinates copied to clipboard: " + coords));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0; // Allow all players to use this command
    }
}
