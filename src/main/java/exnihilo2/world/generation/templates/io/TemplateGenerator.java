package exnihilo2.world.generation.templates.io;

import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exnihilo2.EN2;
import exnihilo2.world.generation.templates.pojos.Template;

public class TemplateGenerator {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	protected static void generateTemplateFile(String path, Template template)
	{
		generateTemplateFile(path, template, false);
	}
	
	protected static void generateTemplateFile(String path, Template template, boolean overwrite)
	{
		File file = new File(path);
		FileWriter writer;
		
		if (!file.exists() && template != null)
		{
			EN2.log.info("Map file not found '" + file + "'. Attempting to generate template at this path.");
			
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
		else if(overwrite)
		{
			EN2.log.info("Attempting to overwrite template at '" + file + "'");
			
			try 
			{
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
