package org.albert.x08_viewmodel.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import org.albert.x08_viewmodel.service.DataManager

class SecondFragmentViewModel(application: Application) : AndroidViewModel(application) {
    var newlyCreated = true

    val checkedMessagesName = "${SecondFragmentViewModel::class.qualifiedName}.MSG_NAME"
    var checkedMessages = ArrayList<String>()

    fun restoreState(bundle: Bundle) {
        if(!newlyCreated) // This ViewModel is not destroyed if the screen just flipped.
            return

        val indexes = bundle.getIntegerArrayList(checkedMessagesName)
        if (indexes != null)
            checkedMessages = DataManager.fromIndexes(indexes)
    }

    fun saveState(bundle: Bundle) {
        val indexes = DataManager.getMessagesIndex(checkedMessages)
        bundle.putIntegerArrayList(checkedMessagesName, indexes)
    }
}