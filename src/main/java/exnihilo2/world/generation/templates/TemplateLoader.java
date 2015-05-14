package exnihilo2.world.generation.templates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exnihilo2.EN2;
import exnihilo2.world.generation.templates.pojos.Template;

public class TemplateLoader {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static Template load(String path)
	{
		File file = new File(path);
		Template template = null;
		
		if (!file.exists())
		{
			EN2.log.info("Map file not found '" + file + "'. Attempting to generate default map at this path.");
			
			template = TemplateDefault.generate();
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
		else
		{
			try 
			{
				BufferedReader reader = new BufferedReader(new FileReader(path)); 
				
				if (reader.ready())
				{
					template = gson.fromJson(reader, Template.class);
				}
				
				reader.close();
			} 
			catch (Exception e) 
			{
				template = TemplateDefault.generate();
				
				EN2.log.error("Failed to read map file: '" + file + "'.");
				EN2.log.error(e);
			}  
		}

		return template;
	}
}
