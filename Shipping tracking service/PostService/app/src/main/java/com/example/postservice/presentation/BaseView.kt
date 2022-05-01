package com.example.postservice.presentation

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT
}