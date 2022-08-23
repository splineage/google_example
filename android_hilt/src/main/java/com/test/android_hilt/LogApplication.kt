package com.test.android_hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 9:59 오전
 * @desc
 */
/**
 * HiltAndroidApp
 * 종속 항목 삽입을 사용할 수 있는 애플리케이션의 기본 클래스가 포함된 Hilt 코드 생성을 트리거함.
 * 애플리케이션 컨테이너는 앱의 상위 컨테이너이므로 다른 컨테이너는 이 상위 컨테이너에서 제공하는 종속 항목에 엑세스 할 수 있습니다.
*/

@HiltAndroidApp
class LogApplication: Application() {

//    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
//        serviceLocator = ServiceLocator(applicationContext)
    }
}