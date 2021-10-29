package com.example.multipleview2

data class MultiData(
    //병원 또는 의사 이미지
    val firstImage: String,
    val secondImage: String,

    //의사 이름 또는 게시글 제목
    val firstName: String,
    val secondName: String,

    //직무
    val firstJob: String,
    val secondJob: String,

    //태그명
    val firstTag: String,
    val secondTag: String,

    //게시글 내용 또는 병원 이름 및 주소
    val firstInfo:String,
    val secondInfo:String,

    //답변 수
    val ripple: String,

    //View 구분 매개체
    val type: Int
)