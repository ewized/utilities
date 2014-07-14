package com.ewized.utilities.bukkit;

import com.ewized.utilities.bukkit.util.BukkitLocale;
import com.ewized.utilities.bukkit.util.MessageUtil;
import com.ewized.utilities.core.message.MessageManager;
import com.ewized.utilities.core.util.LogUtil;
import com.sk89q.bukkit.util.BukkitCommandsManager;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a wrapper for Bukkit plugins, this will allow for
 * quick and creation of common tasks.
 */
@SuppressWarnings("unused")
@NoArgsConstructor
public class BukkitPlugin extends JavaPlugin {
    private static BukkitPlugin inst;
    @Getter
    private final BukkitCommandsManager commands = new BukkitCommandsManager();
    @Getter
    public boolean debug = Boolean.parseBoolean(System.getProperty("debug"));
    @Getter
    protected final LogUtil log = new LogUtil(getLogger(), debug);

    /** Get the instance of this plugin */
    public static BukkitPlugin get() {
        if (inst == null) {
            inst = new BukkitPlugin();
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
        new CommandsManagerRegistration(this, commands).register(commandClass);
    }

    @SuppressWarnings("unchecked")
    public boolean onCommand(CommandSender sender, Command cmd, String commandName, String[] args) {
        List<String> msg = new ArrayList<>();

        BukkitLocale locale = new BukkitLocale(sender instanceof Player ? (Player) sender : null) {{
            localeManager = MessageManager.get();
            locale = DEFAULT_LOCALE; // TODO Add real locale
        }};

        try {
            commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            msg.add(MessageUtil.message(locale.get("error.cmd.permission")));
        } catch (MissingNestedCommandException e) {
            msg.add(MessageUtil.message(locale.get("error.cmd.usage", e.getUsage())));
        } catch (CommandUsageException e) {
            msg.add(MessageUtil.message("&c " + e.getMessage()));
            msg.add(MessageUtil.message(locale.get("error.cmd.usage", e.getUsage())));
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                msg.add(MessageUtil.message(locale.get("error.cmd.number")));
            }
            else {
                msg.add(MessageUtil.message(locale.get("error.cmd.error")));
                e.printStackTrace();
            }
        } catch (CommandException e) {
            msg.add(MessageUtil.message("&c" + e.getMessage()));
        } finally {
            Iterator<String> line = msg.listIterator();
            if (line.hasNext()) {
                sender.sendMessage(MessageUtil.message(" &7[&e!&7] ", line.next()));
                while (line.hasNext()) {
                    sender.sendMessage(MessageUtil.message(line.next()));
                }
            }
        }

        return true;
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
