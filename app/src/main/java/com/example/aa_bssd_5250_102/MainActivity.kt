package com.example.aa_bssd_5250_102

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private val STEP_TAG:String = "com.example.aa_bssd_5250_102.step_tag"
    private val BELL_TAG:String = "com.example.aa_bssd_5250_102.bell_tag"
    private val HORN_TAG:String = "com.example.aa_bssd_5250_102.horn_tag"
    private val LLID:Int = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ll = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            id = LLID
        }
        setContentView(ll)

        if (savedInstanceState == null){
            supportFragmentManager.commit{
                add(ll.id, AudioFragment.newInstance(R.raw.step, "Step.wav"), STEP_TAG)
                replace(ll.id, AudioFragment.newInstance(R.raw.bell, "Bell.wav"),BELL_TAG)
                replace(ll.id, AudioFragment.newInstance(R.raw.horn, "Horn.wav"), HORN_TAG)
            }
        } else {
            //The Step Wav Fragment
            val fragment1 = supportFragmentManager.findFragmentByTag(STEP_TAG) as AudioFragment
            supportFragmentManager.commit{
                add(ll.id, fragment1, STEP_TAG)
            }
            //The Bell Wav Fragment
            val fragment2 = supportFragmentManager.findFragmentByTag(BELL_TAG) as AudioFragment
            supportFragmentManager.commit{
                add(ll.id, fragment2, BELL_TAG)
            }
            //The Horn Wav Fragment
            val fragment3 = supportFragmentManager.findFragmentByTag(HORN_TAG) as AudioFragment
            supportFragmentManager.commit{
                add(ll.id, fragment3, HORN_TAG)
            }
        }
    }
}