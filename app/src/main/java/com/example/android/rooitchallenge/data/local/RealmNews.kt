package com.example.android.rooitchallenge.data.local

import com.example.android.rooitchallenge.data.domain.News
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmNews() : RealmObject() {
    var imageUrl: String? = null
    @PrimaryKey
    var titleSource: String = ""
}

fun List<RealmNews>.asDomain(): List<News> = map {
    News(it.imageUrl, it.titleSource)
}