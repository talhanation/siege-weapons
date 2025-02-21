package com.talhanation.siegeweapons.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.models.CatapultModel;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

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

        poseStack.popPose();

    }
}
