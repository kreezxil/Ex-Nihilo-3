package exnihilo2.util.enums;

import com.google.gson.annotations.SerializedName;

public enum MetadataBehavior
{
	@SerializedName("0")
	Ignored, // Metadata is ignored.
	@SerializedName("1")
	Specified // Specific metadata value is required
}
