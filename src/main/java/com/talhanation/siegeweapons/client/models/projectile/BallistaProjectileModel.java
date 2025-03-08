package com.talhanation.siegeweapons.client.models.projectile;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.talhanation.siegeweapons.entities.projectile.BallistaProjectile;
import net.minecraft.client.model.geom.ModelPart;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BallistaProjectileModel<T extends BallistaProjectile> extends EntityModel<T> {
	private final ModelPart arrow;

	public BallistaProjectileModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.arrow = root.getChild("arrow");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition arrow = partdefinition.addOrReplaceChild("arrow", CubeListBuilder.create().texOffs(0, 0).addBox(-0.4F, -0.5F, -12.0F, 1.0F, 1.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.5F, 0.0F));

		PartDefinition cube_r1 = arrow.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 25).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, -0.2F, 8.4F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r2 = arrow.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 25).addBox(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, -0.2F, 8.4F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r3 = arrow.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(24, 25).addBox(-0.4F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(24, 25).addBox(-0.4F, -0.7F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, -12.0F, 0.0F, -0.3054F, 0.0F));

		PartDefinition cube_r4 = arrow.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 29).addBox(-0.4F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(24, 29).addBox(-0.4F, -0.7F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, -12.0F, 0.0F, 0.3054F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(BallistaProjectile entity, float partialTicks, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		arrow.zRot = entity.getProjectileRotation(partialTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		arrow.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}