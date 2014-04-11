package com.ewized.utilities.bungee.util;

import com.sk89q.bungee.util.BungeeCommandsManager;
import com.sk89q.bungee.util.CommandExecutor;
import com.sk89q.bungee.util.CommandRegistration;
import com.sk89q.minecraft.util.commands.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.plugin.Plugin;

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
        BaseComponent[] msg = null;
        try {
            commands.execute(commandName, args, sender, sender);
        } catch (CommandPermissionsException e) {
            msg = MessageUtil.makeMessage(" &7[&e⚠&7] &cYou don't have permission.");
        } catch (MissingNestedCommandException e) {
            msg = MessageUtil.makeMessage(" &7[&e⚠&7] &c" + e.getUsage());
        } catch (CommandUsageException e) {
            msg = MessageUtil.merge("&c" + e.getMessage(), " &6Usage&7: &c" + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                msg = MessageUtil.makeMessage("&cNumber expected, string received instead.");
            }
            else {
                msg = MessageUtil.makeMessage("&cAn error has occurred. See console.");
                e.printStackTrace();
            }
        } catch (CommandException e) {
            msg = MessageUtil.makeMessage("&c" + e.getMessage());
        } finally {
            if (msg != null) {
                sender.sendMessage(MessageUtil.merge(" &7[&e⚠&7] ", msg));
            }
        }
    }
}
