package com.talhanation.siegeweapons;

import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.entities.TransportCartEntity;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import com.talhanation.siegeweapons.world.SiegeTableRecipe;
import net.minecraft.client.Minecraft;

public enum SiegeWeapons {
    CATAPULT(0, new CatapultEntity(ModEntityTypes.CATAPULT.get(), Minecraft.getInstance().level), SiegeTableRecipe.CATAPULT_RECIPE),
    TRANSPORT_CART(1, new TransportCartEntity(ModEntityTypes.TRANSPORT_CART.get(), Minecraft.getInstance().level), SiegeTableRecipe.TRANSPORT_CART_RECIPE);

    private static final SiegeWeapons[] VALUES = values();
    private final AbstractVehicleEntity entity;
    private final int index;
    private final SiegeTableRecipe siegeTableRecipe;
    SiegeWeapons(int index, AbstractVehicleEntity entity, SiegeTableRecipe siegeTableRecipe) {
        this.index = index;
        this.entity = entity;
        this.siegeTableRecipe = siegeTableRecipe;
    }

    public AbstractVehicleEntity getEntity() {
        return entity;
    }

    public int getIndex() {
        return index;
    }

    public SiegeTableRecipe getRecipe() {
        return siegeTableRecipe;
    }

    public SiegeWeapons getNext() {
        return VALUES[(this.ordinal() + 1) % VALUES.length];
    }

    public SiegeWeapons getBefore() {
        return VALUES[(this.ordinal() - 1 + VALUES.length) % VALUES.length];
    }
}
