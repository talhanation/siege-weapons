package com.talhanation.siegeweapons.client;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TipManager {
    private static int remainingTicks = 0;
    private static List<Component> currentTips = List.of();
    private static final int TOTAL_DURATION = 200;
    private static final int FADE_DURATION = 40;


    public static void showTips(List<Component> tips) {
        remainingTicks = TOTAL_DURATION;
        currentTips = tips;
    }

    public static void tick() {
        if (remainingTicks > 0) remainingTicks--;
    }

    public static float getFadeAlpha() {

        if (remainingTicks > FADE_DURATION) return 1.0F;
        return remainingTicks / (float) FADE_DURATION;
    }

    public static List<Component> getCurrentTips() {
        return currentTips;
    }
}
