@Parcelize
data class StatusResponse(

	@field:SerializedName("data")
	val data: List<Data>
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable

