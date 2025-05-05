package com.talhanation.siegeweapons.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.ModTexts;
import com.talhanation.siegeweapons.SiegeWeapons;
import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import com.talhanation.siegeweapons.client.EntityInScreenRenderer;
import com.talhanation.siegeweapons.inventory.SiegeTableMenu;
import com.talhanation.siegeweapons.network.MessageUpdateSiegeTable;
import com.talhanation.siegeweapons.world.recipe.SiegeTableRecipe;
import de.maxhenkel.corelib.inventory.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
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
    private final BlockPos blockPos;
    public SiegeTableScreen(SiegeTableMenu container, Inventory playerInventory, Component title) {
        super(TEXTURE, container, playerInventory, title);
        this.blockPos = container.getEntity().getBlockPos();
        this.playerInventory = playerInventory;
        this.player = playerInventory.player;
        this.menu.setScreen(this);
        imageWidth = 176;
        imageHeight = 223;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    @Override
    protected void containerTick() {
        super.containerTick();

        if(this.tableEntity == null){
            this.tableEntity = (SiegeTableBlockEntity) player.getCommandSenderWorld().getBlockEntity(blockPos);
        }

        if(selection == null){
            if(tableEntity != null && tableEntity.selection != null){
                this.selection = tableEntity.selection;
            }
            else
                this.selection = SiegeWeapons.CATAPULT;
        }

        this.setButtons();
    }

    public void setButtons(){
        clearWidgets();

        this.leftButton = new ExtendedButton(leftPos + 7, topPos + 53, 16, 16, Component.literal("<"),
                button -> {
                    this.selection = this.selection.getBefore();
                    setButtons();
                });
        this.leftButton.active = tableEntity != null && !tableEntity.getCrafting();
        addRenderableWidget(this.leftButton);

        this.rightButton = new ExtendedButton(leftPos + 45, topPos + 53, 16, 16, Component.literal(">"),
                button -> {
                    this.selection = this.selection.getNext();
                    setButtons();
                });
        this.rightButton.active = tableEntity != null && !tableEntity.getCrafting();
        addRenderableWidget(this.rightButton);

        this.craftButton = new ExtendedButton(leftPos + 79, topPos + 50, 71, 20, ModTexts.CRAFT,
                button -> {
                    selection.getRecipe().consumeMaterials(playerInventory);

                    Main.SIMPLE_CHANNEL.sendToServer(new MessageUpdateSiegeTable(blockPos, selection.getIndex(),true));
                });
        this.craftButton.active = tableEntity != null && !tableEntity.getCrafting() && selection.getRecipe().hasRequiredMaterials(playerInventory);
        addRenderableWidget(this.craftButton);
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTicks, mouseX, mouseY);
        guiGraphics.drawString(font, ModTexts.SIEGE_WEAPON_TABLE, leftPos + 5, topPos + 5, FONT_COLOR, false);
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);


        if(selection != null) {
            guiGraphics.drawString(font, selection.getRecipe().getCraftingAmount() + "x " + selection.getEntityInClient().getName().getString(), leftPos + 62, topPos + 17, FONT_COLOR, false);
            EntityInScreenRenderer.renderEntityInInventoryRotating(guiGraphics,leftPos + 30 + selection.renderXOffset(), topPos + 45 + selection.renderYOffset(), 5, selection.getEntityInClient(), 1.0F, selection.getRenderScale());

            if(tableEntity != null && tableEntity.getCrafting()){
                int progress = tableEntity.getCraftingProgress();

                int progressX = leftPos + 61;
                int progressY = topPos + 40;
                int width = 110;
                int height = 8;

                guiGraphics.fill(progressX, progressY, progressX + width, progressY + height, 0xFF555555);
                guiGraphics.fill(progressX, progressY, progressX + progress, progressY + height, 0xFF00FF00);
            }
            else{
                this.renderRecipeItems(guiGraphics, selection.getRecipe(), leftPos + 61, topPos + 28, 16);
            }
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

    @Override
    public void onClose() {
        super.onClose();
        if(this.tableEntity != null && selection != null){
            Main.SIMPLE_CHANNEL.sendToServer(new MessageUpdateSiegeTable(blockPos, selection.getIndex(), false));
        }
    }

    public void onPlayerInventoryChanged(){
        if(craftButton != null) this.craftButton.active = selection.getRecipe().hasRequiredMaterials(playerInventory);
    }
}
