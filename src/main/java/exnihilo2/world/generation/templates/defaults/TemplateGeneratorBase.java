package exnihilo2.world.generation.templates.defaults;

import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exnihilo2.EN2;
import exnihilo2.world.generation.templates.pojos.Template;

public class TemplateGeneratorBase {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	protected static void generateTemplateFile(String path, Template template)
	{
		File file = new File(path);
		
		if (!file.exists() && template != null)
		{
			EN2.log.info("Map file not found '" + file + "'. Attempting to generate template at this path.");
			
			FileWriter writer;
			
			try 
			{
				file.getParentFile().mkdirs();
				
				writer = new FileWriter(file);
				writer.write(gson.toJson(template)); 
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
