package com.ewized.utilities.bungee.util;

import com.sk89q.bungee.util.*;
import com.sk89q.minecraft.util.commands.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class CommandWrapper implements CommandExecutor<CommandSender> {
    private BungeeCommandsManager commands;

    public CommandWrapper(Plugin plugin, Class<?> clazz) {
        commands = new BungeeCommandsManager();
        new CommandRegistration(
            plugin,
            plugin.getProxy().getPluginManager(),
            commands, this
        ).register(clazz);
    }

    @Override
    public void onCommand(CommandSender sender, String commandName, String[] args) {
        List<BaseComponent[]> msg = new ArrayList<>();
        try {
            commands.execute(commandName, args, sender, sender);
        } catch (CommandPermissionsException e) {
            msg.add(MessageUtil.makeMessage("&cYou don't have permission."));
        } catch (MissingNestedCommandException e) {
            msg.add(MessageUtil.makeMessage("&6Usage&7: &c" + e.getUsage()));
        } catch (CommandUsageException e) {
            msg.add(MessageUtil.makeMessage("&c" + e.getMessage()));
            msg.add(MessageUtil.makeMessage("&6Usage&7: &c" + e.getUsage()));
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                msg.add(MessageUtil.makeMessage("&cNumber expected, string received instead."));
            }
            else {
                msg.add(MessageUtil.makeMessage("&cAn error has occurred. See console."));
                e.printStackTrace();
            }
        } catch (CommandException e) {
            msg.add(MessageUtil.makeMessage("&c" + e.getMessage()));
        } finally {
            if (!msg.isEmpty()) {
                boolean first = true;
                for (BaseComponent[] line : msg) {
                    sender.sendMessage(MessageUtil.merge((first ? " &7[&e⚠&7] " : ""), line));
                    first = false;
                }
            }
        }
    }
}
