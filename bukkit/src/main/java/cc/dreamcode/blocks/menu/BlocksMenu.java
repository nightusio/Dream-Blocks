package cc.dreamcode.blocks.menu;

import cc.dreamcode.blocks.config.PluginConfig;
import cc.dreamcode.menu.bukkit.BukkitMenuBuilder;
import cc.dreamcode.menu.bukkit.base.BukkitMenu;
import cc.dreamcode.menu.bukkit.setup.BukkitMenuSetup;
import eu.okaeri.injector.annotation.Inject;

public class BlocksMenu implements BukkitMenuSetup {

    private @Inject PluginConfig pluginConfig;

    @Override
    public BukkitMenu build() {

        final BukkitMenuBuilder menuBuilder = pluginConfig.blockMenuBuilder;
        final BukkitMenu bukkitMenu = menuBuilder.buildWithItems();





        return bukkitMenu;
    }


}