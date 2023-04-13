package cc.dreamcode.blocks;

import cc.dreamcode.blocks.command.BlocksCommand;
import cc.dreamcode.blocks.config.PluginConfig;
import cc.dreamcode.blocks.controller.MenuController;
import cc.dreamcode.blocks.service.BlocksService;
import cc.dreamcode.command.bukkit.BukkitCommandProvider;
import cc.dreamcode.menu.bukkit.BukkitMenuProvider;
import cc.dreamcode.menu.bukkit.okaeri.MenuBuilderSerdes;
import cc.dreamcode.notice.bukkit.BukkitNoticeProvider;
import cc.dreamcode.notice.bukkit.okaeri_serdes.BukkitNoticeSerdes;
import cc.dreamcode.platform.DreamVersion;
import cc.dreamcode.platform.bukkit.DreamBukkitPlatform;
import cc.dreamcode.platform.bukkit.component.CommandComponentResolver;
import cc.dreamcode.platform.bukkit.component.ConfigurationComponentResolver;
import cc.dreamcode.platform.bukkit.component.ListenerComponentResolver;
import cc.dreamcode.platform.bukkit.component.RunnableComponentResolver;
import cc.dreamcode.platform.component.ComponentManager;
import cc.dreamcode.blocks.config.MessageConfig;

import cc.dreamcode.platform.persistence.resolver.DocumentPersistenceComponentResolver;
import cc.dreamcode.platform.persistence.resolver.DocumentRepositoryComponentResolver;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.tasker.bukkit.BukkitTasker;
import lombok.Getter;
import lombok.NonNull;

public final class BlocksPlugin extends DreamBukkitPlatform {

    @Getter private static BlocksPlugin blocksPlugin;

    @Override
    public void load(@NonNull ComponentManager componentManager) {
        blocksPlugin = this;
    }

    @Override
    public void enable(@NonNull ComponentManager componentManager) {
        this.registerInjectable(BukkitTasker.newPool(this));
        this.registerInjectable(BukkitMenuProvider.create(this));
        this.registerInjectable(BukkitNoticeProvider.create(this));
        this.registerInjectable(BukkitCommandProvider.create(this, this.getInjector()));

        componentManager.registerResolver(CommandComponentResolver.class);
        componentManager.registerResolver(ListenerComponentResolver.class);
        componentManager.registerResolver(RunnableComponentResolver.class);

        componentManager.registerResolver(ConfigurationComponentResolver.class);
        componentManager.registerComponent(MessageConfig.class, messageConfig ->
                this.getInject(BukkitCommandProvider.class).ifPresent(bukkitCommandProvider -> {
                    bukkitCommandProvider.setRequiredPermissionMessage(messageConfig.noPermission);
                    bukkitCommandProvider.setRequiredPlayerMessage(messageConfig.notPlayer);
                }));
        componentManager.registerComponent(PluginConfig.class, (pluginConfig) -> {
            componentManager.registerResolver(DocumentPersistenceComponentResolver.class);
            componentManager.registerResolver(DocumentRepositoryComponentResolver.class);
        });

        componentManager.registerComponent(BlocksService.class, BlocksService::setup);
        componentManager.registerComponent(MenuController.class);
        componentManager.registerComponent(BlocksCommand.class);
    }

    @Override
    public void disable() {
    }

    @Override
    public @NonNull DreamVersion getDreamVersion() {
        return DreamVersion.create("Dream-Blocks", "1.0", "Nightusio");
    }

    @Override
    public @NonNull OkaeriSerdesPack getBukkitConfigurationSerdesPack() {
        return registry -> {
            registry.register(new BukkitNoticeSerdes());
            registry.register(new MenuBuilderSerdes());
        };
    }

    @Override
    public @NonNull OkaeriSerdesPack getBukkitPersistenceSerdesPack() {
        return registry -> {

        };
    }

}
