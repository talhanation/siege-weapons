package com.talhanation.siegeweapons.client;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class EntityInScreenRenderer {

    public static void renderEntityInInventoryRotating(GuiGraphics p_282802_, int p_275688_, int p_275245_, int p_275535_, Entity entity, float rotationSpeed) {
        Minecraft mc = Minecraft.getInstance();
        float rotation = (mc.level != null ? mc.level.getGameTime() : mc.gui.getGuiTicks()) * rotationSpeed % 360;

        Quaternionf rotationQuaternion =
                new Quaternionf()
                .rotateX((float) Math.PI)
                .rotateY(rotation * ((float) Math.PI / 180F));
        renderEntityInInventory(p_282802_, p_275688_, p_275245_, p_275535_, rotationQuaternion, entity, rotation);
    }

    public static void renderEntityInInventory(GuiGraphics p_282665_, int p_283622_, int p_283401_, int p_281360_, Quaternionf p_281880_, Entity entity, float rotation) {
        p_282665_.pose().pushPose();
        p_282665_.pose().translate((double) p_283622_, (double) p_283401_, 50.0D);
        p_282665_.pose().mulPoseMatrix((new Matrix4f()).scaling((float) p_281360_, (float) p_281360_, (float) (-p_281360_)));
        p_282665_.pose().mulPose(p_281880_);
        Lighting.setupForEntityInInventory();

        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();

        float prevYRot = entity.getYRot();
        entity.setYRot(rotation);

        entityrenderdispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, p_282665_.pose(), p_282665_.bufferSource(), 15728880);
        });
        p_282665_.flush();
        entityrenderdispatcher.setRenderShadow(true);

        entity.setYRot(prevYRot);
        p_282665_.pose().popPose();
        Lighting.setupFor3DItems();
    }



}
