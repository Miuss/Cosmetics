package me.genesismiuss.cosmetics;

import me.genesismiuss.cosmetics.api.ArmorStands;
import me.genesismiuss.cosmetics.listeners.EntityListener;
import me.genesismiuss.cosmetics.listeners.PlayerListener;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public final class Cosmetics extends JavaPlugin {

    private Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        logger.info("[Cosmetics] Cosmetics 插件已载入");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //卸载所有在线玩家装饰品盔甲架
        for(Map.Entry<UUID, ArmorStands> en: PlayerListener.playerstands.entrySet()) {
            en.getValue().getArmor().remove();
        }
        logger.info("[Cosmetics] Cosmetics 插件卸载");
    }
}
