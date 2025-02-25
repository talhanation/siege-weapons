package com.talhanation.siegeweapons.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.entities.TransportCartEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class TransportCartModel<T extends TransportCartEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "transportcartmodel"), "main");
	private final ModelPart TransportCart;
	private final ModelPart base;
	private final ModelPart fence2;
	private final ModelPart fence1;
	private final ModelPart fence3;
	private final ModelPart wheelsFrontBase;
	private final ModelPart wheelsFront;
	private final ModelPart wheelsBack;

	public TransportCartModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.TransportCart = root.getChild("TransportCart");
		this.base = this.TransportCart.getChild("base");
		this.fence2 = this.base.getChild("fence2");
		this.fence1 = this.base.getChild("fence1");
		this.fence3 = this.base.getChild("fence3");
		this.wheelsFrontBase = this.TransportCart.getChild("wheelsFrontBase");
		this.wheelsFront = this.wheelsFrontBase.getChild("wheelsFront");
		this.wheelsBack = this.TransportCart.getChild("wheelsBack");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition TransportCart = partdefinition.addOrReplaceChild("TransportCart", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 14.0F));

		PartDefinition base = TransportCart.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 50).addBox(8.0F, 0.0F, -13.0F, 2.0F, 3.0F, 37.0F, new CubeDeformation(0.0F))
				.texOffs(78, 50).addBox(-10.0F, 0.0F, -13.0F, 2.0F, 3.0F, 37.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.0F, -2.0F, -24.0F, 28.0F, 2.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -14.0F));

		PartDefinition fence2 = base.addOrReplaceChild("fence2", CubeListBuilder.create(), PartPose.offset(-14.0F, -7.2F, 14.0F));

		PartDefinition cube_r1 = fence2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(152, 20).addBox(5.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition cube_r2 = fence2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(152, 33).addBox(-7.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(32, 143).addBox(-9.0F, 0.0F, -1.5F, 19.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(32, 147).addBox(-8.0F, 0.0F, 2.0F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(120, 143).addBox(-8.0F, 0.0F, -5.0F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition cube_r3 = fence2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(32, 155).addBox(5.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -21.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition cube_r4 = fence2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(88, 155).addBox(-7.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(156, 85).addBox(-8.0F, 0.0F, 2.0F, 12.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(158, 147).addBox(-9.0F, 0.0F, -1.5F, 12.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(158, 151).addBox(-8.0F, 0.0F, -5.0F, 12.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(60, 155).addBox(5.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -30.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition cube_r5 = fence2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 151).addBox(-8.0F, 0.0F, -5.0F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(116, 147).addBox(-9.0F, 0.0F, -1.5F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(74, 147).addBox(-8.0F, 0.0F, 2.0F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -18.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition fence1 = base.addOrReplaceChild("fence1", CubeListBuilder.create(), PartPose.offsetAndRotation(14.0F, -7.2F, -14.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r6 = fence1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(116, 155).addBox(5.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(74, 151).addBox(-8.0F, 0.0F, -5.0F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(116, 151).addBox(-9.0F, 0.0F, -1.5F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(144, 155).addBox(-7.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(152, 12).addBox(-8.0F, 0.0F, 2.0F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition cube_r7 = fence1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(156, 46).addBox(5.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -21.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition cube_r8 = fence1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(152, 0).addBox(-8.0F, 0.0F, 2.0F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(152, 4).addBox(-8.0F, 0.0F, -5.0F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(152, 8).addBox(-9.0F, 0.0F, -1.5F, 18.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -18.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition cube_r9 = fence1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(156, 59).addBox(5.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(160, 127).addBox(-8.0F, 0.0F, 2.0F, 12.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(160, 131).addBox(-8.0F, 0.0F, -5.0F, 12.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(160, 135).addBox(-8.0F, 0.0F, -1.5F, 11.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(156, 72).addBox(-7.0F, -1.0F, -7.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -30.0F, 1.5708F, -1.5708F, -0.1745F));

		PartDefinition fence3 = base.addOrReplaceChild("fence3", CubeListBuilder.create().texOffs(152, 16).addBox(-23.0F, 7.0F, -18.75F, 17.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(160, 139).addBox(-32.0F, 7.0F, -18.75F, 9.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(184, 98).addBox(-9.0F, 6.0F, -18.75F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(184, 107).addBox(-31.0F, 6.0F, -18.75F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(76, 143).addBox(-24.0F, 7.0F, -15.25F, 19.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(162, 143).addBox(-33.0F, 7.0F, -15.25F, 9.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.0F, -20.375F, 16.875F, 1.5708F, 0.0F, 0.0F));

		PartDefinition wheelsFrontBase = TransportCart.addOrReplaceChild("wheelsFrontBase", CubeListBuilder.create().texOffs(0, 90).addBox(5.0F, -3.0F, -30.0F, 2.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
				.texOffs(66, 90).addBox(-7.0F, -3.0F, -30.0F, 2.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
				.texOffs(0, 159).addBox(-5.0F, -5.0F, -2.0F, 10.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -28.0F));

		PartDefinition wheelsFront = wheelsFrontBase.addOrReplaceChild("wheelsFront", CubeListBuilder.create().texOffs(0, 123).addBox(-20.0F, -1.0F, -1.0F, 40.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 127).addBox(16.5F, -1.0F, -7.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(64, 127).addBox(-18.5F, -1.0F, -7.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(164, 89).addBox(-19.0F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(20, 177).addBox(16.0F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r10 = wheelsFront.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(168, 116).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(164, 107).addBox(-33.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r11 = wheelsFront.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(128, 168).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(48, 168).addBox(-33.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r12 = wheelsFront.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(148, 168).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(68, 168).addBox(-33.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r13 = wheelsFront.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(168, 168).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(108, 168).addBox(-33.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r14 = wheelsFront.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 170).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(88, 168).addBox(-33.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(96, 127).addBox(-33.0F, -1.0F, -7.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(32, 127).addBox(2.0F, -1.0F, -7.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r15 = wheelsFront.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(172, 155).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(28, 168).addBox(-33.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r16 = wheelsFront.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(40, 177).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(164, 98).addBox(-33.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition wheelsBack = TransportCart.addOrReplaceChild("wheelsBack", CubeListBuilder.create().texOffs(84, 123).addBox(-20.0F, -1.0F, -1.0F, 40.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(128, 127).addBox(16.5F, -1.0F, -7.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(132, 106).addBox(-18.5F, -1.0F, -7.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 179).addBox(16.0F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(184, 74).addBox(-19.0F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r17 = wheelsBack.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(180, 29).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(60, 177).addBox(36.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.5F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r18 = wheelsBack.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(180, 177).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(80, 177).addBox(36.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.5F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r19 = wheelsBack.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(184, 38).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(100, 177).addBox(36.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.5F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r20 = wheelsBack.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(184, 47).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(120, 177).addBox(36.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.5F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r21 = wheelsBack.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(184, 56).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(140, 177).addBox(36.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 143).addBox(2.0F, -1.0F, -7.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(132, 90).addBox(37.0F, -1.0F, -7.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.5F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r22 = wheelsBack.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(184, 65).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(160, 177).addBox(36.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.5F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r23 = wheelsBack.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(184, 89).addBox(1.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(180, 20).addBox(36.5F, 6.5F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.5F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(TransportCartEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wheelsFront.xRot = entity.getWheelRotation(ageInTicks);
		wheelsBack.xRot = wheelsFront.xRot;

		wheelsFrontBase.yRot = entity.getYRot();
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		TransportCart.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}