package com.app.ml.data.models

import okhttp3.ResponseBody

sealed class ActionScreen<T> {

    /**
     * No state
     */
    class Undefined<T> : ActionScreen<T>()

    /**
     * Show loader view
     */
    class LoadingAction<T> : ActionScreen<T>()

    /**
     * Show skeleton
     */
    class SkeletonAction<T>(val data: List<String>): ActionScreen<T>()

    /**
     * Show error view
     */
    class ErrorAction<T>(
        val exception: Throwable? = null,
        val errorBody: ResponseBody? = null
    ): ActionScreen<T>()

    /**
     * Show data from response
     */
    class SuccessAction<T>(val responseModel: T): ActionScreen<T>()
}