package com.example.domain.model

enum class Status {
    SUCCESS,
    FAILED
}

class ResponseStatus(val status: Status, val msg: String) {
    companion object {
        val SUCCESS: ResponseStatus
        val ERROR: ResponseStatus

        init {
            SUCCESS = ResponseStatus(Status.SUCCESS, "Success")
            ERROR = ResponseStatus(Status.FAILED, "Something went wrong")
        }
    }
}
