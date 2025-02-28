package com.talhanation.siegeweapons.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleProjectile;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;


public class CatapultProjectileModel<T extends CatapultCobbleProjectile> extends EntityModel<T> {
    private final ModelPart projectile;

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("projectile", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public CatapultProjectileModel() {
        ModelPart root = createBodyLayer().bakeRoot();
        this.projectile = root.getChild("projectile");
    }

    @Override
    public void setupAnim(CatapultCobbleProjectile entity, float partialTicks, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float rotationSpeed = 1.01F;

        projectile.yRot = partialTicks * 0.2F * rotationSpeed;
        projectile.xRot = partialTicks * 0.15F * rotationSpeed;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        projectile.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


