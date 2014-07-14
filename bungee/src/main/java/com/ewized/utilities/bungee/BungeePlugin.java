package com.ewized.utilities.bungee;

import com.ewized.utilities.bungee.util.BungeeLocale;
import com.ewized.utilities.bungee.util.MessageUtil;
import com.ewized.utilities.core.message.MessageManager;
import com.ewized.utilities.core.util.LogUtil;
import com.sk89q.bungee.util.BungeeCommandsManager;
import com.sk89q.bungee.util.CommandExecutor;
import com.sk89q.bungee.util.CommandRegistration;
import com.sk89q.minecraft.util.commands.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a wrapper for BungeeCord plugins, this will allow for
 * quick and creation of common tasks.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class BungeePlugin extends Plugin implements CommandExecutor<CommandSender> {
    private static BungeePlugin inst;
    private final BungeeCommandsManager commands = new BungeeCommandsManager();
    public boolean debug = Boolean.parseBoolean(System.getProperty("debug"));
    protected final LogUtil log = new LogUtil(getLogger(), debug);

    /** Get the instance of this plugin */
    public static BungeePlugin get() {
        if (inst == null) {
            inst = new BungeePlugin();
        }

        return inst;
    }

    /** Set the new debug status of this plugin */
    public void setDebug(boolean debug) {
        this.debug = debug;
        this.log.setDebug(debug);
    }

    // Command Stuff //



    /** Register a command for this plugin */
    public void registerCommand(Class<?> commandClass) {
        new CommandRegistration(this, getProxy().getPluginManager(), commands, this).register(commandClass);
    }

    @Override
    public void onCommand(CommandSender sender, String commandName, String[] args) {
        List<BaseComponent[]> msg = new ArrayList<>();

        BungeeLocale locale = new BungeeLocale(sender instanceof ProxiedPlayer ? (ProxiedPlayer) sender : null) {{
            localeManager = MessageManager.get();
        }};

        try {
            commands.execute(commandName, args, sender, sender);
        } catch (CommandPermissionsException e) {
            msg.add(MessageUtil.makeMessage(locale.get("error.cmd.permission")));
        } catch (MissingNestedCommandException e) {
            msg.add(MessageUtil.makeMessage(locale.get("error.cmd.usage", e.getUsage())));
        } catch (CommandUsageException e) {
            msg.add(MessageUtil.makeMessage("&c" + e.getMessage()));
            msg.add(MessageUtil.makeMessage(locale.get("error.cmd.usage", e.getUsage())));
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                msg.add(MessageUtil.makeMessage(locale.get("error.cmd.number")));
            }
            else {
                msg.add(MessageUtil.makeMessage(locale.get("error.cmd.error")));
                e.printStackTrace();
            }
        } catch (CommandException e) {
            msg.add(MessageUtil.makeMessage("&c" + e.getMessage()));
        } finally {
            Iterator<BaseComponent[]> line = msg.listIterator();

            if (line.hasNext()) {
                sender.sendMessage(MessageUtil.merge(" &7[&e!&7] ", line.next()));
                while (line.hasNext()) {
                    sender.sendMessage(line.next());
                }
            }
        }
    }

    // Logger Stuff //

    /** Logs a message to the console */
    public static void log(String message, Object... args) {
        get().log.debug(message, args);
    }

    /** Logs a debug message to the console */
    public static void debug(String message, Object... args) {
        get().log.debug(message, args);
    }

    /** Print out the stack trace */
    public static void debug(Exception e, boolean simple) {
        get().log.debug(e, simple);
    }

    /** Print out the stack trace */
    public static void log(Exception e, boolean simple) {
        get().log.log(e, simple);
    }
}
