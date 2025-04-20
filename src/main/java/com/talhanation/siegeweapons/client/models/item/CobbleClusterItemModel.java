package com.talhanation.siegeweapons.client.models.item;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleClusterProjectile;

import net.minecraft.client.model.geom.ModelPart;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CobbleClusterItemModel<T extends CatapultCobbleClusterProjectile> extends EntityModel<T> {
	private final ModelPart cobbleClusterItemModel;

	public CobbleClusterItemModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.cobbleClusterItemModel = root.getChild("cobbleClusterItemModel");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition cobbleClusterItemModel = partdefinition.addOrReplaceChild("cobbleClusterItemModel", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, -3.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r1 = cobbleClusterItemModel.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 1.1802F, 10.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = cobbleClusterItemModel.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -1.8198F, 5.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = cobbleClusterItemModel.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 1.1802F, 5.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = cobbleClusterItemModel.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -1.8198F, 7.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r5 = cobbleClusterItemModel.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.8198F, 10.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r6 = cobbleClusterItemModel.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.1802F, 9.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r7 = cobbleClusterItemModel.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -3.8198F, 6.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r8 = cobbleClusterItemModel.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -3.8198F, 8.734F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(CatapultCobbleClusterProjectile entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cobbleClusterItemModel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}