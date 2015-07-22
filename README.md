Potion Commands
====================

This plugin will allow you to dynamically register commands that give potion effects.

Fetures List:
 * Dynamically create commands that give potion effects.
 * Command cooldowns to prevent potion spam.
 * Simple configuration
 * Includes a Lang.yml file so you can modify the output of the plugin.

Usage
---------
The base command is

 * `/potioncommands` or `/pc`
 * `/potioncommands reload` will reload the configuration file if the user has permission.
 * `/milk [player]` clears specified player of all potion effects or the user if no player is provided.

Permissions
----------

Permission | Description | default
-------------- | -------------- | --------------
potioncommanda.milk | Use the /milk command | op
potioncommands.milk.other | Use the /milk command on others | op
potioncommands.milk.bypass | Allows the player to bypass the cooldown | op
potioncommands.reload | Allows the player to reload the configuration | op
potioncommands.<custom permission for dynamic command> | Allows the player to use the command with the permission node specified in the config | op
potioncommands.<custom permission for dynamic command>.bypass | Allows the player to bypass the cooldown for that command | op  

Installing
----------
Put the PotionCommands.jar file into your server's plugin directory.
A default config file will be created on the first run.

License
----------
This project is licensed using the MIT Open Source license.
