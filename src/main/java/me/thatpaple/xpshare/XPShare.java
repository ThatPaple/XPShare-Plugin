package me.thatpaple.xpshare;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class XPShare extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginCommand("share").setExecutor(new XPShare());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 2){
            Player owner = (Player) sender;

            for (int i = 0; i < args.length; i++) {
                owner.sendMessage(i + " : " +args[i]);
            }
            Player reciever = Bukkit.getPlayer(args[0]);

            int origXP = owner.getLevel();

            try{
                int sentXP = Integer.parseInt(args[1].toString());

                if(sentXP > origXP) {
                    owner.sendMessage("You are generous, though you do not possess enough XP to do this! (trying to send " + sentXP + "xp but you have " + origXP + "xp)");
                    return true;
                }

                owner.setLevel(origXP - sentXP);
                owner.sendMessage("You have sent " + reciever.getDisplayName() + " " + sentXP + "xp");
                reciever.setLevel(reciever.getLevel() + sentXP);
                reciever.sendMessage("You have received "+ sentXP + "xp from " + owner.getDisplayName());

            }catch (NumberFormatException e){
                owner.sendMessage("You have to provide a number as your second argument!");
                return false;
            }
            return true;
        }
        return false;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
