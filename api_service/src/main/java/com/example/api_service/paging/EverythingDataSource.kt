package com.example.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.EverythingService
import com.example.common.entity.everything.Article
import kotlin.Exception

class EverythingDataSource(
    private val everythingService : EverythingService,
    private val sources: ArrayList<String>,
    private val q: String

): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?:1
        val prevPage = if (page == 1) null else page - 1
        try {
            params.loadSize
            val result = everythingService.getEverything(
                sources = sources.joinToString(separator = ","), q = q, page = page
            )
            if (result.isSuccessful){
                result.body()?.let {
                    val nextPage = if (it.articles.isEmpty()) null else page + 1
                    return LoadResult.Page(it.articles, prevPage, nextPage)
                } ?: run {
                    return LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else {
                return LoadResult.Error(Exception("Error Backend : ${result.code()}"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        fun createPager(
        pageSize: Int,
        everythingService: EverythingService,
        sources: ArrayList<String>,
        q: String
        ) : Pager<Int, Article> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            EverythingDataSource(everythingService, sources, q)
        }
        )
    }

}

object  EverythingPager {
    fun createPager(
        pageSize: Int,
        everythingService: EverythingService,
        sources: ArrayList<String>,
        q: String
    ) : Pager<Int, Article> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            EverythingDataSource(everythingService, sources, q)
        }
    )
}