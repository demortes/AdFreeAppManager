package com.demortes.adfreeappmanager

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.demortes.adfreeappmanager.R.id.appPackageName
import com.demortes.adfreeappmanager.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var app = this.application as app
        if (app.uninstallList == null)
            app.uninstallList = arrayListOf()
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    fun ShowFAB(view: View) {
        var app = this.application as app
        val packageName = (view.parent as View).findViewById<TextView>(appPackageName).text as String
        if ((view as CheckBox).isChecked)
            app.uninstallList?.add(packageName)
        else
            app.uninstallList?.remove(packageName)

        if (app.uninstallList!!.count() > 0)
            uninstallFAB.show()
        else
            uninstallFAB.hide()
    }

    fun uninstallList(view: View) {
        val app = this.application as app
        val pm = app.packageManager
        app.uninstallList!!.forEach {
            val intent = Intent(Intent.ACTION_UNINSTALL_PACKAGE)
            intent.data = Uri.parse("package:$it")
            startActivityForResult(intent, 0)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val app = this.application as app
        if (requestCode == 0) {
            app.uninstallList?.clear()
        }
        AppListing.invalidateViews()
    }
}
