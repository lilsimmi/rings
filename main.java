package me.simmi.ringsv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class main extends JavaPlugin implements Listener
{
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.addRecipe(getspeedRingRecipe());
        Bukkit.addRecipe(getHasteRingRecipe());
        Bukkit.addRecipe(getNightVisionRingRecipe());
        Bukkit.addRecipe(getSatRingRecipe());
        Bukkit.addRecipe(getHealthRingRecipe());
        Bukkit.addRecipe(getHealth2RingRecipe());
        Bukkit.addRecipe(getJumpRingRecipe());
        Bukkit.addRecipe(getInvisRingRecipe());
        Bukkit.addRecipe(getStrengthRingRecipe());
        this.getCommand("givering").setTabCompleter(new tabList());
        

    }
   
    ItemStack speedRing = new ItemStack(Material.IRON_NUGGET); //done
    ItemMeta speedMeta = speedRing.getItemMeta();
    ItemStack hasteRing = new ItemStack(Material.GOLD_NUGGET); //done
    ItemMeta hasteMeta = hasteRing.getItemMeta();
    ItemStack visionRing = new ItemStack(Material.BLAZE_POWDER); //done
    ItemMeta visionMeta = visionRing.getItemMeta();
    ItemStack satRing = new ItemStack(Material.SLIME_BALL); //
    ItemMeta satMeta = satRing.getItemMeta();
    ItemStack healthRing = new ItemStack(Material.RED_DYE);
    ItemMeta healthMeta = healthRing.getItemMeta();
    ItemStack healthRing2 = new ItemStack(Material.CYAN_DYE);
    ItemMeta health2Meta = healthRing2.getItemMeta();
    ItemStack jumpRing = new ItemStack(Material.YELLOW_DYE);
    ItemMeta jumpMeta = jumpRing.getItemMeta();
    ItemStack invisRing = new ItemStack(Material.LIME_DYE);
    ItemMeta invisMeta = invisRing.getItemMeta();
    ItemStack strengthRing = new ItemStack(Material.PINK_DYE);
    ItemMeta strengthMeta = strengthRing.getItemMeta();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
    	speedMeta.setDisplayName(ChatColor.GOLD+"Speed II Ring");
        List<String> speedLore = new ArrayList<String>();
        speedLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        speedMeta.setLore(speedLore);
        speedRing.setItemMeta(speedMeta);

        hasteMeta.setDisplayName(ChatColor.GOLD+"Haste II Ring");
        List<String> hasteLore = new ArrayList<String>();
        hasteLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        hasteMeta.setLore(hasteLore);
        hasteRing.setItemMeta(hasteMeta);
       
        visionMeta.setDisplayName(ChatColor.GOLD+"Night Vision Ring");
        List<String> visionLore = new ArrayList<String>();
        visionLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        visionMeta.setLore(visionLore);
        visionRing.setItemMeta(visionMeta);


        satMeta.setDisplayName(ChatColor.GOLD+"Saturation Ring");
        List<String> satLore = new ArrayList<String>();
        satLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        satMeta.setLore(satLore);
        satRing.setItemMeta(satMeta);
        
        healthMeta.setDisplayName(ChatColor.GOLD+"Health Ring");
        List<String> healthLore = new ArrayList<String>();
        healthLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        healthMeta.setLore(healthLore);
        healthRing.setItemMeta(healthMeta); 
        
        health2Meta.setDisplayName(ChatColor.GOLD+"Health T2 Ring");
        health2Meta.setLore(healthLore);
        healthRing2.setItemMeta(health2Meta); 
        
        jumpMeta.setDisplayName(ChatColor.GOLD+"Jump Boost II Ring");
        List<String> jumpLore = new ArrayList<String>();
        jumpLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        jumpMeta.setLore(jumpLore);
        jumpRing.setItemMeta(jumpMeta); 
        
        invisMeta.setDisplayName(ChatColor.GOLD+"Invisibility Ring");
        List<String> invisLore = new ArrayList<String>();
        invisLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        invisMeta.setLore(invisLore);
        invisRing.setItemMeta(invisMeta); 
        
        strengthMeta.setDisplayName(ChatColor.GOLD+"Strength II Ring");
        List<String> strengthLore = new ArrayList<String>();
        strengthLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        strengthMeta.setLore(strengthLore);
        strengthRing.setItemMeta(strengthMeta); 
        Player p = (Player) sender;

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        Inventory rings = Bukkit.createInventory(null, 27, "Rings");
		Map<Integer, ItemStack> ringMap = new HashMap<>();
		ringMap.put(0, speedRing);
		ringMap.put(1, hasteRing);
		ringMap.put(2, visionRing);
		ringMap.put(3, satRing);
		ringMap.put(4, jumpRing);
		ringMap.put(5, invisRing);
		ringMap.put(6, strengthRing);
		ringMap.put(7, healthRing);
		ringMap.put(8, healthRing2);
		
        if(label.equalsIgnoreCase("rings") && args.length == 0)
        {
        	 for (int x=0; x<=26; x++) 
    		{
    			  rings.setItem(x, ringMap.containsKey(x) ? ringMap.get(x) : pane);
    		}
            p.openInventory(rings);
        }
        // p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Speed Ring");
        String prefix = ChatColor.GOLD.toString()+ChatColor.BOLD.toString()+"Rings"+ChatColor.DARK_GRAY.toString()+ChatColor.BOLD.toString()+"> ";
        if (label.equalsIgnoreCase("givering") && p.hasPermission("rings.give"))
		{
			if (args.length > 0)
			{
				String ring = args[0];
				String playerName = args[1];
				p.equals(playerName);
		
					if (ring.equalsIgnoreCase("speed2"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Speed II Ring");
						p.getInventory().addItem(speedRing);
					} 
					else if (ring.equalsIgnoreCase("haste2"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Haste II Ring");
						p.getInventory().addItem(hasteRing);
					} 
					else if (ring.equalsIgnoreCase("nvision"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Night Vision Ring");
						p.getInventory().addItem(visionRing);
					} 
					else if (ring.equalsIgnoreCase("saturation"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Saturation Ring");
						p.getInventory().addItem(satRing);
					} 
					 
					else if (ring.equalsIgnoreCase("health"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Health Ring");
						p.getInventory().addItem(healthRing);
					} 
					else if (ring.equalsIgnoreCase("health2"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Health T2 Ring");
						p.getInventory().addItem(healthRing2);
					} 
					else if (ring.equalsIgnoreCase("jumpboost"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Jump Boost Ring");
						p.getInventory().addItem(jumpRing);
					} 
					else if (ring.equalsIgnoreCase("invis"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Invisibility Ring");
						p.getInventory().addItem(invisRing);
					} 
					else if (ring.equalsIgnoreCase("strength"))
					{
						p.sendMessage(prefix+ChatColor.WHITE+"You've recived the "+ChatColor.GRAY+"Strength Ring");
						p.getInventory().addItem(strengthRing);
					} 
			}
			else
			{
				p.sendMessage(ChatColor.RED+"Please specify the following feilds");
				p.sendMessage(ChatColor.RED+"/givering <ring> <player>");
				p.sendMessage(ChatColor.RED+"/ringlist for ring names");
			}
		}
        
        else if (label.equalsIgnoreCase("ringlist"))
        { 
			p.sendMessage(ChatColor.GOLD+"speed2, haste2, nvision, saturation, flight, health, health2, jumpboost, invis, strength");
        }
    	return true;
    }
        
        
    public ShapedRecipe getspeedRingRecipe()
	{
    	ItemStack speedRing = new ItemStack(Material.IRON_NUGGET); //
        ItemMeta speedMeta = speedRing.getItemMeta();
        speedMeta.setDisplayName(ChatColor.GOLD+"Speed II Ring");
        List<String> speedLore = new ArrayList<String>();
        speedLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        speedMeta.setLore(speedLore);
        speedRing.setItemMeta(speedMeta);
        
        NamespacedKey key = new NamespacedKey(this, "iron_nugget");
		ShapedRecipe recipe = new ShapedRecipe(key, speedRing);
		recipe.shape("iii","ini","iii");
		recipe.setIngredient('n', Material.NETHER_STAR);
		recipe.setIngredient('i', Material.IRON_NUGGET);
		return recipe;
		
	}
    public ShapedRecipe getHasteRingRecipe()
	{
    	ItemStack hasteRing = new ItemStack(Material.GOLD_NUGGET); //
        ItemMeta hasteMeta = hasteRing.getItemMeta();
        hasteMeta.setDisplayName(ChatColor.GOLD+"Haste II Ring");
        List<String> hasteLore = new ArrayList<String>();
        hasteLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        hasteMeta.setLore(hasteLore);
        hasteRing.setItemMeta(hasteMeta);
        
        NamespacedKey key = new NamespacedKey(this, "gold_nugget");
		ShapedRecipe recipe = new ShapedRecipe(key, hasteRing);
		recipe.shape("iii","ini","iii");
		recipe.setIngredient('n', Material.NETHER_STAR);
		recipe.setIngredient('i', Material.GOLD_NUGGET);
		return recipe;
		
	}
    public ShapedRecipe getNightVisionRingRecipe()
	{
        ItemStack visionRing = new ItemStack(Material.BLAZE_POWDER); //
        ItemMeta visionMeta = visionRing.getItemMeta();
        visionMeta.setDisplayName(ChatColor.GOLD+"Night Vision Ring");
        List<String> visionLore = new ArrayList<String>();
        visionLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        visionMeta.setLore(visionLore);
        visionRing.setItemMeta(visionMeta);
        
        NamespacedKey key = new NamespacedKey(this, "blaze_powder");
		ShapedRecipe recipe = new ShapedRecipe(key, visionRing);
		recipe.shape("iii","ini","iii");
		recipe.setIngredient('n', Material.NETHER_STAR);
		recipe.setIngredient('i', Material.BLAZE_POWDER);
		return recipe;
		
	}
    
    public ShapedRecipe getSatRingRecipe()
 	{
    	ItemStack satRing = new ItemStack(Material.SLIME_BALL); //
        ItemMeta satMeta = satRing.getItemMeta();
        satMeta.setDisplayName(ChatColor.GOLD+"Saturation Ring");
        List<String> satLore = new ArrayList<String>();
        satLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        satMeta.setLore(satLore);
        satRing.setItemMeta(satMeta);
         
        NamespacedKey key = new NamespacedKey(this, "slime_ball");
 		ShapedRecipe recipe = new ShapedRecipe(key, satRing);
 		recipe.shape("iii","ini","iii");
 		recipe.setIngredient('n', Material.NETHER_STAR);
 		recipe.setIngredient('i', Material.SLIME_BALL);
 		return recipe;
 		
 	}
    public ShapedRecipe getHealthRingRecipe()
 	{
    	 ItemStack healthRing = new ItemStack(Material.RED_DYE);
    	 ItemMeta healthMeta = healthRing.getItemMeta();
    	 healthMeta.setDisplayName(ChatColor.GOLD+"Health Ring");
         List<String> healthLore = new ArrayList<String>();
         healthLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
         healthMeta.setLore(healthLore);
         healthRing.setItemMeta(healthMeta); 
         
        NamespacedKey key = new NamespacedKey(this, "red_dye");
 		ShapedRecipe recipe = new ShapedRecipe(key, healthRing);
 		recipe.shape("iii","ini","iii");
 		recipe.setIngredient('n', Material.NETHER_STAR);
 		recipe.setIngredient('i', Material.RED_DYE);
 		return recipe;
 		
 	}
    public ShapedRecipe getHealth2RingRecipe()
 	{
    	ItemStack healthRing2 = new ItemStack(Material.CYAN_DYE);
        ItemMeta health2Meta = healthRing2.getItemMeta();
        
        health2Meta.setDisplayName(ChatColor.GOLD+"Health T2 Ring");
        List<String> healthLore = new ArrayList<String>();
        healthLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        health2Meta.setLore(healthLore);
        healthRing2.setItemMeta(health2Meta); 
         
        NamespacedKey key = new NamespacedKey(this, "cyan_dye");
 		ShapedRecipe recipe = new ShapedRecipe(key, healthRing2);
 		recipe.shape("iii","ini","iii");
 		recipe.setIngredient('n', Material.NETHER_STAR);
 		recipe.setIngredient('i', Material.CYAN_DYE);
 		return recipe;
 		
 	}
    public ShapedRecipe getJumpRingRecipe()
 	{
    	ItemStack jumpRing = new ItemStack(Material.YELLOW_DYE);
        ItemMeta jumpMeta = jumpRing.getItemMeta();
        
        jumpMeta.setDisplayName(ChatColor.GOLD+"Jump Boost II Ring");
        List<String> jumpLore = new ArrayList<String>();
        jumpLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        jumpMeta.setLore(jumpLore);
        jumpRing.setItemMeta(jumpMeta); 
         
        NamespacedKey key = new NamespacedKey(this, "yellow_dye");
 		ShapedRecipe recipe = new ShapedRecipe(key, jumpRing);
 		recipe.shape("iii","ini","iii");
 		recipe.setIngredient('n', Material.NETHER_STAR);
 		recipe.setIngredient('i', Material.YELLOW_DYE);
 		return recipe;
 		
 	}
    public ShapedRecipe getInvisRingRecipe()
 	{
        ItemStack invisRing = new ItemStack(Material.LIME_DYE);
        ItemMeta invisMeta = invisRing.getItemMeta();
        
        invisMeta.setDisplayName(ChatColor.GOLD+"Invisibility Ring");
        List<String> invisLore = new ArrayList<String>();
        invisLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        invisMeta.setLore(invisLore);
        invisRing.setItemMeta(invisMeta);  
         
        NamespacedKey key = new NamespacedKey(this, "lime_dye");
 		ShapedRecipe recipe = new ShapedRecipe(key, invisRing);
 		recipe.shape("iii","ini","iii");
 		recipe.setIngredient('n', Material.NETHER_STAR);
 		recipe.setIngredient('i', Material.LIME_DYE);
 		return recipe;
 		
 	}
    public ShapedRecipe getStrengthRingRecipe()
 	{
        ItemStack strengthRing = new ItemStack(Material.PINK_DYE);
        ItemMeta strengthMeta = strengthRing.getItemMeta();
        
        strengthMeta.setDisplayName(ChatColor.GOLD+"Strength II Ring");
        List<String> strengthLore = new ArrayList<String>();
        strengthLore.add(ChatColor.WHITE + "Hold in off-hand to gain effect");
        strengthMeta.setLore(strengthLore);
        strengthRing.setItemMeta(strengthMeta);   
         
        NamespacedKey key = new NamespacedKey(this, "pink_dye");
 		ShapedRecipe recipe = new ShapedRecipe(key, strengthRing);
 		recipe.shape("iii","ini","iii");
 		recipe.setIngredient('n', Material.NETHER_STAR);
 		recipe.setIngredient('i', Material.PINK_DYE);
 		return recipe;
 		
 	}
    @EventHandler
    public void onOffHand(PlayerSwapHandItemsEvent e)
    {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInOffHand() != null)
        {
        	
            if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.IRON_NUGGET)//speed
            {
  		      p.addPotionEffect((new PotionEffect(PotionEffectType.SPEED, 100000, 1)));
              p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
            }
            else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.GOLD_NUGGET)//haste
            {
  		      p.addPotionEffect((new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 1)));
  		      p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);

            	//add potion effect
            }
            else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.BLAZE_POWDER)//nvision
            {
  		      p.addPotionEffect((new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 1)));  
  		      p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);

            	//add potion effect
            }
            else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.SLIME_BALL)//saturation
            {
  		      p.addPotionEffect((new PotionEffect(PotionEffectType.SATURATION, 100000, 1)));
              p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);

            	//add potion effect
            }
            else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.RED_DYE)//saturation
            {
              p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
              p.setMaxHealth(24);
              p.setHealth(p.getMaxHealth());
            	//add potion effect
            }
            else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.YELLOW_DYE)//saturation
            {
  		      p.addPotionEffect((new PotionEffect(PotionEffectType.JUMP, 100000, 1)));
              p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
            }
            else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.LIME_DYE)//saturation
            {
  		      p.addPotionEffect((new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1)));
              p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
            }
            else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.PINK_DYE)//saturation
            {
  		      p.addPotionEffect((new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 1)));
              p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
            }
            else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.CYAN_DYE)//saturation
            {
              p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
              p.setMaxHealth(28);
              p.setHealth(p.getMaxHealth());
            	//add potion effect
            }
            	if(e.getMainHandItem().getType().equals(Material.SLIME_BALL))
            	{
            		p.removePotionEffect(PotionEffectType.SATURATION);
            	}
            	if(e.getMainHandItem().getType().equals(Material.BLAZE_POWDER))
            	{
            		p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            	}
            	if(e.getMainHandItem().getType().equals(Material.GOLD_NUGGET))
            	{
            		p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            	}
            	if(e.getMainHandItem().getType().equals(Material.IRON_NUGGET))
            	{
            		p.removePotionEffect(PotionEffectType.SPEED);
            	}
            	if(e.getMainHandItem().getType().equals(Material.RABBIT_FOOT))
            	{
                    p.setAllowFlight(false);
            	}
            	if(e.getMainHandItem().getType().equals(Material.RED_DYE))
            	{
                    p.setMaxHealth(20);
                    p.setHealth(p.getMaxHealth());       
                }
            	if(e.getMainHandItem().getType().equals(Material.YELLOW_DYE))
            	{
            		p.removePotionEffect(PotionEffectType.JUMP);   
                }
            	if(e.getMainHandItem().getType().equals(Material.LIME_DYE))
            	{
            		p.removePotionEffect(PotionEffectType.INVISIBILITY);   
                }
            	if(e.getMainHandItem().getType().equals(Material.PINK_DYE))
            	{
            		p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);   
                }
            	if(e.getMainHandItem().getType().equals(Material.CYAN_DYE))
            	{
            	    p.setMaxHealth(20);
                    p.setHealth(p.getMaxHealth());    
                }
            	
            
        }
        
       
    }
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if(e.getView().getTitle().equalsIgnoreCase("rings"))
        {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            if (clicked.getType().equals(Material.AIR))
            {
            	return;
            }
            else if (clicked.getType().equals(Material.IRON_NUGGET) && p.hasPermission("rings.give"))//speed
            {
            	p.getInventory().addItem(speedRing);
            }
            else if (clicked.getType().equals(Material.GOLD_NUGGET) && p.hasPermission("rings.give")) //haste
            {
            	p.getInventory().addItem(hasteRing);

            }
            else if (clicked.getType().equals(Material.BLAZE_POWDER) && p.hasPermission("rings.give"))//nvision
            {
            	p.getInventory().addItem(visionRing);

            }
            else if (clicked.getType().equals(Material.SLIME_BALL) && p.hasPermission("rings.give"))//satiration
            {
            	p.getInventory().addItem(satRing);

            }
            else if (clicked.getType().equals(Material.RED_DYE) && p.hasPermission("rings.give"))//healthboost
            {
            	p.getInventory().addItem(healthRing);

            }
            else if (clicked.getType().equals(Material.YELLOW_DYE) && p.hasPermission("rings.give"))//jumpboost
            {
            	p.getInventory().addItem(jumpRing);

            }
            else if (clicked.getType().equals(Material.LIME_DYE) && p.hasPermission("rings.give"))//jumpboost
            {
            	p.getInventory().addItem(invisRing);

            }
            else if (clicked.getType().equals(Material.PINK_DYE) && p.hasPermission("rings.give"))//jumpboost
            {
            	p.getInventory().addItem(strengthRing);

            }
            else if (clicked.getType().equals(Material.CYAN_DYE) && p.hasPermission("rings.give"))//jumpboost
            {
            	p.getInventory().addItem(healthRing2);

            }
        }
        
    }
    @EventHandler
    public void onclick(InventoryClickEvent e)
    {
        HumanEntity player = e.getWhoClicked();
        ItemStack currentItem = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
       
        	if (currentItem.getType().equals(Material.SLIME_BALL) && currentItem.hasItemMeta())
            {
        		player.removePotionEffect(PotionEffectType.SATURATION);
            }
            else if (currentItem.getType().equals(Material.BLAZE_POWDER) && currentItem.hasItemMeta())
            {
        		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
            else if (currentItem.getType().equals(Material.GOLD_NUGGET) && currentItem.hasItemMeta())
            {
        		player.removePotionEffect(PotionEffectType.FAST_DIGGING);
            }
            else if (currentItem.getType().equals(Material.IRON_NUGGET) && currentItem.hasItemMeta())
            {
        		player.removePotionEffect(PotionEffectType.SPEED);
            }
            else if (currentItem.getType().equals(Material.RED_DYE) && currentItem.hasItemMeta())
            {
                p.setMaxHealth(20);
                p.setHealth(p.getMaxHealth());        
            }
            else if (currentItem.getType().equals(Material.YELLOW_DYE) && currentItem.hasItemMeta())
            {
        		player.removePotionEffect(PotionEffectType.JUMP);
            }
            else if (currentItem.getType().equals(Material.LIME_DYE) && currentItem.hasItemMeta())
            {
        		player.removePotionEffect(PotionEffectType.INVISIBILITY);
            }
            else if (currentItem.getType().equals(Material.PINK_DYE) && currentItem.hasItemMeta())
            {
        		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            }
            else if (currentItem.getType().equals(Material.CYAN_DYE) && currentItem.hasItemMeta())
            {
                p.setMaxHealth(20);
                p.setHealth(p.getMaxHealth());        
            }
        
        
        
        	
        

        
        
    }
   
}