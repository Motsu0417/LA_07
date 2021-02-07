package app.motsu.hiromoto.drum

import android.content.pm.ActivityInfo
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var mSoundPool: SoundPool
    private lateinit var mSoundID : Array<Int?>

    private val mSoundResource = arrayOf(
        R.raw.cymbal1,
        R.raw.cymbal2,
        R.raw.cymbal3,
        R.raw.tom1,
        R.raw.tom2,
        R.raw.tom3,
        R.raw.hihat,
        R.raw.snare,
        R.raw.bass
    )

    override fun onResume() {
        super.onResume()

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        mSoundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(mSoundResource.size)
            .build()

        mSoundID = arrayOfNulls(mSoundResource.size)

        for (i in 0 .. (mSoundResource.size -1)){
            mSoundID[i] = mSoundPool.load(applicationContext,mSoundResource[i],0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSoundPool.release()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    }

    fun playSound(num: Int){
        if(mSoundID[num] != null){
            mSoundPool.play(mSoundID[num] as Int, 1.0F, 1.0F, 0,0,1.0F)
        }
    }

    fun cymbal1(view: View?){playSound(0)}
    fun cymbal2(view: View?) {playSound(1)}
    fun cymbal3(view: View?){playSound(2)}
    fun tom1(view: View?){playSound(3)}
    fun tom2(view: View?){playSound(4)}
    fun tom3(view: View?){playSound(5)}
    fun snare(view: View?){playSound(7)}
    fun hihat(view: View?){playSound(6)}
    fun bass(view: View?){playSound(8)}



}