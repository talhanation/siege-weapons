package com.talhanation.siegeweapons.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.talhanation.siegeweapons.client.events.ClientEvent;
import com.talhanation.siegeweapons.client.render.CobbleClusterProjectileRenderer;
import com.talhanation.siegeweapons.init.ModItems;
import com.talhanation.siegeweapons.items.CobbleClusterItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;


public class CobbleClusterItemRenderer extends BlockEntityWithoutLevelRenderer {
    private final CobbleClusterProjectileRenderer renderer;
    public CobbleClusterItemRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet) {
        super(renderDispatcher, modelSet);
        renderer = new CobbleClusterProjectileRenderer(ClientEvent.context, true);
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int da) {
        CobbleClusterItem item = ModItems.COBBLE_CLUSTER_ITEM.get();
        renderer.render(item.getEntity(Minecraft.getInstance().level), 1, 1F, poseStack, bufferSource, packedLight);
    }
}
