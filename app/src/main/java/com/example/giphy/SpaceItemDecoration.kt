package com.example.giphy

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class SpaceItemDecoration(space:Int) : RecyclerView.ItemDecoration() {

    private var  space by Delegates.notNull<Int>()

    init{
        this.space= space
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
       // super.getItemOffsets(outRect, view, parent, state)
        outRect.left= space
        outRect.right= space
        outRect.bottom= space

        outRect.top= if(parent.getChildLayoutPosition(view)==0)
                        space
                     else
                         0

    }

}