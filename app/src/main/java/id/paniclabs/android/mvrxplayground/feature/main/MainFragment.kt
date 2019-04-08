package id.paniclabs.android.mvrxplayground.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.airbnb.mvrx.*
import com.link184.kidadapter.setUp
import com.link184.kidadapter.simple.SingleKidAdapter
import id.paniclabs.android.mvrxplayground.R
import id.paniclabs.android.mvrxplayground.data.models.PostModel
import id.paniclabs.android.mvrxplayground.presentation.states.PostListState
import id.paniclabs.android.mvrxplayground.presentation.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_main.view.*
import kotlinx.android.synthetic.main.progress.*


class MainFragment : BaseFragment<PostListState, MainViewModel>() {

    private val mMainVm: MainViewModel by fragmentViewModel()
    private val mPostList = arrayListOf<PostModel>()
    private lateinit var adapter: SingleKidAdapter<PostModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_main, container, false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mMainVm.getPostList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecorator)
        adapter = recyclerView.setUp<PostModel> {
            withLayoutResId(R.layout.item_main)
            withItems(mPostList)
            withItemsComparator { oldItem, newItem ->
                oldItem.hashCode() == newItem.hashCode()
            }
            bindIndexed { item, _ ->
                bodyRow.text = item.body
                titleRow.text = item.title
                setOnClickListener {
                    Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun invalidate() {
        withState(mMainVm) { state ->

            when (state.postList) {
                //Uninitialized
                is Uninitialized -> progressbar.visibility = VISIBLE

                //Loading
                is Loading -> progressbar.visibility = VISIBLE

                //Success
                is Success -> {
                    progressbar.visibility = GONE
                    mPostList.clear()
                    mPostList.addAll(state.postList()!!)
                    adapter + mPostList
                    Toast.makeText(context, "Received Post : ${state.postList()?.size}", Toast.LENGTH_LONG).show()
                }

                //Failed
                is Fail -> {
                    progressbar.visibility = GONE
                    Toast.makeText(context, "API failed to execute", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}

