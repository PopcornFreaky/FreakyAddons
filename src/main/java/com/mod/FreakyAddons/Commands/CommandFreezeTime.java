package com.mod.FreakyAddons.Commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommandFreezeTime extends CommandBase {

    private static boolean isNightTime = false;

    public CommandFreezeTime() {
        // Register this class to listen to events
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public String getCommandName() {
        return "freezetime";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/freezetime - Toggles always night mode (client-side)";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        isNightTime = !isNightTime;

        if (isNightTime) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Time is now set to always night (client-side)."));
        } else {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Time is no longer forced (client-side)."));
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (isNightTime && Minecraft.getMinecraft().theWorld != null) {
            // Override the world time to always render as midnight
            Minecraft.getMinecraft().theWorld.setWorldTime(18000);
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0; // Allow all players to use this command
    }
}
