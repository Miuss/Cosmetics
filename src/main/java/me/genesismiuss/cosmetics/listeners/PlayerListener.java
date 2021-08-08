package me.genesismiuss.cosmetics.listeners;

import dev.lone.itemsadder.api.CustomStack;
import me.genesismiuss.cosmetics.api.ArmorStands;
import me.genesismiuss.cosmetics.utils.ChatFormat;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;

public class PlayerListener implements Listener {

    public static HashMap<UUID, ArmorStands> playerstands = new HashMap<>();

    @EventHandler
    public boolean onPlayerJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();
        p.sendMessage(ChatFormat.t("&e欢迎"+p.getName()+"来到McRealms服务器！1"));

        CustomStack stack = CustomStack.getInstance("bedrock");
        if(stack != null)
        {
            ItemStack itemStack = stack.getItemStack();
            ArmorStands en = new ArmorStands(p,itemStack);
            playerstands.put(p.getUniqueId(),en);
        }

        return true;

    }

    @EventHandler
    public boolean onPlayerQuit(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        ArmorStands en = playerstands.get(p.getUniqueId());
        if(en != null) {
            en.getArmor().remove();
            playerstands.remove(p.getUniqueId());
        }

        return true;

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        ArmorStands en = playerstands.get(p.getUniqueId());

        Block block = p.getWorld().getBlockAt(p.getLocation());
        String netherX = "minecraft:nether_portal[axis=x]";
        String netherZ = "minecraft:nether_portal[axis=z]";
        String end = "minecraft:end_portal";

        String locData = block.getBlockData().getAsString(true);
        if(netherX.equals(locData) || netherZ.equals(locData) ||end.equals(locData)) {
            if(en != null) {
                en.getArmor().remove();
                playerstands.remove(p.getUniqueId());
            }
        }else{
            if(en != null) {
                en.getArmor().setRotation(p.getLocation().getYaw(), p.getLocation().getPitch());


            }else {
                CustomStack stack = CustomStack.getInstance("bedrock");
                if(stack != null)
                {
                    ItemStack itemStack = stack.getItemStack();
                    ArmorStands newArmor = new ArmorStands(p,itemStack);
                    playerstands.put(p.getUniqueId(),newArmor);
                }
            }
        }

    }

    @EventHandler
    public void onTeleport(EntityTeleportEvent e) {

        if(e.getEntityType() == EntityType.PLAYER && !e.isCancelled()) {

            System.out.println("TP");

            Player p = (Player) e.getEntity();

            CustomStack stack = CustomStack.getInstance("bedrock");
            if(stack != null)
            {
                ItemStack itemStack = stack.getItemStack();
                ArmorStands en = new ArmorStands(p,itemStack);
                playerstands.put(p.getUniqueId(),en);
            }
        }

    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {

        Player p = e.getPlayer();

        CustomStack stack = CustomStack.getInstance("bedrock");
        if(stack != null)
        {
            ItemStack itemStack = stack.getItemStack();
            ArmorStands en = new ArmorStands(p,itemStack);
            playerstands.put(p.getUniqueId(),en);
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        ArmorStands en = playerstands.get(p.getUniqueId());
        if(en != null) {
            en.getArmor().remove();
            playerstands.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {

        Player p = e.getPlayer();

        ArmorStands en = playerstands.get(p.getUniqueId());
        if(en != null) {
            en.getArmor().remove();
            playerstands.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent e) {

        Player p = e.getPlayer();

        if(!e.isCancelled()) {

            ArmorStands en = playerstands.get(p.getUniqueId());
            if(en != null) {
                en.getArmor().remove();
                playerstands.remove(p.getUniqueId());
            }

        }

    }


    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent e) {

        Player p = e.getPlayer();

        CustomStack stack = CustomStack.getInstance("bedrock");
        if(stack != null)
        {
            ItemStack itemStack = stack.getItemStack();
            ArmorStands en = new ArmorStands(p,itemStack);
            playerstands.put(p.getUniqueId(),en);
        }

    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onSwiming(EntityToggleSwimEvent e) {
        if(e.getEntityType()==EntityType.PLAYER) {
            Player p = (Player)e.getEntity();

            ArmorStands en = playerstands.get(p.getUniqueId());
            if(en!=null) {
                if(e.isSwimming()) {
                    en.getArmor().setHelmet(null);
                }else {
                    en.getArmor().setHelmet(en.getCosmetics());
                }
            }
        }
    }


    @EventHandler
    public void onPreCommand(PlayerCommandPreprocessEvent e) {


    }

}
