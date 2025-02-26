package com.talhanation.siegeweapons.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class CatapultModel<T extends CatapultEntity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "catapult"), "main");
	private final ModelPart Catapult;
	private final ModelPart wheelsFront;
	private final ModelPart wheelFrontLeft;
	private final ModelPart wheelFrontRight;
	private final ModelPart wheelsBack;
	private final ModelPart wheelBackLeft;
	private final ModelPart wheelBackRight;
	private final ModelPart loader;
	private final ModelPart spoon;

	public CatapultModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.Catapult = root.getChild("Catapult");
		this.wheelsFront = this.Catapult.getChild("wheelsFront");
		this.wheelFrontLeft = this.wheelsFront.getChild("wheelFrontLeft");
		this.wheelFrontRight = this.wheelsFront.getChild("wheelFrontRight");
		this.wheelsBack = this.Catapult.getChild("wheelsBack");
		this.wheelBackLeft = this.wheelsBack.getChild("wheelBackLeft");
		this.wheelBackRight = this.wheelsBack.getChild("wheelBackRight");
		this.loader = this.Catapult.getChild("loader");
		this.spoon = this.Catapult.getChild("spoon");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Catapult = partdefinition.addOrReplaceChild("Catapult", CubeListBuilder.create().texOffs(0, 73).addBox(-15.0F, -42.0F, -4.5F, 30.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(3, 104).addBox(16.0F, -11.05F, 27.5F, 3.0F, 0.525F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(110, 105).addBox(15.0F, -10.5F, -32.0F, 5.0F, 5.0F, 68.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-20.0F, -10.5F, -32.0F, 5.0F, 5.0F, 68.0F, new CubeDeformation(0.0F))
				.texOffs(64, 81).addBox(15.0F, -42.5F, -5.0F, 5.0F, 32.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(64, 81).addBox(-20.0F, -42.5F, -5.0F, 5.0F, 32.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = Catapult.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(84, 93).addBox(15.5F, 0.6709F, -3.1669F, 4.0F, 25.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 93).addBox(-19.5F, 0.6709F, -3.1669F, 4.0F, 25.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.25F, -2.25F, 0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Catapult.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(84, 93).addBox(-2.0F, -22.5F, -2.0F, 4.0F, 25.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 93).addBox(33.0F, -22.5F, -2.0F, 4.0F, 25.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -10.25F, -20.75F, -0.8727F, 0.0F, 0.0F));

		PartDefinition wheelsFront = Catapult.addOrReplaceChild("wheelsFront", CubeListBuilder.create().texOffs(28, 178).addBox(-26.0F, -1.0F, -1.0F, 52.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -22.0F));

		PartDefinition wheelFrontLeft = wheelsFront.addOrReplaceChild("wheelFrontLeft", CubeListBuilder.create().texOffs(0, 182).addBox(-6.0F, -3.375F, -30.85F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.001F)), PartPose.offset(26.0F, -0.15F, 22.35F));

		PartDefinition cube_r3 = wheelFrontLeft.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.5F, -0.25F, -22.675F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r4 = wheelFrontLeft.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -0.375F, -22.35F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r5 = wheelFrontLeft.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.5F, -0.225F, -22.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition wheelFrontRight = wheelsFront.addOrReplaceChild("wheelFrontRight", CubeListBuilder.create().texOffs(0, 182).addBox(-6.0F, -3.375F, -30.85F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.001F)), PartPose.offset(-18.0F, -0.15F, 22.35F));

		PartDefinition cube_r6 = wheelFrontRight.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.5F, -0.25F, -22.675F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r7 = wheelFrontRight.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -0.375F, -22.35F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r8 = wheelFrontRight.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.5F, -0.225F, -22.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition wheelsBack = Catapult.addOrReplaceChild("wheelsBack", CubeListBuilder.create().texOffs(28, 178).addBox(-26.0F, -1.0F, -1.0F, 52.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 16.0F));

		PartDefinition wheelBackLeft = wheelsBack.addOrReplaceChild("wheelBackLeft", CubeListBuilder.create().texOffs(0, 182).addBox(-6.0F, -3.375F, -30.85F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.001F)), PartPose.offset(26.0F, -0.15F, 22.35F));

		PartDefinition cube_r9 = wheelBackLeft.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.5F, -0.25F, -22.675F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r10 = wheelBackLeft.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -0.375F, -22.35F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r11 = wheelBackLeft.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.5F, -0.225F, -22.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition wheelBackRight = wheelsBack.addOrReplaceChild("wheelBackRight", CubeListBuilder.create().texOffs(0, 182).addBox(-6.0F, -3.375F, -30.85F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.001F)), PartPose.offset(-18.0F, -0.15F, 22.35F));

		PartDefinition cube_r12 = wheelBackRight.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.5F, -0.25F, -22.675F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r13 = wheelBackRight.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -0.375F, -22.35F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r14 = wheelBackRight.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 182).addBox(-2.5F, -3.5F, -9.0F, 4.0F, 7.0F, 17.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.5F, -0.225F, -22.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition loader = Catapult.addOrReplaceChild("loader", CubeListBuilder.create(), PartPose.offset(-21.0F, -8.0F, 32.0F));

		PartDefinition cube_r15 = loader.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(100, 93).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.001F))
				.texOffs(100, 93).addBox(41.0F, -1.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r16 = loader.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(100, 93).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(100, 93).addBox(41.0F, -1.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r17 = loader.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(84, 81).addBox(-8.0F, -10.7782F, -5.1213F, 16.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(68, 73).addBox(-15.0F, -9.7782F, -4.1213F, 30.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, 4.0F, 7.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition spoon = Catapult.addOrReplaceChild("spoon", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -8.0F, -2.5F, -0.576F, 0.0F, 0.0F));

		PartDefinition cube_r18 = spoon.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(28, 95).addBox(-6.0F, -12.7782F, -3.1213F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(28, 95).addBox(4.0F, -12.7782F, -3.1213F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 95).addBox(-6.0F, -12.7782F, 4.8787F, 12.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 81).addBox(-6.0F, -6.7782F, -5.1213F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 95).addBox(-6.0F, -12.7782F, -5.1213F, 12.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.8198F, 40.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r19 = spoon.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(48, 81).addBox(-2.0F, -43.0F, -2.0F, 4.0F, 40.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r20 = spoon.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(84, 81).addBox(-8.0F, -10.7782F, -5.1213F, 16.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(68, 73).addBox(-15.0F, -9.7782F, -4.1213F, 30.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 7.5F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(CatapultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wheelsFront.xRot = entity.getWheelRotation(ageInTicks);
		wheelsBack.xRot = wheelsFront.xRot;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Catapult.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}