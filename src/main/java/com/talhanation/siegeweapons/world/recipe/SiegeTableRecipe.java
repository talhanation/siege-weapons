package com.talhanation.siegeweapons.world.recipe;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.Map;
public class SiegeTableRecipe {
    //Max 7 entries per recipe
    public static final SiegeTableRecipe CATAPULT_RECIPE = new SiegeTableRecipe(Map.of(
            Items.OAK_LOG, 16,
            Items.OAK_PLANKS, 64,
            Items.STRING, 32,
            Items.LEATHER, 16,
            Items.LEAD, 4,
            Items.IRON_INGOT, 16,
            Items.IRON_NUGGET, 8),
            8500
    );

    public static final SiegeTableRecipe BALLISTA_RECIPE = new SiegeTableRecipe(Map.of(
            Items.OAK_PLANKS, 16,
            Items.STRING, 16,
            Items.LEAD, 4,
            Items.IRON_INGOT, 16,
            Items.IRON_NUGGET, 8),
            4000
    );

    public static final SiegeTableRecipe BALLISTA_BOLT = new SiegeTableRecipe(Map.of(
            Items.OAK_PLANKS, 3,
            Items.FEATHER, 1,
            Items.IRON_NUGGET, 5),
            10
    );

    public static final SiegeTableRecipe FIRE_POT = new SiegeTableRecipe(Map.of(
            Items.DECORATED_POT, 1,
            Items.GUNPOWDER, 1,
            Items.WHEAT, 3,
            Items.FIRE_CHARGE, 1),
            30
    );

    public static final SiegeTableRecipe EXPLOSION_POT = new SiegeTableRecipe(Map.of(
            Items.DECORATED_POT, 1,
            Items.GUNPOWDER, 5,
            Items.SAND, 4),
            30
    );

    public static final SiegeTableRecipe COBBLE_CLUSTER = new SiegeTableRecipe(Map.of(
            Items.COBBLESTONE, 3),
            10
    );

    public static final SiegeTableRecipe TRANSPORT_CART_RECIPE = new SiegeTableRecipe(Map.of(
            Items.CHEST, 2,
            Items.OAK_LOG, 8,
            Items.OAK_PLANKS, 32,
            Items.LEAD, 4,
            Items.IRON_NUGGET, 16),
            5500
    );

    public static final SiegeTableRecipe CART_RECIPE = new SiegeTableRecipe(Map.of(
            Items.CHEST, 1,
            Items.OAK_LOG, 4,
            Items.OAK_PLANKS, 16,
            Items.LEAD, 1,
            Items.IRON_NUGGET, 16),
            1500
    );


    private final Map<Item, Integer> requiredMaterials;
    private final int time;

    public SiegeTableRecipe(Map<Item, Integer> requiredMaterials, int time) {
        this.requiredMaterials = requiredMaterials;
        this.time = time;
    }

    public int getCraftingTime(){
        return time;
    }
    public Map<Item, Integer> getRequiredMaterials() {
        return requiredMaterials;
    }

    public boolean hasRequiredMaterials(Inventory inventory) {
        for (Map.Entry<Item, Integer> entry : requiredMaterials.entrySet()) {
            if (countItemInInventory(inventory, entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean consumeMaterials(Inventory inventory) {
        if (!hasRequiredMaterials(inventory)) {
            return false;
        }

        for (Map.Entry<Item, Integer> entry : requiredMaterials.entrySet()) {
            removeItemsFromInventory(inventory, entry.getKey(), entry.getValue());
        }

        return true;
    }

    private int countItemInInventory(Inventory inventory, Item item) {
        int count = 0;
        for (ItemStack stack : inventory.items) {
            if (stack.getItem() == item) {
                count += stack.getCount();
            }
        }
        return count;
    }

    private void removeItemsFromInventory(Inventory inventory, Item item, int amount) {
        for (int i = 0; i < inventory.items.size(); i++) {
            ItemStack stack = inventory.items.get(i);
            if (stack.getItem() == item) {
                int toRemove = Math.min(stack.getCount(), amount);
                stack.shrink(toRemove);
                amount -= toRemove;

                if (stack.isEmpty()) {
                    inventory.items.set(i, ItemStack.EMPTY);
                }

                if (amount <= 0) {
                    return;
                }
            }
        }
    }
}