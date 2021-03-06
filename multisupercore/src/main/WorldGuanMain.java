package main;

import com.sun.istack.internal.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class WorldGuanMain extends JavaPlugin  implements Listener{
    public static boolean monsterBool = true;
    public static boolean playerBool = true;
    @Nullable
    public HashSet<ItemStack> noItems;

    @Override
    public void onEnable() {
        this.getCommand("worldguan").setExecutor(new Command());
        Bukkit.getServer().broadcastMessage("세계관 플러그인 활성화됨");
        getServer().getPluginManager().registerEvents(this, this);;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (playerBool) {
                    giveEffect((List<Player>) Bukkit.getServer().getOnlinePlayers());
                }
                if (monsterBool) {
                    giveEffect();
                }
            }
        }.runTaskTimer(this, 20, 20);
    }







    public void onDisable() {
        Bukkit.getServer().broadcastMessage("세계관 플러그인 비활성화됨");
    }


    public void giveEffect(List<Player> players){
        for (Player player : players) {
            Set<String> playerTags = player.getScoreboardTags();
            if (playerTags.contains("power")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1));
            } else if (playerTags.contains("smart")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 1));
            } else {
            }
        }
    }

    public void giveEffect() {
        List<LivingEntity> monsters = Bukkit.getWorld("world").getLivingEntities();
        for (LivingEntity entity: monsters) {
            if (!(entity instanceof Player)){
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
                if (entity instanceof Monster) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 2));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 2));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 40, 2));
                    Monster monster = (Monster) entity;
                }
            }
        }
        monsters = Bukkit.getWorld("world_nether").getLivingEntities();
        for (LivingEntity entity: monsters) {
            if (!(entity instanceof Player)){
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
                if (entity instanceof Monster) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 2));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 2));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 40, 2));
                }
            }
        }
        monsters = Bukkit.getWorld("world_the_end").getLivingEntities();
        for (LivingEntity entity: monsters) {
            if (!(entity instanceof Player)){
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
                if (entity instanceof Monster) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 2));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 2));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 40, 2));
                }
            }
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (playerBool) {
            HumanEntity crafter = event.getWhoClicked();
            Set<String> crafterTags = crafter.getScoreboardTags();
            if (crafterTags.contains("power")) {
                crafter.sendMessage("당신은 힘캐여서 능지가 딸려 제작을 할 수 없습니다..");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event){
        event.setCancelled(true);
        event.getPlayer().sendMessage("이 서버에선 잘 수 없습니다!");
    }
}
