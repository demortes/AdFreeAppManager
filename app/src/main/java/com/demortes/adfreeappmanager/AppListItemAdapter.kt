package com.demortes.adfreeappmanager

import android.content.Context
import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.demortes.adfreeappmanager.R.id.*

class AppListItemAdapter(private val context: Context?, private val dataSource: List<ApplicationInfo>?) :
    BaseAdapter() {
    private val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getItem(position: Int): Any {
        return dataSource!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource!!.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val app = context!!.applicationContext as app
        val item = dataSource!![position]
        val pm = context.packageManager
        if (convertView == null) {
            val view = inflater.inflate(R.layout.app_info_list_item, parent, false)
            view.findViewById<TextView>(ListItemAppName).text = pm!!.getApplicationLabel(item).toString()
            view.findViewById<TextView>(appPackageName).text = item.packageName
            view.findViewById<ImageView>(app_icon).setImageDrawable(pm.getApplicationIcon(item))
            view.findViewById<CheckBox>(appCheckbox).isChecked = app.uninstallList!!.contains(item.packageName)
            return view
        } else {
            convertView.findViewById<TextView>(ListItemAppName).text = pm!!.getApplicationLabel(item).toString()
            convertView.findViewById<TextView>(appPackageName).text = item.packageName
            convertView.findViewById<ImageView>(app_icon).setImageDrawable(pm.getApplicationIcon(item))
            convertView.findViewById<CheckBox>(appCheckbox).isChecked = app.uninstallList!!.contains(item.packageName)
        }
        return convertView
    }

}