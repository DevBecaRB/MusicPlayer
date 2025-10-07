import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musicplayer.R

@Composable
fun MusicPlayerScreen(context: Context) {
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.sobrenome)
    }

    var isPlaying by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                isPlaying = true
            }
        }) {
            Text("Play")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlaying = false
            }
        }) {
            Text("Pause")
        }
    }
}