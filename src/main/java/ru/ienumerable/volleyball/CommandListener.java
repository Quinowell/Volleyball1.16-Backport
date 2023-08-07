package ru.ienumerable.volleyball;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.ienumerable.volleyball.skin.SkinChanger;
import ru.ienumerable.volleyball.skin.SkullSkin;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("ballskin")) {
                    ItemStack item = Bukkit.getPlayer(sender.getName()).getInventory().getItemInMainHand();

                    if (!item.hasItemMeta() || !SkullSkin.isContainSkin(item)) {
                        Bukkit.getPlayer(sender.getName()).sendMessage(ChatColor.RED + "Возьмите мяч в руки, чтобы сменить скин!");
                        return true;
                    }

                    SkinChanger skinChanger = new SkinChanger(Bukkit.getPlayer(sender.getName()), SkullSkin.getSkin(item));
                    skinChanger.openMenu();
                
            }
        if (command.getName().equalsIgnoreCase("ballreload")){
                Volleyball.getBallsContainer().dropAllBalls();
                Volleyball.loadConfig();
                sendMsgToOps(ChatColor.GREEN + "Презагружено!");
            }

        return true;
    }

    private void sendMsgToOps(String msg){

        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.isOp()) player.sendMessage(msg);
        }
    }
}
