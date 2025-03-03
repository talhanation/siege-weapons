package com.talhanation.siegeweapons.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CatapultModel<T extends CatapultEntity> extends EntityModel<T> {
	private final ModelPart Catapult;
	private final ModelPart wheelsFront;
	private final ModelPart wheelFrontLeft;
	private final ModelPart wheelFrontRight;
	private final ModelPart wheelsBack;
	private final ModelPart wheelBackLeft;
	private final ModelPart wheelBackRight;
	private final ModelPart loader;
	private final ModelPart spoon;
	private final ModelPart cobbleProjectile;
	private final ModelPart cobbleBundleProjectile;
	private final ModelPart firePotProjectile;
	private final ModelPart explosionPotProjectile;


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
		this.cobbleProjectile = this.spoon.getChild("cobbleProjectile");
		this.cobbleBundleProjectile = this.spoon.getChild("cobbleBundleProjectile");
		this.firePotProjectile = this.spoon.getChild("firePotProjectile");
		this.explosionPotProjectile = this.spoon.getChild("explosionPotProjectile");

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

		PartDefinition spoon = Catapult.addOrReplaceChild("spoon", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, -2.5F));

		PartDefinition cube_r18 = spoon.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(25, 95).addBox(-6.0F, -12.7782F, -4.1213F, 1.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(26, 95).addBox(5.0F, -12.7782F, -4.1213F, 1.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(1, 96).addBox(-6.0F, -12.7782F, 5.8787F, 12.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 81).addBox(-6.0F, -6.7782F, -5.1213F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(1, 96).addBox(-6.0F, -12.7782F, -5.1213F, 12.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.8198F, 40.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r19 = spoon.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(48, 81).addBox(-2.0F, -43.0F, -2.0F, 4.0F, 40.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r20 = spoon.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(84, 81).addBox(-8.0F, -10.7782F, -5.1213F, 16.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(68, 73).addBox(-15.0F, -9.7782F, -4.1213F, 30.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 7.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cobbleProjectile = spoon.addOrReplaceChild("cobbleProjectile", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 2.5F));

		PartDefinition cube_r21 = cobbleProjectile.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -14.7782F, -5.1213F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -39.8198F, 35.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cobbleBundleProjectile = spoon.addOrReplaceChild("cobbleBundleProjectile", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 2.5F));

		PartDefinition cube_r22 = cobbleBundleProjectile.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(11, 5).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -39.8198F, 40.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r23 = cobbleBundleProjectile.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(11, 5).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -42.8198F, 35.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r24 = cobbleBundleProjectile.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(11, 5).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(11, 5).addBox(7.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -39.8198F, 35.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r25 = cobbleBundleProjectile.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(11, 5).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -42.8198F, 37.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r26 = cobbleBundleProjectile.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(11, 5).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -42.8198F, 40.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r27 = cobbleBundleProjectile.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(11, 5).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -39.8198F, 39.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r28 = cobbleBundleProjectile.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(11, 5).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -44.8198F, 36.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r29 = cobbleBundleProjectile.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(11, 5).addBox(2.0F, -8.7782F, -5.1213F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -44.8198F, 38.734F, 0.7854F, 0.0F, 0.0F));

		PartDefinition firePotProjectile = spoon.addOrReplaceChild("firePotProjectile", CubeListBuilder.create().texOffs(0, 20).addBox(-5.0F, 10.3333F, -4.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(9, 27).addBox(-2.0F, 9.3333F, -1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 40).addBox(-3.0F, 7.3333F, -2.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -46.3333F, 22.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition explosionPotProjectile = spoon.addOrReplaceChild("explosionPotProjectile", CubeListBuilder.create().texOffs(216, 0).addBox(-5.0F, 10.3333F, -4.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(225, 7).addBox(-2.0F, 9.3333F, -1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(216, 20).addBox(-3.0F, 7.3333F, -2.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -46.3333F, 22.5F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}


	@Override
	public void setupAnim(CatapultEntity entity, float partialTicks, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wheelsFront.xRot = entity.getWheelRotation(partialTicks);
		wheelsBack.xRot = wheelsFront.xRot;

		loader.xRot = entity.getLoaderRotation(partialTicks);
		spoon.xRot = entity.getAngleRotation(partialTicks);

		boolean projectileVisible = entity.getState() == CatapultEntity.CatapultState.PROJECTILE_LOADED || entity.getState() == CatapultEntity.CatapultState.SHOOTING;

		cobbleProjectile.visible = projectileVisible && entity.getProjectile() == CatapultEntity.CatapultProjectiles.COBBLE_SHOT;
		cobbleBundleProjectile.visible = projectileVisible && entity.getProjectile() == CatapultEntity.CatapultProjectiles.BUNDLE_SHOT;
		firePotProjectile.visible = projectileVisible && entity.getProjectile() == CatapultEntity.CatapultProjectiles.FIRE_SHOT;
		explosionPotProjectile.visible = projectileVisible && entity.getProjectile() == CatapultEntity.CatapultProjectiles.EXPLOSION_SHOT;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Catapult.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}