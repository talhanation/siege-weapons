package com.talhanation.siegeweapons.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.talhanation.siegeweapons.client.events.ClientEvent;
import com.talhanation.siegeweapons.client.render.FirePotProjectileRenderer;
import com.talhanation.siegeweapons.init.ModItems;
import com.talhanation.siegeweapons.items.FirePotItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;


public class FirePotItemRenderer extends BlockEntityWithoutLevelRenderer {
    private final FirePotProjectileRenderer renderer;
    public FirePotItemRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet) {
        super(renderDispatcher, modelSet);
        renderer = new FirePotProjectileRenderer(ClientEvent.context);
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int da) {
        FirePotItem item = ModItems.FIRE_POT_ITEM.get();
        renderer.render(item.getEntity(Minecraft.getInstance().level), -90F, 1F, poseStack, bufferSource, packedLight);
    }
}
