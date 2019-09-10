
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.foxhis.c_network.exception.HttpException
import com.foxhis.c_network.listener.ResultListener
import com.sj.sjwanandroid.BaseFragment
import com.sj.sjwanandroid.R
import com.sj.sjwanandroid.adapter.HomeAdapter
import com.sj.sjwanandroid.bean.ArticleData
import com.sj.sjwanandroid.utils.network.HttpUtils
import kotlinx.android.synthetic.main.fragment_homepage.*

class HomePageFragment : BaseFragment() {
    override var layoutId = R.layout.fragment_homepage;
    val imageUrl = "https://www.baidu.com/img/bd_logo1.png"
    val baseUrl = "https://www.wanandroid.com/article/list/"
    var page = 0
    lateinit var adapter: HomeAdapter


    override fun initData() {
        vp.adapter
        rv.layoutManager = LinearLayoutManager(activity)
        adapter = HomeAdapter(mutableListOf())
        rv.adapter= adapter
        getArticle()
        adapter.loadLastOne = {
            Log.e("Tag","--------")
            page++
            getArticle()
        }
    }

    private fun getArticle() {
        var url = baseUrl+page+"/json"
        Log.e("Tag","--------$url")

        HttpUtils.getData(url, object : ResultListener<ArticleData> {
            override fun onSuccess(result: ArticleData?) {
                var articleData = result as ArticleData
                adapter.dataList.addAll(articleData.datas)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(httpException: HttpException?) {
            }

        })
    }


    

}


