package cc.dreamcode.blocks.service;

import cc.dreamcode.blocks.BlocksPlugin;
import cc.dreamcode.blocks.menu.BlocksMenu;
import cc.dreamcode.menu.bukkit.base.BukkitMenu;
import eu.okaeri.injector.annotation.Inject;
import lombok.NonNull;
import org.bukkit.entity.HumanEntity;

public class BlocksService {

    public @Inject BlocksPlugin blocksPlugin;
    public BukkitMenu bukkitMenu;


    public void setup() {
        this.bukkitMenu = this.blocksPlugin.createInstance(BlocksMenu.class).build();
    }

    public void open(@NonNull HumanEntity humanEntity) {
        if (this.bukkitMenu == null) {
            this.setup();
        }

        this.bukkitMenu.open(humanEntity);
    }

}

