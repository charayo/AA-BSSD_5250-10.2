package com.example.aa_bssd_5250_102

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

private const val AUDIO_RES = "audio_file"
private const val NAME_RES = "audio_name"
class AudioFragment : Fragment() {

    private var name: String? = null

    private lateinit var viewModel: AudioViewModel

    private var mediaPlayer: MediaPlayer? = null
    private var audioRes: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AudioViewModel::class.java)
        arguments?.let {
            audioRes = it.getInt(AUDIO_RES)
            name = it.getString(NAME_RES)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mediaPlayer = MediaPlayer.create(context, audioRes!!)
        val v:View = inflater.inflate(R.layout.fragment_audio, container, false)
        v.findViewById<Button>(R.id.play_button).apply{
            setOnClickListener{
                playMedia()
            }
        }
        v.findViewById<Button>(R.id.stop_button).apply{
            setOnClickListener{
                stopMedia()
            }
        }
        v.findViewById<TextView>(R.id.sound_name).apply {
            text = name
        }
        return v
    }

    private fun playMedia(time:Int = 0){
        mediaPlayer?.seekTo(time)
        mediaPlayer?.start()
    }

    private fun stopMedia(){
        mediaPlayer?.stop()
        mediaPlayer?.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setCurrentTime(this.mediaPlayer?.currentPosition!!)
        stopMedia()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(viewModel.getCurrentTime() > 0){
            playMedia(viewModel.getCurrentTime())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(audio:Int, name:String) =
            AudioFragment().apply {
                arguments = Bundle().apply {
                    putInt(AUDIO_RES, audio)
                    putString(NAME_RES, name)
                }
            }
    }
}