package com.talhanation.siegeweapons.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.models.projectile.CatapultFirePotProjectileModel;
import com.talhanation.siegeweapons.entities.projectile.FirePotProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class FirePotProjectileRenderer extends EntityRenderer<FirePotProjectile> {
    private final boolean isItem;
    private static final ResourceLocation[] TEXTURE = {
            new ResourceLocation(Main.MOD_ID,"textures/entity/pot.png")
    };
    public FirePotProjectileRenderer(EntityRendererProvider.Context context, boolean isItem) {
        super(context);
        this.shadowRadius = 1.5F;
        this.isItem = isItem;
    }

    @Override
    public ResourceLocation getTextureLocation(FirePotProjectile entity) {
        return TEXTURE[0];
    }
    private final CatapultFirePotProjectileModel<FirePotProjectile> model = new CatapultFirePotProjectileModel<>();
    @Override
    public void render(FirePotProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight) {
        poseStack.pushPose();

        if(isItem){
            poseStack.mulPose(Axis.XN.rotationDegrees(-180));
        }

        poseStack.translate(0.0D, -0.0D, 0.0D);

        poseStack.scale(-1.0F, -1.0F, 1.0F);

        poseStack.translate(0.0D, 0.0D,0.0D);

        if(!this.isItem)this.model.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);

        VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(this.model.renderType(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();

    }
}
