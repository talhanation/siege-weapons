package com.talhanation.siegeweapons.math;

import net.minecraft.util.Mth;

public class Kalkuel {

    /**
     * Subtracts from the provided number, but does not cross zero
     *
     * @param num the number
     * @param sub the amount to subtract
     * @return the resulting number
     */
    public static float subtractToZero(float num, float sub) {
        float erg;
        if (num < 0F) {
            erg = num + sub;
            if (erg > 0F) {
                erg = 0F;
            }
        }
        else {
            erg = num - sub;
            if (erg < 0F) {
                erg = 0F;
            }
        }
        return erg;
    }

    /**
     * Subtracts and adds from the provided number, but does not cross the set point
     *
     * @param current the current number
     * @param positiveChange the amount to add
     * @param setPoint the amount to not cross
     * @return the resulting number
     */
    public static float changeToSetPoint(float current, float positiveChange, float negativeChange, float setPoint) {
        if (current < setPoint) {
            current = current + positiveChange;
        }
        else {
            current = current - negativeChange;

        }
        return current;
    }

    /**
     * Adds from the provided number, but does not cross the set point
     *
     * @param current the current number
     * @param positiveChange the amount to add
     * @param setPoint the amount to not cross
     * @return the resulting number
     */
    public static float addToSetPoint(float current, float positiveChange, float setPoint) {
        if (current < setPoint) {
            current = current + positiveChange;
        }
        return current;
    }


    public static double calculateMotionX(float speed, float rotationYaw) {
        return Mth.sin(-rotationYaw * 0.017453292F) * speed;
    }

    public static double calculateMotionZ(float speed, float rotationYaw) {
        return Mth.cos(rotationYaw * 0.017453292F) * speed;
    }

    public static float getKilometerPerHour(float speed) {
        return (speed * 20 * 60 * 60) / 1000;
    }
    public static float getKnots(float speed) {
        return (getKilometerPerHour(speed)) / 1.852F;
    }
    public static float getMeterPerSecond(float speed) {
        return (getKilometerPerHour(speed)) / 3.6F;
    }

    public static float getMilesPerHour(float speed) {
        return (getKilometerPerHour(speed)) / 1.609F;
    }


}
