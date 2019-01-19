package com.demortes.adfreeappmanager.ui.main

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.demortes.adfreeappmanager.AppListItemAdapter
import com.demortes.adfreeappmanager.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var appList: List<ApplicationInfo>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val pm = context?.packageManager
        appList = pm?.getInstalledApplications(PackageManager.GET_META_DATA)
        val listview = AppListing
        appList = appList?.sortedBy { context?.packageManager?.getApplicationLabel(it).toString() }
        listview.adapter = AppListItemAdapter(this.context, appList)
    }

}
