package com.sj.sjwanandroid.bean
import java.io.Serializable

data class BaseBean<T>(
    val errorCode:Int,
    val errorMsg:String,
    val data:T
)

data class ArticleData(
    val offset:Int,
    val over:Boolean,
    val pageCount:Int,
    val size:Int,
    val total:Int,
    val datas:MutableList<Article>
)

data class Article(
    var apkLink: String,
    var author: String,
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var fresh: Boolean,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var projectLink: String,
    var publishTime: Long,
    var superChapterId: Int,
    var superChapterName: String,
    var tags: List<Tag>,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int
//    var isTop: Boolean,
//    var bannerData: List<Banner>
)

data class Tag(
    var name: String,
    var url: String
)



data class Page<T>(
    var curPage: Int,
    var datas: MutableList<T>,
    var offset: Int,
    var pageCount: Int,
    var size: Int,
    var total: Int,
    var over: Boolean
)

data class Chapter(
    var children: List<Chapter>,
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int
) : Serializable

data class Navigation(
    var articles: List<Article>,
    var cid: Int,
    var name: String
)

data class Banner(
    var desc: String,
    var id: Int,
    var imagePath: String,
    var isVisible: Int,
    var order: Int,
    var title: String,
    var type: Int,
    var url: String
)