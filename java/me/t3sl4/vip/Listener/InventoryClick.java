package me.t3sl4.vip.Listener;

import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {
   String ver = Bukkit.getVersion();

   @EventHandler
   public void onClick(InventoryClickEvent e) {
      Player p = (Player)e.getWhoClicked();
      Inventory inv = e.getClickedInventory();
      String invTitle = e.getView().getTitle();
      Material mat = XMaterial.SKELETON_SKULL.parseMaterial();
      if (inv != null && invTitle.contains(MessageUtil.TITLE)) {
         if (e.getCurrentItem() == null) {
            return;
         }

         if (e.getCurrentItem().getType() == mat) {
            e.setCancelled(true);
            p.closeInventory();
         }
      }

   }
}
