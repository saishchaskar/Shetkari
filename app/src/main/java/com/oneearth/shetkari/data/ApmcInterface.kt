//package com.oneearth.shetkari.data
//
//
//import retrofit2.http.GET
//import retrofit2.http.Query
//
//interface ApmcInterface {
//
//    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070")
//    suspend fun getApmc(@Query("api-key") apiKey: String = API_KEY2,
//                        @Query("format") format: String = "json",
//                        @Query("offset") offset: Int = 0,
//                        @Query("limit") limit: Int = 1000): APMCMain
//
//    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070")
//    suspend fun getDistricts(@Query("api-key") apiKey: String = API_KEY2,
//                             @Query("format") format: String = "json",
//                             @Query("offset") offset: Int = 0,
//                             @Query("limit") limit: Int = 1000,
//                             @Query("filters[state]") stateId: String): DistrictResponse
//
//    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070")
//    suspend fun getMarketPrices(@Query("api-key") apiKey: String = API_KEY2,
//                                @Query("format") format: String = "json",
//                                @Query("offset") offset: Int = 0,
//                                @Query("limit") limit: Int = 1000,
//                                @Query("filters[district]") districtId: String): MarketPriceResponse
//}
