package com.talhanation.siegeweapons.client.events;

import com.talhanation.siegeweapons.ModTexts;
import com.talhanation.siegeweapons.client.TipManager;
import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import com.talhanation.siegeweapons.entities.BallistaEntity;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClientRenderEvents {
    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        if (Minecraft.getInstance().player.getVehicle() instanceof BallistaEntity ballista && ballista.getShowTrajectory()) {
            event.setCanceled(true);
        }
    }

    private static final int LINE_SPACING = 2;
    private static final int BASE_Y_OFFSET = 50;
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onPlayerMountVehicle(EntityMountEvent event) {
        Entity vehicle = event.getEntityBeingMounted();
        Entity passenger = event.getEntityMounting();
        Entity clientPlayer = Minecraft.getInstance().player;

        if (event.isMounting() && passenger instanceof Player player && clientPlayer != null && clientPlayer.getUUID().equals(player.getUUID())) {
            List<Component> tips = null;
            Minecraft mc = Minecraft.getInstance();
            String loadShoot = mc.options.keyJump.getTranslatedKeyMessage().getString();
            String aim = mc.options.keyUse.getTranslatedKeyMessage().getString();
            if (vehicle instanceof CatapultEntity catapult) {

                tips = List.of(
                        Component.literal("[" + aim + " + Mouse Wheel] - ").append(ModTexts.CATAPULT_RANGE),
                        Component.literal("[" + loadShoot + "] - ").append(ModTexts.VEHICLE_LOAD_SHOOT),
                        Component.literal("[" + aim + "] - ").append(ModTexts.VEHICLE_AIM)
                );

                if (event.isMounting()) {
                    Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
                } else {
                    Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
                }
            }

            else if(vehicle instanceof BallistaEntity){
                tips = List.of(
                        Component.literal("[" + aim + " + " + ModTexts.BALLISTA_BOLT.getString() +  "] - ").append(ModTexts.BALLISTA_RELOAD),
                        Component.literal("[" + loadShoot + "] - ").append(ModTexts.VEHICLE_LOAD_SHOOT),
                        Component.literal("[" + aim + "] - ").append(ModTexts.VEHICLE_AIM)
                );
            }

            if (player.level().isClientSide() && tips != null) {
                TipManager.showTips(tips);
            }
        }
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event){
        TipManager.tick();
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderGui(RenderGuiEvent.Post event) {
        List<Component> tips = TipManager.getCurrentTips();
        float alpha = TipManager.getFadeAlpha();

        if (alpha > 0 && !tips.isEmpty()) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            Minecraft minecraft = Minecraft.getInstance();
            Font font = minecraft.font;

            int screenWidth = event.getWindow().getGuiScaledWidth();
            int screenHeight = event.getWindow().getGuiScaledHeight();

            int color = (int)(alpha * 255) << 24 | 0xFFFFFF;
            int yStart = (int) (screenHeight / 1.25F);

            for (int i = 0; i < tips.size(); i++) {
                Component line = tips.get(i);
                int x = screenWidth / 2 - 90;
                int y = yStart - (i * (font.lineHeight + LINE_SPACING));

                guiGraphics.drawString(
                        font,
                        line,
                        x,
                        y,
                        color,
                        false
                );
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderGuiPre(RenderGuiOverlayEvent.Pre event) {
        if (Minecraft.getInstance().player.getVehicle() instanceof AbstractVehicleEntity) {
            Minecraft.getInstance().gui.setOverlayMessage(Component.empty(), false);
        }
    }

}
