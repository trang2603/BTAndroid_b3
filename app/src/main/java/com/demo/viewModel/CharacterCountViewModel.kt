package com.demo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.model.CharacterCount

class CharacterCountViewModel: ViewModel() {
    private val _characterCounts = MutableLiveData<List<CharacterCount>>()
    val characterCounts: LiveData<List<CharacterCount>>
        get() = _characterCounts

    fun characterCountCurrent(text: String) {
        val characterCountMap = mutableMapOf<Char, Int>()

        for(char in text) {
            if(characterCountMap.containsKey(char)) {
                characterCountMap[char] = characterCountMap[char]!! + 1
            }
            else characterCountMap[char] = 1
        }

        val characterCountList = characterCountMap.map { CharacterCount(it.key, it.value) }
        _characterCounts.value = characterCountList
    }
}