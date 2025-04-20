package com.talhanation.siegeweapons.client.gui;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.ModTexts;
import com.talhanation.siegeweapons.SiegeWeapons;
import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.inventory.SiegeTableMenu;
import com.talhanation.siegeweapons.inventory.VehicleInventoryMenu;
import com.talhanation.siegeweapons.math.Kalkuel;
import de.maxhenkel.corelib.inventory.ScreenBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InventoryVehicleScreen extends ScreenBase<VehicleInventoryMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID,"textures/gui/siege_weapon_gui.png" );
    private final Player player;
    private final AbstractVehicleEntity vehicle;
    public InventoryVehicleScreen(VehicleInventoryMenu container, Inventory playerInventory, Component title) {
        super(TEXTURE, container, playerInventory, title);
        this.player = playerInventory.player;
        this.vehicle = container.getEntity();
        imageWidth = 176;
        imageHeight = 223;
    }

    @Override
    protected void init() {
        super.init();
        setButtons();
    }

    public void setButtons(){
        clearWidgets();

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int i, int j) {
        super.renderLabels(guiGraphics, i, j);
        int leftPos = 160;
        int leftPos2 = 223;
        int topPos = 38;
        int gap = 14;
        int dmg = (int) (this.vehicle.getHealth() * 100 / this.vehicle.getMaxHealth());
        String unit = "km/h";
        int maxSpeed = (Mth.ceil(Kalkuel.getKilometerPerHour(this.vehicle.getMaxSpeedInKmH())));
        int currentSpeed = (Mth.ceil(Kalkuel.getKilometerPerHour(this.vehicle.getSpeed())));

        String projectiles;
        if(this.vehicle instanceof CatapultEntity){
            projectiles = "" + ModTexts.CATAPULT;
        }

        guiGraphics.drawString(font, "Type:", leftPos, topPos + gap * 0, FONT_COLOR, false);
        guiGraphics.drawString(font, "Speed " + unit + ":", leftPos, topPos + gap * 1, FONT_COLOR, false);
        guiGraphics.drawString(font, "Damage:", leftPos, topPos + gap * 2, FONT_COLOR, false);
        guiGraphics.drawString(font, "Projectiles:", leftPos, topPos + gap * 3, FONT_COLOR, false);

        guiGraphics.drawString(font, currentSpeed + "/" + maxSpeed, leftPos2, topPos + gap * 0, FONT_COLOR, false);
        guiGraphics.drawString(font, currentSpeed + "/" + maxSpeed, leftPos2, topPos + gap * 1, FONT_COLOR, false);
        guiGraphics.drawString(font, dmg + "%", leftPos2, topPos + gap * 2, FONT_COLOR, false);
        guiGraphics.drawString(font, dmg + "%", leftPos2, topPos + gap * 2, FONT_COLOR, false);
    }

}
