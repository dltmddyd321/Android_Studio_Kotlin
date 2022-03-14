package com.example.servicestylerx

//open class ServerSyncInfoApiTask {
//
//    companion object {
//        private val gson =
//            GsonBuilder()
//                .setLenient()
//                .create()
//        private var client = OkHttpClient.Builder()
//            .connectTimeout(1, TimeUnit.MINUTES)
//            .readTimeout(1, TimeUnit.MINUTES)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .build()
//        fun postApi(): ServerSyncInfoApi = Retrofit.Builder()
//            .baseUrl(ServerStatus.BASE_URL)
//            .client(client)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build()
//                .create(ServerSyncInfoApi::class.java)
//
//    }
//}
//
//interface ServerSyncInfoApi {
//    @FormUrlEncoded
//    @POST(Swagger Url (ex : api/mem/tcCategories))
//    fun postRepos(@HeaderMap headers: HashMap<String, String>,
//                  @FieldMap categoryUid: HashMap<String, List<String>>) : Single<Data>
//
//    //GET의 경우에는 Header는 그대로 사용, 파라미터의 경우에는 오직 @Query
//}
//
//data class Data(받아올 데이터 형식)