package cc.dreamcode.blocks.menu;

import cc.dreamcode.blocks.config.PluginConfig;
import cc.dreamcode.menu.bukkit.BukkitMenuBuilder;
import cc.dreamcode.menu.bukkit.base.BukkitMenu;
import cc.dreamcode.menu.bukkit.setup.BukkitMenuSetup;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.inventory.ItemStack;

public class BlocksMenu implements BukkitMenuSetup {

    private @Inject PluginConfig pluginConfig;

    @Override
    public BukkitMenu build() {

        final BukkitMenuBuilder menuBuilder = pluginConfig.blockMenuBuilder;
        final BukkitMenu bukkitMenu = menuBuilder.buildWithItems();


        ItemStack ironExchange = new ItemBuilder(pluginConfig.ironExchange).toItemStack();
        bukkitMenu.setItem(pluginConfig.ironExchangeSlot, ironExchange);


        return bukkitMenu;
    }


}