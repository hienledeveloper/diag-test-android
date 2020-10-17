package test.diag.com.diagtest_android.model.local

import com.google.gson.annotations.SerializedName

data class CovidArea(
    @SerializedName("Cases")
    val cases: Int,
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Lat")
    val lat: String,
    @SerializedName("Lon")
    val lon: String,
    @SerializedName("Status")
    val status: String
)