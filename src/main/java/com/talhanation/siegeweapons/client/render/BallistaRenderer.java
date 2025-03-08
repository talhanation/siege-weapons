package com.talhanation.siegeweapons.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.models.BallistaModel;
import com.talhanation.siegeweapons.entities.BallistaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BallistaRenderer extends EntityRenderer<BallistaEntity> {

    private static final ResourceLocation[] TEXTURE = {
            new ResourceLocation(Main.MOD_ID,"textures/entity/ballista.png")
    };
    public BallistaRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 1.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(BallistaEntity entity) {
        return TEXTURE[0];
    }
    private final BallistaModel<BallistaEntity> model = new BallistaModel<>();
    double translation = -1.5D;

    @Override
    public void render(BallistaEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight) {
        poseStack.pushPose();

        poseStack.mulPose(Axis.YN.rotationDegrees(180));

        poseStack.scale(-1.0F, -1.0F, 1.0F);

        poseStack.translate(0.0D, translation,0.0D);

        float pitch = entity.xRotO + partialTicks * (entity.getXRot() - entity.xRotO);
        this.model.setCrossBowRotation(entity.isForward() || entity.isBackward(), pitch, entityYaw);

        this.model.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);

        VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(this.model.renderType(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();

    }
}
