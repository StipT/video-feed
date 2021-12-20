package hr.tostipanic.video_feed.features.video_feed_list.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.tostipanic.video_feed.features.video_feed_list.domain.use_case.GetVideoFeedUseCase
import hr.tostipanic.video_feed.features.video_feed_list.domain.model.wrapper.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getVideoFeedUseCase: GetVideoFeedUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FeedState())
    val state: State<FeedState> = _state

    init {
        getFeed(sport = "", page = "1")
    }

    private fun getFeed(sport: String, page: String) {
        getVideoFeedUseCase(sport = sport, page = page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FeedState(feed = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = FeedState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = FeedState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
