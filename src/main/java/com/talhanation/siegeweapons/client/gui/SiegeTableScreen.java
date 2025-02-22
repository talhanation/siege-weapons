package com.talhanation.siegeweapons.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import com.talhanation.siegeweapons.inventory.SiegeTableMenu;
import de.maxhenkel.corelib.inventory.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SiegeTableScreen extends ScreenBase<SiegeTableMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID,"textures/gui/siege_container.png" );

    private SiegeTableBlockEntity tableEntity;
    private Player player;

    public SiegeTableScreen(SiegeTableMenu container, Inventory playerInventory, Component title) {
        super(TEXTURE, container, playerInventory, title);
        this.tableEntity = container.getEntity();
        this.player = playerInventory.player;
        imageWidth = 176;
        imageHeight = 223;
    }


    @Override
    protected void init() {
        super.init();

    }

    protected void renderBg(GuiGraphics poseStack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(poseStack, partialTicks, mouseX, mouseY);

        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        //InventoryScreen.renderEntityInInventoryFollowsMouse(poseStack,i + 30, j + 60, 15, (float)(i + 50) - mouseX, (float)(j + 75 - 50) - mouseY, this.recruit);
        //if(recruit.getVehicle() instanceof AbstractHorse horse) InventoryScreen.renderEntityInInventoryFollowsMouse(poseStack, i + 30, j + 72, 15, (float)(i + 50) - mouseX, (float)(j + 75 - 50) - mouseY, horse);
    }

}
