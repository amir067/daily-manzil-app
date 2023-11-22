package com.appverse.daily.manzil.ui.listen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appverse.daily.manzil.R
import com.appverse.daily.manzil.databinding.FragmentListenBinding
import com.arges.sepan.argmusicplayer.Enums.ErrorType
import com.arges.sepan.argmusicplayer.Models.ArgAudio
import com.arges.sepan.argmusicplayer.Models.ArgAudioList
import com.arges.sepan.argmusicplayer.Models.ArgNotificationOptions
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerLargeView
import java.util.Locale

class ListenFragment : Fragment() {

    private  val TAG = "ListenFragment"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var binding: FragmentListenBinding? = null
    private var rootView: View? = null

    var playlist = ArgAudioList(true)

    val AssetSong = "manzil64kbps.mp3"
    var audioAsset = ArgAudio.createFromAssets("", "Manzil Audio", AssetSong)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutUsViewModel =
            ViewModelProvider(this).get(ListenViewModel::class.java)

        if(rootView==null){
            binding = FragmentListenBinding.inflate(inflater, container, false)
            rootView = binding?.root
        }
//        aboutUsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        setUpUi()
        return rootView!!
    }

    private fun setUpUi() {
        Log.d(TAG, "setUpUi: ")
        val options =ArgNotificationOptions(requireActivity())
        options.imageResoureId = R.drawable.ic_audio_notification

        //binding?.argmusicplayer?.enableNotification(options)
        binding?.argmusicplayer?.setOnErrorListener { errorType: ErrorType?, description: String? ->
            Log.e(TAG, "setUpUi: ${String.format("Error:\nType: %s\nDescription: %s", errorType, description)}", )

        }
        binding?.argmusicplayer?.setOnPlaylistAudioChangedListener { playlist: ArgAudioList, currentAudioIndex: Int ->
            binding?.tvMusicType!!.text = String.format(
                Locale.getDefault(),
                "PLAYLIST Audio%d: %s",
                playlist.currentIndex,
                playlist.currentAudio.title
            )
        }


        binding?.argmusicplayer?.play(audioAsset)

    }

    override fun onPause() {
        if(binding?.argmusicplayer!=null) binding?.argmusicplayer?.pause();
        super.onPause()
    }

    override fun onDestroyView() {
        if(binding?.argmusicplayer!=null) binding?.argmusicplayer?.stop();
        super.onDestroyView()
        //_binding = null
    }
}