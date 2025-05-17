package com.ceyda.androidbootcampodev.tvtimeproto.model

data class Movie(
    override val id: Int,
    override val title: String,
    override val imageUrl: String
) : Content
