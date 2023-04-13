package cc.dreamcode.blocks.config;

import cc.dreamcode.menu.bukkit.BukkitMenuBuilder;
import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import cc.dreamcode.platform.persistence.StorageConfig;
import cc.dreamcode.utilities.builder.MapBuilder;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import org.bukkit.inventory.ItemStack;

@Configuration(
        child = "config.yml"
)
@Header("## Dream-Blocks (Main-Config) ##")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfig extends OkaeriConfig {
    @Comment("Debug pokazuje dodatkowe informacje do konsoli. Lepiej wylaczyc. :P")
    public boolean debug = true;

    @Comment("Jak ma wygladac menu od blokow?")
    public BukkitMenuBuilder blockMenuBuilder = new BukkitMenuBuilder("Dream-Blocks", 6, true, new MapBuilder<Integer, ItemStack>()
            .build());

    @Comment({"Gdzie ma znajdowac sie item ktory bedzie odpowiadal za wymiane zelaza"})
    public int ironExchangeSlot = 10;

    @Comment({"Jak ma wygladac item, ktory bedzie reprezentowal itemek do wymiany zelaza"})
    public ItemStack ironExchange = new ItemBuilder(XMaterial.IRON_BLOCK.parseItem())
            .setName("&6&lZelazo")
            .setLore(
                    "&8Kliknij &4LPM &8aby wymienic na sztabki",
                    "&8Kliknij &4PPM &8aby wymienic na bloki")
            .toItemStack();

}
