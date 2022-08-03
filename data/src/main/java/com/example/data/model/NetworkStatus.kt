package com.example.data.model

enum class Status {
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val msg: String) {
    companion object {
        val SUCCESS: NetworkState
        val ERROR: NetworkState

        init {
            SUCCESS = NetworkState(Status.SUCCESS, "Success")
            ERROR = NetworkState(Status.FAILED, "Something went wrong")
        }
    }
}
