package test.diag.com.diagtest_android.model.local

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Slug")
    val slug: String,
) : StatusConfirmed()