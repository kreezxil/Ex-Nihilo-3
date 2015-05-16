package exnihilo2.util.enums;

import com.google.gson.annotations.SerializedName;

public enum MetadataBehavior
{
	@SerializedName("ignored")
	IGNORED, // Metadata is ignored.
	@SerializedName("specific")
	SPECIFIC // Specific metadata value is required
}
