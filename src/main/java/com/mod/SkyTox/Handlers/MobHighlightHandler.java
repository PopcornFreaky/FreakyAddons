package com.mod.SkyTox.Handlers;

import com.mod.SkyTox.Utils.MobSelector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;


public class MobHighlightHandler {

    private static String targetedMobName = null; // Store the current targeted mob name
    private Minecraft mc = Minecraft.getMinecraft();

    public static void setTargetedMobName(String mobName) {
        targetedMobName = mobName;
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (targetedMobName == null) return; // No mob is targeted

        // Loop through all loaded entities in the world
        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (entity instanceof EntityMob && entity.getName().equalsIgnoreCase(targetedMobName)) {
                renderOutline(entity, event.partialTicks);
            }
        }
    }

    private void renderOutline(Entity entity, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(0.0F, 1.0F, 0.0F, 0.5F); // Green with transparency

        RenderManager renderManager = mc.getRenderManager();
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - renderManager.viewerPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - renderManager.viewerPosY;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - renderManager.viewerPosZ;

        GlStateManager.translate(x, y, z);
        GL11.glLineWidth(3.0F);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        // Draw outline here
        GL11.glEnd();

        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

}
