package exnihilo2.world.generation.templates;

import java.util.ArrayList;

import net.minecraft.init.Items;
import exnihilo2.EN2;
import exnihilo2.world.generation.templates.pojos.Template;
import exnihilo2.world.generation.templates.pojos.TemplateBlock;
import exnihilo2.world.generation.templates.pojos.TemplateItem;

public class TemplateDefault {
	public static Template generate()
	{
		//TODO add chest and stuff.
		Template map = new Template();
		
		ArrayList<TemplateBlock> blocks = map.getBlocks();
		//layer 0
		blocks.add(new TemplateBlock("minecraft:grass", 0, -1, 0, -1));
		blocks.add(new TemplateBlock("minecraft:grass", 0, -1, 0, 0));
		blocks.add(new TemplateBlock("minecraft:grass", 0, -1, 0, 1));
		
		blocks.add(new TemplateBlock("minecraft:grass", 0, 0, 0, -1));
		blocks.add(new TemplateBlock("minecraft:grass", 0, 0, 0, 0));
		blocks.add(new TemplateBlock("minecraft:grass", 0, 0, 0, 1));
		
		blocks.add(new TemplateBlock("minecraft:grass", 0, 1, 0, -1));
		blocks.add(new TemplateBlock("minecraft:grass", 0, 1, 0, 0));
		blocks.add(new TemplateBlock("minecraft:grass", 0, 1, 0, 1));
		
		//layer 1
		blocks.add(new TemplateBlock("minecraft:dirt", 0, -1, -1, -1));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, -1, -1, 0));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, -1, -1, 1));
		
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 0, -1, -1));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 0, -1, 0));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 0, -1, 1));
		
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 1, -1, -1));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 1, -1, 0));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 1, -1, 1));
		
		//layer 2
		blocks.add(new TemplateBlock("minecraft:dirt", 0, -1, -2, -1));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, -1, -2, 0));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, -1, -2, 1));
		
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 0, -2, -1));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 0, -2, 0));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 0, -2, 1));
		
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 1, -2, -1));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 1, -2, 0));
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 1, -2, 1));
		
		//chest
		TemplateBlock chest = new TemplateBlock("minecraft:chest", 0, 0, 1, 1);
		chest.setContents(new ArrayList<TemplateItem>());
		chest.getContents().add(new TemplateItem("minecraft:lava_bucket", 1, 0));
		chest.getContents().add(new TemplateItem("minecraft:ice", 1, 0));
		blocks.add(chest);
		
		return map;
	}
}
