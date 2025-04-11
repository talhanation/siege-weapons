package com.talhanation.siegeweapons.client.models.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.talhanation.siegeweapons.entities.projectile.FirePotProjectile;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;


public class CatapultFirePotProjectileModel<T extends FirePotProjectile> extends EntityModel<T> {
    private final ModelPart pot_projectile;

    public CatapultFirePotProjectileModel() {
        ModelPart root = createBodyLayer().bakeRoot();
        this.pot_projectile = root.getChild("pot_projectile");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition pot_projectile = partdefinition.addOrReplaceChild("pot_projectile", CubeListBuilder.create().texOffs(0, 0).addBox(-6.6667F, -2.1667F, -3.3333F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(9, 7).addBox(-3.6667F, 7.8333F, -0.3333F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-4.6667F, 8.8333F, -1.3333F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.6667F, 2.1667F, -1.6667F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(FirePotProjectile entity, float partialTicks, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        pot_projectile.xRot = entity.getProjectileRotation(partialTicks);
        pot_projectile.yRot = entity.getProjectileRotation(partialTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        pot_projectile.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


