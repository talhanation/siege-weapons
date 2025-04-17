package com.talhanation.siegeweapons.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.models.CatapultModel;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class CatapultRenderer extends EntityRenderer<CatapultEntity> {

    private static final ResourceLocation[] TEXTURE = {
            new ResourceLocation(Main.MOD_ID,"textures/entity/catapult.png")
    };
    public CatapultRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 1.5F;
    }

    @Override
    public ResourceLocation getTextureLocation(CatapultEntity entity) {
        return TEXTURE[0];
    }
    private final CatapultModel model = new CatapultModel();
    @Override
    public void render(CatapultEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, -0.25D, 0.0D);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));

        poseStack.scale(-1.3F, -1.3F, 1.3F);

        poseStack.translate(0.0D, -1.7D,-0.5D);

        poseStack.mulPose(Axis.YP.rotationDegrees(0F));
        this.model.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);

        VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(this.model.renderType(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        if(entity.getShowTrajectory()){
            Vec3 forward = new Vec3(Math.sin(Math.toRadians(entity.getXRot())), 0, Math.cos(Math.toRadians(entity.getXRot())));

            double yShootVec = forward.y() + 35F / 40F;
            List<Vec3> trajectory = calculateTrajectory(forward, yShootVec, entity.getCalcRange()/2F, 3000, -2.2);

            VertexConsumer lineVertexConsumer = multiBufferSource.getBuffer(RenderType.LINES);
            renderBallistaTrajectory(poseStack, lineVertexConsumer, trajectory, 1.0f, 0.0f, 0.0f, 100.0f); // Red line
        }

        poseStack.popPose();

    }
    public static List<Vec3> calculateTrajectory(Vec3 forward, double yShootVec, float initialVelocity, int steps, double heightOffset) {
        List<Vec3> trajectory = new ArrayList<>();
        double timeStep = 1.0/20.0;
        double gravityValue = -0.05;
        double drag = 0.98d;
        Vec3 vec3 = new Vec3(forward.x, yShootVec, forward.z).reverse();

        for (int i = 0; i < steps; i++) {
            double t = i * timeStep;

            double dx = vec3.x * initialVelocity * t;
            double dy = vec3.y * initialVelocity * t;
            double dz = vec3.z * initialVelocity * t;

            double yOffset = 0.5 * gravityValue * t * t;


            Vec3 point = new Vec3(dx, dy - yOffset + heightOffset, dz).multiply(drag, drag, drag);

            trajectory.add(point);
        }

        return trajectory;
    }


    public static void renderBallistaTrajectory(PoseStack poseStack, VertexConsumer vertexConsumer, List<Vec3> points, float r, float g, float b, float alpha) {
        PoseStack.Pose pose = poseStack.last();

        for (int i = 0; i < points.size() - 1; i++) {
            Vec3 p1 = points.get(i);
            Vec3 p2 = points.get(i + 1);

            vertexConsumer.vertex(pose.pose(), (float) p1.x, (float) p1.y, (float) p1.z)
                    .color(r, g, b, alpha)
                    .normal(pose.normal(), 0.0F, 1.0F, 0.0F) // Richtung normalisieren
                    .endVertex();

            vertexConsumer.vertex(pose.pose(), (float) p2.x, (float) p2.y, (float) p2.z)
                    .color(r, g, b, alpha)
                    .normal(pose.normal(), 0.0F, 1.0F, 0.0F)
                    .endVertex();
        }
    }

}
