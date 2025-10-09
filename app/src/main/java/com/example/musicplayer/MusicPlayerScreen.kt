import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicplayer.R
import kotlinx.coroutines.delay

@Composable
fun MusicPlayerScreen(context: Context) {
    val musicList = listOf(
       R.raw.sobrenome to "Sobrenome",
        R.raw.fatoconsumado to "Fato Consumado",
        R.raw.sentadamalvada to "Sentada Malvada"
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Image(
            painter = painterResource(R.drawable.baseline_music_note_24),
            contentDescription = "logo",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("The Trending Songs", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(50.dp))

        musicList.forEach { (resId, title) ->

            MusicItem(context = context, resId = resId, title = title)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MusicItem(context: Context, resId: Int, title: String){
    val mediaPlayer = remember { MediaPlayer.create(context, resId) }
    var isPlaying by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(isPlaying) {
        while (isPlaying && mediaPlayer.isPlaying){
            progress = mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration
            delay(500)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(12.dp)
            .clip(RoundedCornerShape(4.dp)),
        color = ProgressIndicatorDefaults.linearColor,
        trackColor = ProgressIndicatorDefaults.linearTrackColor,
        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlaying = false
            } else {
                mediaPlayer.start()
                isPlaying = true
            }
        }) {
            Text(if (isPlaying) "Pause" else "Play")
        }
    }

}