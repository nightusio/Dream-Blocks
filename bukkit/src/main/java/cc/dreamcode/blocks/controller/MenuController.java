package cc.dreamcode.blocks.controller;

import cc.dreamcode.blocks.config.PluginConfig;
import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.injector.annotation.Inject;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuController implements Listener {

    public @Inject PluginConfig pluginConfig;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent e) {
        InventoryAction action = e.getAction();
        int slot = e.getSlot();
        Player player = (Player) e.getWhoClicked();
        Inventory clickedInventory = e.getClickedInventory();

        if (clickedInventory != null && clickedInventory.getTitle().equalsIgnoreCase(pluginConfig.blockMenuBuilder.getName())) {
            if (slot == pluginConfig.ironExchangeSlot) {
                if(action.equals(Action.LEFT_CLICK_BLOCK)) {
                    replaceBlockInInventory(player, XMaterial.IRON_INGOT.parseMaterial(), 9, XMaterial.IRON_BLOCK.parseMaterial(), 1);
                } else {
                    replaceBlockInInventory(player, XMaterial.IRON_BLOCK.parseMaterial(), 1, XMaterial.IRON_INGOT.parseMaterial(), 9);

                }
            }
        }
    }

    public void replaceBlockInInventory(Player player, Material blockType, int inputQuantity, Material replacementBlockType, int replacementQuantity) {
        int replacedBlocks = 0;
        int replacedItems = 0;

        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.getType() == blockType && itemStack.getAmount() == inputQuantity) {
                // Remove the input block from the inventory
                player.getInventory().removeItem(itemStack);
                replacedBlocks++;

                // Add the replacement block to the inventory
                ItemStack replacementStack = new ItemStack(replacementBlockType, replacementQuantity);
                player.getInventory().addItem(replacementStack);
                replacedItems += replacementQuantity;
            }
        }
    }
}
