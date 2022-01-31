package com.example.unsplash.model

import java.io.Serializable

//직렬화로 해당 모델 데이터를 다루기 위해서는 반드시 직렬화에 대한 선언이 필요
data class Photo(var thumbnail: String?, var author: String?, var createdAt: String?, var likesCount: Int?)
    : Serializable{
}