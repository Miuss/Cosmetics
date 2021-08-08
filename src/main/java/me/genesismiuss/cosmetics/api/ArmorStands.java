package me.genesismiuss.cosmetics.api;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.UUID;

public class ArmorStands {

    private Player player;

    private String type;

    private  UUID uuid;

    private ArmorStand armor;

    private ItemStack cosmetics;

    public ArmorStands(Player player,ItemStack cosmetics) {
        this.player = player;
        this.type = "cos";
        this.uuid = UUID.randomUUID();
        this.armor = this.create(player, cosmetics);
        this.cosmetics = cosmetics;
    }

    /**
     * 生成盔甲架实体
     * @param p
     */
    @SuppressWarnings("deprecation")
    public ArmorStand create(Player p, ItemStack cosmetics) {
        ArmorStand armorstand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        armorstand.setInvisible(true);
        armorstand.setCollidable(false);
        armorstand.setInvulnerable(true);
        armorstand.setMarker(true);
        armorstand.setHelmet(cosmetics);

        p.addPassenger(armorstand);

        return armorstand;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ArmorStand getArmor() {
        return armor;
    }

    public void setArmor(ArmorStand armor) {
        this.armor = armor;
    }

    public ItemStack getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(ItemStack cosmetics) {
        this.cosmetics = cosmetics;
    }
}
