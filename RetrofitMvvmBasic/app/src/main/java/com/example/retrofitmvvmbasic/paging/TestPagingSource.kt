package com.example.retrofitmvvmbasic.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.retrofitmvvmbasic.TestApi
import com.example.retrofitmvvmbasic.TestInfo
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TestPagingSource(private val testApi : TestApi, private val userId : Int) : PagingSource<Int, TestInfo>() {

    //LoadParams : Load 키와 항목 수
    //LoadResult : Load 작업의 결과
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TestInfo> {
        return try {
            //키 값이 없을 경우 기본 값 사용
            val position = params.key ?: STARTING_PAGE_INDEX

            val response = testApi.getCustomPost(
                userId = userId
            )
            val post = response.body()

            LoadResult.Page(
                data = post!!,
                prevKey = if(position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = null
            )
        } catch (e : IOException) {
            LoadResult.Error(e)
        } catch (e : HttpException) {
            LoadResult.Error(e)
        }
    }

    //데이터가 새로고침되거나 최초 Load 이후 무효화되었을때 키를 반환하여 load()로 전달
    override fun getRefreshKey(state: PagingState<Int, TestInfo>): Int? {
        TODO("Not yet implemented")
    }
}