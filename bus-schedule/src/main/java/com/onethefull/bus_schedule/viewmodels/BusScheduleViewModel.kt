package com.onethefull.bus_schedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onethefull.bus_schedule.database.Schedule
import com.onethefull.bus_schedule.database.ScheduleDao

/**
 * viewModel 클래스는 수명 주기를 인식해야 하므로 수명 주기 이벤트에 응답할 수 있는 객체로 인스턴스 해야함.
 * 프래그먼트 중 하나에 직접 인스턴스화하면 프래그먼트 객체가 앱 코드의 기능 범위를 벗어나는 모든 메모리 관리를
 * 처리해야 함. 대신 뷰 모델 객체를 인스턴스화하는 팩토리라는 클래스를 만들 수 있음.
 */
class BusScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {
    fun fullSchedule(): List<Schedule> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): List<Schedule> = scheduleDao.getByStopName(name)
}

/**
 * create 로 Factory 객체를 인스턴스화 할 수 있으므로 프래그먼트가 이를 직접 처리하지 않고도 viewmodel 이
 * 수명 주기를 인식할 수 있음.
 */
class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}