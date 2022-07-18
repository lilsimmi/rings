package me.simmi.ringsv2;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class tabList implements TabCompleter
{

	List<String> arguments = new ArrayList<>();

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (arguments.isEmpty())
		{
			arguments.add("speed2"); 
			arguments.add("haste2");
			arguments.add("nvision");
			arguments.add("saturation"); 
			arguments.add("health"); 
			arguments.add("health2");
			arguments.add("jumpboost");
			arguments.add("invis");
			arguments.add("strength");
		}

		return arguments;
	}
}
