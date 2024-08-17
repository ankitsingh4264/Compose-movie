package com.example.featuresapp.data.repo.di

import com.example.featuresapp.data.repo.MovieSearchRepoContract
import com.example.featuresapp.data.repo.MovieSearchRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideMovieSearchRepo(repo: MovieSearchRepoImpl): MovieSearchRepoContract
}