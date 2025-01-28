package com.mod.SkyTox;

import com.mod.SkyTox.Commands.CommandCopyCoords;
import com.mod.SkyTox.Commands.CommandFreezeTime;
import com.mod.SkyTox.Commands.CommandSkytox;
import com.mod.SkyTox.Handlers.MobHighlightHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = SkyTox.MODID, version = SkyTox.VERSION)
public class SkyTox
{
    public static final String MODID = "SkyTox";
    public static final String VERSION = "1.0";
    private Configuration config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Initialize the configuration file
        File configFile = event.getSuggestedConfigurationFile();
        config = new Configuration(configFile);
        config.load();

        // Add any configuration settings initialization here


        // Save the configuration if any changes were made
        if (config.hasChanged()) {
            config.save();
        }
    }

    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Example code
//        System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName());

        // Register event handlers
        MinecraftForge.EVENT_BUS.register(new MobHighlightHandler());

        // Register commands
        ClientCommandHandler.instance.registerCommand(new CommandSkytox(config));
        ClientCommandHandler.instance.registerCommand(new CommandCopyCoords());
        ClientCommandHandler.instance.registerCommand(new CommandFreezeTime());


    }
}
