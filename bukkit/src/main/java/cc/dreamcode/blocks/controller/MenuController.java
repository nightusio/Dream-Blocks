package cc.dreamcode.blocks.controller;

import cc.dreamcode.blocks.config.MessageConfig;
import cc.dreamcode.blocks.config.PluginConfig;
import cc.dreamcode.notice.bukkit.BukkitNotice;
import cc.dreamcode.utilities.bukkit.ChatUtil;
import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.injector.annotation.Inject;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuController implements Listener {

    public @Inject PluginConfig pluginConfig;
    public @Inject MessageConfig messageConfig;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent e) {
        InventoryAction action = e.getAction();
        int slot = e.getSlot();
        Player p = (Player) e.getWhoClicked();
        Inventory clickedInventory = e.getClickedInventory();

        if (clickedInventory != null && e.getView().getTitle().equalsIgnoreCase(pluginConfig.blockMenuBuilder.getName())) {
            if(slot == pluginConfig.ironExchangeSlot) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    convertItemStacks(p, new ItemStack(XMaterial.IRON_BLOCK.parseMaterial()), new ItemStack(XMaterial.IRON_INGOT.parseMaterial()), 1, 9, this.messageConfig.noIronToExchange, this.messageConfig.ironSuccessfullyExchanged);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    convertItemStacks(p, new ItemStack(XMaterial.IRON_INGOT.parseMaterial()), new ItemStack(XMaterial.IRON_BLOCK.parseMaterial()), 9, 1, this.messageConfig.noIronToExchange, this.messageConfig.ironSuccessfullyExchanged);
                }
            }
        }
    }

    public void convertItemStacks(Player p, ItemStack inputItem, ItemStack outputItem, int inputAmount, int outputAmount, BukkitNotice noItems, BukkitNotice itemsExchanged) {
        if (!p.getInventory().containsAtLeast(inputItem, inputAmount)) {
            noItems.send(p);
            return;
        }

        for (int x = 0; x < 256; ++x) {
            p.getInventory().removeItem(new ItemStack(inputItem.getType(), inputAmount));
            p.getInventory().addItem(new ItemStack(outputItem.getType(), outputAmount));
            itemsExchanged.send(p);
        }
    }
}
