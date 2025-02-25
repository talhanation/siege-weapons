package com.talhanation.siegeweapons.world;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Map;
public class SiegeTableRecipe {

    public static final SiegeTableRecipe CATAPULT_RECIPE = new SiegeTableRecipe(Map.of(
            Items.OAK_LOG, 16,
            Items.OAK_PLANKS, 64,
            Items.STRING, 32,
            Items.LEAD, 4,
            Items.IRON_INGOT, 10,
            Items.IRON_NUGGET, 16
    ));


    public static final SiegeTableRecipe BALLISTA_RECIPE = new SiegeTableRecipe(Map.of(
            Items.OAK_PLANKS, 16,
            Items.STRING, 16,
            Items.LEAD, 4,
            Items.IRON_NUGGET, 16
    ));

    public static final SiegeTableRecipe TRANSPORT_CART_RECIPE = new SiegeTableRecipe(Map.of(
            Items.CHEST, 2,
            Items.OAK_LOG, 8,
            Items.OAK_PLANKS, 32,
            Items.LEAD, 4,
            Items.IRON_NUGGET, 16
    ));

    public static final SiegeTableRecipe CART_RECIPE = new SiegeTableRecipe(Map.of(
            Items.CHEST, 1,
            Items.OAK_LOG, 4,
            Items.OAK_PLANKS, 16,
            Items.LEAD, 1,
            Items.IRON_NUGGET, 16
    ));


    private final Map<Item, Integer> requiredMaterials;

    public SiegeTableRecipe(Map<Item, Integer> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
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
