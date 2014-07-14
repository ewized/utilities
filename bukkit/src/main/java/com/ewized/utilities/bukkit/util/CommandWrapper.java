package com.ewized.utilities.bukkit.util;

import com.sk89q.bukkit.util.BukkitCommandsManager;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Deprecated
public class CommandWrapper<T> {
    //private T t;
    private CommandsManager commands;

    public CommandWrapper(final T t, Class<?> clazz) {
        commands = new BukkitCommandsManager();
        new CommandsManagerRegistration((JavaPlugin)t, commands).register(clazz);
    }

    @SuppressWarnings("unchecked")
    public boolean onCommand(CommandSender sender, Command cmd, String commandName, String[] args) {
        List<String> msg = new ArrayList<>();
        try {
            commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            msg.add(MessageUtil.replaceColors("&cYou don't have permission to use this command."));
        } catch (MissingNestedCommandException e) {
            msg.add(MessageUtil.replaceColors("&6Usage&7: &c" + e.getUsage()));
        } catch (CommandUsageException e) {
            msg.add(MessageUtil.replaceColors("&c" + e.getMessage()));
            msg.add(MessageUtil.replaceColors("&6Usage&7: &c" + e.getUsage()));
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                msg.add(MessageUtil.replaceColors("&cNumber expected, string received instead."));
            }
            else {
                msg.add(MessageUtil.replaceColors("&cAn error has occurred. See console."));
                e.printStackTrace();
            }
        } catch (CommandException e) {
            msg.add(MessageUtil.replaceColors("&c" + e.getMessage()));
        } finally {
            if (!msg.isEmpty()) {
                boolean first = true;
                for (String line : msg) {
                    sender.sendMessage((first ? " &7[&e⚠&7] " : "") + line);
                    first = false;
                }
            }
        }

        return true;
    }
}
