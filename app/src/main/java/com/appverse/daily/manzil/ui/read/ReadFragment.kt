package com.appverse.daily.manzil.ui.read

import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appverse.daily.manzil.databinding.FragmentReadBinding
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnDrawListener
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.scroll.ScrollHandle

class ReadFragment : Fragment() {

    private  val TAG = "ReadFragment"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var binding: FragmentReadBinding? = null
    private var rootView: View? = null

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
            ViewModelProvider(this).get(ReadViewModel::class.java)

        if(rootView==null){
            binding = FragmentReadBinding.inflate(inflater, container, false)
            rootView = binding?.root


        }
//        aboutUsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return rootView!!
    }

    override fun onResume() {
        super.onResume()
        setUpUi()
    }

    private fun setUpUi() {
        Log.d(TAG, "setUpUi: ")
        binding?.pdfView?.fromAsset("manzil.pdf")
            //?.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
            ?.enableSwipe(true) // allows to block changing pages using swipe
            //?.swipeHorizontal(true)
            ?.scrollHandle(object : ScrollHandle {
                override fun setScroll(position: Float) {

                }

                override fun setupLayout(pdfView: PDFView?) {

                }

                override fun destroyLayout() {

                }

                override fun setPageNum(pageNum: Int) {

                }

                override fun shown(): Boolean {
                    return true
                }

                override fun show() {

                }

                override fun hide() {

                }

                override fun hideDelayed() {

                }

            })
            ?.enableDoubletap(true)
            ?.defaultPage(0)
            // allows to draw something on the current page, usually visible in the middle of the screen
            ?.onDraw(object : OnDrawListener {
                override fun onLayerDrawn(
                    canvas: Canvas?,
                    pageWidth: Float,
                    pageHeight: Float,
                    displayedPage: Int
                ) {

                }

            })
            // allows to draw something on all pages, separately for every page. Called only for visible pages
            //?.onDrawAll(onDrawListener)
            ?.onLoad(object : OnLoadCompleteListener {
                override fun loadComplete(nbPages: Int) {

                }

            }) // called after document is loaded and starts to be rendered
            //?.onPageChange(onPageChangeListener)
            //?.onPageScroll(onPageScrollListener)
            //?.onError(onErrorListener)
            //?.onPageError(onPageErrorListener)
            //?.onRender(onRenderListener) // called after document is rendered for the first time
            // called on single tap, return true if handled, false to toggle scroll handle visibility
            //?.onTap(onTapListener)
            //?.onLongPress(onLongPressListener)
            ?.enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
            ?.password(null)
            ?.scrollHandle(null)
            ?.enableAntialiasing(true) // improve rendering a little bit on low-res screens
            // spacing between pages in dp. To define spacing color, set view background
            ?.spacing(0)
            //.autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
            //.linkHandler(DefaultLinkHandler)
            //.pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
            //?.fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
            //.pageSnap(false) // snap pages to screen boundaries
            //?.pageFling(false) // make a fling change only a single page like ViewPager
            //.nightMode(false) // toggle night mode
            ?.load();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }


}