package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetBanelUseCase( private val productRepository: ProductRepository)  {

    operator fun invoke() : Flow<NetworkResponseState<List<String>>> {
       return productRepository.getAllBanels()
    }
}