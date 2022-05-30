package com.example.cupcake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cupcake.model.OrderViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import java.text.NumberFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun quantity_twelve_cupcakes(){
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever{}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.quantity.value)
    }
    @Test
    fun price_twelve_cupcakes(){
        val viewModel = OrderViewModel()
        viewModel.price.observeForever{}
        viewModel.setDate(viewModel.dateOptions[0])
        viewModel.setQuantity(12)
        assertEquals(NumberFormat.getCurrencyInstance().format(27), viewModel.price.value)
    }
}