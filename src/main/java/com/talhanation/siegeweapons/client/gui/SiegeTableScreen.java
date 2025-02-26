package com.talhanation.siegeweapons.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.SiegeWeapons;
import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import com.talhanation.siegeweapons.client.EntityInScreenRenderer;
import com.talhanation.siegeweapons.inventory.SiegeTableMenu;
import com.talhanation.siegeweapons.world.SiegeTableRecipe;
import de.maxhenkel.corelib.inventory.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.widget.ExtendedButton;

import java.util.Map;

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
                    setButtons();
                });

        addRenderableWidget(this.leftButton);

        this.rightButton = new ExtendedButton(leftPos + 51, topPos + 59, 10, 10, Component.literal(">"),
                button -> {
                    this.selection = this.selection.getNext();
                    setButtons();
                });

        addRenderableWidget(this.rightButton);

        this.craftButton = new ExtendedButton(leftPos + 79, topPos + 50, 71, 20, Component.literal("Craft"),
                button -> {
                    selection.getRecipe().consumeMaterials(playerInventory);
                    //Main.SIMPLE_CHANNEL.sendToServer();
                });
        this.craftButton.active = selection.getRecipe().hasRequiredMaterials(playerInventory);
        addRenderableWidget(this.craftButton);
    }


    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTicks, mouseX, mouseY);

        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        if(selection != null) {
            this.renderRecipeItems(guiGraphics, selection.getRecipe(), x + 70, y + 25, 15);
            EntityInScreenRenderer.renderEntityInInventoryRotating(guiGraphics,x + 30, y + 45, 10, selection.getEntity(), 1.0F);
        }
    }

    public void renderRecipeItems(GuiGraphics guiGraphics, SiegeTableRecipe recipe, int startX, int startY, int spacing) {
        int xOffset = 0;

        for (Map.Entry<Item, Integer> entry : recipe.getRequiredMaterials().entrySet()) {
            ItemStack stack = new ItemStack(entry.getKey(), entry.getValue());
            int itemX = startX + xOffset;
            int itemY = startY;

            guiGraphics.renderFakeItem(stack, itemX, itemY);
            guiGraphics.renderItemDecorations(font, stack, itemX, itemY);

            xOffset += spacing;
        }
    }

    public void onPlayerInventoryChanged(){
        if(craftButton != null) this.craftButton.active = selection.getRecipe().hasRequiredMaterials(playerInventory);
    }
}
