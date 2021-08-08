package me.genesismiuss.cosmetics.listeners;

import me.genesismiuss.cosmetics.api.ArmorStands;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class EntityListener implements Listener {

    /**
     * 修复盔甲架下水BUG
     * @param e
     */
    @EventHandler
    public void dismountPlayer(EntityDismountEvent e) {
        Entity en = e.getDismounted();
        ArmorStands armor = PlayerListener.playerstands.get(en.getUniqueId());
        if(armor != null && en.isInWater()) {
            e.setCancelled(true);
        }

    }

}
