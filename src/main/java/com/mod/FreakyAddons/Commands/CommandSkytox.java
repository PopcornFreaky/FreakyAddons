package com.mod.FreakyAddons.Commands;

import com.mod.FreakyAddons.GUI.SettingsGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;

public class CommandSkytox extends CommandBase {

    private static boolean playersVisible = true; // Tracks player visibility state
    private static boolean isDarkModeActive = false; // Tracks dark mode state
    private final Configuration config; // Configuration instance

    // Constructor to initialize config
    public CommandSkytox(Configuration config) {
        this.config = config;
    }

    @Override
    public String getCommandName() {
        return "skytox";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/skytox turnoff players | /skytox turnon players | /skytox gui | /skytox darkmode";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /skytox turnoff players | /skytox turnon players | /skytox gui | /skytox darkmode"));
            return;
        }

        String subcommand = args[0];

        switch (subcommand.toLowerCase()) {
            case "turnoff":
                if (args.length > 1 && args[1].equalsIgnoreCase("players")) {
                    setPlayersVisibility(false, sender);
                } else {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /skytox turnoff players"));
                }
                break;
            case "turnon":
                if (args.length > 1 && args[1].equalsIgnoreCase("players")) {
                    setPlayersVisibility(true, sender);
                } else {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /skytox turnon players"));
                }
                break;
            case "gui":
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    Minecraft.getMinecraft().displayGuiScreen(new SettingsGui(Minecraft.getMinecraft().currentScreen, config));
                });
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Opening SkyTox GUI..."));
                break;
            default:
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Unknown subcommand!"));
        }
    }

    private void setPlayersVisibility(boolean visible, ICommandSender sender) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null) return;

        // Iterate through all player entities in the world
        int affectedPlayers = 0;
        for (Entity entity : mc.theWorld.playerEntities) {
            if (entity instanceof EntityOtherPlayerMP) {
                EntityOtherPlayerMP player = (EntityOtherPlayerMP) entity;
                player.setInvisible(!visible); // Toggle visibility
                affectedPlayers++;
            }
        }

        // Update visibility state and notify sender
        playersVisible = visible;
        String message = visible
                ? "All players are now visible. (" + affectedPlayers + " affected)"
                : "All players are now invisible. (" + affectedPlayers + " affected)";
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + message));
    }


    @Override
    public int getRequiredPermissionLevel() {
        return 0; // Allow all players to use this command
    }
}
