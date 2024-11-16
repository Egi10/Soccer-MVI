package com.bajapuik.soccer_mvi.screens.home.action

import com.bajapuik.soccer_mvi.domain.model.Country

typealias OnHomeUiAction = (HomeUiAction) -> Unit

sealed class HomeUiAction {
    data class OnSelectedCountry(val country: Country): HomeUiAction()
    data class OnItemClick(val idEvent: String) : HomeUiAction()
}