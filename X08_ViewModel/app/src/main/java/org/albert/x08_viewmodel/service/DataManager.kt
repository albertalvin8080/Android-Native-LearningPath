package org.albert.x08_viewmodel.service

object DataManager {
    val messages = ArrayList<String>()

    init {
        initializeMessages()
    }

    private fun initializeMessages() {
        messages.add("Detestatio Sacrorum")
        messages.add("Summa Blasphemia")
        messages.add("Exemplaris Exconmvnicationis")
        messages.add("Requiem AEternam")
    }

    fun getMessagesIndex(checkedMessages: ArrayList<String>): ArrayList<Int> {
        val indexes = ArrayList<Int>()
        for (msg in checkedMessages)
            indexes += messages.indexOf(msg)
        return indexes
    }

    fun fromIndexes(indexes: ArrayList<Int>): ArrayList<String> {
        val out = ArrayList<String>()

        for (i in indexes)
            out += messages[i]

        return out
    }
}