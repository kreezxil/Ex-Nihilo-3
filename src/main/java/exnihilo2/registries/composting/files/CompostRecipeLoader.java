package exnihilo2.registries.composting.files;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exnihilo2.EN2;
import exnihilo2.registries.composting.CompostRegistryEntry;
import exnihilo2.registries.composting.pojos.CompostRecipeList;

public class CompostRecipeLoader {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static List<CompostRegistryEntry> load(String path)
	{
		generateExampleJsonFile(path);
		
		
		//TODO: loop through all the files in the path
		// parse each file to a recipe list.
		// loop through each recipe
		// attempt to get registry entries for each.
		// return all the loaded recipes.
		return null;
	}
	
	private static void generateExampleJsonFile(String path)
	{
		File file = new File(path + "example.json");
		CompostRecipeList recipes = null;
		
		if (!file.exists())
		{
			EN2.log.info("Example compost recipe file not found '" + file + "'. Attempting to generate example file at this path.");
			
			recipes = CompostRecipeExample.getExampleRecipeList();
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
}
