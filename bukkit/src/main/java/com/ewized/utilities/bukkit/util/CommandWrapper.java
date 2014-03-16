package com.ewized.utilities.bukkit.util;

import com.sk89q.bukkit.util.BukkitCommandsManager;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandWrapper extends JavaPlugin {
    private CommandsManager commands;

    public CommandWrapper(final JavaPlugin plugin, Class<?> clazz) {
        commands = new BukkitCommandsManager();
        new CommandsManagerRegistration(plugin, commands).register(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandName, String[] args) {
        String msg = null;
        try {
            commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            msg = MessageUtil.replaceColors("&cYou don't have permission.");
        } catch (MissingNestedCommandException e) {
            msg = MessageUtil.replaceColors("&c" + e.getUsage());
        } catch (CommandUsageException e) {
            msg = MessageUtil.replaceColors("&c" + e.getMessage());
            msg = MessageUtil.replaceColors("&c" + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                msg = MessageUtil.replaceColors("&cNumber expected, string received instead.");
            }
            else {
                msg = MessageUtil.replaceColors("&cAn error has occurred. See console.");
                e.printStackTrace();
            }
        } catch (CommandException e) {
            msg = MessageUtil.replaceColors("&c" + e.getMessage());
        } finally {
            if (msg != null)
                sender.sendMessage(msg);
        }

        return true;
    }
}
