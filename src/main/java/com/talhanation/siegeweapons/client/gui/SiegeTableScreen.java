package com.talhanation.siegeweapons.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.SiegeWeapons;
import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import com.talhanation.siegeweapons.client.EntityInScreenRenderer;
import com.talhanation.siegeweapons.inventory.SiegeTableMenu;
import de.maxhenkel.corelib.inventory.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.widget.ExtendedButton;

@OnlyIn(Dist.CLIENT)
public class SiegeTableScreen extends ScreenBase<SiegeTableMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID,"textures/gui/siege_table.png" );

    private SiegeTableBlockEntity tableEntity;
    public Player player;
    public Inventory playerInventory;
    private SiegeWeapons selection;
    private ExtendedButton leftButton;
    private ExtendedButton rightButton;
    private ExtendedButton craftButton;

    public SiegeTableScreen(SiegeTableMenu container, Inventory playerInventory, Component title) {
        super(TEXTURE, container, playerInventory, title);
        this.tableEntity = container.getEntity();
        this.playerInventory = playerInventory;
        this.player = playerInventory.player;
        this.menu.setScreen(this);
        imageWidth = 176;
        imageHeight = 223;
    }

    @Override
    protected void init() {
        super.init();
        this.selection = SiegeWeapons.CATAPULT;

        this.setButtons();
    }


    public void setButtons(){
        this.leftButton = new ExtendedButton(leftPos + 7, topPos + 59, 10, 10, Component.literal("<"),
                button -> {
                    this.selection = this.selection.getBefore();
                });

        addRenderableWidget(this.leftButton);

        this.rightButton = new ExtendedButton(leftPos + 51, topPos + 59, 10, 10, Component.literal(">"),
                button -> {
                    this.selection = this.selection.getNext();
                });

        addRenderableWidget(this.rightButton);

        this.craftButton = new ExtendedButton(leftPos + 79, topPos + 50, 71, 20, Component.literal("Craft"),
                button -> {
                    //Main.SIMPLE_CHANNEL.sendToServer();
                });
        this.craftButton.active = selection.getRecipe().hasRequiredMaterials(playerInventory);
        addRenderableWidget(this.craftButton);
    }


    protected void renderBg(GuiGraphics poseStack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(poseStack, partialTicks, mouseX, mouseY);

        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        if(selection != null) {
            //selection.getRecipe().getRequiredMaterials().forEach(() ->);
            EntityInScreenRenderer.renderEntityInInventoryRotating(poseStack,x + 30, y + 45, 10, selection.getEntity(), 1.0F);
        }
    }

    public void onPlayerInventoryChanged(){
        if(craftButton != null) this.craftButton.active = selection.getRecipe().hasRequiredMaterials(playerInventory);
    }


}
