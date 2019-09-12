
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.foxhis.c_network.exception.HttpException
import com.foxhis.c_network.listener.ResultListener
import com.google.gson.Gson
import com.sj.sjwanandroid.BaseFragment
import com.sj.sjwanandroid.R
import com.sj.sjwanandroid.adapter.HomeAdapter
import com.sj.sjwanandroid.bean.ArticleData
import com.sj.sjwanandroid.bean.Banner
import com.sj.sjwanandroid.utils.network.HttpUtils
import ikidou.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.item_home_banner.view.*

class HomePageFragment : BaseFragment() {
    override var layoutId = R.layout.fragment_homepage;

    val imageUrl = "https://www.baidu.com/img/bd_logo1.png"
    val baseUrl = "https://www.wanandroid.com/article/list/"
    var page = 0
    lateinit var adapter: HomeAdapter
    lateinit var vpAdapter: VPAdapter


    override fun initView() {
        vpAdapter = VPAdapter(mutableListOf(),context!!)
        vp.adapter = vpAdapter


        rv.layoutManager = LinearLayoutManager(activity)
        adapter = HomeAdapter(mutableListOf())
        rv.adapter = adapter
        adapter.loadLastOne = {
            Log.e("Tag", "--------")
            page++
            getArticle()
        }
        rflayout.setOnRefreshListener {
            rflayout.isRefreshing = false
            page = 0
            initData()
        }
    }

    override fun initData() {

        getBanner()
        getArticle()

    }

    private fun getBanner() {
        var url = "https://www.wanandroid.com/banner/json"
        HttpUtils.getData(url, object : ResultListener {
            override fun onSuccess(result: Any?) {
                var bannerList = Gson().fromJson(result.toString(),object :TypeToken<List<Banner>>(){}.type) as MutableList<Banner>
                vpAdapter.bannerList = bannerList
                vpAdapter.notifyDataSetChanged()
            }
            override fun onFailure(httpException: HttpException?) {
            }
        })
    }

    private fun getArticle() {
        var url = baseUrl + page + "/json"
        Log.e("Tag", "--------$url")

        HttpUtils.getData(url, object : ResultListener {
            override fun onSuccess(result: Any?) {
            var articleData = Gson().fromJson(result.toString(),ArticleData::class.java) as ArticleData
                adapter.dataList.addAll(articleData.datas)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(httpException: HttpException?) {
            }

        })
    }


    class VPAdapter(var bannerList: MutableList<Banner>, var context: Context) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            var view = View.inflate(context, R.layout.item_home_banner, null)
            var imagePath = bannerList[position].imagePath
            Glide.with(context).load(imagePath).into(view.iv)
            view.title.text = bannerList[position].title
            container.addView(view)
            return view
        }

        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        override fun getCount(): Int {
            return bannerList.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }


}


