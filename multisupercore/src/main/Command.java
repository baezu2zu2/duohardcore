package main;

import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("커맨드블럭은 안됩니다!");
            return true;
        }
        if (label.equals("worldguan")){
            if (args.length >= 2){
                if (args[0].equals("monster")){
                    if (args[1].equals("true")){
                        sender.sendMessage("몬스터들의 버프가 생겼습니다");
                        WorldGuanMain.monsterBool = true;
                    }else if (args[1].equals("false")){
                        sender.sendMessage("몬스터들의 버프가 없어졌습니다");
                        WorldGuanMain.monsterBool = false;
                    }
                }else if(args[0].equals("player")){
                    if (args[1].equals("true")){
                        sender.sendMessage("플레이어의 버프가 생겼습니다");
                        WorldGuanMain.playerBool = true;
                    }else if (args[1].equals("false")){
                        sender.sendMessage("플레이어의 버프가 없어졌습니다");
                        WorldGuanMain.playerBool = false;
                    }
                }
            }else{
                sender.sendMessage("worldGuan [monster/player] [true/false] 형식으로 입력해 주세요");
            }
        }
        return true;
    }
}
