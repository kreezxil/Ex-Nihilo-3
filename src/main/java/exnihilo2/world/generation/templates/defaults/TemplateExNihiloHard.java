package exnihilo2.world.generation.templates.defaults;

import java.io.File;
import java.util.ArrayList;

import exnihilo2.world.generation.templates.pojos.Template;
import exnihilo2.world.generation.templates.pojos.TemplateBlock;

public class TemplateExNihiloHard extends TemplateGeneratorBase{

	public static void generate(String path)
	{
		generateTemplateFile(path + File.separator + "ex_nihilo_hard_overworld.json", getOverworldTemplate());
	}

	private static Template getOverworldTemplate()
	{
		Template map = new Template();

		ArrayList<TemplateBlock> blocks = map.getBlocks();

		//DIRT ISLAND
		//layer 0
		blocks.add(new TemplateBlock("minecraft:dirt", 0, 0, 0, 0));

		//TREE
		//logs
		blocks.add(new TemplateBlock("minecraft:log", 2, 0, 1, 0));
		blocks.add(new TemplateBlock("minecraft:log", 2, 0, 2, 0));
		blocks.add(new TemplateBlock("minecraft:log", 2, 0, 3, 0));
		blocks.add(new TemplateBlock("minecraft:log", 2, 0, 4, 0));
		
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 5, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 6, 0));

		//leaves layer 0
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -2, 4, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -2, 4, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -2, 4, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -2, 4, -2));

		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 4, 2));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 4, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 4, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 4, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 4, -2));

		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 4, 2));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 4, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 4, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 4, -2));

		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 4, 2));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 4, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 4, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 4, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 4, -2));

		blocks.add(new TemplateBlock("minecraft:leaves", 2, 2, 4, 2));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 2, 4, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 2, 4, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 2, 4, -1));

		//leaves layer 1
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -2, 5, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -2, 5, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -2, 5, -1));

		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 5, 2));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 5, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 5, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 5, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 5, -2));

		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 5, 2));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 5, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 5, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 5, -2));

		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 5, 2));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 5, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 5, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 5, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 5, -2));

		blocks.add(new TemplateBlock("minecraft:leaves", 2, 2, 5, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 2, 5, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 2, 5, -1));
		
		//leaves layer 2
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 6, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 6, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 6, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 6, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 6, 0));

		//leaves layer 3
		blocks.add(new TemplateBlock("minecraft:leaves", 2, -1, 7, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 7, 1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 7, 0));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 0, 7, -1));
		blocks.add(new TemplateBlock("minecraft:leaves", 2, 1, 7, 0));

		return map;
	}
}
