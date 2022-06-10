package com.example.hiltbasic.module

import com.example.hiltbasic.market.BookMarket
import com.example.hiltbasic.market.ClothingMarket
import com.example.hiltbasic.market.Market
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Qualifier

@Module
@InstallIn(ActivityComponent::class)
abstract class MarketModule {
    @BookMarketQualifier
    @Binds
    abstract fun BookMarketImpl(bookMarket: BookMarket) : Market

    @ClothingMarketQualifier
    @Binds
    abstract fun ClothingMarketImpl(clothingMarket: ClothingMarket) : Market
}

@Qualifier
annotation class BookMarketQualifier

@Qualifier
annotation class ClothingMarketQualifier