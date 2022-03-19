package com.example.servicestylerx.component

class SimplePresenter(private val repo : HelloRepo) {
    fun sayHello() = "${repo.giveHello()} from $this"
}