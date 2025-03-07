package org.albert.x08_viewmodel.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import org.albert.x08_viewmodel.R

class FirstFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val msgName = "${FirstFragmentViewModel::class.qualifiedName}.MSG_NAME"
    var msg: String = application.getString(R.string.msg)

    fun restoreState(bundle: Bundle) {
        msg = bundle.getString(msgName, msg)
    }

    fun saveState(bundle: Bundle) {
        bundle.putString(msgName, msg)
    }
}