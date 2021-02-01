package me.t3sl4.vip.util;

import java.util.ArrayList;
import java.util.Iterator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GuiManager {
   Inventory inv;
   static SettingsManager manager = SettingsManager.getInstance();
   PlayerUtil putil = new PlayerUtil();
   Material mat = XMaterial.SKELETON_SKULL.parseMaterial();

   public GuiManager(Player p) {
      this.inv = Bukkit.createInventory((InventoryHolder)null, InventoryType.HOPPER, MessageUtil.TITLE);
      ItemStack skull = new ItemStack(mat, 1, (short)SkullType.PLAYER.ordinal());
      SkullMeta meta = (SkullMeta)skull.getItemMeta();
      meta.setOwner(p.getName());
      meta.setDisplayName(ChatColor.AQUA.toString() + p.getName());
      ArrayList<String> outLore = new ArrayList();
      Iterator var5 = MessageUtil.ITEMLORE.iterator();

      while(var5.hasNext()) {
         String s = (String)var5.next();
         outLore.add(MessageUtil.colorize(s.replace("%kalan%", String.valueOf(this.putil.getRemainingTime(p))).replace("%bitis%", this.putil.getBitis(p)).replace("%rank%", this.putil.getRank(p))));
      }

      meta.setLore(outLore);
      skull.setItemMeta(meta);
      this.inv.setItem(2, skull);
      p.openInventory(this.inv);
   }
}
