package com.talhanation.siegeweapons.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.talhanation.siegeweapons.entities.BallistaEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BallistaModel<T extends BallistaEntity> extends EntityModel<T> {
	private final ModelPart ballista;
	private final ModelPart stand;
	private final ModelPart crossbow;
	private final ModelPart loaded;
	private final ModelPart leftArmLoaded;
	private final ModelPart rightArmLoaded;
	private final ModelPart unloaded;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart loader;
	private final ModelPart arrow;
	private final ModelPart wheels;
	public BallistaModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.ballista = root.getChild("ballista");
		this.stand = this.ballista.getChild("stand");
		this.crossbow = this.ballista.getChild("crossbow");
		this.loaded = this.crossbow.getChild("loaded");
		this.leftArmLoaded = this.loaded.getChild("leftArmLoaded");
		this.rightArmLoaded = this.loaded.getChild("rightArmLoaded");
		this.unloaded = this.crossbow.getChild("unloaded");
		this.leftArm = this.unloaded.getChild("leftArm");
		this.rightArm = this.unloaded.getChild("rightArm");
		this.loader = this.crossbow.getChild("loader");
		this.arrow = this.crossbow.getChild("arrow");
		this.wheels = this.stand.getChild("wheels");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ballista = partdefinition.addOrReplaceChild("ballista", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, -7.0F));

		PartDefinition stand = ballista.addOrReplaceChild("stand", CubeListBuilder.create().texOffs(3, 33).addBox(-5.5F, 13.0F, -6.0F, 12.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(54, 46).addBox(-2.0F, 13.0F, 2.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(38, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.4F, 7.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition wheels = stand.addOrReplaceChild("wheels", CubeListBuilder.create().texOffs(6, 42).addBox(-7.404F, -0.652F, -0.54F, 15.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(55, 63).addBox(-8.504F, 1.448F, -1.04F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 33).addBox(-8.404F, -1.452F, -0.54F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(14, 33).addBox(-8.404F, 0.548F, -1.54F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(18, 47).addBox(-8.304F, -1.452F, -1.54F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 32).addBox(-8.404F, -0.452F, 0.46F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(54, 63).addBox(6.496F, 1.448F, -1.04F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 33).addBox(7.346F, -1.452F, -0.54F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(14, 33).addBox(7.346F, 0.548F, -1.54F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(18, 47).addBox(7.446F, -1.452F, -1.54F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 32).addBox(7.346F, -0.452F, 0.46F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(18, 47).addBox(-9.304F, -0.452F, -0.54F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(47, 57).addBox(7.296F, -0.452F, -0.54F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.404F, 14.552F, -3.96F));

		PartDefinition cube_r1 = wheels.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 63).addBox(-1.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(55, 63).addBox(-16.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.996F, 0.048F, -0.04F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = wheels.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 63).addBox(-1.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(55, 63).addBox(-16.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.996F, 0.048F, -0.04F, -2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r3 = wheels.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(54, 63).addBox(-1.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(55, 63).addBox(-16.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.996F, 0.048F, -0.04F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r4 = wheels.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(54, 63).addBox(-1.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(55, 63).addBox(-16.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.996F, 0.048F, -0.04F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r5 = wheels.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(54, 63).addBox(-1.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(55, 63).addBox(-16.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.996F, 0.048F, -0.04F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r6 = wheels.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(54, 63).addBox(-1.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(55, 63).addBox(-16.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.996F, 0.048F, -0.04F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r7 = wheels.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(54, 63).addBox(-1.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(55, 63).addBox(-16.5F, 1.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.996F, 0.048F, -0.04F, 0.7854F, 0.0F, 0.0F));

		PartDefinition crossbow = ballista.addOrReplaceChild("crossbow", CubeListBuilder.create().texOffs(20, 59).addBox(-3.0F, -6.9F, -12.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 53).addBox(-5.0F, -2.0F, -20.0F, 10.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -3.9F, -11.0F, 6.0F, 4.0F, 29.0F, new CubeDeformation(0.0F))
				.texOffs(0, 39).addBox(-8.0F, -3.95F, -14.0F, 16.0F, 4.05F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 7.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition loaded = crossbow.addOrReplaceChild("loaded", CubeListBuilder.create(), PartPose.offset(6.625F, -2.0F, -12.6F));

		PartDefinition leftArmLoaded = loaded.addOrReplaceChild("leftArmLoaded", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r8 = leftArmLoaded.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(39, 33).addBox(-16.4916F, -0.0991F, -1.01F, 18.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.375F, -2.0F, 10.6F, 0.0F, 0.9599F, 0.0F));

		PartDefinition cube_r9 = leftArmLoaded.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, -2.0F, -2.0F, 16.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 59).addBox(13.0F, -2.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition rightArmLoaded = loaded.addOrReplaceChild("rightArmLoaded", CubeListBuilder.create(), PartPose.offsetAndRotation(-13.225F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r10 = rightArmLoaded.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(39, 33).addBox(-1.5084F, -0.0991F, -1.01F, 18.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.3F, -1.9F, 10.6F, 0.0F, -0.9599F, 0.0F));

		PartDefinition cube_r11 = rightArmLoaded.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(10, 59).addBox(-16.0F, -2.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(38, 39).addBox(-16.0F, -2.0F, -2.0F, 16.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition unloaded = crossbow.addOrReplaceChild("unloaded", CubeListBuilder.create(), PartPose.offset(6.625F, -2.0F, -12.6F));

		PartDefinition leftArm = unloaded.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(40, 33).addBox(-6.625F, -1.9991F, 9.5869F, 17.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r12 = leftArm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, -2.0F, -2.0F, 16.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 59).addBox(13.0F, -2.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition rightArm = unloaded.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(40, 33).addBox(-10.4F, -1.9991F, 9.5869F, 17.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.225F, 0.0F, 0.0F));

		PartDefinition cube_r13 = rightArm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(10, 59).addBox(-16.0F, -2.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(38, 39).addBox(-16.0F, -2.0F, -2.0F, 16.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.025F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition loader = crossbow.addOrReplaceChild("loader", CubeListBuilder.create(), PartPose.offset(-4.0F, -2.0F, 15.0F));

		PartDefinition cube_r14 = loader.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(20, 62).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(54, 56).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(54, 56).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 62).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(20, 62).addBox(7.0F, -1.0F, 1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(20, 62).addBox(7.0F, -1.0F, -4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(54, 56).addBox(7.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(54, 56).addBox(7.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition arrow = crossbow.addOrReplaceChild("arrow", CubeListBuilder.create().texOffs(11, 5).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, -4.5F, -13.6F));

		PartDefinition cube_r15 = arrow.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(52, 33).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2F, 20.4F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r16 = arrow.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(52, 33).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2F, 20.4F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r17 = arrow.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(54, 60).addBox(-0.4F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(54, 60).addBox(-0.4F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, -0.1F, 0.0F, 0.0F, -0.3054F, 0.0F));

		PartDefinition cube_r18 = arrow.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(54, 60).addBox(-0.4F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(54, 60).addBox(-0.4F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, -0.1F, 0.0F, 0.0F, 0.3054F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(BallistaEntity entity, float partialTicks, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wheels.xRot = entity.getWheelRotation(partialTicks);

		loader.xRot = entity.getLoaderRotation(partialTicks);

		arrow.visible = entity.getState() == BallistaEntity.BallistaState.PROJECTILE_LOADED;
		loaded.visible = entity.getState() == BallistaEntity.BallistaState.PROJECTILE_LOADED || entity.getState() == BallistaEntity.BallistaState.LOADED;
		unloaded.visible = entity.getState() == BallistaEntity.BallistaState.UNLOADED || entity.getState() == BallistaEntity.BallistaState.LOADING;
	}

	public void setCrossBowRotation(boolean isMoving, float pitch, float yaw) {
		this.crossbow.xRot = (float) Math.toRadians(pitch);
		this.crossbow.yRot = (float) Math.toRadians(yaw);

		if(isMoving){
			this.stand.yRot = (float) Math.toRadians(yaw);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ballista.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}