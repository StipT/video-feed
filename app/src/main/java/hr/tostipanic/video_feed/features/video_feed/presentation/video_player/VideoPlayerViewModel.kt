package hr.tostipanic.video_feed.features.video_feed.presentation.video_player

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.tostipanic.video_feed.features.video_feed.domain.model.wrapper.Resource
import hr.tostipanic.video_feed.features.video_feed.domain.use_case.GetVideoPostUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val useCase: GetVideoPostUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _state = mutableStateOf(VideoPlayerState())
    val state: State<VideoPlayerState> = _state
    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<String>("videoId")?.let { videoId ->
            if (videoId != "-1") {
                getVideoPost(videoId.toInt())
            }
        }
    }

    private fun getVideoPost(videoId: Int) {
        useCase(videoId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = VideoPlayerState(videoPost = result.data)
                }
                is Resource.Error -> {
                    _state.value = VideoPlayerState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = VideoPlayerState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
