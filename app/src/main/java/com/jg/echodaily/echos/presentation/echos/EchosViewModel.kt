package com.jg.echodaily.echos.presentation.echos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg.echodaily.R
import com.jg.echodaily.core.presentation.desingsystem.dropdowns.Selectable
import com.jg.echodaily.core.presentation.util.UIText
import com.jg.echodaily.echos.presentation.echos.models.EchoFilterChip
import com.jg.echodaily.echos.presentation.echos.models.MoodChipContent
import com.jg.echodaily.echos.presentation.models.MoodUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class EchosViewModel : ViewModel() {
    private var hasLoadedInitialData = false

    private val selectedMoodFilter = MutableStateFlow<List<MoodUI>>(emptyList())
    private val selectedTopicFilter = MutableStateFlow<List<String>>(emptyList())

    private val _state = MutableStateFlow(EchosState())
    val state = _state
        .onStart {
            if(!hasLoadedInitialData){
                observeFilters()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = EchosState()
        )

    fun onAction(action : EchosAction){
        when(action){
            EchosAction.OnFabClick -> {

            }
            EchosAction.OnFabLongClick -> {

            }
            EchosAction.OnSettingsClick -> {

            }
            is EchosAction.OnRemoveFilters -> {
                when(action.filterType){
                    EchoFilterChip.MOODS -> selectedMoodFilter.update { emptyList() }
                    EchoFilterChip.TOPICS -> selectedTopicFilter.update { emptyList() }
                }
            }
            EchosAction.OnTopicChipClick -> {
                _state.update { it.copy(
                    selectedEchoFilterChip = EchoFilterChip.TOPICS
                ) }
            }
            EchosAction.OnMoodChipClick -> {
                _state.update { it.copy(
                    selectedEchoFilterChip = EchoFilterChip.MOODS
                ) }
            }
            EchosAction.OnDismissTopicDropDown,
            EchosAction.OnDismissMoodDropDown -> {
                _state.update {
                    it.copy(
                        selectedEchoFilterChip = null
                    )
                }
            }
            is EchosAction.OnFilterByMoodClick -> {
                toggleMoodFilter(action.mood)
            }
            is EchosAction.OnFilterByTopicClick -> {
                toggleTopicFilter(action.topic)
            }
        }
    }
    private fun toggleMoodFilter(moodUi:MoodUI){
        selectedMoodFilter.update {selectedMoods ->
            if(moodUi in selectedMoods){
                selectedMoods - moodUi
            }else{
                selectedMoods + moodUi
            }
        }
    }

    private fun toggleTopicFilter(topic:String){
        selectedTopicFilter.update {selectedTopics ->
            if(topic in selectedTopics){
                selectedTopics - topic
            }else{
                selectedTopics + topic
            }
        }
    }

    private fun observeFilters(){
        combine(
            selectedTopicFilter,
            selectedMoodFilter
        ){ selectedTopics, selectedMoods ->
            _state.update { it.copy(
                topics = it.topics.map { topic ->
                    Selectable(
                        item = topic.item,
                        selected = selectedTopics.contains(topic.item)
                    )
                },
                moods = MoodUI.entries.map {
                    Selectable(
                        item = it,
                        selected = selectedMoods.contains(it)
                    )
                },
                hasActiveTopicFilters = selectedTopics.isNotEmpty(),
                hasActiveMoodFilters = selectedMoods.isNotEmpty(),
                topicChipTitle = selectedTopics.deriveTopicsToText(),
                moodChipContent = selectedMoods.asMoodChipContent()
            ) }
        }.launchIn(viewModelScope)
    }

    private fun List<String>.deriveTopicsToText():UIText{
        return when(size){
            0-> UIText.StringResource(R.string.all_topics)
            1 -> UIText.Dynamic(this.first())
            2 -> UIText.Dynamic("${this.first()}, ${this.last()}")
            else ->{
                val extraElementCount = size - 2
                UIText.Dynamic("${this.first()}, ${this[1]} +$extraElementCount")
            }
        }
    }

    private fun List<MoodUI>.asMoodChipContent():MoodChipContent{
        if(this.isEmpty()){
            return MoodChipContent()
        }
        val icons = this.map { it.iconSet.fill }
        val moodNames = this.map { it.title }

        return when(size){
            1 -> MoodChipContent(
                iconsRes = icons,
                title = moodNames.first()
            )
            2 ->MoodChipContent(
                iconsRes = icons,
                title = UIText.Combined(
                    format = "%s. %s",
                    uiText = moodNames.toTypedArray()
                )
            )
            else ->{
                val extraElementCount = size - 2
                MoodChipContent(
                    iconsRes = icons,
                    title = UIText.Combined(
                        format = "%s. %s +$extraElementCount",
                        uiText = moodNames.take(2).toTypedArray()
                    )
                )
            }
        }

    }
}