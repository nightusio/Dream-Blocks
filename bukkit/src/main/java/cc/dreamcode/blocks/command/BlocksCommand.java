package cc.dreamcode.blocks.command;

import cc.dreamcode.blocks.service.BlocksService;
import cc.dreamcode.command.annotations.RequiredPlayer;
import cc.dreamcode.command.bukkit.BukkitCommand;
import eu.okaeri.injector.annotation.Inject;
import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

import java.util.List;

@RequiredPlayer
public class BlocksCommand extends BukkitCommand {

    public BlocksCommand() {
        super("bloki", "blocks");
    }

    public @Inject BlocksService blocksService;

    @Override
    public void content(@NonNull CommandSender sender, @NonNull String[] args) {
        this.blocksService.open((HumanEntity) sender);
    }

    @Override
    public List<String> tab(@NonNull CommandSender sender, @NonNull String[] args) {
        return null;
    }

}