package com.kft.foodorder.screens

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kft.foodorder.domain.FoodDBRepository
import com.kft.foodorder.local.CART_ACTION
import com.kft.foodorder.local.FoodDB
import com.kft.foodorder.local.FoodDBRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class MainActivityViewModel(context: Context) : ViewModel() {

    val FoodDBRepo : FoodDBRepository = FoodDBRepoImpl(context)

    private var _myFoodList : MutableStateFlow<List<FoodDB>> = MutableStateFlow(emptyList())
    val myFoodList : StateFlow<List<FoodDB>> = _myFoodList.asStateFlow()

    private var _myCartFoodList : MutableStateFlow<List<FoodDB>> = MutableStateFlow(emptyList())

    private var _myFavouriteFoodList : MutableStateFlow<List<FoodDB>> = MutableStateFlow(emptyList())
    val myFavouriteFoodList : StateFlow<List<FoodDB>> = _myFavouriteFoodList.asStateFlow()

    private var _myCurrentFood : MutableStateFlow<FoodDB?> = MutableStateFlow(null)
    val myCurrentFood : StateFlow<FoodDB?> = _myCurrentFood

    private var currentFoodJob: Job? = null // Keep track of the current collection job


    fun changeFavorite(foodDB: FoodDB){
        viewModelScope.launch {
            foodDB.apply {
                isFavorite = !isFavorite
            }

            FoodDBRepo.changeFavorite(foodDB)
        }
    }

    fun changeCart(foodDB: FoodDB, action : CART_ACTION){
        Log.e("FoodDB", "change ${action}")
        Log.e("FoodDB", "change ${foodDB}")

        viewModelScope.launch {
            foodDB.apply {
                when(action){
                    CART_ACTION.ADD->{
                        cart += 1
                    }
                    CART_ACTION.MINUS->{
                        cart -= 1
                    }
                    CART_ACTION.REMOVE->{
                        cart = 0
                    }
                }
            }

            Log.e("FoodDB", "change 2 ${foodDB}")

            FoodDBRepo.changeCart(foodDB)
        }
    }


    fun getCurrentItem(id : Int): Flow<FoodDB> {
        return FoodDBRepo.getSpecificFood(id)
//        _myCurrentFood.value = null
//        currentFoodJob?.cancel() // Cancel previous job, if any
//
//
//
//        currentFoodJob =  viewModelScope.launch(Dispatchers.IO) {
//
//            FoodDBRepo.getSpecificFood(id).collectLatest {
//                Log.d("FoodItem", it.toString())
//
//
//
//                    _myCurrentFood.value = it
//
//
//
//            }
//        }
    }



    fun getAllFoods(){
        viewModelScope.launch {
            FoodDBRepo.getAllFoods().collectLatest {
                _myFoodList.value = it
            }
        }
    }



    fun getAllCarts(): Flow<List<FoodDB>> {
       return FoodDBRepo.getAllCardFoods()
    }



    fun getFavoriteList(){
        viewModelScope.launch {
            FoodDBRepo.getAllFavoriteFood().collectLatest {
                _myFavouriteFoodList.value = it
            }
        }
    }


}


sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    // You can add more cases like Error, Loading, etc. as needed
}



class MainActivityViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}