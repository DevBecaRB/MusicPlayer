import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.musicplayer.R

@Composable
fun MusicPlayerScreen(context: Context) {
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.sobrenome)
    }

    var isPlaying by remember { mutableStateOf(false) }
}