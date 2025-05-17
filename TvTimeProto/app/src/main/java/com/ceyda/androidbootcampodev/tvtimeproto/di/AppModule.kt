package com.ceyda.androidbootcampodev.tvtimeproto.di

import com.ceyda.androidbootcampodev.tvtimeproto.data.datasource.ContentDataSource
import com.ceyda.androidbootcampodev.tvtimeproto.data.repo.ContentRepository


class AppModule {

    fun provideContentRepository(): ContentRepository {
        val dataSource = provideContentDataSource()
        return ContentRepository(dataSource)
    }

    fun provideContentDataSource(): ContentDataSource {
        return ContentDataSource()
    }
}
