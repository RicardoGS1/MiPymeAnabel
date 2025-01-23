package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.dto.OrderProducts
import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import com.virtualworld.mipymeanabel.domain.useCase.AddOrderUseCase
import com.virtualworld.mipymeanabel.domain.useCase.AuthUseCase
import com.virtualworld.mipymeanabel.domain.useCase.DeletCartUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetProductCartUseCase
import com.virtualworld.mipymeanabel.ui.screen.common.model.DataTotals
import com.virtualworld.mipymeanabel.ui.screen.utils.convertMillisToDate
import com.virtualworld.mipymeanabel.ui.screen.utils.roundToDecimals
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mipymeanabel.composeapp.generated.resources.Res


class CartViewModel(
    private val getProductCartUseCase: GetProductCartUseCase,
    private val addOrderUseCase: AddOrderUseCase,
    private val deleteCartUseCase: DeletCartUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductCart>>(emptyList())
    val productsState: StateFlow<List<ProductCart>> get() = _products.asStateFlow()

    private val _quantity = MutableStateFlow<Map<Long, Int>>(emptyMap())
    val quantity: StateFlow<Map<Long, Int>> get() = _quantity

    private val _totals = MutableStateFlow<DataTotals>(DataTotals())
    val totals: StateFlow<DataTotals> get() = _totals

    private val _dateDelivery = MutableStateFlow<Long>(0L)
    val dateDelivery: Flow<String> = _dateDelivery.map { millis ->
        if (millis == 0L) "" else convertMillisToDate(millis)
    }

    private val _isAuthenticate = MutableStateFlow<Boolean>(false)
    val isAuthenticate: StateFlow<Boolean> get() = _isAuthenticate.asStateFlow()

    var uid : String? = null



    init {
        getProductsCart()
        checkAuth()
    }

    //Collect products in cart and calculate totals
    private fun getProductsCart() {
        viewModelScope.launch {
            getProductCartUseCase().collect { products ->

                _products.update {
                    products
                }

                for (product in products) {
                    if (_quantity.value[product.idp] == null) {
                        _quantity.update {
                            products.associate { it.idp to 1 }
                        }
                    }

                }
                calculateTotals()
            }
        }
    }

    //Checking if the user is authenticated
    private fun checkAuth() {
        viewModelScope.launch {
            authUseCase.loadUser().collect { state ->

                if (state is AuthenticationState.Authenticated) {
                    uid=state.result.uid
                    _isAuthenticate.value = true
                }
                if(state is AuthenticationState.Unauthenticated){
                    uid=null
                    _isAuthenticate.value = false
                }

            }
        }
    }

    //Update quantity and recalculate
    fun updateQuantity(idp: Long, unit: Int) {

        _quantity.update { currentQuantity ->

            val newQuantity = currentQuantity[idp]?.plus(unit) ?: 0
            if (newQuantity >= 0) currentQuantity.plus(idp to newQuantity)
            else currentQuantity
        }
        calculateTotals()
    }

    //Calculate Totals
    private fun calculateTotals() {

        var totalUSD = 0f
        var totalMN = 0f
        var units = 0

        _products.value.forEach { prod ->

            val priceUSD = prod.priceUSD
            val priceMN = prod.priceMN

            totalUSD += priceUSD * _quantity.value[prod.idp]!!
            totalMN += priceMN * _quantity.value[prod.idp]!!
            units += _quantity.value[prod.idp]!!
        }


        _totals.update {
            DataTotals(
                totalUSD.roundToDecimals(2),
                totalMN.roundToDecimals(2),
                units
            )
        }
    }


    fun onClickAddOrder() {


        if (_products.value.isNotEmpty()) {

            val name = "Nueva Orden"
            val dateDelviry = _dateDelivery.value.toString()
            val dateActual =   GMTDate().timestamp.toString()

            val orderProducts = _products.value.map {
                OrderProducts(
                    idp = it.idp.toString(),
                    name = it.name,
                    priceMn = it.priceMN.toString(),
                    priceUsd = it.priceUSD.toString(),
                    image = it.image,
                    unit = _quantity.value[it.idp].toString(),
                )
            }


            val myOrder = Order(
                name = name,
                dateOrder = dateActual,
                dateDelivery = dateDelviry,
                listOrderProducts = orderProducts
            )


            viewModelScope.launch {
                if(uid!=null) {
                    addOrderUseCase(myOrder, uid!!)
                    deleteCartUseCase.deleteCart()
                }
            }
        }

    }

    fun changerDateDelivery(date: Long) {
        _dateDelivery.value = date
    }


}

