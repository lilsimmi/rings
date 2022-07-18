# RingsReworked

## Description

A minecraft plugin which allows you to create custom rings with certain effects. 
Currently, you can give these rings two types of effects: You can ever apply potion effects to the ring
or change a players attributes like max health. Both can be easily applied in the configuration.
In general there can be infinite amount of rings. They can be created in the config file with everything customizable.
You can setup how the crafting recipe for the ring and the following result item should look like.
There is an option for a custom use permission and craft permission. The end-result of the ring also supports custom data values.
This option will help resoucepack creators to define custom textures for their rings.

Also, the whole plugin messages can be changed and configurated to your likings.

## Commands
| Command                               | PermissionNode         | Default | Description                                                                     |
|---------------------------------------|------------------------|---------|---------------------------------------------------------------------------------|
| `/rings`                              | `none`                 | `true`  | Just shows some credentials about the plugin                                    |
| `/rings gui`                          | `rings.command.gui`    | `OP`    | Will open a GUI with all the rings available *(Click ring to add to inventory)* |
| `/rings list`                         | `rings.command.list`   | `OP`    | Prints a list with all available rings in the chat                              |
| `/rings recipe [ringname]`            | `rings.command.recipe` | `OP`    | Will open a GUI which will show the crafting recipe for the ring                |
| `/rings give [ringname] (playername)` | `rings.command.give`   | `OP`    | Allows you to give yourself or another player a certain ring                    |
| `/rings reload`                       | `rings.command.reload` | `OP`    | Reload the plugin                                                               |

## Configuration

The main configuration:
````yaml
# RingsReworked - by LuckyMutt

Rings:
  # This is the ring name. You can set it to whatever you like, but you should keep in mind
  # that you have to work with the name ingame later on
  SpeedI:
    # The permission the player needs to be able to use the ring
    Permission: rings.use.speed.I
    # Section to add the potion effects for the ring. Can have multiple effects at once.
    # Format: POTION_NAME: AMPLIFIER (If the amplifier would be 0 the potion level would be 1.
    #
    # For a list of possible potion-effects check out https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html
    #
    Potions:
      SPEED: 0
    # The crafting section will define how the crafting recipe for the item should be and how the result item should look like.
    Crafting:
      # Player need this permission if he should be able to craft the item.
      Permission: rings.craft.speed.I
      # Here you can define the Shape of the item. The letters are placeholders for materials in the crafting grid.
      Shape:
        - XXX
        - XNX
        - XXX
      # Here you can define which materials should be used for which placeholder letter.
      Materials:
        X: IRON_NUGGET
        N: NETHER_STAR
      # This section will define how the result-item should look like.
      Result:
        Material: IRON_NUGGET
        Name: "&aSpeed I Ring"
        Lore:
          - "&7Wear this ring in your offhand for the effect to apply."
        # Important! For enchantments use the vanilla styled names in lowercase.
        # The number behind the enchantment will represent the level of the enchantment
        Enchantments:
          unbreaking: 1
        # You can define a custom model data value to apply for later on resourcepack usage.
        CustomModelData: 0
````

