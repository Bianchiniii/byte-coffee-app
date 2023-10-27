package com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.ProfileLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource
) : ViewModel() {

    private var _userProfile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val userProfile = _userProfile.asStateFlow()

    init {
        viewModelScope.launch {
            val request = profileLocalDataSource.getProfile()

            _userProfile.value = request.getOrNull()
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            // TODO: mapear rota de get products
        }
    }
}