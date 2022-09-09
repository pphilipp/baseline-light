package com.poid.baseline.light.di

import com.poid.baseline.light.data.Repository
import com.poid.baseline.light.data.data_source.ApiDataSource
import com.poid.baseline.light.domain.GetSmtUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //DataSource
    single { ApiDataSource(get()) }
//    single { DataBaseDataSource(get()) }
    //DataSource mappers
//    single { GenresDataToGenresEntityMapper() }
//    single { ResultDataToAlbumArtistEntityMapper() }

    // Repositories
    single { Repository(get()) }

    // domain
    single { GetSmtUseCase(get()) }
//    single { GetAllCashedAlbumsUseCase(get()) }
//    single { GetAlbumsListUseCase(get()) }

    //presentation mappers
//    single { FeedResultToAlbumUiModelMapper() }
//    single { AlbumArtistEntityToAlbumUiModelMapper() }
//    single { GenreEntityToGenreUiModelMapper() }

    // ViewModel
//    viewModel { AlbumsViewModel(get(), get(),get(), get()) }
//    viewModel { AlbumDetailsViewModel(get()) }
}