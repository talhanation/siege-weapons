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
	private final ModelPart wheels1;
	private final ModelPart wgeels2;
	private final ModelPart Rahmen;
	private final ModelPart Loffel_0;
	private final ModelPart loader;

	public CatapultModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.Catapult = root.getChild("Catapult");
		this.wheels1 = this.Catapult.getChild("wheels1");
		this.wgeels2 = this.Catapult.getChild("wgeels2");
		this.Rahmen = this.Catapult.getChild("Rahmen");
		this.Loffel_0 = this.Catapult.getChild("Loffel_0");
		this.loader = this.Catapult.getChild("loader");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Catapult = partdefinition.addOrReplaceChild("Catapult", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition wheels1 = Catapult.addOrReplaceChild("wheels1", CubeListBuilder.create().texOffs(84, 75).addBox(-20.5F, -0.5F, -0.5F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(84, 77).addBox(-6.5F, -0.5F, -0.5F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 49).addBox(6.5F, -0.5F, 0.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(54, 105).addBox(-20.6F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(80, 114).addBox(-20.5F, -4.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(88, 31).addBox(-20.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 105).addBox(-20.4F, -0.5F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(96, 87).addBox(-20.5F, -0.5F, 0.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(72, 104).addBox(6.5F, -0.5F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 49).addBox(6.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(88, 26).addBox(6.5F, -4.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 114).addBox(6.6F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -4.8F, -6.0F));

		PartDefinition cube_r1 = wheels1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(114, 112).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r2 = wheels1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(114, 107).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r3 = wheels1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(114, 73).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r4 = wheels1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(82, 104).addBox(-0.5F, 11.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r5 = wheels1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(70, 114).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r6 = wheels1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(112, 102).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r7 = wheels1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(10, 111).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r8 = wheels1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 105).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r9 = wheels1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(44, 105).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r10 = wheels1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(92, 105).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r11 = wheels1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(102, 105).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r12 = wheels1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 106).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r13 = wheels1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(10, 106).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r14 = wheels1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(106, 87).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.1F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition wgeels2 = Catapult.addOrReplaceChild("wgeels2", CubeListBuilder.create().texOffs(94, 93).addBox(-20.5F, -0.5F, -0.5F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 95).addBox(-6.5F, -0.5F, -0.5F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(106, 97).addBox(6.5F, -0.5F, 0.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 58).addBox(-20.6F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(44, 115).addBox(-20.5F, -4.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 115).addBox(-20.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(108, 79).addBox(-20.4F, -0.5F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 109).addBox(-20.5F, -0.5F, 0.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(74, 109).addBox(6.5F, -0.5F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 115).addBox(6.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 115).addBox(6.5F, -4.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 110).addBox(6.6F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -4.8F, 16.0F));

		PartDefinition cube_r15 = wgeels2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 111).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 10).addBox(-27.7F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r16 = wgeels2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(104, 110).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 15).addBox(-27.7F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r17 = wgeels2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(94, 110).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 20).addBox(-27.7F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r18 = wgeels2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(84, 110).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 48).addBox(-27.7F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r19 = wgeels2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(50, 110).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 53).addBox(-27.7F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r20 = wgeels2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(30, 110).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 63).addBox(-27.7F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r21 = wgeels2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(20, 110).addBox(-0.5F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 68).addBox(-27.7F, 3.8F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition Rahmen = Catapult.addOrReplaceChild("Rahmen", CubeListBuilder.create().texOffs(0, 80).addBox(-11.0F, -8.0F, 19.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(72, 80).addBox(11.0F, -8.0F, 9.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 93).addBox(11.0F, -8.0F, 9.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(70, 0).addBox(10.0F, -8.0F, -1.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(84, 49).addBox(11.0F, -8.0F, -11.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 80).addBox(11.0F, -8.0F, 19.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(24, 80).addBox(-11.0F, -8.0F, 9.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(68, 36).addBox(-11.0F, -8.0F, -1.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(84, 62).addBox(-11.0F, -8.0F, -11.0F, 2.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition cube_r22 = Rahmen.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(28, 54).addBox(-34.0F, -2.0F, -18.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, 24.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r23 = Rahmen.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(34, 36).addBox(17.5F, 0.0F, -13.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(36, 18).addBox(-4.5F, 0.0F, -13.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -9.7604F, 15.5976F, -0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r24 = Rahmen.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(36, 0).addBox(-15.5F, -3.7604F, -3.4024F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(6.5F, -3.7604F, -3.4024F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -7.0F, -3.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r25 = Rahmen.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(56, 67).addBox(5.0F, -2.0F, -8.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(28, 67).addBox(5.0F, -2.0F, -20.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(56, 54).addBox(-21.0F, -2.0F, -20.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(94, 37).addBox(-16.0F, -16.5F, -18.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(94, 26).addBox(-17.0F, -0.5F, -17.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -7.0F, 20.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r26 = Rahmen.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 18).addBox(4.0F, 22.0F, -1.0F, 2.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -9.0F, 0.0F, 1.5708F, -1.5708F, 0.0F));

		PartDefinition cube_r27 = Rahmen.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 0).addBox(4.0F, 4.0F, -1.0F, 2.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -9.0F, 0.0F, 1.5708F, -1.5708F, 0.0F));

		PartDefinition cube_r28 = Rahmen.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(72, 93).addBox(-21.0F, -0.5F, -12.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(70, 13).addBox(-20.0F, -16.5F, -13.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 67).addBox(-25.0F, -2.0F, -12.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -7.0F, 24.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r29 = Rahmen.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 54).addBox(-34.0F, -2.0F, -10.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -7.0F, 24.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition Loffel_0 = Catapult.addOrReplaceChild("Loffel_0", CubeListBuilder.create().texOffs(24, 93).addBox(-1.0F, -0.75F, 16.1875F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 93).addBox(-1.0F, -0.75F, 6.1875F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(70, 26).addBox(-1.0F, -0.75F, -0.8125F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(96, 4).addBox(-2.5F, 1.25F, 27.1875F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.3605F, 1.8614F, 0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r30 = Loffel_0.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(94, 97).addBox(-3.5F, -1.5F, -0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(96, 79).addBox(2.5F, -1.5F, -0.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 31.6875F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r31 = Loffel_0.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(96, 10).addBox(-2.5F, -1.5F, -1.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(96, 18).addBox(3.5F, -1.5F, -1.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.25F, 28.6875F, 0.0F, -1.5708F, 0.0F));

		PartDefinition loader = Catapult.addOrReplaceChild("loader", CubeListBuilder.create().texOffs(20, 106).addBox(18.0F, -8.0F, 13.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 105).addBox(18.0F, -9.0F, 14.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(60, 110).addBox(18.0F, -12.0F, 13.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 115).addBox(18.0F, -9.0F, 10.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(88, 115).addBox(-9.0F, -12.0F, 13.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(84, 115).addBox(-9.0F, -8.0F, 13.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 115).addBox(-9.0F, -9.0F, 14.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(28, 115).addBox(-9.0F, -9.0F, 10.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(96, 0).addBox(5.0F, -9.0F, 13.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 2).addBox(-9.0F, -9.0F, 13.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.7F, 13.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(CatapultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wgeels2.xRot = entity.getWheelRotation(ageInTicks);
		wheels1.xRot = wgeels2.xRot;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Catapult.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}