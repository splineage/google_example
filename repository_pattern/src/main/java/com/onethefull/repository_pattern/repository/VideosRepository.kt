package com.onethefull.repository_pattern.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.onethefull.repository_pattern.database.VideosDatabase
import com.onethefull.repository_pattern.database.asDomainModel
import com.onethefull.repository_pattern.domain.DevByteVideo
import com.onethefull.repository_pattern.network.DevByteNetwork
import com.onethefull.repository_pattern.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/28 1:18 오전
 * @desc
 */
class VideosRepository(private val database: VideosDatabase) {
    // live data 변환.
    val videos: LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()){
        it.asDomainModel()
    }

    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val playlist = DevByteNetwork.devbytes.getPlaylist()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }
}