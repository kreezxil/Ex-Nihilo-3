package exnihilo2.registries.hammering.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exnihilo2.EN2;
import exnihilo2.registries.composting.CompostRegistryEntry;
import exnihilo2.registries.composting.pojos.CompostRecipe;
import exnihilo2.registries.composting.pojos.CompostRecipeList;
import exnihilo2.registries.hammering.HammerRegistryEntry;
import exnihilo2.registries.hammering.pojos.HammerRecipe;
import exnihilo2.registries.hammering.pojos.HammerRecipeList;
import exnihilo2.world.generation.templates.defaults.TemplateSkyblock21;
import exnihilo2.world.generation.templates.pojos.Template;

public class HammerRecipeLoader {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static ArrayList<HammerRegistryEntry> entries; 
	
	public static List<HammerRegistryEntry> load(String path)
	{	
		generateExampleJsonFile(path);
		entries = new ArrayList<HammerRegistryEntry>();
		
		File[] files = new File(path).listFiles();
		
		for (File file : files)
		{
			if (!file.getName().equals("example.json"))//Ignore the example file
			{
				HammerRecipeList list = loadRecipes(file);
				
				if (list != null && !list.getRecipes().isEmpty())
				{
					for (HammerRecipe recipe : list.getRecipes())
					{
						HammerRegistryEntry entry = HammerRegistryEntry.fromRecipe(recipe);
						
						if (entry != null)
						{
							entries.add(entry);
						}
					}
				}
			}
		}
		
		return entries;
	}
	
	private static void generateExampleJsonFile(String path)
	{
		File file = new File(path + "example.json");
		HammerRecipeList recipes = null;
		
		if (!file.exists())
		{
			EN2.log.info("Attempting to generate example hammer recipe file: '" + file + "'.");
			
			recipes = HammerRecipeExample.getExampleRecipeList();
			FileWriter writer;
			
			try 
			{
				file.getParentFile().mkdirs();
				
				writer = new FileWriter(file);
				writer.write(gson.toJson(recipes)); 
				writer.close();
			} 
			catch (Exception e) 
			{
				EN2.log.error("Failed to write file: '" + file + "'.");
				EN2.log.error(e);
			}  
		}
	}
	
	private static HammerRecipeList loadRecipes(File file)
	{
		HammerRecipeList recipes = null;
		
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(file)); 
			
			if (reader.ready())
			{
				recipes = gson.fromJson(reader, HammerRecipeList.class);
			}
			
			reader.close();
		} 
		catch (Exception e) 
		{
			EN2.log.error("Failed to read hammer recipe file: '" + file + "'.");
			EN2.log.error(e);
		}  
		
		return recipes;
	}
}
